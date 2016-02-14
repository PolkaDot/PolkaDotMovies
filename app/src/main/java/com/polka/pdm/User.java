package com.polka.pdm;

/**
 * User class
 *
 * Created by C. Shih on 2/12/2016.
 */
public class User {

    // labels table name
    public static final String TABLE = "User";

    // labels table columns names
    public static final String KEY_username = "username";
    public static final String KEY_password = "password";
    public static final String KEY_firstName = "firstName";
    public static final String KEY_lastName = "lastName";
    public static final String KEY_email = "email";
    public static final String KEY_phone = "phone";
    public static final String KEY_interests = "interests";
    public static final String KEY_isLocked = "isLocked";
    public static final String KEY_isBanned = "isBanned";
    public static final String KEY_isAdmin = "isAdmin";


    // attributes to keep data
    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public String email;
    public String phone;
    public String interests;
    public int isLocked;
    public int isBanned;
    public int isAdmin;


    /**
     * constructor for user, with no params, inserted later
     */
    public User() {
    }


    /**
     * constructor for user, with username, password, firstname, lastname
     * as parameters, testing mostly
     *
     * @param aUsername of the user
     * @param aPassword of the user
     * @param aFirstName of the user
     * @param aLastName of the user
     * @param aEmail of the user
     */
    public User(String aUsername, String aPassword, String aFirstName, String aLastName, String aEmail) {
        this.username = aUsername;
        this.password = aPassword;
        this.firstName = aFirstName;
        this.lastName = aLastName;
        this.email = aEmail;
    }

    /**
     * converts to string the username and password
     *
     * @return the username and password of the user object
     */
    public String toString() {
        return this.username + " " + this.password;
    }
}
