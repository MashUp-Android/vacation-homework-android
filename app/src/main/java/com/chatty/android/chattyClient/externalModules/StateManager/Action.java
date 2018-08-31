package com.chatty.android.chattyClient.externalModules.StateManager;

public class Action {
  private Payload payload;
  private String type;

  private Action() {
  }

  public static Action of(String type) {
    Action action = new Action();
    action.type = type;
    action.payload = Payload.create();
    return action;
  }

  public Action payloadAdd(String key, Object object) {
    this.payload.add(key, object);
    return this;
  }

  public Payload getPayload() {
    return payload;
  }

  public void setPayload(Payload payload) {
    this.payload = payload;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
