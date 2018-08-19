package com.chatty.android.chattyClient.state.action;

import com.chatty.android.chattyClient.api.ChattyApi;
import com.chatty.android.chattyClient.constants.ActionType;
import com.chatty.android.chattyClient.model.TimelineEntry;
import com.chatty.android.chattyClient.model.response.ChatResponse;
import com.chatty.android.chattyClient.externalModules.StateManager.Action;
import com.chatty.android.chattyClient.externalModules.StateManager.StateManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiaryAction {
  public static StateManager.DispatcherMiddleware requestGetDiaries() {
    return (dispatch) -> {
      dispatch.run(new Action(ActionType.REQUEST_GET_DIARIES));

      ChattyApi.getApi().postInitChat()
        .enqueue(new Callback<ChatResponse>() {
          @Override
          public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
            ArrayList<TimelineEntry> dummyEntries = new ArrayList<>();

            TimelineEntry dummyEntry1 = new TimelineEntry(
              "https://cdn1.medicalnewstoday.com/content/images/headlines/271/271157/bananas.jpg",
              new Date(),
              "dummy1"
            );

            TimelineEntry dummyEntry2 = new TimelineEntry(
              "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cb/Pineapple_and_cross_section.jpg/220px-Pineapple_and_cross_section.jpg",
              new Date(),
              "dummy2"
            );

            dummyEntries.addAll(Arrays.asList(dummyEntry1, dummyEntry2));

            HashMap result = new HashMap<>();
            result.put("timeline", dummyEntries);
            dispatch.run(new Action(ActionType.REQUEST_GET_DIARIES_SUCCESS, result));
          }

          @Override
          public void onFailure(Call<ChatResponse> call, Throwable t) {
            dispatch.run(new Action(ActionType.REQUEST_GET_DIARIES_ERROR));
          }
        });
    };
  }
}
