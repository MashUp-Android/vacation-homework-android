package com.chatty.android.chattyClient.presenter.write;


import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.externalModules.ReduxJava.ReduxJava;
import com.chatty.android.chattyClient.model.ChatBalloon;
import com.chatty.android.chattyClient.view.write.WriteActivity;

import java.util.ArrayList;
import java.util.List;

public class ChatDialogueAdapter extends RecyclerView.Adapter {
  private List<ChatBalloon> chatBalloons;
  Context context;

  public ChatDialogueAdapter(List<ChatBalloon> chatBalloons) {
    this.chatBalloons = chatBalloons;
  }

  public void update(ArrayList<ChatBalloon> chatBalloons,Context context) {
    this.chatBalloons.clear();
    this.chatBalloons.addAll(chatBalloons);
    this.context = context;
    this.notifyDataSetChanged();
  }

  public class ChatEntryPartnerViewHolder extends RecyclerView.ViewHolder {
    TextView textView_contents;
    TextView textView_isRead;

    ChatEntryPartnerViewHolder (View itemView) {
      super(itemView);
      this.textView_contents = itemView.findViewById(R.id.textView_contents);
      this.textView_isRead = itemView.findViewById(R.id.textView_isRead);
    }
  }

  public static class ChatEntrySelfViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    ImageView imageView;

    ChatEntrySelfViewHolder(View itemView) {
      super(itemView);
      this.textView = itemView.findViewById(R.id.textView_self);
      this.imageView = itemView.findViewById(R.id.imageView_send_image);
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
          .inflate(R.layout.item_chat_entry_partner, parent, false);
        return new ChatEntryPartnerViewHolder(view);
      case 2:
        view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_chat_entry_self, parent, false);
        return new ChatEntrySelfViewHolder(view);
    }
    return null;
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    ChatBalloon chatBalloon = this.chatBalloons.get(position);

    if (chatBalloon != null) {
      switch (holder.getItemViewType()) {
        case 0:
          ((ChatEntryPartnerViewHolder) holder).textView_contents.setText(chatBalloon.speech);
          ((ChatEntryPartnerViewHolder) holder).textView_isRead.setText(chatBalloon.username);
          break;
        case 2:
            Log.e("내 말", String.valueOf(chatBalloon.selectImage+"0"));
          if (chatBalloon.selectImage != null) {
            ImageView imageView = ((ChatEntrySelfViewHolder) holder).imageView;
            imageView.setVisibility(View.VISIBLE);
            Glide.with(this.context)
              .load(chatBalloon.selectImage)
              .into(imageView);
          }
          ((ChatEntrySelfViewHolder) holder).textView.setText(chatBalloon.speech);
          break;
      }
    }
  }
  @Override
  public int getItemCount() {
    return this.chatBalloons.size();
  }
}
