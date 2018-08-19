package com.chatty.android.chattyClient.model;

import java.util.ArrayList;

public class State {
  ArrayList<TimelineEntry> timeline;

  public State() {
    this.timeline = new ArrayList<>();
  }

  public ArrayList<TimelineEntry> getTimeline() {
    return timeline;
  }

  public void setTimeline(ArrayList<TimelineEntry> timeline) {
    this.timeline = timeline;
  }

  public State clone() {
    State instance = new State();
    instance.timeline = this.timeline;
    return instance;
  }
}
