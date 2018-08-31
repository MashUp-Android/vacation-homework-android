package com.chatty.android.chattyClient.externalModules.StateManager;

import java.util.HashMap;

public class Payload {
  private HashMap<String, Object> map;

  private Payload() {
    this.map = new HashMap<>();
  }

  public static Payload create() {
    return new Payload();
  }

  public Payload add(String key, Object obj) {
    this.map.put(key, obj);
    return this;
  }

  public HashMap toMap() {
    return (HashMap) this.map.clone();
  }

  public Object get(String key) {
    return this.map.get(key);
  }
}
