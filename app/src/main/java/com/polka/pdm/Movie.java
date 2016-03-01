package com.polka.pdm;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Arsh on 2/23/16.
 * @author Arsh
 * @version 1.0
 */
public class Movie implements Parcelable {

    private String title;
    private int year;
    private String synopsis;
    private String poster;


    public Movie(String title, int year, String synopsis, String poster) {
        this.title = title;
        this.year = year;
        this.synopsis = synopsis;
        this.poster = poster;
    }

    public Movie(Parcel in) {
        String[] data = new String[4];
        in.readStringArray(data);

        this.title = data[0];
        this.year = Integer.parseInt(data[1]);
        this.synopsis = data[2];
        this.poster = data[3];
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
        Movie that = (Movie) object;
        return this.title.equals(that.title) && this.year == that.year;
    }

    @Override
    public int hashCode() {
        return 7 * year + synopsis.length();
    }

    /**
     * Gets the title
     *
     * @return the title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the year movie was made
     *
     * @return gets the year the movie was made
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the synopsis
     *
     * @return synopsis of the movie
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * Gets the poster
     *
     * @return the poster image for the movie
     */
    public String getPoster() {
        return poster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{
                this.getTitle(),
                Integer.toString(this.getYear()),
                this.getSynopsis(),
                this.getPoster()
        });
    }

    /**
     * inner class
     * creates a new creator
     */
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        /**
         * creates a user from parcel
         * @param in parcel (of data)
         * @return user that we created
         */
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        /**
         * gives an array of users
         * @param size size of array
         * @return user array
         */
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
