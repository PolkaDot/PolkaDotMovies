package com.polka.pdm;

/**
 * Ratings AND COMMENTS class (but we wanted the name to be short hehehe)
 * Created by Arsh on 2/23/16.
 * @author Arsh
 * @version 1.0
 */
public class Rating {
    private Movie movie;
    private int rating;
    private String comment;
    private User user;
    public Rating(User user, Movie movie, int rating) {
        this.user = user;
        this.movie = movie;
        this.rating = rating;
        this.comment = null;

    }

    public Rating(User user, Movie movie, int rating, String comment) {
        this.user = user;
        this.movie = movie;
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
        return this.movie.equals(that.movie) && this.rating == that.rating;
    }

    @Override
    public int hashCode() {
        return 13 * rating + comment.length();
    }

}
