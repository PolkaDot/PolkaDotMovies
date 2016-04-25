package com.polka.pdm;

/**
 * Created by alisha on 4/7/2016.
 *
 * does not need to be in the manifest
 */

import org.junit.Test;

import static org.junit.Assert.*;

public class ratingInfoTest extends ReviewPage{

    /**
     * Checks if neither rating nor comment is entered
     */
    @Test
    public void neitherEntered() {

        assertEquals(true, ratingInfoNotEntered("", 0));
    }

    /**
     * Checks if both comment and rating is entered
     *
     */
    @Test
    public void bothEntered() {
        assertEquals(false,ratingInfoNotEntered("",1 ));

    }
    /**
     *Checks if either rating or comment is entered
     */
    @Test
    public void eitherEntered() {
        assertEquals(false, ratingInfoNotEntered("comment",0));
        assertEquals(false,ratingInfoNotEntered(null, 1));

    }
}