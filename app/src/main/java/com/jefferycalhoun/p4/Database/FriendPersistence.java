package com.jefferycalhoun.p4.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jefferycalhoun.p4.Model.Friend;
import com.jefferycalhoun.p4.Model.FriendContract;

import java.util.ArrayList;

/**
 * Created by Jeff on 3/17/17.
 */

public class FriendPersistence {

    private Context context;
    private SQLiteDatabase db;

    private static FriendPersistence friendPersistence;

    public static FriendPersistence sharedInstance(Context context){
        if(friendPersistence == null){
            friendPersistence = new FriendPersistence(context);
        }
        return friendPersistence;
    }

    private FriendPersistence(Context context){
        this.context = context.getApplicationContext();
        this.db = new FriendDbHelper(context).getWritableDatabase();
    }

    public ArrayList<Friend> queryFriends(String selectionClause, String[] selectionArgs){
        ArrayList<Friend> friends = new ArrayList<>();

        Cursor cursor = db.query(FriendContract.TABLE_NAME, null, selectionClause, selectionArgs, null, null, null);
        FriendCursorWrapper friendCursorWrapper = new FriendCursorWrapper(cursor);

        try{
            while(friendCursorWrapper.moveToNext()){
                friends.add(friendCursorWrapper.getFriend());
            }
        }
        finally{
            friendCursorWrapper.close();
        }

        return friends;
    }

    public void updateFriend(Friend friend){

        String selectionClause = FriendContract.FriendEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(friend.getId())};
        ContentValues contentValues = getContentValues(friend);
        db.update(FriendContract.TABLE_NAME, contentValues, selectionClause, selectionArgs);
    }

    public void deleteFriend(Friend friend){

        String selectionClause = FriendContract.FriendEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(friend.getId())};

        db.delete(FriendContract.TABLE_NAME, selectionClause, selectionArgs);
    }

    public void clearFriendsTable(){
        db.delete(FriendContract.TABLE_NAME, null, null);
    }


    public void addFriend(Friend friend){
        ContentValues contentValues = getContentValues(friend);
        db.insert(FriendContract.TABLE_NAME, null, contentValues);
    }

    public ContentValues getContentValues(Friend friend){
        ContentValues contentValues = new ContentValues();
        contentValues.put(FriendContract.FriendEntry._ID, friend.getId());
        contentValues.put(FriendContract.FriendEntry.COLUMN_NAME_FIRSTNAME, friend.getFirstName());
        contentValues.put(FriendContract.FriendEntry.COLUMN_NAME_LASTNAME, friend.getLastName());
        contentValues.put(FriendContract.FriendEntry.COLUMN_NAME_EMAIL, friend.getEmailAddress());

        return contentValues;
    }
}
