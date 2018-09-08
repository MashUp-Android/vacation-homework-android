package com.chatty.android.chattyClient.presenter.main;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.model.TimelineEntry;
import com.chatty.android.chattyClient.view.diaryDetail.DiaryDetailActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TimelineRecyclerViewAdapter extends RecyclerView.Adapter<TimelineRecyclerViewAdapter.ViewHolder> {
  private final static DateFormat df = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());

  private List<TimelineEntry> timelineEntries;
  private RecyclerViewClickListener recyclerViewClickListener;
  private View view;

  public TimelineRecyclerViewAdapter(
    List<TimelineEntry> timelineEntries,
    RecyclerViewClickListener recyclerViewClickListener
  ) {
    this.timelineEntries = timelineEntries;
    this.recyclerViewClickListener = recyclerViewClickListener;
  }

  public void update(List<TimelineEntry> timelineEntries) {
    this.timelineEntries.clear();
    this.timelineEntries.addAll(timelineEntries);
    this.notifyDataSetChanged();
  }

  public interface RecyclerViewClickListener {
    void onClick(View view, TimelineEntry entry);
  }

  @NonNull
  @Override
  public TimelineRecyclerViewAdapter.ViewHolder onCreateViewHolder(
    @NonNull ViewGroup parent,
    int viewType
  ) {
    this.view = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.item_timeline_entry, parent, false);

    return new TimelineRecyclerViewAdapter.ViewHolder(this.view, this.recyclerViewClickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    TimelineEntry entry = this.timelineEntries.get(position);
    String date = TimelineRecyclerViewAdapter.df.format(entry.getDate());

    holder.contents.setText(entry.getContent());
    holder.date.setText(date);
    holder.entry = entry;

    String thumbnailUrl = entry.imgUrl != null
      ? entry.imgUrl
      : "https://msr7.net/images/flower-google-9.jpg";

    Glide.with(view)
      .load(thumbnailUrl)
      .into(holder.thumbnail);
  }

  @Override
  public int getItemCount() {
    return this.timelineEntries.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TimelineEntry entry;
    TextView contents;
    TextView date;
    ImageView thumbnail;
    RecyclerViewClickListener recyclerViewClickListener;

    public ViewHolder(
      @NonNull View view,
      RecyclerViewClickListener recyclerViewClickListener
    ) {
      super(view);
      this.contents = view.findViewById(R.id.textView_contents);
      this.date = view.findViewById(R.id.textView_timeline_date);
      this.recyclerViewClickListener = recyclerViewClickListener;
      this.thumbnail = view.findViewById(R.id.imageView_timeline);
      view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      this.recyclerViewClickListener.onClick(view, entry);
    }
  }
}
