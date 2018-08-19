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

import java.util.List;

public class TimelineRecyclerViewAdapter extends RecyclerView.Adapter<TimelineRecyclerViewAdapter.ViewHolder> {
  private Context context;
  private List<TimelineEntry> data;

  public TimelineRecyclerViewAdapter(Context applicationContext, List<TimelineEntry> data) {
    this.context = applicationContext;
    this.data = data;
  }

  @NonNull
  @Override
  public TimelineRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
    System.out.println("onBindViewholder " + entry);
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView textView_contents;
    TextView textView_isRead;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      System.out.println("baba");

//      textView_contents = itemView.findViewById(R.id.textView_contents);
//      textView_isRead = itemView.findViewById(R.id.textView_isRead);
    }
  }
}
