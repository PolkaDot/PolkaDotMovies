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
    /**
     * database helper
     */
    private final DBHelper dbHelper;//database helper

    /**
     * String literal for " =?"
     */
    private static final String QUESTIONMARK = " = ?";

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
     */
    public void insert(User user) {

        // open connection to write data
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final ContentValues values = new ContentValues();
        values.put(User.KEY_USERNAME, user.getUsername());
        values.put(User.KEY_PASSWORD, user.getPassword());
        values.put(User.KEY_FIRSTNAME, user.getFirstName());
        values.put(User.KEY_LASTNAME, user.getLastName());
        values.put(User.KEY_EMAIL, user.getEmail());


        //inserting row
        try {
            db.insertOrThrow(User.TABLE, null, values);
            db.close();
//            return user_username;
        } catch (SQLException e) {
//            return -1;
            Log.d("SQL", "SQLException");

        }
    }

    /**
     * get all useres in the database
     * @return returns User array that contains all the users in the db
     */
    public User[] getAllUsers() {

        final String countQ = "SELECT * FROM " + User.TABLE;
        final SQLiteDatabase adb = dbHelper.getReadableDatabase();
        final Cursor acursor = adb.rawQuery(countQ, null);
        final int cnt = acursor.getCount();
        acursor.close();
        adb.close();


        final SQLiteDatabase db = dbHelper.getReadableDatabase();
        final String selectQuery = "SELECT * FROM " + User.TABLE;
        final User[] users = new User[cnt];


        final Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            int i = 0;
            do {
                final String username = cursor.getString(cursor.getColumnIndex(User.KEY_USERNAME));
                final String password = cursor.getString(cursor.getColumnIndex(User.KEY_PASSWORD));
                final String firstName = cursor.getString(cursor.getColumnIndex(User.KEY_FIRSTNAME));
                final String lastName = cursor.getString(cursor.getColumnIndex(User.KEY_LASTNAME));
                final String email = cursor.getString(cursor.getColumnIndex(User.KEY_EMAIL));
                final String major = cursor.getString(cursor.getColumnIndex(User.KEY_MAJOR));
                final String phone = cursor.getString(cursor.getColumnIndex(User.KEY_PHONE));
                final String interests = cursor.getString(cursor.getColumnIndex(User.KEY_INTERESTS));
                final int isLock = cursor.getInt(cursor.getColumnIndex(User.KEY_ISLOCKED));
                final int isBanned = cursor.getInt(cursor.getColumnIndex(User.KEY_ISBANNED));
                final int isAdmin = cursor.getInt(cursor.getColumnIndex(User.KEY_ISADMIN));

                final User user = new User(username, password, firstName, lastName, email,
                        major, phone, interests, isLock, isBanned, isAdmin); // may need new data struct to store total rating
                Log.d("CCC", "DID STUFF WITH USER ARRAY");
                users[i++] = user;
            } while (cursor.moveToNext() && i < users.length);
        }

//        for (int i = 0; i < users.length; i++) {
//            User t = users[i];
//            Log.d("AAA", "has a user");
//
//            if (t != null) {
//                Log.d("BBB", "username: " + t.getUsername());
//            }
//        }
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
        final SQLiteDatabase db = dbHelper.getReadableDatabase();
        final String selectQuery = "SELECT * FROM " + User.TABLE
                + " WHERE " +
                User.KEY_USERNAME + " =?";

        final User user = new User();

        final Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(username)});
        if (cursor.moveToFirst()) {
            do {
                user.setUsername(cursor.getString(cursor.getColumnIndex(User.KEY_USERNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(User.KEY_PASSWORD)));
                user.setFirstName(cursor.getString(cursor.getColumnIndex(User.KEY_FIRSTNAME)));
                user.setLastName(cursor.getString(cursor.getColumnIndex(User.KEY_LASTNAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(User.KEY_EMAIL)));
                user.setMajor(cursor.getString(cursor.getColumnIndex(User.KEY_MAJOR)));
                user.setPhone(cursor.getString(cursor.getColumnIndex(User.KEY_PHONE)));
                user.setInterests(cursor.getString(cursor.getColumnIndex(User.KEY_INTERESTS)));
                user.setIsLock(cursor.getInt(cursor.getColumnIndex(User.KEY_ISLOCKED)));
                user.setIsBanned(cursor.getInt(cursor.getColumnIndex(User.KEY_ISBANNED)));
                user.setIsAdmin(cursor.getInt(cursor.getColumnIndex(User.KEY_ISADMIN)));


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

//    /**
//     * setter for user
//     * @param user name of user
//     * @param newUser new username
//     */
//    public void setUsername(String user, String newUser) {
//
//        // open connection to write data
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(User.KEY_username, newUser);
//
//
//        String where = User.KEY_username + " = ?";
//        String[] whereArgs = {user};
//
//        // update
//        db.update(User.TABLE, values, where, whereArgs);
//        db.close();
//    }

    /**
     * setter for first name
     * @param user name of user
     * @param name new first name
     */
    private void setFirstName(String user, String name) {

        // open connection to write data
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        final ContentValues values = new ContentValues();
        values.put(User.KEY_FIRSTNAME, name);

        final String where = User.KEY_USERNAME + QUESTIONMARK;
        final String[] whereArgs = {user};

        // update
        db.update(User.TABLE, values, where, whereArgs);

        db.close();
    }

    /**
     * setter for last name
     * @param user name of user
     * @param name new last name
     */
    private void setLastName(String user, String name) {

        // open connection to write data
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        final ContentValues values = new ContentValues();
        values.put(User.KEY_LASTNAME, name);

        final String where = User.KEY_USERNAME + QUESTIONMARK;
        final String[] whereArgs = {user};

        // update
        db.update(User.TABLE, values, where, whereArgs);

        db.close();
    }

    /**
     * setter for passsword
     * @param user name of user
     * @param pass new password
     */
    private void setPass(String user, String pass) {

        // open connection to write data
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        final ContentValues values = new ContentValues();
        values.put(User.KEY_PASSWORD, pass);

        final String where = User.KEY_USERNAME + QUESTIONMARK;
        final String[] whereArgs = {user};

        // update
        db.update(User.TABLE, values, where, whereArgs);

        db.close();
    }

    /**
     * setter for email name
     * @param user name of user
     * @param email new email
     */
    private void setEmail(String user, String email) {

        // open connection to write data
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        final ContentValues values = new ContentValues();
        values.put(User.KEY_EMAIL, email);

        final String where = User.KEY_USERNAME + QUESTIONMARK;
        final String[] whereArgs = {user};

        // update
        db.update(User.TABLE, values, where, whereArgs);

        db.close();
    }

    /**
     * setter for phone name
     * @param user name of user
     * @param phone new number
     */
    private void setPhone(String user, String phone) {

        // open connection to write data
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        final ContentValues values = new ContentValues();
        values.put(User.KEY_PHONE, phone);

        final String where = User.KEY_USERNAME + QUESTIONMARK;
        final String[] whereArgs = {user};

        // update
        db.update(User.TABLE, values, where, whereArgs);

        db.close();
    }

    /**
     * setter for major
     * @param user name of user
     * @param major new major
     */
    private void setMajor(String user, String major) {

        // open connection to write data
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        final ContentValues values = new ContentValues();
        values.put(User.KEY_MAJOR, major);

        final String where = User.KEY_USERNAME + QUESTIONMARK;
        final String[] whereArgs = {user};

        // update
        db.update(User.TABLE, values, where, whereArgs);

        db.close();
    }

    /**
     * setter for interests
     * @param user name of user
     * @param interests new interests
     */
    private void setInterests(String user, String interests) {

        // open connection to write data
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        final ContentValues values = new ContentValues();
        values.put(User.KEY_INTERESTS, interests);

        final String where = User.KEY_USERNAME + QUESTIONMARK;
        final String[] whereArgs = {user};

        // update
        db.update(User.TABLE, values, where, whereArgs);

        db.close();
    }

    /**
     * setter for isLocked
     * @param user name of user
     * @param isLock value of user
     */
    public void setLock(String user, int isLock) {

        // open connection to write data
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        final ContentValues values = new ContentValues();
        values.put(User.KEY_ISLOCKED, isLock);

        final String where = User.KEY_USERNAME + QUESTIONMARK;
        final String[] whereArgs = {user};

        // update
        db.update(User.TABLE, values, where, whereArgs);

        db.close();
    }

    /**
     * setter for isBanned
     * @param user name of user
     * @param isBanned value of user
     */
    public void setBanned(String user, int isBanned) {

        // open connection to write data
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        final ContentValues values = new ContentValues();
        values.put(User.KEY_ISBANNED, isBanned);

        final String where = User.KEY_USERNAME + QUESTIONMARK;
        final String[] whereArgs = {user};

        // update
        db.update(User.TABLE, values, where, whereArgs);

        db.close();
    }
}
