package com.chatty.android.chattyClient.model;

import com.chatty.android.chattyClient.model.response.DiaryResponse;

import java.util.ArrayList;
import java.util.List;

public class State {
  public List<ChatBalloon> chatBalloons;
  public boolean hasFriend;
  PartnerProfileDetailEntry partnerProfileDetail;
  List<TimelineEntry> timeline;
  ArrayList<Diary> diaries;
  List<FriendItemEntry> friends;
  public Partner partner;

  public State() {
    this.chatBalloons = new ArrayList<>();
    this.diaries = new ArrayList<>();
    this.friends = new ArrayList<>();
    this.partnerProfileDetail = new PartnerProfileDetailEntry();
    this.timeline = new ArrayList<>();
  }

  public PartnerProfileDetailEntry getPartnerProfileDetail() {
    return partnerProfileDetail;
  }

  public void setPartnerProfileDetail(PartnerProfileDetailEntry partnerProfileDetail) {
    this.partnerProfileDetail = partnerProfileDetail;
  }

  public List<TimelineEntry> getTimeline() {
    return timeline;
  }

  public ArrayList<Diary> getDiaries() {
    return diaries;
  }

  public List<FriendItemEntry> getFriends() { return friends; }

  public void setTimeline(List<TimelineEntry> timeline) {
    this.timeline = timeline;
  }
  public void setDiaries(ArrayList<Diary> diaries) {
    this.diaries = diaries;
  }

  public void setFriends(List<FriendItemEntry> friends) { this.friends = friends; }

  public State clone() {
    State instance = new State();
    instance.timeline = this.timeline;
    instance.diaries = this.diaries;
    instance.friends = this.friends;
    return instance;
  }

}
