package com.chatty.android.chattyClient.presenter.friendsList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class FriendsListRecyclerViewAdapter extends RecyclerView.Adapter<FriendsListRecyclerViewAdapter.ViewHolder> {


  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return null;
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return 0;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(@NonNull View itemView) {
      super(itemView);
    }
  }
}
