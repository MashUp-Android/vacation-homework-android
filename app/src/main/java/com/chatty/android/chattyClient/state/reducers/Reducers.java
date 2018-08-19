package com.chatty.android.chattyClient.state.reducers;

import com.chatty.android.chattyClient.constants.ActionType;
import com.chatty.android.chattyClient.externalModules.StateManager.Action;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.model.TimelineEntry;

import java.util.ArrayList;

public class Reducers {
  private static final String REDUCERS = "REDUCERS";

  public static Object reduce(Object state, Action action) {
    System.out.println(REDUCERS + " " + state + action.getType());

    State newState = ((State) state).clone();

    switch (action.getType()) {
      case ActionType.REQUEST_GET_DIARIES_SUCCESS:
        @SuppressWarnings("unchecked")
        ArrayList<TimelineEntry> list = (ArrayList<TimelineEntry>) action.getPayload().get("timeline");
        newState.setTimeline(list);
        return newState;

      default:
        return newState;
    }
  }
}
