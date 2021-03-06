package com.polka.pdm;

/**
 * Ratings AND COMMENTS class
 * Holds information regarding a review for a movie
 * Created by Arsh on 2/23/16.
 * @author Arsh
 * @version 1.0
 */
public class Review {

    /**
     * rating's table name
     */
    public static final String TABLE = "Rating";

    /**
     * rating id column
     */
    public static final String KEY_RATINGID = "ratingId"; // primary key in db to keep unique

    /**
     * movie column
     */
    public static final String KEY_MOVIE = "movie";

    /**
     * movie year column
     */
    public static final String KEY_MOVIEYEAR = "movieYear";

    /**
     * user column
     */
    public static final String KEY_USER = "user";

    /**
     * major column
     */
    public static final String KEY_MAJOR = "major";

    /**
     * rating column
     */
    public static final String KEY_RATING = "rating";

    /**
     * comment column
     */
    public static final String KEY_COMMENT = "comment";

    
   // private int hashNum = 13;//why 13?
    /**
     * movie being reviewed
     */
    private String movie;
    /**
     * rating of the movie
     */
    private double rating;
    /**
     * comment of the movie
     */
    private String comment;
    /**
     * year movie was made
     */
    private int movieYear;
    /**
     * user using app
     */
    private String user;
    /**
     * user's major
     */
    private String major;

    /**
     * Empty constructor
     */
    public Review(){

    }

    /**
     * constructor with user, movie, and rating input, comment null
     *
     * @param users who wrote the rating
     * @param majors of the user
     * @param movies that rating is about
     * @param movieYears year movie made
     * @param comments about that movie
     */
    public Review(String users, String majors, String movies, int movieYears, String comments) {
        this.user = users;
        this.major = majors;
        this.movie = movies;
        this.movieYear = movieYears;
        this.comment = comments;
    }

    /**
     * constructor with user, movie, rating, and comment
     *
     * @param users who wrote the rating
     * @param majors user's major
     * @param movies that rating is about
     * @param movieYears that movie was made
     * @param ratings of that movie
     * @param comments about that movie
     */
    public Review(String users, String majors, String movies, int movieYears, double ratings, String comments) {
        this.user = users;
        this.major = majors;
        this.movie = movies;
        this.movieYear = movieYears;
        this.rating = ratings;
        this.comment = comments;
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
     * @param movies review is about
     */
    public void setMovie(String movies) {
        this.movie = movies;
    }

    /**
     * sets review's rating
     * @param ratings of the movie
     */
    public void setRating(double ratings) {
        this.rating = ratings;
    }

    /**
     * sets review's comment
     * @param comments about review
     */
    public void setComment(String comments) {
        this.comment = comments;
    }

    /**
     * sets the movie's year
     * @param movieYears of the movie
     */
    public void setMovieYear(int movieYears) {
        this.movieYear = movieYears;
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
     * @param majors of the user
     */
    public void setMajor(String majors) {
        this.major = majors;
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
        final Review that = (Review) object;
        return this.movie.equals(that.movie) && this.rating == that.rating
                && this.user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return (int) ( rating + comment.length());
    } //deleted(13*rating) 13 was a magic number was it important?

}
