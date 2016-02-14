package com.polka.pdm;
/**
 * DBHelper to create the database and the table
 *
 * Created by C. Shih on 2/12/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user.db";

    public DBHelper(Context context){
            super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_USER = "CREATE TABLE " + User.TABLE + " ("
                + User.KEY_username + " TEXT PRIMARY KEY, "
                + User.KEY_password + " TEXT, "
                + User.KEY_firstName + " TEXT, "
                + User.KEY_lastName + " TEXT, "
                + User.KEY_email + " TEXT, "
                + User.KEY_phone + " TEXT, "
                + User.KEY_interests + " TEXT, "
                + User.KEY_isLocked + " INTEGER, "
                + User.KEY_isBanned + " INTEGER, "
                + User.KEY_isAdmin + " INTEGER)";
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {}
}
