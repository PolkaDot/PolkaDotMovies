package com.polka.pdm;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * User class
 *
 * Created by C. Shih on 2/12/2016.
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
    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public String email;
    public String major;
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
        return "username" + " " + this.username + " password: " + this.password;
    }

    // Parcelling part
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

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
