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

    /**
     * User table name
     */
    public static final String TABLE = "User";// labels table name
    /**
     * username column
     */
    public static final String KEY_USERNAME = "username";// labels table columns names
    /**
     * password column
     */
    public static final String KEY_PASSWORD = "password"; // labels table columns names
    /**
     * first name column
     */
    public static final String KEY_FIRSTNAME = "firstName";// labels table columns names
    /**
     * last name column
     */
    public static final String KEY_LASTNAME= "lastName"; // labels table columns names
    /**
     * email column
     */
    public static final String KEY_EMAIL = "email";// labels table columns names
    /**
     * major column
     */
    public static final String KEY_MAJOR = "major";// labels table columns names
    /**
     * phone column
     */
    public static final String KEY_PHONE = "phone";// labels table columns names
    /**
     * interests column
     */
    public static final String KEY_INTERESTS = "interests";// labels table columns names
    /**
     * is locked column
     */
    public static final String KEY_ISLOCKED = "isLocked";// labels table columns names
    /**
     * is banned column
     */
    public static final String KEY_ISBANNED = "isBanned";// labels table columns names
    /**
     * is admin column
     */
    public static final String KEY_ISADMIN = "isAdmin";// labels table columns names


    // attributes to keep data
    /**
     * username
     */
    private String username;
    /**
     * password
     */
    private String password;
    /**
     * first name
     */
    private String firstName;
    /**
     * last name
     */
    private String lastName;
    /**
     * email
     */
    private String email;
    /**
     * major
     */
    private String major;
    /**
     * phone
     */
    private String phone;
    /**
     * interests
     */
    private String interests;
    /**
     * is locked or not
     */
    private int isLocked;
    /**
     * is banned or not
     */
    private int isBanned;
    /**
     * is admin or not
     */
    private int isAdmin;

    /**
     * Constant used for the creation of the hashcode
     */
    private static final int HASHCODE_CONSTANT = 7;

    /**
     * constructor for user, with no params, inserted later
     */
    public User() {
    }


    /**
     * constructor for user, with username, password, first name, last name
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
     * @param ausername of user
     */
    public void setUsername(String ausername) {
        this.username = ausername;
    }

    /**
     * sets user's password
     * @param apassword of user
     */
    public void setPassword(String apassword) {
        this.password = apassword;
    }

    /**
     * sets user's first name
     * @param afirstName of the user
     */
    public void setFirstName(String afirstName) {
        this.firstName = afirstName;
    }

    /**
     * sets last name of user
     * @param alastName of user
     */
    public void setLastName(String alastName) {
        this.lastName = alastName;
    }

    /**
     * sets user's phone
     * @param aphone of user
     */
    public void setPhone(String aphone) {
        this.phone = aphone;
    }

    /**
     * sets user's email
     * @param aemail of user
     */
    public void setEmail(String aemail) {
        this.email = aemail;
    }

    /**
     * sets user's major
     * @param amajor of user
     */
    public void setMajor(String amajor) {
        this.major = amajor;
    }

    /**
     * set user's interests
     * @param ainterests of the user
     */
    public void setInterests(String ainterests) {
        this.interests = ainterests;
    }

    /**
     * sets if user is admin
     * @param aisAdmin int val if admin
     */
    public void setIsAdmin(int aisAdmin) {
        this.isAdmin = aisAdmin;
    }

    /**
     * sets if user is banned
     * @param aisBanned int value if user banned
     */
    public void setIsBanned(int aisBanned) {
        this.isBanned = aisBanned;
    }

    /**
     * sets if user locked
     * @param aisLocked int value if user locked
     */
    public void setIsLock(int aisLocked) {
        this.isLocked = aisLocked;
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
    private User(Parcel in) {
        // 11 is number of fields, and the 0 - 10 are corresponding index to it in array
        // for that specific data value
        final String[] data = new String[11]; //11 stuff that we want
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
        final User that = (User) object;
        return this.username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return HASHCODE_CONSTANT * username.hashCode();
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
