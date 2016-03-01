package com.polka.pdm;

/**
 * Ratings AND COMMENTS class (but we wanted the name to be short hehehe)
 * Created by Arsh on 2/23/16.
 * @author Arsh
 * @version 1.0
 */
public class Rating {

    public static final String TABLE = "Rating";

    public static final String KEY_ratingId = "ratingId";
    public static final String KEY_movie = "movie";
    public static final String KEY_movieYear = "movieYear";
    public static final String KEY_user = "user";
    public static final String KEY_rating = "rating";
    public static final String KEY_comment = "comment";

    protected String movie;
    //    protected Movie movie;
    protected int rating;
    protected String comment;
    protected int movieYear;
    protected  String user;
//    protected User user;


    /**
     * no arg constructor
     */
    public Rating() {
    }


    /**
     * constructor with user, movie, and rating input, comment null
     *
     * @param user who wrote the rating
     * @param movie that rating is about
     * @param movieYear year movie made
     * @param rating of that movie
     */
    public Rating(String user, String movie, int movieYear, int rating) {
        this.user = user;
        this.movie = movie;
        this.movieYear = movieYear;
        this.rating = rating;
        this.comment = null;

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
    public Rating(String user, String movie, int movieYear, int rating, String comment) {
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
        Rating that = (Rating) object;
        return this.movie.equals(that.movie) && this.rating == that.rating && this.user.equals(that.user);
    }

//    @Override
//    public int hashCode() {
//        return 13 * rating + comment.length();
//    }

}