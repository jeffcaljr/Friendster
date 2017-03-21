package com.jefferycalhoun.p4.Controller;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.jefferycalhoun.p4.Database.FriendPersistence;
import com.jefferycalhoun.p4.Model.Friend;
import com.jefferycalhoun.p4.R;
import com.jefferycalhoun.p4.View.AddFriendFragment;

public class AddFriendActivity extends AppCompatActivity implements AddFriendFragment.AddFriendFragmentListener {

    private static final String TAG_ADD_FRIEND_FRAGMENT = "TAG_ADD_FRIEND_FRAGMENT";

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Create Friend Record");
        }

        manager = getSupportFragmentManager();

        AddFriendFragment addFriendFragment = (AddFriendFragment) manager.findFragmentByTag(TAG_ADD_FRIEND_FRAGMENT);

        if(addFriendFragment == null){
            addFriendFragment = new AddFriendFragment();
            manager.beginTransaction()
                    .replace(R.id.add_friend_fragment_container, addFriendFragment, TAG_ADD_FRIEND_FRAGMENT)
//                    .add(R.id.add_friend_fragment_container, addFriendFragment, TAG_ADD_FRIEND_FRAGMENT)
                    .commit();
        }


    }

    @Override
    public void saveFriendButtonClicked(Friend friend) {
        FriendPersistence.sharedInstance(this).addFriend(friend);
        finish();

    }
}
