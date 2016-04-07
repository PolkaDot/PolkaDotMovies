package com.polka.pdm;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Junit test for checkInput method
 * does not need to be in manifest
 * Created by Esha Singh on 4/3/2016.
 */
public class checkInputTest extends LoginApplication{


    /**
     * test to check that both Username and password are not empty
     */
    @Test
    public void checksBothInputs() {

        assertEquals(2, checkInput("User", "password"));
    }

    /**
     * test to check that both Username and password are empty
     *
     */
    @Test
    public void checksBothMissing() {
        assertEquals(-1,checkInput("", ""));

    }
    /**
     test to check that Username is empty or password is empty
     *
     */
    @Test
    public void checksOneMissing() {
        assertEquals(1, checkInput("User",""));
        assertEquals(0,checkInput("", "pass"));

    }
}
