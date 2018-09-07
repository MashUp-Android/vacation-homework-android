package com.chatty.android.chattyClient.presenter.calendar;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedPresenter;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.view.calendar.CalendarActivity;
import com.chatty.android.chattyClient.view.calendar.CalendarActivityProps;
import com.chatty.android.chattyClient.view.calendar.CalendarActivityState;

public class CalendarPresenter
  extends ExtendedPresenter<CalendarActivityProps, CalendarActivityState, State> {
  private CalendarActivity view;

  @Override
  public CalendarActivityProps initiate() {
    return null;
  }

  public CalendarActivityProps stateListener(State state) {
    return null;
  }
}
