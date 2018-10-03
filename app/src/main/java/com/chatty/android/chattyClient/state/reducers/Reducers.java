package com.chatty.android.chattyClient.state.reducers;

import android.util.Log;

import com.chatty.android.chattyClient.externalModules.ReduxJava.Action;
import com.chatty.android.chattyClient.model.State;

public class Reducers {
  private static ChatReducer chatReducer = new ChatReducer();
  private static DiaryReducer diaryReducer = new DiaryReducer();
  private static FriendReducer friendReducer = new FriendReducer();

  public static State reduce(State state, Action action) {
    Log.d(Reducers.class.getSimpleName(), action.getType() + " " + state);

    if (action.getPayload().get("error") != null) {
      Log.e(Reducers.class.getSimpleName(), "error: " + action.getPayload().get("error"));
    }

    state.chat = Reducers.chatReducer.run(state.chat, action);
    state.diary = Reducers.diaryReducer.run(state.diary, action);
    state.friend = Reducers.friendReducer.run(state.friend, action);
    return state;
  }
}
