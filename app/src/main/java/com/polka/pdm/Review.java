package com.polka.pdm;

/**
 * Ratings AND COMMENTS class
 * Holds information regarding a review for a movie
 * Created by Arsh on 2/23/16.
 * @author Arsh
 * @version 1.0
 */
public class Review {

    public static final String TABLE = "Rating";

    public static final String KEY_ratingId = "ratingId"; // primary key in db to keep unique
    public static final String KEY_movie = "movie";
    public static final String KEY_movieYear = "movieYear";
    public static final String KEY_user = "user";
    public static final String KEY_major = "major";
    public static final String KEY_rating = "rating";
    public static final String KEY_comment = "comment";

    private String movie;
    private double rating;
    private String comment;
    private int movieYear;
    private String user;
    private String major;

    /*
    * Empty constructor
     */
    public Review(){

    }

    /**
     * constructor with user, movie, and rating input, comment null
     *
     * @param user who wrote the rating
     * @param major of the user
     * @param movie that rating is about
     * @param movieYear year movie made
     * @param comment about that movie
     */
    public Review(String user, String major, String movie, int movieYear, String comment) {
        this.user = user;
        this.major = major;
        this.movie = movie;
        this.movieYear = movieYear;
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
    public Review(String user, String major, String movie, int movieYear, double rating, String comment) {
        this.user = user;
        this.major = major;
        this.movie = movie;
        this.movieYear = movieYear;
        this.rating = rating;
        this.comment = comment;
    }

    /**
     * gets review's movie
     * @return review's movie
     */
    public String getMovie() {
        return this.movie;
    }

    /**
     * gets review's rating
     * @return review's rating
     */
    public double getRating() {
        return this.rating;
    }

    /**
     * gets review's comment
     * @return review's comment
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * gets review's movie year
     * @return review's movie year
     */
    public int getMovieYear() {
        return this.movieYear;
    }

    /**
     * gets review's user
     * @return review's username
     */
    public String getUser() {
        return this.user;
    }

    /**
     * gets review's user's major
     * @return review's user's major
     */
    public String getMajor() {
        return this.major;
    }

    /**
     * sets review's movie
     * @param movie review is about
     */
    public void setMovie(String movie) {
        this.movie = movie;
    }

    /**
     * sets review's rating
     * @param rating of the movie
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * sets review's comment
     * @param comment about review
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * sets the movie's year
     * @param movieYear of the movie
     */
    public void setMovieYear(int movieYear) {
        this.movieYear = movieYear;
    }

    /**
     * sets review's username
     * @param username of the user
     */
    public void setUser(String username) {
        this.user = username;
    }

    /**
     * review's user's major
     * @param major of the user
     */
    public void setMajor(String major) {
        this.major = major;
    }


    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!(object instanceof Review)) {
            return false;
        }
        Review that = (Review) object;
        return this.movie.equals(that.movie) && this.rating == that.rating
                && this.user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return (int) (13 * rating + comment.length());
    }

}
