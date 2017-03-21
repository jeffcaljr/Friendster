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
 * Created by Jeff on 3/19/17.
 */

public class AddFriendFragment extends Fragment {

    private static final String EXTRA_FIRST_NAME = "FIRST_NAME";
    private static final String EXTRA_LAST_NAME = "FLAST_NAME";
    private static final String EXTRA_EMAIL = "EMAIL";

    private Friend friend;

    private Button saveFriendButton;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;

    private AddFriendFragmentListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.fragment_add_friend, container, false);

        firstNameEditText = (EditText) view.findViewById(R.id.friend_first_name_textfield);
        lastNameEditText = (EditText) view.findViewById(R.id.friend_last_name_textfield);
        emailEditText = (EditText) view.findViewById(R.id.friend_email_textfield);

        if(savedInstanceState != null){
            firstNameEditText.setHint("");
            lastNameEditText.setHint("");
            emailEditText.setHint("");
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

        saveFriendButton = (Button)view.findViewById(R.id.save_friend_button);

        saveFriendButton.setOnClickListener(new SaveFriendClickListener());

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
            mListener = (AddFriendFragmentListener) context;
        }
        catch(ClassCastException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListener = null;
    }

    public interface AddFriendFragmentListener{
        void saveFriendButtonClicked(Friend friend);
    }

    private class SaveFriendClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String firstName = firstNameEditText.getText().toString();
            String lastName = lastNameEditText.getText().toString();
            String email = emailEditText.getText().toString();

            friend = new Friend(firstName, lastName, email);

            mListener.saveFriendButtonClicked(friend);
        }
    }
}
