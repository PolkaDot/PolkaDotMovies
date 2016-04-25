package com.polka.pdm;
/**
 * DBHelper to create the database and the table
 * @author Christine Shih 2/12/2016
 * @version 1.0
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper extends SQLiteOpenHelper {
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
                + User.KEY_USERNAME + " TEXT PRIMARY KEY, "
                + User.KEY_PASSWORD + TEXT
                + User.KEY_FIRSTNAME + TEXT
                + User.KEY_LASTNAME + TEXT
                + User.KEY_EMAIL + TEXT
                + User.KEY_MAJOR + TEXT
                + User.KEY_PHONE + TEXT
                + User.KEY_INTERESTS + TEXT
                + User.KEY_ISLOCKED + " INTEGER, "
                + User.KEY_ISBANNED + " INTEGER, "
                + User.KEY_ISADMIN + " INTEGER)";
        db.execSQL(createTableUser);
        final String insertAdmin = "INSERT INTO " + User.TABLE
                + " (" + User.KEY_USERNAME + COMMA + User.KEY_PASSWORD + COMMA + User.KEY_FIRSTNAME + COMMA
                + User.KEY_LASTNAME + COMMA + User.KEY_ISADMIN + ") VALUES ("
                + "'admin', 'pass', 'polka', 'dots', 1);";
        db.execSQL(insertAdmin);

        // creates rating table

        final String createRatingTable = "CREATE TABLE " + Review.TABLE + " ("
                + Review.KEY_MOVIE + TEXT
                + Review.KEY_MOVIEYEAR + " INTEGER, "
                + Review.KEY_USER + TEXT
                + Review.KEY_MAJOR + TEXT
                + Review.KEY_RATING + " DOUBLE, "
                + Review.KEY_COMMENT + TEXT
                + "PRIMARY KEY (" + Review.KEY_USER + COMMA + Review.KEY_MOVIE + COMMA + Review.KEY_MOVIEYEAR + "))";
        db.execSQL(createRatingTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {}
}
