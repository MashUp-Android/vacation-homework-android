package com.chatty.android.chattyClient.externalModules.ReduxJava;

import java.util.HashMap;

public class Payload {
  public HashMap<String, Object> map;

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

  public Object get(String key) {
    return this.map.get(key);
  }
}
