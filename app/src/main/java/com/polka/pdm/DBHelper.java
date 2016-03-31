package com.polka.pdm;
/**
 * DBHelper to create the database and the table
 * @author Christine Shih 2/12/2016
 * @version 1.0
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user.db";

    /**
     * constructor for DBHelper
     * @param context of the app
     */
    public DBHelper(Context context){
            super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creates user table
        String CREATE_TABLE_USER = "CREATE TABLE " + User.TABLE + " ("
                + User.KEY_username + " TEXT PRIMARY KEY, "
                + User.KEY_password + " TEXT, "
                + User.KEY_firstName + " TEXT, "
                + User.KEY_lastName + " TEXT, "
                + User.KEY_email + " TEXT, "
                + User.KEY_major + " TEXT, "
                + User.KEY_phone + " TEXT, "
                + User.KEY_interests + " TEXT, "
                + User.KEY_isLocked + " INTEGER, "
                + User.KEY_isBanned + " INTEGER, "
                + User.KEY_isAdmin + " INTEGER)";
        db.execSQL(CREATE_TABLE_USER);
        String INSERT_ADMIN = "INSERT INTO " + User.TABLE
                + " (" + User.KEY_username + ", " + User.KEY_password + ", " + User.KEY_firstName + ", "
                + User.KEY_lastName + ", " + User.KEY_isAdmin + ") VALUES ("
                + "'admin', 'pass', 'polka', 'dots', 1);";
        db.execSQL(INSERT_ADMIN);

        String CREATE_TABLE_RATING = "CREATE TABLE " + Review.TABLE + " ("
                + Review.KEY_movie + " TEXT, "
                + Review.KEY_movieYear + " INTEGER, "
                + Review.KEY_user + " TEXT, "
                + Review.KEY_major + " TEXT, "
                + Review.KEY_rating + " DOUBLE, "
                + Review.KEY_comment + " TEXT, "
                + "PRIMARY KEY (" + Review.KEY_user + ", " + Review.KEY_movie + ", " + Review.KEY_movieYear + "))";
        db.execSQL(CREATE_TABLE_RATING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {}
}
