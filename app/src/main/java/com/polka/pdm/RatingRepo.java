package com.polka.pdm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Rating repository for all the inserts from application into database
 *
 * @author C. Shih on 2/23/2016.
 * @version 1.0
 */
public class RatingRepo {

    private DBHelper dbHelper;

    /**
     * create of db helper
     * @param context context of the application?
     */
    public RatingRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    /**
     * Inserts new rating into the database
     *
     * @param rating that it is inserting
     * @return returns row ID of newly inserted row, -1 otherwise
     */
    public long insert(Rating rating) {

        // open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Rating.KEY_movie, rating.movie);
        values.put(Rating.KEY_movieYear, rating.movieYear);
        values.put(Rating.KEY_user, rating.user);
        values.put(Rating.KEY_major, rating.major);
        values.put(Rating.KEY_rating, rating.rating);
        values.put(Rating.KEY_rating, rating.comment);


        //inserting row
        try {
            long rating_ratingId = db.insertOrThrow(Rating.TABLE, null, values);
            db.close();
            return rating_ratingId;
        } catch (SQLException e) {
            return -1;
        }
    }

    /**
     * gets the user object by username from database
     *
     * @param movie to find by
     * @return rating array if found ratings with corresponding movie, null otherwise
     */
    public List<Rating> getRatingsByMovie(Movie movie) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Rating.TABLE
                + " WHERE " +
                Rating.KEY_movie + " = " + movie.getTitle() +
                " AND " + Rating.KEY_movieYear  + " = "
                + movie.getYear();

        ArrayList<Rating> ratings = new ArrayList<>();

        Rating ratingForSize = new Rating();

        Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(ratingForSize)});
        if (cursor.moveToFirst()) {
            do {
                Rating rating = new Rating();
                rating.movie = cursor.getString(cursor.getColumnIndex(Rating.KEY_movie));
                rating.user = cursor.getString(cursor.getColumnIndex(Rating.KEY_user));
                rating.major = cursor.getString(cursor.getColumnIndex(Rating.KEY_major));
                rating.rating = cursor.getInt(cursor.getColumnIndex(Rating.KEY_rating));
                rating.comment = cursor.getString(cursor.getColumnIndex(Rating.KEY_comment));
                ratings.add(rating);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ratings;
    }

<<<<<<< HEAD
}
=======
}
>>>>>>> origin/alisha
