package com.jefferycalhoun.p4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jefferycalhoun.p4.Model.FriendContract;

/**
 * Created by Jeff on 3/15/17.
 */

public class FriendDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "friends.db";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + FriendContract.TABLE_NAME + " (" +
            FriendContract.FriendEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FriendContract.FriendEntry.COLUMN_NAME_FIRSTNAME + " TEXT," +
            FriendContract.FriendEntry.COLUMN_NAME_LASTNAME + " TEXT," +
            FriendContract.FriendEntry.COLUMN_NAME_EMAIL + " TEXT)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FriendContract.TABLE_NAME;

    public FriendDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
