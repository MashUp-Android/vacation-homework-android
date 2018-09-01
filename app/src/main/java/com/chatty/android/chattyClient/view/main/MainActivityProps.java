package com.chatty.android.chattyClient.view.main;

import android.view.View;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.Props;
import com.chatty.android.chattyClient.model.TimelineEntry;
import com.chatty.android.chattyClient.presenter.main.TimelineRecyclerViewAdapter;

import java.util.List;

public class MainActivityProps extends Props {
  public TimelineRecyclerViewAdapter.RecyclerViewClickListener handleClickTimelineEntry;
  public View.OnClickListener handleClickWriteButton;
  public List<TimelineEntry> timeline;
}
