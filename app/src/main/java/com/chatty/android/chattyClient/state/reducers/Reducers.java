package com.chatty.android.chattyClient.state.reducers;

import com.chatty.android.chattyClient.constants.ActionType;
import com.chatty.android.chattyClient.externalModules.StateManager.Action;
import com.chatty.android.chattyClient.model.PartnerProfileDetailEntry;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.model.TimelineEntry;
import com.chatty.android.chattyClient.model.response.PartnerProfileDetailResponse;
import com.chatty.android.chattyClient.model.Diary;
import com.chatty.android.chattyClient.model.response.DiaryResponse;

import java.util.ArrayList;

public class Reducers {
  private static final String REDUCERS = "REDUCERS";

  public static Object reduce(Object _state, Action action) {
    System.out.println(REDUCERS + " " + _state + action.getType());

    State state = (State) _state;

    switch (action.getType()) {
      case ActionType.REQUEST_GET_DIARIES_SUCCESS:
        @SuppressWarnings("unchecked")
        ArrayList<TimelineEntry> timelineList = (ArrayList<TimelineEntry>) action.getPayload().get("timeline");
        state.setTimeline(timelineList);
        return state;
      case ActionType.REQUEST_GET_PARTNER_PROFILE_DETAIL_SUCCESS:
        PartnerProfileDetailResponse partnerProfileDetailResponse = (PartnerProfileDetailResponse) action.getPayload().get("partnerProfileDetail");
        PartnerProfileDetailEntry partnerProfileDetailEntry = makePartnerProfile(partnerProfileDetailResponse);
        state.setPartnerProfileDetail(partnerProfileDetailEntry);
        return state;
      case ActionType.REQUEST_GET_DIARIES_DETAIL_SUCCESS:
        ArrayList<DiaryResponse> diaryResponse = (ArrayList<DiaryResponse>) action.getPayload().get("diary");
        System.out.println("123123123 " + diaryResponse.size());

        for (DiaryResponse r : diaryResponse) {
          System.out.println("131313 " + r.getAnswer());
        }

        ArrayList<Diary> diaryList = makeDiary(diaryResponse);
        for (Diary d : diaryList) {
          System.out.println("23232323" + d.getAnswer());
        }
        state.setDiary(diaryList);
        return state;
      default:
        return state;
    }
  }

  private static PartnerProfileDetailEntry makePartnerProfile(PartnerProfileDetailResponse partnerProfileDetailResponse) {
    PartnerProfileDetailEntry partnerProfileDetailEntry = new PartnerProfileDetailEntry(
      partnerProfileDetailResponse.getId(),
      partnerProfileDetailResponse.getProfileImage(),
      partnerProfileDetailResponse.getName(),
      partnerProfileDetailResponse.getBio(),
      partnerProfileDetailResponse.getDiaryCount(),
      partnerProfileDetailResponse.getDaysTogether(),
      partnerProfileDetailResponse.getCreateDate()
    );
    return partnerProfileDetailEntry;
  }


  private static ArrayList<Diary> makeDiary(ArrayList<DiaryResponse> diaryResponse) {
    ArrayList<Diary> resultDiary = new ArrayList<>();
    for (DiaryResponse currentDiary : diaryResponse) {
      Diary diary = new Diary(
        currentDiary.getMessage(),
        currentDiary.getAnswer()
      );
      resultDiary.add(diary);
    }
    return resultDiary;
  }
}
