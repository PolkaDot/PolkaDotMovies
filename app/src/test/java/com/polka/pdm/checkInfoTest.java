package com.polka.pdm;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Junit test for checkInfoTest method
 * does not need to be in manifeest
 * Created by C. Shih on 4/2/2016.
 */
public class checkInfoTest extends RegistrationPage{
    /**
     * user instance with one field missing (username)
     */
    private User userWoutField1;

    /**
     * user instance with one field missing (password)
     */
    private User userWoutField2;

    /**
     * user instance with one field missing (first name)
     */
    private User userWoutField3;

    /**
     * user instance with one field missing (last name)
     */
    private User userWoutField4;

    /**
     * user instance with one field missing (email)
     */
    private User userWoutField5;


    /**
     * user instance with all valid fields
     */
    private User userAll;
    /**
     * user instance with valid email and other valid info
     */
    private User validEmail;
    /**
     * user instance with invalid email
     */
    private User invalidEmail;



    // just for reference of which parameters match up to what kind of user

    /**
     * sets up the junit test with the different types of users
     */
    @Before
    public void setup() {
        userAll = new User("testUsernameAll", "testPasswordAll", "TestFirstNameAll", "TestLastNameAll", "TestEmailAll@.com");
        userWoutField1 = new User("", "testPassword1", "TestFirstName1", "TestLastName1", "TestEmail1@.com");
        userWoutField2 = new User("testUsername2", "", "TestFirstName2", "TestLastName2", "TestEmail2@.com");
        userWoutField3 = new User("testUsername3", "testPassword3", "", "TestLastName3", "TestEmail3@.com");
        userWoutField4 = new User("testUsername4", "testPassword4", "TestFirstName4", "", "TestEmail4@.com");
        userWoutField5 = new User("testUsername5", "testPassword5", "TestFirstName5", "TestLastName5", "");
        validEmail = new User("testUsernameV", "testPasswordV", "TestFirstNameV", "TestLastNameV", "TestEmailValid@.com");
        invalidEmail = new User("testUsernameIV", "testPasswordIV", "TestFirstNameIV", "TestLastNameIV", "TestEmailNot");
    }


    /**
     * test for all the "true" conditions
     * goes through all the ifs and returns at very end
     */
    @Test
    public void checksInfoWantTrue() {

        assertEquals(1, checkInfo(userAll.getFirstName(), userAll.getLastName(), userAll.getUsername(), userAll.getEmail(), userAll.getPassword())); // userAll
        assertEquals(1, checkInfo(validEmail.getFirstName(), validEmail.getLastName(), validEmail.getUsername(), validEmail.getEmail(), validEmail.getPassword())); // userAll
    }

    /**
     * checks for all the "false" conditions
     * goes through either 1 or 2 of the ifs and
     * returns before it gets to the end
     */
    @Test
    public void checksInfoWantFalse() {
        assertEquals(-1, checkInfo(userWoutField1.getFirstName(), userWoutField1.getLastName(), userWoutField1.getUsername(), userWoutField1.getEmail(), userWoutField1.getPassword()));
        assertEquals(-1, checkInfo(userWoutField2.getFirstName(), userWoutField2.getLastName(), userWoutField2.getUsername(), userWoutField2.getEmail(), userWoutField2.getPassword()));
        assertEquals(-1, checkInfo(userWoutField3.getFirstName(), userWoutField3.getLastName(), userWoutField3.getUsername(), userWoutField3.getEmail(), userWoutField3.getPassword()));
        assertEquals(-1, checkInfo(userWoutField4.getFirstName(), userWoutField4.getLastName(), userWoutField4.getUsername(), userWoutField4.getEmail(), userWoutField4.getPassword()));
        assertEquals(-1, checkInfo(userWoutField5.getFirstName(), userWoutField5.getLastName(), userWoutField5.getUsername(), userWoutField5.getEmail(), userWoutField5.getPassword()));

        assertEquals(0, checkInfo(invalidEmail.getFirstName(), invalidEmail.getLastName(), invalidEmail.getUsername(), invalidEmail.getEmail(), invalidEmail.getPassword()));

    }




}
