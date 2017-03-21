package com.jefferycalhoun.p4.View;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jefferycalhoun.p4.Model.Friend;
import com.jefferycalhoun.p4.R;

/**
 * Created by Jeff on 3/20/17.
 */

public class UpdateFriendFragment extends Fragment {

    private static final String EXTRA_FIRST_NAME = "FIRST_NAME";
    private static final String EXTRA_LAST_NAME = "FLAST_NAME";
    private static final String EXTRA_EMAIL = "EMAIL";

    private Friend friend;

    private Button updateFriendButton;
    private Button deleteFriendButton;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;

    private UpdateFriendFragmentListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_friend, container, false);


        deleteFriendButton = (Button) view.findViewById(R.id.delete_friend_button);
        updateFriendButton = (Button) view.findViewById(R.id.update_friend_button);
        firstNameEditText = (EditText) view.findViewById(R.id.friend_first_name_textfield);
        lastNameEditText = (EditText) view.findViewById(R.id.friend_last_name_textfield);
        emailEditText = (EditText) view.findViewById(R.id.friend_email_textfield);


        if(savedInstanceState != null){
            if(savedInstanceState.containsKey(EXTRA_FIRST_NAME)){
                firstNameEditText.setText(savedInstanceState.getString(EXTRA_FIRST_NAME));
            }
            if(savedInstanceState.containsKey(EXTRA_LAST_NAME)){
                lastNameEditText.setText(savedInstanceState.getString(EXTRA_LAST_NAME));
            }
            if(savedInstanceState.containsKey(EXTRA_EMAIL)){
                emailEditText.setText(savedInstanceState.getString(EXTRA_EMAIL));
            }
        }
        else{
            Bundle args = getArguments();

            if(args != null && args.containsKey("friend")){
                friend = (Friend) args.getSerializable("friend");
            }

            if(friend != null){
                firstNameEditText.setText(friend.getFirstName());
                lastNameEditText.setText(friend.getLastName());
                emailEditText.setText(friend.getEmailAddress());
            }
        }

       deleteFriendButton.setOnClickListener(new DeleteFriendClickListener());
        updateFriendButton.setOnClickListener(new UpdateFriendClickListener());



        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String fName = firstNameEditText.getText().toString();
        String lName = lastNameEditText.getText().toString();
        String email = emailEditText.getText().toString();

        outState.putString(EXTRA_FIRST_NAME, fName);
        outState.putString(EXTRA_LAST_NAME, lName);
        outState.putString(EXTRA_EMAIL, email);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener = (UpdateFriendFragmentListener) context;
        }
        catch(ClassCastException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface UpdateFriendFragmentListener{
        void deleteFriendInitiated(Friend friend);
        void updateFriendInitiated(Friend friend);
    }


    private class DeleteFriendClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String firstName = firstNameEditText.getText().toString();
            String lastName = lastNameEditText.getText().toString();
            String email = emailEditText.getText().toString();

            friend.setFirstName(firstName);
            friend.setLastName(lastName);
            friend.setEmailAddress(email);

            mListener.deleteFriendInitiated(friend);
        }
    }


    private class UpdateFriendClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String firstName = firstNameEditText.getText().toString();
            String lastName = lastNameEditText.getText().toString();
            String email = emailEditText.getText().toString();

            friend.setFirstName(firstName);
            friend.setLastName(lastName);
            friend.setEmailAddress(email);

            mListener.updateFriendInitiated(friend);
        }
    }
}
