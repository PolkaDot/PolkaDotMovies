package com.polka.pdm;

/**
 * Created by Arsh on 2/23/16.
 * @author Arsh
 * @version 1.0
 */
public class Movie {
//    title
//    year
//    synopsis
//    posters (string to online)

    //    ratings
//    runtime
//    release dates
//    bridge cast
//    links (online rotten tomatoes)
//    links to search page
    private String title;
    private int year;
    private String synopsis;
    private String poster;
//    private Rating (array list of Ratings)


    public Movie(String title, int year, String synopsis, String poster) {
        this.title = title;
        this.year = year;
        this.synopsis = synopsis;
        this.poster = poster;
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
        return 7*year + synopsis.length();
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
    public String getPoster() {
        return poster;
    }
}
