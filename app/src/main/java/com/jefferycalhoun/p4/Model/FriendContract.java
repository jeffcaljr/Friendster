package com.jefferycalhoun.p4.Model;

import android.provider.BaseColumns;

/**
 * Created by Jeff on 3/15/17.
 */

public class FriendContract {
    public static final String TABLE_NAME = "friends";
    private FriendContract(){}

    public static class FriendEntry implements BaseColumns{
        public static final String COLUMN_NAME_ID_FRIEND = "friend_id";
        public static final String COLUMN_NAME_FIRSTNAME = "first_name";
        public static final String COLUMN_NAME_LASTNAME = "last_name";
        public static final String COLUMN_NAME_EMAIL = "email";
    }
}
