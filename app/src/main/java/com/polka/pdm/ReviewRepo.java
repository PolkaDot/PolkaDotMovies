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
    private final DBHelper dbHelper;
    /**
     * comment connector
     */

    private static final String COMMENTCONNECTOR = " = ? AND ";

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
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final ContentValues values = new ContentValues();
        values.put(Review.KEY_MOVIE, rating.getMovie());
        values.put(Review.KEY_MOVIEYEAR, rating.getMovieYear());
        values.put(Review.KEY_USER, rating.getUser());
        values.put(Review.KEY_MAJOR, rating.getMajor());
        values.put(Review.KEY_RATING, rating.getRating());
        values.put(Review.KEY_COMMENT, rating.getComment());


        //inserting row
        try {
            final long ratingRatingId = db.insertOrThrow(Review.TABLE, null, values);
            db.close();
            return ratingRatingId;
        } catch (SQLException e) {
            db.close();
            updateReview(rating.getUser(), rating.getMovie(), rating.getMovieYear(), rating.getRating(), rating.getComment());
            return -1;
        }
    }

    /**
     * sets ratings of movie
     *
     * @param user using app
     * @param movie that user is rating
     * @param movieYear year movie made
     * @param rating rating of the movie
     */
    private void setRating(String user, String movie, int movieYear, double rating) {

        // open connection to write data
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        final ContentValues values = new ContentValues();
        values.put(Review.KEY_RATING, rating);
        // creates where and where arguments
        final String where = Review.KEY_USER + COMMENTCONNECTOR
                + Review.KEY_MOVIE + COMMENTCONNECTOR
                + Review.KEY_MOVIEYEAR + " = ? ";
        final String[] whereArgs = {user, movie, ((Integer)movieYear).toString()};

        // update
        db.update(Review.TABLE, values, where, whereArgs);
        db.close();
    }

    /**
     * sets comments for that move
     *
     * @param user using app
     * @param movie user is rating
     * @param movieYear movie was made
     * @param comment user's comment
     */
    private void setComment(String user, String movie, int movieYear, String comment) {

        // open connection to write data
        final  SQLiteDatabase db = dbHelper.getWritableDatabase();

        final ContentValues values = new ContentValues();
        values.put(Review.KEY_COMMENT, comment);
        // creates where and where arguments
        final String where = Review.KEY_USER + COMMENTCONNECTOR
                + Review.KEY_MOVIE + COMMENTCONNECTOR
                + Review.KEY_MOVIEYEAR + " = ? ";
        final String[] whereArgs = {user, movie, ((Integer)movieYear).toString()};

        // update
        db.update(Review.TABLE, values, where, whereArgs);
        db.close();
    }


    /**
     * updates review of a movie if user already wrote a review of that movie
     *
     * @param user using app
     * @param movie that is being rated
     * @param movieYear year movie made
     * @param rating rating of that movie
     * @param comment comment of that movie
     */
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
        final SQLiteDatabase db = dbHelper.getReadableDatabase();
        final String selectQuery = "SELECT * FROM " + Review.TABLE
                + " WHERE " +
                Review.KEY_MOVIE + " = " + movie.getTitle() +
                " AND " + Review.KEY_MOVIEYEAR  + " = "
                + movie.getYear();

        final ArrayList<Review> ratings = new ArrayList<>();

        final Review ratingForSize = new Review();

        final Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(ratingForSize)});
        if (cursor.moveToFirst()) {
            do {
                final Review rating = new Review();
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
     * @param dataCount specifies the size of the list of movies to return // changes depending on the major in the dataset
     * @return returns a list of movies sorted by ratings by specified major
     */
    public Movie[] getRatingsByMajor(String major, int dataCount) {
        final SQLiteDatabase db = dbHelper.getReadableDatabase();
        final String selectQuery = "SELECT " + Review.KEY_MOVIE + ", " + Review.KEY_MOVIEYEAR + ", " + Review.KEY_MAJOR + ", sum(" + Review.KEY_RATING + ") as total"
                + " FROM " +
                Review.TABLE
                + " WHERE " +
                Review.KEY_MAJOR + " = " + "'" + major + "'"
                + " GROUP BY " +
                Review.KEY_MOVIE
                + " ORDER BY total DESC";
        final Movie[] movies = new Movie[dataCount];


        final Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            int i = 0;
            do {

                final String title = cursor.getString(cursor.getColumnIndex(Review.KEY_MOVIE));
                final int year = cursor.getInt(cursor.getColumnIndex(Review.KEY_MOVIEYEAR));
                cursor.getInt(cursor.getColumnIndex("total"));

                final Movie movie = new Movie(title, year, null, null); // may need new data struct to store total rating
                movies[i++] = movie;
            } while (cursor.moveToNext() && i < movies.length);
        }

//        //do we actually need this? what does this do?
//        for (int i = 0; i < movies.length; i++) {
//            Movie t = movies[i]; //need for database
//        }
        cursor.close();
        db.close();
        return movies;
    }

}
