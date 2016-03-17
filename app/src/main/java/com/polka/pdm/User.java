package com.polka.pdm;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * User class
 *
 * @author C. Shih on 2/12/2016.
 *
 * @version 1.0
 */
public class User implements Parcelable {

    // labels table name
    public static final String TABLE = "User";

    // labels table columns names
    public static final String KEY_username = "username";
    public static final String KEY_password = "password";
    public static final String KEY_firstName = "firstName";
    public static final String KEY_lastName = "lastName";
    public static final String KEY_email = "email";
    public static final String KEY_major = "major";
    public static final String KEY_phone = "phone";
    public static final String KEY_interests = "interests";
    public static final String KEY_isLocked = "isLocked";
    public static final String KEY_isBanned = "isBanned";
    public static final String KEY_isAdmin = "isAdmin";


    // attributes to keep data
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String major;
    private String phone;
    private String interests;
    private int isLocked;
    private int isBanned;
    private int isAdmin;


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
     * constructor for all of the attributes of user
     *
     * @param aUsername of user
     * @param aPassword of user
     * @param aFirstName of user
     * @param aLastName of user
     * @param aEmail of user
     * @param aMajor of user
     * @param aPhone of user
     * @param aInterests of user
     * @param aIsLocked of user
     * @param aIsBanned of user
     * @param aIsAdmin of user
     */
    public User(String aUsername, String aPassword, String aFirstName, String aLastName, String aEmail,
        String aMajor, String aPhone, String aInterests, int aIsLocked, int aIsBanned, int aIsAdmin) {
        this.username = aUsername;
        this.password = aPassword;
        this.firstName = aFirstName;
        this.lastName = aLastName;
        this.email = aEmail;
        this.major = aMajor;
        this.phone = aPhone;
        this.interests = aInterests;
        this.isLocked = aIsLocked;
        this.isBanned = aIsBanned;
        this.isAdmin = aIsAdmin;
    }




    /**
     * gets User's username
     * @return username of User
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * gets User's password
     * @return password of User
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * gets User's first name
     * @return first name of User
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * gets User's last name
     * @return last name of User
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * gets User's email
     * @return email of User
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * gets User's phone
     * @return phone of User
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * gets User's major
     * @return major of User
     */
    public String getMajor() {
        return this.major;
    }

    /**
     * gets User's interests
     * @return interests of User
     */
    public String getInterests() {
        return this.interests;
    }

    /**
     * gets if user is locked
     * @return int value (num of login attempts) for locked
     */
    public int getIsLocked() {
        return this.isLocked;
    }

    /**
     * gets if user is banned
     * @return 1 if user is banned
     */
    public int getIsBanned() {
        return this.isBanned;
    }

    /**
     * gets if user is admin
     * @return 1 if user is admin
     */
    public int getIsAdmin() {
        return this.isAdmin;
    }

    /**
     * sets User's username
     * @param username of user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * sets user's password
     * @param password of user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * sets user's firstname
     * @param firstName of the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * sets last name of user
     * @param lastName of user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * sets user's phone
     * @param phone of user
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * sets user's email
     * @param email of user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * sets user's major
     * @param major of user
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * set user's interests
     * @param interests of the user
     */
    public void setInterests(String interests) {
        this.interests = interests;
    }

    /**
     * sets if user is admin
     * @param isAdmin int val if admin
     */
    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * sets if user is banned
     * @param isBanned int value if user banned
     */
    public void setIsBanned(int isBanned) {
        this.isBanned = isBanned;
    }

    /**
     * sets if user locked
     * @param isLocked int value if user locked
     */
    public void setIsLock(int isLocked) {
        this.isLocked = isLocked;
    }



    /**
     * converts to string the username and password
     *
     * @return the username and password of the user object
     */
    public String toString() {
        return "username" + " " + this.username + " password: " + this.password;
    }

    /**
     * takes in the user data from database
     * @param in input from database
     */
    public User(Parcel in) {
        String[] data = new String[11];
        in.readStringArray(data);

        this.username = data[0];
        this.password = data[1];
        this.firstName = data[2];
        this.lastName = data[3];
        this.email = data[4];
        this.major = data[5];
        this.phone = data[6];
        this.interests = data[7];
        this.isLocked = Integer.parseInt(data[8]);
        this.isBanned = Integer.parseInt(data[9]);
        this.isAdmin = Integer.parseInt(data[10]);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                this.username,
                this.password,
                this.firstName,
                this.lastName,
                this.email,
                this.major,
                this.phone,
                this.interests,
                Integer.toString(this.isLocked),
                Integer.toString(this.isBanned),
                Integer.toString(this.isAdmin)});
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!(object instanceof User)) {
            return false;
        }
        User that = (User) object;
        return this.username.equals(that.username);
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
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        /**
         * gives an array of users
         * @param size size of array
         * @return user array
         */
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
