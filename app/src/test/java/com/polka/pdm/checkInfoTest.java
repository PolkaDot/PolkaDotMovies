package com.polka.pdm;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by C. Shih on 4/2/2016.
 */
public class checkInfoTest extends RegistrationPage{
    private User userWoutField;
    private User userAll;
    private User validEmail;
    private User invalidEmail;



    // just for reference of which parameters match up to what kind of user
    @Before
    public void setup() {
        userAll = new User("testUsername", "testPassword", "TestFirstName", "TestLastName", "TestEmail@.com");
        userWoutField = new User("", "testPassword", "TestFirstName", "TestLastName", "TestEmail@.com");
        validEmail = new User("testUsername", "testPassword", "TestFirstName", "TestLastName", "TestEmail@.com");
        invalidEmail = new User("testUsername", "testPassword", "TestFirstName", "TestLastName", "TestEmail");
    }


    @Test
    public void checksInfoWantTrue() {

        assertEquals(1, checkInfo(userAll.getFirstName(), userAll.getLastName(), userAll.getUsername(), userAll.getEmail(), userAll.getPassword())); // userAll
        assertEquals(1, checkInfo(validEmail.getFirstName(), validEmail.getLastName(), validEmail.getUsername(), validEmail.getEmail(), validEmail.getPassword())); // userAll
    }

    @Test
    public void checksInfoWantFalse() {
        assertEquals(-1, checkInfo(userWoutField.getFirstName(), userWoutField.getLastName(), userWoutField.getUsername(), userWoutField.getEmail(), userWoutField.getPassword()));
        assertEquals(0, checkInfo(invalidEmail.getFirstName(), invalidEmail.getLastName(), invalidEmail.getUsername(), invalidEmail.getEmail(), invalidEmail.getPassword()));

    }




}
