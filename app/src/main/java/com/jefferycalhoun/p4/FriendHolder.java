package com.jefferycalhoun.p4;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jefferycalhoun.p4.Model.Friend;

/**
 * Created by Jeff on 3/15/17.
 */

public class FriendHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView firstNameTextView;
    private TextView lastNameTextView;
    private TextView emailTextView;

    public FriendHolder(View itemView) {
        super(itemView);
        firstNameTextView = (TextView) itemView.findViewById(R.id.first_name_textview);
        lastNameTextView = (TextView) itemView.findViewById(R.id.last_name_textview);
        emailTextView = (TextView) itemView.findViewById(R.id.email_textview);
        itemView.setOnClickListener(this);
    }

    public void bindFriend(Friend friend){
        firstNameTextView.setText(friend.getFirstName());
        lastNameTextView.setText(friend.getLastName());
        emailTextView.setText(friend.getEmailAddress());
    }

    @Override
    public void onClick(View view) {
        Log.d("TAG", "Clicked " + this + " at position: " + getAdapterPosition());
    }
}
