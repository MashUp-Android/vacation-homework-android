package com.chatty.android.chattyClient.presenter.friendsList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.model.FriendItemEntry;

import java.util.List;

public class FriendsListRecyclerViewAdapter extends RecyclerView.Adapter<FriendsListRecyclerViewAdapter.ViewHolder> {
  private List<FriendItemEntry> friendItemEntries;
  private Context context;

  public FriendsListRecyclerViewAdapter(
    List<FriendItemEntry> friendItemEntries,
    Context context) {
    this.friendItemEntries = friendItemEntries;
    this.context = context;
  }

  @NonNull
  @Override
  public FriendsListRecyclerViewAdapter.ViewHolder onCreateViewHolder(
    @NonNull ViewGroup parent,
    int viewType
  ) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friends_list, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    FriendItemEntry friendItemEntry = this.friendItemEntries.get(position);

    holder.textViewName.setText(friendItemEntry.getName());
    holder.textViewBio.setText(friendItemEntry.getBio());
    holder.textViewDate.setText(friendItemEntry.getCreated_at());

    Glide.with(context)
      .load(friendItemEntry.getProfile_image())
      .into(holder.imageViewProfile);
  }

  @Override
  public int getItemCount() {
    return this.friendItemEntries.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView imageViewProfile;
    TextView textViewName;
    TextView textViewBio;
    TextView textViewDate;

    public ViewHolder(@NonNull View view) {
      super(view);
      this.imageViewProfile = view.findViewById(R.id.imageView_profile_now);
      this.textViewName = view.findViewById(R.id.textView_profile_name_now);
      this.textViewBio = view.findViewById(R.id.textView_profile_bio_now);
      this.textViewDate = view.findViewById(R.id.textView_profile_date_now);
    }
  }
}
