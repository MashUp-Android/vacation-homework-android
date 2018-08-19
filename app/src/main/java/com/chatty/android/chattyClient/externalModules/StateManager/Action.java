package com.chatty.android.chattyClient.externalModules.StateManager;

import java.util.HashMap;

public class Action {
  private HashMap<String, Object> payload;
  private String type;

  public Action(String type) {
    this.payload = null;
    this.type = type;
  }

  public Action(String type, HashMap<String, Object> payload) {
    this.payload = payload;
    this.type = type;
  }

  public HashMap<String, Object> getPayload() {
    return payload;
  }

  public void setPayload(HashMap<String, Object> payload) {
    this.payload = payload;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
