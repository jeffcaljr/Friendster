package com.jefferycalhoun.p4.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.jefferycalhoun.p4.Model.Friend;
import com.jefferycalhoun.p4.Model.FriendContract;

/**
 * Created by Jeff on 3/17/17.
 */

public class FriendCursorWrapper extends CursorWrapper {

    public FriendCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Friend getFriend(){
        int id = getInt(getColumnIndex(FriendContract.FriendEntry._ID));
        String firstName = getString(getColumnIndex(FriendContract.FriendEntry.COLUMN_NAME_FIRSTNAME));
        String lastName = getString(getColumnIndex(FriendContract.FriendEntry.COLUMN_NAME_LASTNAME));
        String email = getString(getColumnIndex(FriendContract.FriendEntry.COLUMN_NAME_EMAIL));
        Friend friend = new Friend(id, firstName, lastName, email);
        return friend;
    }
}
