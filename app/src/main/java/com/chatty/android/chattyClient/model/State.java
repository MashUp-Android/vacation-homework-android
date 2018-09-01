package com.chatty.android.chattyClient.model;

import com.chatty.android.chattyClient.model.response.DiaryResponse;

import java.util.ArrayList;
import java.util.List;

public class State {
  PartnerProfileDetailEntry partnerProfileDetail;
  List<TimelineEntry> timeline;
  ArrayList<Diary> diaries;

  public State() {
    this.partnerProfileDetail = new PartnerProfileDetailEntry();
    this.timeline = new ArrayList<>();
    this.diaries = new ArrayList<>();
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

  public void setTimeline(List<TimelineEntry> timeline) {
    this.timeline = timeline;
  }
  public void setDiaries(ArrayList<Diary> diaries) {
    this.diaries = diaries;
  }

  public State clone() {
    State instance = new State();
    instance.timeline = this.timeline;
    instance.diaries = this.diaries;
    return instance;
  }

}
