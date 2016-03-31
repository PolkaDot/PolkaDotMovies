package com.polka.pdm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;



/**
 * Rev9ew repository for all the inserts from application into database
 *
 * @author C. Shih on 2/23/2016.
 * @version 1.0
 */
public class ReviewRepo {

    private DBHelper dbHelper;

    /**
     * create of db helper
     *
     * @param context context of the application?
     */
    public ReviewRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    /**
     * Inserts new rating into the database
     *
     * @param rating that it is inserting
     * @return returns row ID of newly inserted row, -1 otherwise
     */
    public long insert(Review rating) {

        // open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Review.KEY_movie, rating.getMovie());
        values.put(Review.KEY_movieYear, rating.getMovieYear());
        values.put(Review.KEY_user, rating.getUser());
        values.put(Review.KEY_major, rating.getMajor());
        values.put(Review.KEY_rating, rating.getRating());
        values.put(Review.KEY_comment, rating.getComment());


        //inserting row
        try {
            long rating_ratingId = db.insertOrThrow(Review.TABLE, null, values);
            db.close();
            return rating_ratingId;
        } catch (SQLException e) {
            db.close();
            updateReview(rating.getUser(), rating.getMovie(), rating.getMovieYear(), rating.getRating(), rating.getComment());
            return -1;
        }
    }


    private void setRating(String user, String movie, int movieYear, double rating) {

        // open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Review.KEY_rating, rating);
        // creates where and where arguments
        String where = Review.KEY_user + " = ? AND "
                + Review.KEY_movie + " = ? AND "
                + Review.KEY_movieYear + " = ? ";
        String[] whereArgs = {user, movie, ((Integer)movieYear).toString()};

        // update
        int numberRowsUpdated = db.update(Review.TABLE, values, where, whereArgs);
        db.close();
    }

    private void setComment(String user, String movie, int movieYear, String comment) {

        // open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Review.KEY_comment, comment);
        // creates where and where arguments
        String where = Review.KEY_user + " = ? AND "
                + Review.KEY_movie + " = ? AND "
                + Review.KEY_movieYear + " = ? ";
        String[] whereArgs = {user, movie, ((Integer)movieYear).toString()};

        // update
        int numberRowsUpdated = db.update(Review.TABLE, values, where, whereArgs);
        db.close();
    }


    public void updateReview(String user, String movie, int movieYear, double rating, String comment) {
        setRating(user, movie, movieYear, rating);
        setComment(user, movie, movieYear, comment);
    }



    /**
     * gets the user object by username from database
     *
     * @param movie to find by
     * @return rating array if found ratings with corresponding movie, null otherwise
     */
    public List<Review> getRatingsByMovie(Movie movie) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Review.TABLE
                + " WHERE " +
                Review.KEY_movie + " = " + movie.getTitle() +
                " AND " + Review.KEY_movieYear  + " = "
                + movie.getYear();

        ArrayList<Review> ratings = new ArrayList<>();

        Review ratingForSize = new Review();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(ratingForSize)});
        if (cursor.moveToFirst()) {
            do {
                Review rating = new Review();
                rating.setMovie(cursor.getString(cursor.getColumnIndex(Review.KEY_movie)));
                rating.setMovieYear(cursor.getInt(cursor.getColumnIndex(Review.KEY_movieYear)));
                rating.setUser(cursor.getString(cursor.getColumnIndex(Review.KEY_user)));
                rating.setMajor(cursor.getString(cursor.getColumnIndex(Review.KEY_major)));
                rating.setRating(cursor.getDouble(cursor.getColumnIndex(Review.KEY_rating)));
                rating.setComment(cursor.getString(cursor.getColumnIndex(Review.KEY_comment)));
                ratings.add(rating);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ratings;
    }

    public Movie[] getRatingsByMajor(String major, int dataCount) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " + Review.KEY_movie + ", " + Review.KEY_movieYear + ", " + Review.KEY_major + ", sum(" + Review.KEY_rating + ") as total"
                + " FROM " +
                Review.TABLE
                + " WHERE " +
                Review.KEY_major + " = " + "'" + major + "'"
                + " GROUP BY " +
                Review.KEY_movie
                + " ORDER BY total DESC";
        Movie[] movies = new Movie[dataCount];


        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            int i = 0;
            do {
                String title = cursor.getString(cursor.getColumnIndex(Review.KEY_movie));
                int year = cursor.getInt(cursor.getColumnIndex(Review.KEY_movieYear));
                int totalRating = cursor.getInt(cursor.getColumnIndex("total"));
                Movie movie = new Movie(title, year, null, null); // may need new data struct to store total rating
                Log.d("CCC", "Entry # " + i + " " + movie.getTitle() + " - " + totalRating);
                movies[i++] = movie;
            } while (cursor.moveToNext() && i < movies.length);
        }

        for (int i = 0; i < movies.length; i++) {
            Movie t = movies[i];
            if (t != null) {
                Log.d("BBB", i + " " + t.getTitle());
            }
        }
        cursor.close();
        db.close();
        return movies;
    }

}
