package com.chatty.android.chattyClient.state.reducers;

import com.chatty.android.chattyClient.constants.ActionType;
import com.chatty.android.chattyClient.module.StateManager.Action;

import java.util.HashMap;

public class Reducers {
  private static final String REDUCERS = "REDUCERS";

  public static HashMap<String, Object> reduce(HashMap<String, Object> state, Action action) {
    System.out.println(REDUCERS + " " + state + action.getType());

    HashMap<String, Object> newState = (HashMap) state.clone();

    switch (action.getType()) {
      case ActionType.REQUEST_GET_DIARIES_SUCCESS:
        System.out.println("131313");
        newState.put("diaries", action.getPayload());
        return newState;
      default:
        return newState;
    }
  }
}
