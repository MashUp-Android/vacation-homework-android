package com.chatty.android.chattyClient.state.reducers;

import com.chatty.android.chattyClient.constants.ActionType;
import com.chatty.android.chattyClient.externalModules.ReduxJava.Action;
import com.chatty.android.chattyClient.externalModules.ReduxJava.Reducer;
import com.chatty.android.chattyClient.model.Diary;
import com.chatty.android.chattyClient.model.Partner;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.model.TimelineEntry;
import com.chatty.android.chattyClient.model.response.DiaryResponse;
import com.chatty.android.chattyClient.model.response.TimelineResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiaryReducer implements Reducer<State.Diary> {
  @Override
  public State.Diary run(State.Diary diary, Action action) {
    switch (action.getType()) {
      case ActionType.REQUEST_GET_DIARIES_SUCCESS:
        TimelineResponse timeline = (TimelineResponse) action.getPayload().get("timeline");
        ArrayList<TimelineEntry> timelineEntries = timeline
          .diaries
          .stream()
          .map((_diary) -> {
            TimelineEntry entry = new TimelineEntry();
            entry.setDiaryId(_diary.diary_id);
            entry.setDate(_diary.created_at);
            String content = "";
            for (TimelineResponse.Answer answer : _diary.answers) {
              content = content + answer.label + " ";
            }
            entry.setContent(content);
            if (_diary.answers.size() > 0) {
              entry.setImgUrl(_diary.answers.get(0).image);
            }
            return entry;
          })
          .collect(Collectors.toCollection(ArrayList::new));
        diary.timeline = timelineEntries;
        return diary;

      case ActionType.REQUEST_GET_DIARIES_DETAIL_SUCCESS:
        DiaryResponse diaryResponse = (DiaryResponse) action.getPayload().get("diary");
        ArrayList<Diary> diaryList = makeDiary(diaryResponse);
        diary.diaries = diaryList;
        return diary;

      default:
        return diary;
    }
  }

  private static ArrayList<Diary> makeDiary(DiaryResponse diaryResponse) {
    ArrayList<Diary> diaries = new ArrayList<>();
    for (int i = 0; i < diaryResponse.questions.size(); i++) {
      diaries.add(
        new Diary(
          diaryResponse.questions.get(i).message,
          diaryResponse.answers.get(i).label));
    }
    return diaries;
  }
}
