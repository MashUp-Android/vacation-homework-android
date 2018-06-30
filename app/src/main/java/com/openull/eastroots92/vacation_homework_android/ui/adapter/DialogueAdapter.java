package com.openull.eastroots92.vacation_homework_android.ui.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openull.eastroots92.vacation_homework_android.R;
import com.openull.eastroots92.vacation_homework_android.models.ChatBalloon;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DialogueAdapter extends RecyclerView.Adapter<DialogueAdapter.DialogueHolder> {
  private Context context;
  private List<ChatBalloon> data;

  public DialogueAdapter(Context applicationContext, List<ChatBalloon> chatBalloons) {
    this.context = applicationContext;
    this.data = chatBalloons;
  }

  @NonNull
  @Override
  public DialogueAdapter.DialogueHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.chat_entry_base, parent, false);

    return new DialogueHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull DialogueHolder holder, int position) {
    ChatBalloon chatBalloon = this.data.get(position);

    holder.textView_contents.setText(chatBalloon.getSpeech());
    holder.textView_isRead.setText("isRead" + position);
    holder.textView_time.setText(
      "time (hh:mm)"
      + chatBalloon.getCalendar().get(Calendar.HOUR_OF_DAY)
      + " "
      + chatBalloon.getCalendar().get(Calendar.MINUTE)
    );
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  public class DialogueHolder extends RecyclerView.ViewHolder {
    TextView textView_contents;
    TextView textView_isRead;
    TextView textView_time;

    public DialogueHolder(@NonNull View itemView) {
      super(itemView);

      textView_contents = itemView.findViewById(R.id.textView_contents);
      textView_isRead = itemView.findViewById(R.id.textView_isRead);
      textView_time = itemView.findViewById(R.id.textView_time);
    }
  }
}
