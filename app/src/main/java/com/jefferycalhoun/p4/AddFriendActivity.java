package com.jefferycalhoun.p4;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.jefferycalhoun.p4.Database.FriendPersistence;
import com.jefferycalhoun.p4.Model.Friend;
import com.jefferycalhoun.p4.View.AddFriendFragment;

public class AddFriendActivity extends AppCompatActivity implements AddFriendFragment.AddFriendFragmentListener {

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);



        manager = getSupportFragmentManager();

        AddFriendFragment addFriendFragment = new AddFriendFragment();

        manager.beginTransaction()
                .add(R.id.add_friend_fragment_container, addFriendFragment)
                .commit();

    }

    @Override
    public void saveFriendButtonClicked(Friend friend) {
        FriendPersistence.sharedInstance(this).addFriend(friend);
        finish();

    }
}
