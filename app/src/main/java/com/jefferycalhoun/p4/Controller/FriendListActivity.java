package com.jefferycalhoun.p4.Controller;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jefferycalhoun.p4.Model.Friend;
import com.jefferycalhoun.p4.R;
import com.jefferycalhoun.p4.View.FriendsListFragment;

public class FriendListActivity extends AppCompatActivity implements FriendsListFragment.FriendsListFragmentListener {

    public static final int ADD_FRIEND_REQUEST_CODE = 101;
    public static final String TAG_FRIEND_LIST_FRAGMENT = "TAG_FRIEND_LIST_FRAGMENT";

    private FragmentManager manager;
    FriendsListFragment friendsListFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Friends List");
        }

        manager = getSupportFragmentManager();

        friendsListFragment = (FriendsListFragment) manager.findFragmentByTag(TAG_FRIEND_LIST_FRAGMENT);

        if(friendsListFragment == null){
            friendsListFragment = new FriendsListFragment();
            manager.beginTransaction()
                    .replace(R.id.main_container, friendsListFragment, TAG_FRIEND_LIST_FRAGMENT)
//                    .add(R.id.main_container, friendsListFragment, TAG_FRIEND_LIST_FRAGMENT)
                    .commit();
        }


    }


    @Override
    public void addFriendButtonPressed() {
        Intent intent = new Intent(FriendListActivity.this, AddFriendActivity.class);
        startActivityForResult(intent, ADD_FRIEND_REQUEST_CODE);
    }

    @Override
    public void friendItemClicked(Friend friend) {

        Intent intent = new Intent(this, UpdateFriendActivity.class);
        intent.putExtra("friend", friend);
        startActivity(intent);
    }
}
