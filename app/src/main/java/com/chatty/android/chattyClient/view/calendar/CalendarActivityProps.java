package com.chatty.android.chattyClient.view.calendar;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.Props;
import com.chatty.android.chattyClient.model.FriendItemEntry;
import com.chatty.android.chattyClient.model.TimelineEntry;

import java.util.ArrayList;

public class CalendarActivityProps extends Props {
  public ArrayList<FriendItemEntry> friendsList;
  public ArrayList<TimelineEntry> timelineEntries = new ArrayList<>();
}
