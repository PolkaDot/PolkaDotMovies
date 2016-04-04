package com.polka.pdm;

/**
 * The class that holds all the movie information
 *
 * @author Arsh
 * @version 1.0
 */
import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    /**
     * Title of movie
     */
    private final String title;
    /**
     * Year movie was made
     */
    private final int year;
    /**
     * Synopsis of movie
     */
    private final String synopsis;
    /**
     * Represents image of movie poster
     */
    private final String poster;
    /**
     * Represents number of instance variables for movie.
     * This is used to create a movie from a parcel
     */
    private static final int NUMBER_OF_INSTANCE_VARS = 4;
    /**
     * Index in parcel where data for the title is stored
     */
    private static final int TITLE_INDEX = 0;
    /**
     * Index in parcel where data for the year is stored
     */
    private static final int YEAR_INDEX = 1;
    /**
     * Index in parcel where data for the synopsis is stored
     */
    private static final int SYNOPSIS_INDEX = 2;
    /**
     * Index in parcel where data for the poster is stored
     */
    private static final int POSTER_INDEX = 3;
    /**
     * Constant used for the creation of the hashcode
     */
    private static final int HASHCODE_CONSTANT = 7;

    /**
     * Constructor.
     *
     * @param  movieTitle The name of the movie
     * @param movieYear The year the movie was released
     * @param movieSynopsis The synopsis of the movie
     * @param  moviePoster The poster of the movie
     */
    public Movie(String movieTitle, int movieYear, String movieSynopsis, String moviePoster) {
        this.title = movieTitle;
        this.year = movieYear;
        this.synopsis = movieSynopsis;
        this.poster = moviePoster;
    }
    /**
     * Constructor.
     *
     * @param  in An object of the Parcel class
     */
    private Movie(Parcel in) {
        final String[] data = new String[NUMBER_OF_INSTANCE_VARS];
        in.readStringArray(data);

        this.title = data[TITLE_INDEX];
        this.year = Integer.parseInt(data[YEAR_INDEX]);
        this.synopsis = data[SYNOPSIS_INDEX];
        this.poster = data[POSTER_INDEX];
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!(object instanceof Movie)) {
            return false;
        }
        final Movie that = (Movie) object;
        return this.title.equals(that.title) && this.year == that.year;
    }

    

    @Override
    public int hashCode() {
        return HASHCODE_CONSTANT * year + synopsis.length();
    }


    /**
     * Gets the title
     * @return the title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the year movie was made
     * @return gets the year the movie was made
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the synopsis
     * @return synopsis of the movie
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * Gets the poster
     * @return the poster image for the movie
     */
    private String getPoster() {
        return poster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeStringArray(new String[] {
                this.getTitle(),
                Integer.toString(this.getYear()),
                this.getSynopsis(),
                this.getPoster()
        });
    }

    /**
     * Inner class that creates a new creator
     */
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        /**
         * Creates a user from parcel
         * @param in parcel (of data)
         * @return user that we created
         */
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        /**
         * Gives an array of users
         * @param size size of array
         * @return user array
         */
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
