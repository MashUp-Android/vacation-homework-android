package com.chatty.android.chattyClient.model;

import com.chatty.android.chattyClient.model.response.DiaryResponse;
import com.chatty.android.chattyClient.model.Diary;

import java.util.ArrayList;
import java.util.List;

public class State {
  public Chat chat = new Chat();
  public Diary diary = new Diary();
  public Friend friend = new Friend();

//  public List<ChatBalloon> chatBalloons;
//  public String writeDiaryId;
//  public boolean hasFriend;
//  PartnerProfileDetailEntry partnerProfileDetail;
//  List<TimelineEntry> timeline;
//  ArrayList<Diary> diaries;
//  List<FriendItemEntry> friends;
//  public Partner partner;
//
//  public State() {
//    this.chatBalloons = new ArrayList<>();
//    this.diaries = new ArrayList<>();
//    this.friends = new ArrayList<>();
//    this.partnerProfileDetail = new PartnerProfileDetailEntry();
//    this.timeline = new ArrayList<>();
//    this.writeDiaryId = "0";
//  }
//
//  public PartnerProfileDetailEntry getPartnerProfileDetail() {
//    return partnerProfileDetail;
//  }
//
//  public void setPartnerProfileDetail(PartnerProfileDetailEntry partnerProfileDetail) {
//    this.partnerProfileDetail = partnerProfileDetail;
//  }
//
//  public List<TimelineEntry> getTimeline() {
//    return timeline;
//  }
//
//  public ArrayList<Diary> getDiaries() {
//    return diaries;
//  }
//
//  public List<FriendItemEntry> getFriends() {
//    return friends;
//  }
//
//  public void setTimeline(List<TimelineEntry> timeline) {
//    this.timeline = timeline;
//  }
//
//  public void setDiaries(ArrayList<Diary> diaries) {
//    this.diaries = diaries;
//  }
//
//  public void setFriends(List<FriendItemEntry> friends) {
//    this.friends = friends;
//  }

  public class Chat {
    public ArrayList<ChatBalloon> chatBalloons = new ArrayList<>();
    public String writeDiaryId = "";
  }

  public class Diary {
    public ArrayList<TimelineEntry> timeline = new ArrayList<>();
    public ArrayList<com.chatty.android.chattyClient.model.Diary> diaries = new ArrayList<>();
    public PartnerProfileDetailEntry partnerProfileDetail = new PartnerProfileDetailEntry();
  }

  public class Friend {
    public boolean hasFriend = false;
    public ArrayList<FriendItemEntry> friends = new ArrayList<>();
    public Partner partner = new Partner();
    public PartnerProfileDetailEntry partnerProfileDetail = new PartnerProfileDetailEntry();
  }
}
