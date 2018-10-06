package com.chatty.android.chattyClient.state.action;

import android.util.Log;

import com.chatty.android.chattyClient.api.ChattyApi;
import com.chatty.android.chattyClient.constants.ActionType;
import com.chatty.android.chattyClient.externalModules.ReduxJava.Action;
import com.chatty.android.chattyClient.externalModules.ReduxJava.ReduxJava;
import com.chatty.android.chattyClient.model.request.ChatRequest;
import com.chatty.android.chattyClient.model.response.AppendChatResponse;
import com.chatty.android.chattyClient.model.response.ChatResponse;

import org.w3c.dom.Entity;

import java.util.HashMap;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatAction {
  public static ReduxJava.DispatcherMiddleware requestStartChat() {
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

  public static ReduxJava.DispatcherMiddleware requestAppendChat(String diaryId, String text, String uri, MultipartBody.Part file) {
    return (dispatch) -> {
      ChatRequest request = new ChatRequest();
      request.label = text;
      request.image = uri;
      Log.e("통신시작",text+uri);
      dispatch.run(Action.of(ActionType.REQUEST_APPEND_CHAT)
        .payloadAdd("chat", request));

      ChattyApi.getApi().postChat(Integer.parseInt(diaryId),request.label,file)
        .enqueue(new Callback<AppendChatResponse>() {
          @Override
          public void onResponse(Call<AppendChatResponse> call, Response<AppendChatResponse> response) {
                Log.e("통신성공","ㅋ");
            dispatch.run(Action.of(ActionType.REQUEST_APPEND_CHAT_SUCCESS)
              .payloadAdd("chat", response.body()));
          }

          @Override
          public void onFailure(Call<AppendChatResponse> call, Throwable t) {
                Log.e("통신실패","ㅋ");
            dispatch.run(Action.of(ActionType.REQUEST_APPEND_CHAT_ERROR)
              .payloadAdd("error", t.getMessage()));
          }
        });
    };
  }
}
