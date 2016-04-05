package com.polka.pdm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * UserRepo is used in random with the database
 * inserts user into db and gets user by username
 *
 * Created by C. Shih on 2/12/2016.
 * Updated by Y. Avila-Stanley
 */
public class UserRepo {
    private final DBHelper dbHelper;//database helper

    /**
     * Create of db helper
     * @param context context of the application?
     */
    public UserRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    /**
     * Inserts new user into the database, only has info for registration
     * everything else is null
     *
     * @param user that it is inserting
//     * @return returns row ID of newly inserted row, -1 otherwise
     */
    public void insert(User user) {

        // open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.KEY_username, user.getUsername());
        values.put(User.KEY_password, user.getPassword());
        values.put(User.KEY_firstName, user.getFirstName());
        values.put(User.KEY_lastName, user.getLastName());
        values.put(User.KEY_email, user.getEmail());


        //inserting row
        try {
            long user_username = db.insertOrThrow(User.TABLE, null, values);
            db.close();
//            return user_username;
        } catch (SQLException e) {
//            return -1;
        }
    }

    /**
     * get all useres in the database
     * @return returns User array that contains all the users in the db
     */
    public User[] getAllUsers() {

        String countQ = "SELECT * FROM " + User.TABLE;
        SQLiteDatabase adb = dbHelper.getReadableDatabase();
        Cursor acursor = adb.rawQuery(countQ, null);
        int cnt = acursor.getCount();
        acursor.close();
        adb.close();


        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + User.TABLE;
        User[] users = new User[cnt];


        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            int i = 0;
            do {
                String username = cursor.getString(cursor.getColumnIndex(User.KEY_username));
                String password = cursor.getString(cursor.getColumnIndex(User.KEY_password));
                String firstName = cursor.getString(cursor.getColumnIndex(User.KEY_firstName));
                String lastName = cursor.getString(cursor.getColumnIndex(User.KEY_lastName));
                String email = cursor.getString(cursor.getColumnIndex(User.KEY_email));
                String major = cursor.getString(cursor.getColumnIndex(User.KEY_major));
                String phone = cursor.getString(cursor.getColumnIndex(User.KEY_phone));
                String interests = cursor.getString(cursor.getColumnIndex(User.KEY_interests));
                int isLock = cursor.getInt(cursor.getColumnIndex(User.KEY_isLocked));
                int isBanned = cursor.getInt(cursor.getColumnIndex(User.KEY_isBanned));
                int isAdmin = cursor.getInt(cursor.getColumnIndex(User.KEY_isAdmin));

                User user = new User(username, password, firstName, lastName, email,
                        major, phone, interests, isLock, isBanned, isAdmin); // may need new data struct to store total rating
                Log.d("CCC", "DID STUFF WITH USER ARRAY");
                users[i++] = user;
            } while (cursor.moveToNext() && i < users.length);
        }

        for (int i = 0; i < users.length; i++) {
            User t = users[i];
            Log.d("AAA", "has a user");

            if (t != null) {
                Log.d("BBB", "username: " + t.getUsername());
            }
        }
        cursor.close();
        db.close();
        return users;
    }


    /**
     * Gets the user object by username from database
     *
     * @param username to find by
     * @return user if found, null otherwise
     */
    public User getUserByUsername(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + User.TABLE
                + " WHERE " +
                User.KEY_username + " =?";

        User user = new User();

        Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(username)});
        if (cursor.moveToFirst()) {
            do {
                user.setUsername(cursor.getString(cursor.getColumnIndex(User.KEY_username)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(User.KEY_password)));
                user.setFirstName(cursor.getString(cursor.getColumnIndex(User.KEY_firstName)));
                user.setLastName(cursor.getString(cursor.getColumnIndex(User.KEY_lastName)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(User.KEY_email)));
                user.setMajor(cursor.getString(cursor.getColumnIndex(User.KEY_major)));
                user.setPhone(cursor.getString(cursor.getColumnIndex(User.KEY_phone)));
                user.setInterests(cursor.getString(cursor.getColumnIndex(User.KEY_interests)));
                user.setIsLock(cursor.getInt(cursor.getColumnIndex(User.KEY_isLocked)));
                user.setIsBanned(cursor.getInt(cursor.getColumnIndex(User.KEY_isBanned)));
                user.setIsAdmin(cursor.getInt(cursor.getColumnIndex(User.KEY_isAdmin)));


            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return user;
    }

    /**
     * updates the profile of a user
     * @param user username
     * @param pass password
     * @param firstname the first name
     * @param lastname the sir name
     * @param email the email of the user
     * @param phone number
     * @param major major offcial
     * @param interests things the user likes
     */
    public void updateProfile(String user, /*String newuser,*/ String pass, String firstname, String lastname, String email, String phone, String major, String interests) {
        setPass(user, pass);
        setFirstName(user, firstname);
        setLastName(user,lastname);
        setEmail(user, email);
        setPhone(user, phone);
        setMajor(user, major);
        setInterests(user, interests);
    }

    /**
     * setter for user
     * @param user name of user
     * @param newUser new username
     */
    public void setUsername(String user, String newUser) {

        // open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.KEY_username, newUser);

        // update
        db.close();
    }

    /**
     * setter for first name
     * @param user name of user
     * @param name new first name
     */
    private void setFirstName(String user, String name) {

        // open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.KEY_firstName, name);

        db.close();
    }

    /**
     * setter for last name
     * @param user name of user
     * @param name new last name
     */
    private void setLastName(String user, String name) {

        // open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.KEY_lastName, name);

        db.close();
    }

    /**
     * setter for passsword
     * @param user name of user
     * @param pass new password
     */
    private void setPass(String user, String pass) {

        // open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.KEY_password, pass);

        db.close();
    }

    /**
     * setter for email name
     * @param user name of user
     * @param email new email
     */
    private void setEmail(String user, String email) {

        // open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.KEY_email, email);

        db.close();
    }

    /**
     * setter for phone name
     * @param user name of user
     * @param phone new number
     */
    private void setPhone(String user, String phone) {

        // open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.KEY_phone, phone);

        db.close();
    }

    /**
     * setter for major
     * @param user name of user
     * @param major new major
     */
    private void setMajor(String user, String major) {

        // open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.KEY_major, major);

        db.close();
    }

    /**
     * setter for interests
     * @param user name of user
     * @param interests new interests
     */
    private void setInterests(String user, String interests) {

        // open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.KEY_interests, interests);

        db.close();
    }

    /**
     * setter for isLocked
     * @param user name of user
     * @param isLock value of user
     */
    public void setLock(String user, int isLock) {

        // open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.KEY_isLocked, isLock);

        db.close();
    }

    /**
     * setter for isBanned
     * @param user name of user
     * @param isBanned value of user
     */
    public void setBanned(String user, int isBanned) {

        // open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.KEY_isBanned, isBanned);

        db.close();
    }
}
