package com.chatty.android.chattyClient.state.reducers;

import android.util.Log;

import com.chatty.android.chattyClient.constants.ActionType;
import com.chatty.android.chattyClient.externalModules.ReduxJava.Action;
import com.chatty.android.chattyClient.model.ChatBalloon;
import com.chatty.android.chattyClient.model.FriendItemEntry;
import com.chatty.android.chattyClient.model.Partner;
import com.chatty.android.chattyClient.model.PartnerProfileDetailEntry;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.model.TimelineEntry;
import com.chatty.android.chattyClient.model.response.AppendChatResponse;
import com.chatty.android.chattyClient.model.response.ChatResponse;
import com.chatty.android.chattyClient.model.response.FriendItemResponse;
import com.chatty.android.chattyClient.model.response.PartnerProfileDetailResponse;
import com.chatty.android.chattyClient.model.Diary;
import com.chatty.android.chattyClient.model.response.DiaryResponse;
import com.chatty.android.chattyClient.model.response.TimelineResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

//    This commented code will be DELETED
//    State state = (State) _state;

//    switch (action.getType()) {
//      case ActionType.REQUEST_APPEND_CHAT:
//        String text = (String) action.getPayload().get("chat");
//        ChatBalloon chatBalloon = new ChatBalloon();
//        chatBalloon.speech = text;
//        state.chatBalloons.add(chatBalloon);
//
//        ArrayList<ChatBalloon> newChatBalloons = new ArrayList<>();
//        newChatBalloons.addAll(state.chatBalloons);
//
//        state.chatBalloons = newChatBalloons;
//        return state;
//
//      case ActionType.REQUEST_APPEND_CHAT_SUCCESS:
//        AppendChatResponse appendChatResponse = (AppendChatResponse) action.getPayload().get("chat");
//        ChatBalloon chatBalloon2 = new ChatBalloon();
//        chatBalloon2.speech = appendChatResponse.message;
//        state.chatBalloons.add(chatBalloon2);
//
//        ArrayList<ChatBalloon> newChatBalloons2 = new ArrayList<>();
//        newChatBalloons2.addAll(state.chatBalloons);
//
//        state.chatBalloons = newChatBalloons2;
//        return state;
//
//      case ActionType.REQUEST_GET_DIARIES_SUCCESS:
//        TimelineResponse timeline = (TimelineResponse) action.getPayload().get("timeline");
//        List<TimelineEntry> timelineEntries = timeline
//          .diaries
//          .stream()
//          .map((diary) -> {
//            TimelineEntry entry = new TimelineEntry();
//            entry.setDiaryId(diary.diary_id);
//            entry.setDate(diary.created_at);
//            String content = "";
//            for (TimelineResponse.Answer answer : diary.answers) {
//              content = content + answer.label + " ";
//            }
//            entry.setContent(content);
//            if (diary.answers.size() > 0) {
//              entry.setImgUrl(diary.answers.get(0).image);
//            }
//            return entry;
//          })
//          .collect(Collectors.toList());
//        state.setTimeline(timelineEntries);
//
//        Partner partner = new Partner();
//        partner.diary_count = timeline.partner.diary_count;
//        partner.days_together = timeline.partner.days_together;
//        partner.imageUrl = timeline.partner.profile_image;
//        partner.name = timeline.partner.name;
//        state.partner = partner;
//        return state;
//
//      case ActionType.REQUEST_GET_DIARIES_DETAIL_SUCCESS:
//        DiaryResponse diaryResponse = (DiaryResponse) action.getPayload().get("diary");
//        ArrayList<Diary> diaryList = makeDiary(diaryResponse);
//        state.setDiaries(diaryList);
//        return state;
//
//      case ActionType.REQUEST_GET_FRIENDS_LIST_SUCCESS:
//        FriendItemResponse friendItemResponse = (FriendItemResponse) action.getPayload().get("friendsList");
//        List<FriendItemEntry> friendItemEntries = friendItemResponse
//          .partners
//          .stream()
//          .map((friendItem) -> {
//            FriendItemEntry entry = new FriendItemEntry(
//              friendItem.profile_image,
//              friendItem.name,
//              friendItem.bio,
//              friendItem.created_at
//            );
//            return entry;
//          })
//          .collect(Collectors.toList());
//        state.setFriends(friendItemEntries);
//        return state;
//
//      case ActionType.REQUEST_GET_PARTNER_PROFILE_DETAIL_SUCCESS:
//        PartnerProfileDetailResponse partnerProfileDetailResponse = (PartnerProfileDetailResponse) action.getPayload().get("partnerProfileDetail");
//        PartnerProfileDetailEntry partnerProfileDetailEntry = makePartnerProfile(partnerProfileDetailResponse);
//        state.setPartnerProfileDetail(partnerProfileDetailEntry);
//        return state;
//
//      case ActionType.REQUEST_START_CHAT_SUCCESS:
//        ChatResponse chatResponse = (ChatResponse) action.getPayload().get("chat");
//        ChatBalloon chatBalloon3 = new ChatBalloon();
//        chatBalloon3.speech = chatResponse.question.message;
//        state.chatBalloons.add(chatBalloon3);
//        state.writeDiaryId = chatResponse.diary_id;
//        return state;
//
//      default:
//        return state;
//    }
  }

//  private static PartnerProfileDetailEntry makePartnerProfile(PartnerProfileDetailResponse partnerProfileDetailResponse) {
//    PartnerProfileDetailEntry partnerProfileDetailEntry = new PartnerProfileDetailEntry(
//      partnerProfileDetailResponse.getId(),
//      partnerProfileDetailResponse.getProfileImage(),
//      partnerProfileDetailResponse.getName(),
//      partnerProfileDetailResponse.getBio(),
//      partnerProfileDetailResponse.getDiaryCount(),
//      partnerProfileDetailResponse.getDaysTogether(),
//      partnerProfileDetailResponse.getCreateDate()
//    );
//    return partnerProfileDetailEntry;
//  }
//
//
//  private static ArrayList<Diary> makeDiary(DiaryResponse diaryResponse) {
//    ArrayList<Diary> diaries = new ArrayList<>();
//    for (int i = 0; i < diaryResponse.questions.size(); i++) {
//      diaries.add(
//        new Diary(
//          diaryResponse.questions.get(i).message,
//          diaryResponse.answers.get(i).label));
//    }
//    return diaries;
//  }
}
