package com.chatty.android.chattyClient.presenter.calendar;

import android.os.Bundle;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedPresenter;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.model.TimelineEntry;
import com.chatty.android.chattyClient.state.Store;
import com.chatty.android.chattyClient.view.calendar.CalendarActivity;
import com.chatty.android.chattyClient.view.calendar.CalendarActivityProps;
import com.chatty.android.chattyClient.view.calendar.CalendarActivityState;

public class CalendarPresenter
  extends ExtendedPresenter<CalendarActivityProps, CalendarActivityState, State> {
  private CalendarActivity view;

  @Override
  public CalendarActivityProps initiate() {
    State state = Store.getState();

    CalendarActivityProps props = new CalendarActivityProps();
    props.timelineEntries = state.diary.timeline;
    return props;
  }

  public CalendarActivityProps stateListener(State state) {
    return null;
  }
}
