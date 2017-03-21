package com.jefferycalhoun.p4.Controller;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jefferycalhoun.p4.Database.FriendPersistence;
import com.jefferycalhoun.p4.Model.Friend;
import com.jefferycalhoun.p4.R;
import com.jefferycalhoun.p4.View.UpdateFriendFragment;

public class UpdateFriendActivity extends AppCompatActivity implements UpdateFriendFragment.UpdateFriendFragmentListener {

    private static final String TAG_UPDATE_FRIEND_FRAGMENT = "TAG_UPDATE_FRIEND_FRAGMENT";

    private Friend friend;

    private FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_friend);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Details");
        }

        Bundle extras = getIntent().getExtras();
        if(extras != null && extras.containsKey("friend")){
            friend = (Friend) extras.getSerializable("friend");
        }
        else{
            Log.e("UpdateFriendActivity", "UpdateFriendActivity expects to be passed a Friend object labeled 'friend'");
            finish();
        }



        manager = getSupportFragmentManager();

        UpdateFriendFragment updateFriendFragment = (UpdateFriendFragment) manager.findFragmentByTag(TAG_UPDATE_FRIEND_FRAGMENT);

        if(updateFriendFragment == null){
            updateFriendFragment = new UpdateFriendFragment();
            Bundle args = new Bundle();
            args.putSerializable("friend", friend);
            updateFriendFragment.setArguments(args);

            manager.beginTransaction()
                    .replace(R.id.update_friend_fragment_container, updateFriendFragment, TAG_UPDATE_FRIEND_FRAGMENT)
//                .add(R.id.update_friend_fragment_container, updateFriendFragment, TAG_UPDATE_FRIEND_FRAGMENT)
                    .commit();
        }
    }

    @Override
    public void deleteFriendInitiated(Friend friend) {
        FriendPersistence.sharedInstance(this).deleteFriend(friend);
        finish();
    }

    @Override
    public void updateFriendInitiated(Friend friend) {
        FriendPersistence.sharedInstance(this).updateFriend(friend);
        finish();
    }
}
