package com.openull.eastroots92.vacation_homework_android.ui.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openull.eastroots92.vacation_homework_android.R;

public class DialogueAdapter extends RecyclerView.Adapter<DialogueAdapter.DialogueHolder> {
  @NonNull
  @Override
  public DialogueAdapter.DialogueHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.chat_entry_base, parent, false);

    return new DialogueHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull DialogueHolder holder, int position) {
    System.out.println("123123 " + holder);
    holder.textView_contents.setText("contents" + position);
    holder.textView_isRead.setText("isRead" + position);
    holder.textView_time.setText("time" + position);
  }

  @Override
  public int getItemCount() {
    return 10;
  }

  public class DialogueHolder extends RecyclerView.ViewHolder {
    TextView textView_contents;
    TextView textView_isRead;
    TextView textView_time;

    public DialogueHolder(@NonNull View itemView) {
      super(itemView);
      System.out.println("234");

      textView_contents = itemView.findViewById(R.id.textView_contents);
      textView_isRead = itemView.findViewById(R.id.textView_isRead);
      textView_time = itemView.findViewById(R.id.textView_time);
    }
  }
}
