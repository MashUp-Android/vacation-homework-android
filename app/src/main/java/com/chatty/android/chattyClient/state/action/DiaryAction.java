package com.chatty.android.chattyClient.state.action;

import com.chatty.android.chattyClient.api.ChattyApi;
import com.chatty.android.chattyClient.constants.ActionType;
import com.chatty.android.chattyClient.externalModules.StateManager.Action;
import com.chatty.android.chattyClient.externalModules.StateManager.StateManager;
import com.chatty.android.chattyClient.model.response.DiaryResponse;
import com.chatty.android.chattyClient.model.response.TimelineResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiaryAction {

  public static StateManager.DispatcherMiddleware requestGetDiaries() {
    return (dispatch) -> {
      dispatch.run(Action.of(ActionType.REQUEST_GET_DIARIES));

      ChattyApi.getApi().getTimeline()
        .enqueue(new Callback<TimelineResponse>() {
          @Override
          public void onResponse(Call<TimelineResponse> call, Response<TimelineResponse> response) {
            dispatch.run(Action.of(ActionType.REQUEST_GET_DIARIES_SUCCESS)
              .payloadAdd("timeline", response.body()));
          }

          @Override
          public void onFailure(Call<TimelineResponse> call, Throwable t) {
            dispatch.run(Action.of(ActionType.REQUEST_GET_DIARIES_ERROR)
              .payloadAdd("error", t));
          }
        });
    };
  }

  public static StateManager.DispatcherMiddleware requestGetDiaryDetail(int diaryId) {
    return (dispatch) -> {
      dispatch.run(Action.of(ActionType.REQUEST_GET_DIARIES_DETAIL));

      ChattyApi.getApi().getDiaryDetail(diaryId)
        .enqueue(new Callback<DiaryResponse>() {
          @Override
          public void onResponse(Call<DiaryResponse> call, Response<DiaryResponse> response) {
            DiaryResponse diaryDetail = response.body();

            dispatch.run(Action.of(ActionType.REQUEST_GET_DIARIES_DETAIL_SUCCESS)
              .payloadAdd("diary", diaryDetail));
          }

          @Override
          public void onFailure(Call<DiaryResponse> call, Throwable t) {
            dispatch.run(Action.of(ActionType.REQUEST_GET_DIARIES_DETAIL_ERROR));
          }
        });
    };
  }
}
