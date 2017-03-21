package com.jefferycalhoun.p4;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jefferycalhoun.p4.Database.FriendPersistence;
import com.jefferycalhoun.p4.Model.Friend;
import com.jefferycalhoun.p4.View.UpdateFriendFragment;

public class UpdateFriendActivity extends AppCompatActivity implements UpdateFriendFragment.UpdateFriendFragmentListener {

    private Friend friend;

    private FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_friend);

        Bundle extras = getIntent().getExtras();
        if(extras != null && extras.containsKey("friend")){
            friend = (Friend) extras.getSerializable("friend");
        }
        else{
            Log.e("UpdateFriendActivity", "UpdateFriendActivity expects to be passed a Friend object labeled 'friend'");
            finish();
        }



        manager = getSupportFragmentManager();

        UpdateFriendFragment updateFriendFragment = new UpdateFriendFragment();

        Bundle args = new Bundle();
        args.putSerializable("friend", friend);
        updateFriendFragment.setArguments(args);

        manager.beginTransaction()
                .add(R.id.update_friend_fragment_container, updateFriendFragment)
                .commit();
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
