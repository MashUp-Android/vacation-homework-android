package com.chatty.android.chattyClient.presenter.write;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.model.ChatBalloon;

import java.util.ArrayList;
import java.util.List;

public class ChatDialogueAdapter extends RecyclerView.Adapter {
  private List<ChatBalloon> chatBalloons;

  public ChatDialogueAdapter(List<ChatBalloon> chatBalloons) {
    this.chatBalloons = chatBalloons;
  }

  public void update(ArrayList<ChatBalloon> chatBalloons) {
    System.out.println("333 " + chatBalloons.size());
    this.chatBalloons.clear();
    this.chatBalloons.addAll(chatBalloons);
    this.notifyDataSetChanged();
  }

  public class EntryBaseViewHolder extends RecyclerView.ViewHolder {
    TextView textView_contents;
    TextView textView_isRead;

    public EntryBaseViewHolder(View itemView) {
      super(itemView);
      this.textView_contents = itemView.findViewById(R.id.textView_contents);
      this.textView_isRead = itemView.findViewById(R.id.textView_isRead);
    }
  }

  public class RequestBaseViewHolder extends RecyclerView.ViewHolder{
    TextView textView;

    public RequestBaseViewHolder(View itemView) {
      super(itemView);
      this.textView= itemView.findViewById(R.id.textView);
    }
  }
  @Override
  public int getItemViewType(int position) {
    return position % 2 * 2;
  }

  @NonNull
  @Override
  public  RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view;
    switch (viewType) {
      case 0:
        view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_chat_entry_base, parent, false);
        return new EntryBaseViewHolder(view);
      case 2:
        view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_chat_request_base, parent, false);
        return new RequestBaseViewHolder(view);
    }
    return null;
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    ChatBalloon chatBalloon = this.chatBalloons.get(position);
    System.out.println("!!!" + chatBalloon);

    if (chatBalloon != null) {
      switch (holder.getItemViewType()) {
        case 0:
          ((EntryBaseViewHolder) holder).textView_contents.setText(chatBalloon.speech);
          ((EntryBaseViewHolder) holder).textView_isRead.setText(chatBalloon.username);
          break;
        case 2:
          ((RequestBaseViewHolder) holder).textView.setText(chatBalloon.speech);
          break;
      }
    }
  }


  @Override
  public int getItemCount() {
    return this.chatBalloons.size();
  }

}
