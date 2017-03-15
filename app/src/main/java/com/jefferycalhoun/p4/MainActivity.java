package com.jefferycalhoun.p4;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jefferycalhoun.p4.View.FriendPropertiesFragment;
import com.jefferycalhoun.p4.View.FriendsListFragment;

public class MainActivity extends AppCompatActivity {

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        FriendsListFragment friendsListFragment = new FriendsListFragment();

        manager.beginTransaction()
                .add(R.id.main_container, friendsListFragment)
                .commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_friend_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FriendPropertiesFragment friendPropertiesFragment = new FriendPropertiesFragment();

                manager.beginTransaction()
                        .replace(R.id.main_container, friendPropertiesFragment)
                        .commit();
            }
        });
    }
}
