package com.chatty.android.chattyClient.state.action;

import com.chatty.android.chattyClient.api.ChattyApi;
import com.chatty.android.chattyClient.api.ChattyApiCore;
import com.chatty.android.chattyClient.constants.ActionType;
import com.chatty.android.chattyClient.model.response.ChatResponse;
import com.chatty.android.chattyClient.module.StateManager.Action;
import com.chatty.android.chattyClient.module.StateManager.StateManager;

import java.util.Arrays;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiaryAction {
  private static ChattyApi chattyApi = null;

  private static void initApis() {
    if (chattyApi == null) {
      chattyApi = ChattyApiCore.getApi();
    }
  }

  public static StateManager.DispatcherMiddleware requestGetDiaries() {
    initApis();
    return (dispatch) -> {
      dispatch.run(new Action(ActionType.REQUEST_GET_DIARIES));

      chattyApi.postInitChat()
        .enqueue(new Callback<ChatResponse>() {
          @Override
          public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
            HashMap result = new HashMap<String, Object>();
            result.put("diaries", Arrays.asList("d1", "d2"));
            dispatch.run(new Action(ActionType.REQUEST_GET_DIARIES_SUCCESS, result));
          }

          @Override
          public void onFailure(Call<ChatResponse> call, Throwable t) {
            dispatch.run(new Action(ActionType.REQUEST_GET_DIARIES_ERROR));
          }
        });

      // successAction
//      dispatch.run();
    };
  }
}
