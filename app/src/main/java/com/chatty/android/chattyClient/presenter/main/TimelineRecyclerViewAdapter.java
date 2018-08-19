package com.chatty.android.chattyClient.presenter.main;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.model.TimelineEntry;
import com.chatty.android.chattyClient.view.diaryDetail.DiaryDetailActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TimelineRecyclerViewAdapter extends RecyclerView.Adapter<TimelineRecyclerViewAdapter.ViewHolder> {
  private Context context;
  private List<TimelineEntry> data;
  private final static DateFormat df = new SimpleDateFormat("MM/DD/yyyy", Locale.getDefault());

  public TimelineRecyclerViewAdapter(Context applicationContext, List<TimelineEntry> data) {
    this.context = applicationContext;
    this.data = data;
  }

  public void update(List<TimelineEntry> data) {
    this.data.clear();
    this.data.addAll(data);
    this.notifyDataSetChanged();
  }

  @NonNull
  @Override
  public TimelineRecyclerViewAdapter.ViewHolder onCreateViewHolder(
    @NonNull ViewGroup parent,
    int viewType
  ) {
    View view = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.item_timeline_entry, parent, false);

    view.setOnClickListener((__) -> {
      Intent intent = new Intent(this.context, DiaryDetailActivity.class);
      context.startActivity(intent);
    });

    return new TimelineRecyclerViewAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    TimelineEntry entry = this.data.get(position);
    String date = TimelineRecyclerViewAdapter.df.format(entry.getDate());

    holder.contents.setText(entry.getContent());
    holder.date.setText(date);
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView contents;
    TextView date;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      this.contents = itemView.findViewById(R.id.textView_contents);
      this.date = itemView.findViewById(R.id.textView_timeline_date);
    }
  }
}
