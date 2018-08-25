package com.chatty.android.chattyClient.state.reducers;

import com.chatty.android.chattyClient.constants.ActionType;
import com.chatty.android.chattyClient.externalModules.StateManager.Action;
import com.chatty.android.chattyClient.model.PartnerProfileDetailEntry;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.model.TimelineEntry;

import java.util.ArrayList;

public class Reducers {
  private static final String REDUCERS = "REDUCERS";

  public static Object reduce(Object _state, Action action) {
    System.out.println(REDUCERS + " " + _state + action.getType());

//    State newState = ((State) state).clone();
    State state = (State) _state;

    switch (action.getType()) {
      case ActionType.REQUEST_GET_DIARIES_SUCCESS:
        @SuppressWarnings("unchecked")
        ArrayList<TimelineEntry> list = (ArrayList<TimelineEntry>) action.getPayload().get("timeline");
        state.setTimeline(list);
        return state;
      case ActionType.REQUEST_GET_PARTNER_PROFILE_DETAIL_SUCCESS:
        PartnerProfileDetailEntry partnerProfileDetailEntry = (PartnerProfileDetailEntry) action.getPayload().get("partnerProfileDetail");
        state.setPartnerProfileDetail(partnerProfileDetailEntry);
      default:
        return state;
    }
  }
}
