package com.polka.pdm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * UserRepo is used in tandom with the database
 * inserts user into db and gets user by username
 *
 * Created by C. Shih on 2/12/2016.
 */
public class UserRepo {
    private DBHelper dbHelper;

    public UserRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    /**
     * Inserts new user into the database, only has info for registration
     * everything else is null
     *
     * @param user that it is inserting
     * @return returns row ID of newly inserted row, -1 otherwise
     */
    public long insert(User user) {

        // open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.KEY_username, user.username);
        values.put(User.KEY_password, user.password);
        values.put(User.KEY_firstName, user.firstName);
        values.put(User.KEY_lastName, user.lastName);
        values.put(User.KEY_email, user.email);


        //inserting row
        try {
            long user_username = db.insertOrThrow(User.TABLE, null, values);
            db.close();
            return user_username;
        } catch (SQLException e) {
            return -1;
        }
    }

    /**
     * gets the user object by username from database
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
                user.username = cursor.getString(cursor.getColumnIndex(User.KEY_username));
                user.password = cursor.getString(cursor.getColumnIndex(User.KEY_password));
                user.firstName = cursor.getString(cursor.getColumnIndex(User.KEY_firstName));
                user.lastName = cursor.getString(cursor.getColumnIndex(User.KEY_lastName));
                user.email = cursor.getString(cursor.getColumnIndex(User.KEY_email));
                user.phone = cursor.getString(cursor.getColumnIndex(User.KEY_phone));
                user.interests = cursor.getString(cursor.getColumnIndex(User.KEY_interests));
                user.isLocked = cursor.getInt(cursor.getColumnIndex(User.KEY_isLocked));
                user.isBanned = cursor.getInt(cursor.getColumnIndex(User.KEY_isBanned));
                user.isAdmin = cursor.getInt(cursor.getColumnIndex(User.KEY_isAdmin));


            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return user;
    }


}
