package com.polka.pdm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;



/**
 * Rev9ew repository for all the inserts from application into database
 *
 * @author C. Shih on 2/23/2016.
 * @version 1.0
 */
class ReviewRepo {

    /**
     * database helper
     */
    private DBHelper dbHelper;
    /**
     * comment connector
     */
    private String commentConnector = " = ? AND ";

    /**
     * create db helper
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
        values.put(Review.KEY_MOVIE, rating.getMovie());
        values.put(Review.KEY_MOVIEYEAR, rating.getMovieYear());
        values.put(Review.KEY_USER, rating.getUser());
        values.put(Review.KEY_MAJOR, rating.getMajor());
        values.put(Review.KEY_RATING, rating.getRating());
        values.put(Review.KEY_COMMENT, rating.getComment());


        //inserting row
        try {
            long ratingRatingId = db.insertOrThrow(Review.TABLE, null, values);
            db.close();
            return ratingRatingId;
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
        values.put(Review.KEY_RATING, rating);
        // creates where and where arguments
        String where = Review.KEY_USER + commentConnector
                + Review.KEY_MOVIE + commentConnector
                + Review.KEY_MOVIEYEAR + " = ? ";
        String[] whereArgs = {user, movie, ((Integer)movieYear).toString()};

        // update
        db.update(Review.TABLE, values, where, whereArgs);
        db.close();
    }

    private void setComment(String user, String movie, int movieYear, String comment) {

        // open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Review.KEY_COMMENT, comment);
        // creates where and where arguments
        String where = Review.KEY_USER + commentConnector
                + Review.KEY_MOVIE + commentConnector
                + Review.KEY_MOVIEYEAR + " = ? ";
        String[] whereArgs = {user, movie, ((Integer)movieYear).toString()};

        // update
        db.update(Review.TABLE, values, where, whereArgs);
        db.close();
    }


    private void updateReview(String user, String movie, int movieYear, double rating, String comment) {
        setRating(user, movie, movieYear, rating);
        setComment(user, movie, movieYear, comment);
    }



    /**
     * gets the user object by username from database
     *
     * @param movie to find by
     * @return rating array if found ratings with corresponding movie, null otherwise
     * //may implement later
     */
    public List<Review> getRatingsByMovie(Movie movie) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Review.TABLE
                + " WHERE " +
                Review.KEY_MOVIE + " = " + movie.getTitle() +
                " AND " + Review.KEY_MOVIEYEAR  + " = "
                + movie.getYear();

        ArrayList<Review> ratings = new ArrayList<>();

        Review ratingForSize = new Review();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(ratingForSize)});
        if (cursor.moveToFirst()) {
            do {
                Review rating = new Review();
                rating.setMovie(cursor.getString(cursor.getColumnIndex(Review.KEY_MOVIE)));
                rating.setMovieYear(cursor.getInt(cursor.getColumnIndex(Review.KEY_MOVIEYEAR)));
                rating.setUser(cursor.getString(cursor.getColumnIndex(Review.KEY_USER)));
                rating.setMajor(cursor.getString(cursor.getColumnIndex(Review.KEY_MAJOR)));
                rating.setRating(cursor.getDouble(cursor.getColumnIndex(Review.KEY_RATING)));
                rating.setComment(cursor.getString(cursor.getColumnIndex(Review.KEY_COMMENT)));
                ratings.add(rating);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ratings;
    }

    /**
     * Generates a list of movies rated by specified major in order of descending rating
     * @param major major used to search movie ratings
     * @param dataCount specifies the size of the list of movies to return
     * @return returns a list of movies sorted by ratings by specified major
     */
    public Movie[] getRatingsByMajor(String major, int dataCount) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " + Review.KEY_MOVIE + ", " + Review.KEY_MOVIEYEAR + ", " + Review.KEY_MAJOR + ", sum(" + Review.KEY_RATING + ") as total"
                + " FROM " +
                Review.TABLE
                + " WHERE " +
                Review.KEY_MAJOR + " = " + "'" + major + "'"
                + " GROUP BY " +
                Review.KEY_MOVIE
                + " ORDER BY total DESC";
        Movie[] movies = new Movie[dataCount];


        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            int i = 0;
            do {

                String title = cursor.getString(cursor.getColumnIndex(Review.KEY_MOVIE));
                int year = cursor.getInt(cursor.getColumnIndex(Review.KEY_MOVIEYEAR));
                cursor.getInt(cursor.getColumnIndex("total"));

                Movie movie = new Movie(title, year, null, null); // may need new data struct to store total rating
                movies[i++] = movie;
            } while (cursor.moveToNext() && i < movies.length);
        }

        for (int i = 0; i < movies.length; i++) {
            Movie t = movies[i]; //need for database
        }
        cursor.close();
        db.close();
        return movies;
    }

}
