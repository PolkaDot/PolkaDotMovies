package com.polka.pdm;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Junit test for CheckValidTest Method
 *
 * Created by Yami on 4/4/2016.
 */
 class CheckValidChangeTest extends EditProfile{
    /**
     * Instance of user without password filled in
     */
    private User userWoutPassword;

    /**
     * Instance of user without firstname filled in
     */
    private User userWoutFirstName;

    /**
     * Instance of user without lastname filled in
     */
    private User userWoutLastName;

    /**
     * Instance of user without email filled in
     */
    private User userWoutEmail;

    /**
     * Instance of user with all fields filled in appropriately
     */
    private User userAllValid;

    /**
     * Instance of user without valid email
     */
    private User userWoutValidEmail;

    /**
     * Instance of user with valid email
     */
    private User userWValidEmail;

    /**
     * Sets up users for the junit tests
     */
    @Before
    public void setup() {
        userAllValid = new User("validUsername", "validPassword", "validFirstName", "validLastName", "Valid@Email.com");
        userWoutPassword = new User("validUsername", "", "validFirstName", "validLastName", "Valid@Email.com");
        userWoutEmail = new User("validUsername", "validPassword", "validFirstName", "validLastName", "");
        userWoutFirstName = new User("validUsername", "validPassword", "", "validLastName", "Valid@Email.com");
        userWoutLastName = new User("validUsername", "validPassword", "validFirstName", "", "Valid@Email.com");
        userWoutValidEmail = new User("validUsername", "validPassword", "validFirstName", "validLastName", "InvalidEmail.com");
        userWValidEmail = new User("validUsername", "validPassword", "validFirstName", "validLastName", "Valid@Email.com");
    }

    /**
     * Tests that true conditions return 1
     */
    @Test
    public void checkValidChangeReturns1() {
        assertEquals(1, checkValidChange(userAllValid.getFirstName(), userAllValid.getLastName(), userAllValid.getEmail(), userAllValid.getPassword()));
        assertEquals(1, checkValidChange(userWValidEmail.getFirstName(), userWValidEmail.getLastName(), userWValidEmail.getEmail(), userWValidEmail.getPassword()));
    }

    /**
     * Test that conditions return 0
     */
    @Test
    public void checkValidChangeReturns0() {
        assertEquals(0, checkValidChange(userWoutValidEmail.getFirstName(), userWoutValidEmail.getLastName(), userWoutValidEmail.getEmail(), userWoutValidEmail.getPassword()));
    }

    /**
     * Tests that all conditions that should return -1, return -1
     */
    @Test
    public void checkValidChangeReturnsNeg1() {
        assertEquals(-1, checkValidChange(userWoutPassword.getFirstName(), userWoutPassword.getLastName(), userWoutPassword.getEmail(), userWoutPassword.getPassword()));
        assertEquals(-1, checkValidChange(userWoutFirstName.getFirstName(), userWoutFirstName.getLastName(), userWoutFirstName.getEmail(), userWoutFirstName.getPassword()));
        assertEquals(-1, checkValidChange(userWoutLastName.getFirstName(), userWoutLastName.getLastName(), userWoutLastName.getEmail(), userWoutLastName.getPassword()));
        assertEquals(-1, checkValidChange(userWoutEmail.getFirstName(), userWoutEmail.getLastName(), userWoutEmail.getEmail(), userWoutEmail.getPassword()));
    }

}
