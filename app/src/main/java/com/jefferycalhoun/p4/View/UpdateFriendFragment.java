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
        View view = inflater.inflate(R.layout.update_friend_data, container, false);

        Bundle args = getArguments();

        if(args != null && args.containsKey("friend")){
            friend = (Friend) args.getSerializable("friend");
        }

        deleteFriendButton = (Button) view.findViewById(R.id.delete_friend_button);
        updateFriendButton = (Button) view.findViewById(R.id.update_friend_button);
        firstNameEditText = (EditText) view.findViewById(R.id.friend_first_name_textfield);
        lastNameEditText = (EditText) view.findViewById(R.id.friend_last_name_textfield);
        emailEditText = (EditText) view.findViewById(R.id.friend_email_textfield);

        if(friend != null){
            firstNameEditText.setText(friend.getFirstName());
            lastNameEditText.setText(friend.getLastName());
            emailEditText.setText(friend.getEmailAddress());
        }

       deleteFriendButton.setOnClickListener(new DeleteFriendClickListener());
        updateFriendButton.setOnClickListener(new UpdateFriendClickListener());



        return view;
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

            friend = new Friend(firstName, lastName, email);

            mListener.deleteFriendInitiated(friend);
        }
    }

    private class UpdateFriendClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String firstName = firstNameEditText.getText().toString();
            String lastName = lastNameEditText.getText().toString();
            String email = emailEditText.getText().toString();

            friend = new Friend(firstName, lastName, email);

            mListener.updateFriendInitiated(friend);
        }
    }
}
