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
//    private String poster
//    private Rating (array list of Ratings)


    public Movie(String title, int year, String synopsis) {
        this.title = title;
        this.year = year;
        this.synopsis = synopsis;
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


    /**
     * to string of a movie's title
     *
     * @return movie title
     */
    public String getTitle() {
        return this.title;
    }


    /**
     * to string of a movie's year
     *
     * @return movie's year made
     */
    public int getYear() {
        return this.year;
    }

    @Override
    public int hashCode() {
        return 7*year + synopsis.length();
    }
}
