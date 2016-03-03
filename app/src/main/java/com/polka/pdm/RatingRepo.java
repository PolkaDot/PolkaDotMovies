package com.polka.pdm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.Rating;

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
    public long insert(ReviewAndRate rating) {

        // open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ReviewAndRate.KEY_movie, rating.movie);
        values.put(ReviewAndRate.KEY_movieYear, rating.movieYear);
        values.put(ReviewAndRate.KEY_user, rating.user);
        values.put(ReviewAndRate.KEY_rating, rating.rating);
        values.put(ReviewAndRate.KEY_comment, rating.comment);


        //inserting row
        try {
            long rating_ratingId = db.insertOrThrow(ReviewAndRate.TABLE, null, values);
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
    public List<ReviewAndRate> getRatingsByMovie(Movie movie) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + ReviewAndRate.TABLE
                + " WHERE " +
                ReviewAndRate.KEY_movie + " = " + movie.getTitle() +
                " AND " + ReviewAndRate.KEY_movieYear  + " = "
                + movie.getYear();

        ArrayList<ReviewAndRate> ratings = new ArrayList<>();

        ReviewAndRate ratingForSize = new ReviewAndRate();

        Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(ratingForSize)});
        if (cursor.moveToFirst()) {
            do {
                ReviewAndRate rating = new ReviewAndRate();
                rating.movie = cursor.getString(cursor.getColumnIndex(ReviewAndRate.KEY_movie));
                rating.user = cursor.getString(cursor.getColumnIndex(ReviewAndRate.KEY_user));
                rating.rating = cursor.getDouble(cursor.getColumnIndex(ReviewAndRate.KEY_rating));
                rating.comment = cursor.getString(cursor.getColumnIndex(ReviewAndRate.KEY_comment));
                ratings.add(rating);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ratings;
    }

}