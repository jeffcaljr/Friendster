package com.jefferycalhoun.p4.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jefferycalhoun.p4.FriendHolder;
import com.jefferycalhoun.p4.Model.Friend;
import com.jefferycalhoun.p4.R;

/**
 * Created by Jeff on 3/15/17.
 */

public class FriendsListFragment extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friend_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.friends_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new FriendAdapter());

        return view;
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

            Friend friend = new Friend(0, "Jeff", "Calhoun", "jeffcaljr@gmail.com");
            holder.bindFriend(friend);

        }

        @Override
        public int getItemCount() {
            return 30;
        }
    }
}
