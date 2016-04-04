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
    /**
     * standard database name
     */
    private static final String DATABASE_NAME = "user.db";

    /**
     * test replacement for " TEXT, "
     */
    private static final String TEXT = " TEXT, ";

    /**
     * test replacement for ", "
     */
    private static final String COMMA = ", ";

    /**
     * constructor for DBHelper
     * @param context of the app
     */
    public DBHelper(Context context){
            super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //needed
        // creates user table
        final String createTableUser = "CREATE TABLE " + User.TABLE + " ("
                + User.KEY_username + " TEXT PRIMARY KEY, "
                + User.KEY_password + TEXT
                + User.KEY_firstName + TEXT
                + User.KEY_lastName + TEXT
                + User.KEY_email + TEXT
                + User.KEY_major + TEXT
                + User.KEY_phone + TEXT
                + User.KEY_interests + TEXT
                + User.KEY_isLocked + " INTEGER, "
                + User.KEY_isBanned + " INTEGER, "
                + User.KEY_isAdmin + " INTEGER)";
        db.execSQL(createTableUser);
        final String insertAdmin = "INSERT INTO " + User.TABLE
                + " (" + User.KEY_username + COMMA + User.KEY_password + COMMA + User.KEY_firstName + COMMA
                + User.KEY_lastName + COMMA + User.KEY_isAdmin + ") VALUES ("
                + "'admin', 'pass', 'polka', 'dots', 1);";
        db.execSQL(insertAdmin);

        // creates rating table

        final String createRatingTable = "CREATE TABLE " + Review.TABLE + " ("
                + Review.KEY_movie + TEXT
                + Review.KEY_movieYear + " INTEGER, "
                + Review.KEY_user + TEXT
                + Review.KEY_major + TEXT
                + Review.KEY_rating + " DOUBLE, "
                + Review.KEY_comment + TEXT
                + "PRIMARY KEY (" + Review.KEY_user + COMMA + Review.KEY_movie + COMMA + Review.KEY_movieYear + "))";
        db.execSQL(createRatingTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {}
}
