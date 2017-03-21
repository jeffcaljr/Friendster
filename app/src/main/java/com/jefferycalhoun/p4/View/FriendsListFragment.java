package com.jefferycalhoun.p4.View;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jefferycalhoun.p4.Database.FriendPersistence;
import com.jefferycalhoun.p4.Model.Friend;
import com.jefferycalhoun.p4.R;

import java.util.ArrayList;

/**
 * Created by Jeff on 3/15/17.
 */

public class FriendsListFragment extends Fragment {

    ArrayList<Friend> friends;

    private Button addFriendButton;
    private RecyclerView recyclerView;
    private FriendAdapter recyclerViewAdapter;

    private FriendsListFragmentListener mListener;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friend_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.friends_recycler_view);
        recyclerViewAdapter = new FriendAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);


        addFriendButton = (Button) view.findViewById(R.id.add_friend_button);
        addFriendButton.setOnClickListener(new AddFriendButtonClickListener());



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getContext().getApplicationContext(), "Friendslist fragment resumed", Toast.LENGTH_SHORT).show();

        updateFriendsList();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener = (FriendsListFragmentListener) context;
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

    public interface FriendsListFragmentListener{
        void addFriendButtonPressed();
        void friendItemClicked(Friend friend);
    }

    public void updateFriendsList(){
        friends = FriendPersistence.sharedInstance(getContext()).queryFriends(null, null);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private class FriendAdapter extends RecyclerView.Adapter<FriendHolder>{
        @Override
        public FriendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.friend_list_item, parent, false);


            return new FriendHolder(view);
        }

        @Override
        public void onBindViewHolder(FriendHolder holder, int position) {
            holder.bindFriend(friends.get(position));

        }

        @Override
        public int getItemCount() {
            return friends.size();
        }
    }

    private class FriendHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            FriendsListFragment.this.mListener.friendItemClicked(friends.get(getAdapterPosition()));
        }
    }


    private class AddFriendButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            mListener.addFriendButtonPressed();

        }
    }


}
