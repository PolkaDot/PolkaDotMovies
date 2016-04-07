package com.polka.pdm;

import org.junit.Test;
import static org.junit.Assert.*;
//import assertEquals;


/**
 * Junit test for equals method in the Review class
 *
 * does not need to be in manifest
 * Created by Arsh Momin
 */

public class checkToastTest extends Review{


    /**
     * test to see if review equal function works as intended
     */
    @Test
    public void checkEquals() {
        //equal if movie, rating and user equal

        //tests when creating a movie with the first constructor (without rating)
        Review all = new Review("user", "major", "movie", 2000, "sucks");
        Review allButUser = new Review("u", "major", "movie", 2000, "sucks");
        Review allButMajor = new Review("user", "m", "movie", 2000, "sucks");
        Review allButMovie = new Review("user", "major", "m", 2000, "sucks");
        Review allButYear = new Review("user", "major", "movie", 100, "sucks");
        Review allButComment = new Review("user", "major", "movie", 2000, "s");
        Review allButNot = new Review("user", "major", "movie", 2000, "sucks");


        assertEquals(true, all.equals(allButNot));
        assertEquals(false, all.equals(allButUser));
        assertEquals(true, all.equals(allButMajor));
        assertEquals(false, all.equals(allButMovie));
        assertEquals(true, all.equals(allButYear));
        assertEquals(true, all.equals(allButComment));


        //tests when creating a movie with the second constructor (with rating)
        Review allWithRating = new Review("USER", "MAJOR", "MOVIE", 2110, 1.9, "AMAZING");
        Review allExceptUser = new Review("U", "MAJOR", "MOVIE", 2110, 1.9, "AMAZING");
        Review allExceptMajor = new Review("USER", "M", "MOVIE", 2110, 1.9, "AMAZING");
        Review allExceptMovie = new Review("USER", "MAJOR", "M", 2110, 1.9, "AMAZING");
        Review allExceptYear = new Review("USER", "MAJOR", "MOVIE", 2000, 1.9, "AMAZING");
        Review allExceptRating = new Review("USER", "MAJOR", "MOVIE", 2110, 2.3, "AMAZING");
        Review allExceptComment = new Review("USER", "MAJOR", "MOVIE", 2110, 1.9, "A");
        Review allExceptNot = new Review("USER", "MAJOR", "MOVIE", 2110, 1.9, "AMAZING");

        assertEquals(true, allWithRating.equals(allExceptNot));
        assertEquals(false, allWithRating.equals(allExceptUser));
        assertEquals(true, allWithRating.equals(allExceptMajor));
        assertEquals(false, allWithRating.equals(allExceptMovie));
        assertEquals(true, allWithRating.equals(allExceptYear));
        assertEquals(false, allWithRating.equals(allExceptRating));
        assertEquals(true, allWithRating.equals(allExceptComment));
//
//        Toast user = Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT);
//        assertEquals(user.getView().isShown(), makeToast(checkInput("", "pass")));
//
//        Toast pass = Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT);
//        assertEquals(pass.getView().isShown(), makeToast(checkInput("user", "")));

//        assertEquals(true, makeToast(0));
//        assertEquals(true, makeToast(-1));
//        assertEquals(true, makeToast(1));
//
//        assertEquals(false, makeToast(2));
//        assertEquals(false, makeToast(-2));




    }


}
