package com.chatty.android.chattyClient.module.StateManager;

import java.util.HashMap;

public class Action {
  private HashMap<String, String> payload;
  private String type;

  public Action(String type) {
    this.payload = null;
    this.type = type;
  }

  public Action(String type, HashMap<String, String> payload) {
    this.payload = payload;
    this.type = type;
  }

  public HashMap<String, String> getPayload() {
    return payload;
  }

  public void setPayload(HashMap<String, String> payload) {
    this.payload = payload;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
