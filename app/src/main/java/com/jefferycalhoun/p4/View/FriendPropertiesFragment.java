package com.jefferycalhoun.p4.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jefferycalhoun.p4.Model.Friend;
import com.jefferycalhoun.p4.R;

/**
 * Created by Jeff on 3/15/17.
 */

public class FriendPropertiesFragment extends Fragment {

    private Friend friend;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friend_properties_edit, container, false);

        friend = (Friend) getArguments().getSerializable("friend");

        firstNameEditText = (EditText) view.findViewById(R.id.friend_first_name_textfield);
        lastNameEditText = (EditText) view.findViewById(R.id.friend_last_name_textfield);
        emailEditText = (EditText) view.findViewById(R.id.friend_email_textfield);

        if(friend != null){
            firstNameEditText.setText(friend.getFirstName());
            lastNameEditText.setText(friend.getLastName());
            emailEditText.setText(friend.getEmailAddress());
        }

        return view;
    }
}
