package com.chatty.android.chattyClient.state.action;

import com.chatty.android.chattyClient.api.ChattyApi;
import com.chatty.android.chattyClient.constants.ActionType;
import com.chatty.android.chattyClient.externalModules.StateManager.Action;
import com.chatty.android.chattyClient.externalModules.StateManager.StateManager;
import com.chatty.android.chattyClient.model.response.ChatResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatAction {
  public static StateManager.DispatcherMiddleware requestStartChat() {
    return (dispatch) -> {
      dispatch.run(Action.of(ActionType.REQUEST_START_CHAT));
      ChattyApi.getApi().postStartChat()
        .enqueue(new Callback<ChatResponse>() {
          @Override
          public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
            dispatch.run(Action.of(ActionType.REQUEST_START_CHAT_SUCCESS)
              .payloadAdd("chat", response.body()));
          }

          @Override
          public void onFailure(Call<ChatResponse> call, Throwable t) {
            dispatch.run(Action.of(ActionType.REQUEST_START_CHAT_ERROR)
              .payloadAdd("error", t.getMessage()));
          }
        });
    };
  }
}
