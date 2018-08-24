package com.chatty.android.chattyClient.view.main;

import android.view.View;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.Props;
import com.chatty.android.chattyClient.model.TimelineEntry;

import java.util.ArrayList;

public class MainActivityProps extends Props {
  public View.OnClickListener handleClickWriteButton;
  public ArrayList<TimelineEntry> timeline;
}
