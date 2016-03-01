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
                + User.KEY_phone + " TEXT, " //TODO: Text or int?
                + User.KEY_interests + " TEXT, "
                + User.KEY_isLocked + " INTEGER, "
                + User.KEY_isBanned + " INTEGER, "
                + User.KEY_isAdmin + " INTEGER)";
        db.execSQL(CREATE_TABLE_USER);

        // creates rating table
        String CREATE_TABLE_RATING = "CREATE TABLE " + Rating.TABLE + " ("
                + Rating.KEY_ratingId + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Rating.KEY_movie + " TEXT, "
                + Rating.KEY_movieYear + " INTEGER, "
                + Rating.KEY_user + " TEXT, "
                + Rating.KEY_rating + " DOUBLE, "
                + Rating.KEY_comment + " TEXT)";
        db.execSQL(CREATE_TABLE_RATING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {}
}
