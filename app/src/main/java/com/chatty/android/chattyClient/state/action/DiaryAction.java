package com.chatty.android.chattyClient.state.action;

import android.util.Log;

import com.chatty.android.chattyClient.api.ChattyApi;
import com.chatty.android.chattyClient.constants.ActionType;
import com.chatty.android.chattyClient.model.Diary;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.model.TimelineEntry;
import com.chatty.android.chattyClient.model.response.ChatResponse;
import com.chatty.android.chattyClient.externalModules.StateManager.Action;
import com.chatty.android.chattyClient.externalModules.StateManager.StateManager;
import com.chatty.android.chattyClient.model.response.DiaryResponse;
import com.chatty.android.chattyClient.model.response.TimelineResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiaryAction {

  public static StateManager.DispatcherMiddleware requestGetDiaries() {
    return (dispatch) -> {
      dispatch.run(new Action(ActionType.REQUEST_GET_DIARIES));

      ChattyApi.getApi().getTimeline()
        .enqueue(new Callback<TimelineResponse>() {
          @Override
          public void onResponse(Call<TimelineResponse> call, Response<TimelineResponse> response) {
            if (response.code() != 200) {
              dispatch.run(new Action(ActionType.REQUEST_GET_DIARIES_ERROR));
              return;
            }

            List<TimelineEntry> entries = response.body()
              .diaries
              .stream()
              .map((diary) -> {
                TimelineEntry entry = new TimelineEntry();
                entry.setDate(diary.created_at);
                entry.setContent(diary.answer.label);
                entry.setImgUrl(diary.answer.image);
                return entry;
              })
              .collect(Collectors.toList());

            HashMap result = new HashMap<>();
            result.put("timeline", entries);
            dispatch.run(new Action(ActionType.REQUEST_GET_DIARIES_SUCCESS, result));
          }

          @Override
          public void onFailure(Call<TimelineResponse> call, Throwable t) {
            System.out.println(t.getMessage() + " " + t.getCause());
            dispatch.run(new Action(ActionType.REQUEST_GET_DIARIES_ERROR));
          }
        });
    };
  }

  public static StateManager.DispatcherMiddleware requestGetDiaryDetail() {
    return (dispatch) -> {
      dispatch.run(new Action(ActionType.REQUEST_GET_DIARIES_DETAIL));

      ChattyApi.getApi().postDiaryChat(1)
        .enqueue(new Callback<DiaryResponse>() {
          @Override
          public void onResponse(Call<DiaryResponse> call, Response<DiaryResponse> response) {
            ArrayList<DiaryResponse> dummyEntries = new ArrayList<>();

            DiaryResponse dummy1 = new DiaryResponse("dummy", "pretty good. I had study session with friends from mashup. \n" +
              "I learned how to make a random chatbot!!!!!!!!\n" +
              "It was really basic but still i had fun today cuz it&apos;s been a while since i learned sth new and fun.;sd");
            DiaryResponse dummy2 = new DiaryResponse("Hellow java?2", "Ths is my answer2");
            DiaryResponse dummy3 = new DiaryResponse("Hellow java?33", "Ths is my answer3");

            dummyEntries.addAll(Arrays.asList(dummy1, dummy2, dummy3));

            HashMap result = new HashMap<>();
            result.put("diary", dummyEntries);
            dispatch.run(new Action(ActionType.REQUEST_GET_DIARIES_DETAIL_SUCCESS, result));
          }

          @Override
          public void onFailure(Call<DiaryResponse> call, Throwable t) {
            dispatch.run(new Action(ActionType.REQUEST_GET_DIARIES_DETAIL_ERROR));
          }
        });
    };
  }
}
