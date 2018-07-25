package com.chatty.android.chattyClient.module.StateManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

public class StateManager {
  final private static HashMap<String, Object> state = new HashMap<>();
  final private static ArrayList<Function<HashMap, Object>> subscribers = new ArrayList<>();

  public static void initialize() {
    System.out.println("123123 init " + StateManager.state);
    StateManager.state.put("power", "1");
  }

  public static void dispatch(Action action) {
    // todo: reduce
    emit();
  }

  public static void printState() {
    for (String key : state.keySet()) {
      System.out.println("333 " + state.get(key));
    }
  }

  public static void emit() {
    for (int i = 0; i < StateManager.subscribers.size(); i++) {
      StateManager.subscribers.get(i).apply(StateManager.state);
    }
  }

  public static HashMap<String, Object> subscribe(Function<HashMap, Object> f) {
    StateManager.subscribers.add(f);
    return null;
  }
}
