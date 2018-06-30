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

import java.util.Calendar;
import java.util.List;

public class TimelineRecyclerViewAdapter extends RecyclerView.Adapter<TimelineRecyclerViewAdapter.ViewHolder> {
  private Context context;
  private List<String> data;

  public TimelineRecyclerViewAdapter(Context applicationContext, List<String> data) {
    this.context = applicationContext;
    this.data = data;
  }

  @NonNull
  @Override
  public TimelineRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.chat_entry_base, parent, false);

    return new TimelineRecyclerViewAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    String str = this.data.get(position);
    System.out.println("131313 " + str);
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView textView_contents;
    TextView textView_isRead;
    TextView textView_time;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      textView_contents = itemView.findViewById(R.id.textView_contents);
      textView_isRead = itemView.findViewById(R.id.textView_isRead);
      textView_time = itemView.findViewById(R.id.textView_time);
    }
  }
}
