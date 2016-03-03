package com.polka.pdm;

import android.media.Rating;

/**
 * Ratings AND COMMENTS class (but we wanted the name to be short hehehe)
 * Created by Arsh on 2/23/16.
 * @author Arsh
 * @version 1.0
 */
public class ReviewAndRate {

    public static final String TABLE = "Rating";

    public static final String KEY_ratingId = "ratingId";
    public static final String KEY_movie = "movie";
    public static final String KEY_movieYear = "movieYear";
    public static final String KEY_user = "user";
    public static final String KEY_rating = "rating";
    public static final String KEY_comment = "comment";

    protected String movie;
    //    protected Movie movie;
    protected double rating;
    protected String comment;
    protected int movieYear;
    protected  String user;
//    protected User user;

    /*
    * Empty constructor
     */
    public ReviewAndRate(){

    }

    /**
     * constructor with user, movie, and rating input, comment null
     *
     * @param user who wrote the rating
     * @param movie that rating is about
     * @param movieYear year movie made
     * @param comment about that movie
     */
    public ReviewAndRate(String user, String movie, int movieYear,String comment) {
        this.user = user;
        this.movie = movie;
        this.movieYear = movieYear;
        this.rating = -1 ;
        this.comment = comment;

    }

    /**
     * constructor with user, movie, rating, and comment
     *
     * @param user who wrote the rating
     * @param movie that rating is about
     * @param movieYear that movie was made
     * @param rating of that movie
     * @param comment about that movie
     */
    public ReviewAndRate(String user, String movie, int movieYear, double rating, String comment) {
        this.user = user;
        this.movie = movie;
        this.movieYear = movieYear;
        this.rating = rating;
        this.comment = comment;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!(object instanceof Rating)) {
            return false;
        }
        ReviewAndRate that = (ReviewAndRate) object;
        return this.movie.equals(that.movie) && this.rating == that.rating && this.user.equals(that.user);
    }

//    @Override
//    public int hashCode() {
//        return 13 * rating + comment.length();
//    }

}
