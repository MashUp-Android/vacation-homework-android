package com.chatty.android.chattyClient.module.StateManager;

import android.util.Log;

import com.chatty.android.chattyClient.constants.ActionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class StateManager {
  final private static String STATE_MANAGER = "STATE_MANAGER";

  private static Reducer reducer = null;
  private static HashMap<String, Object> state = new HashMap<>();
  private static final ArrayList<Function<HashMap, Object>> subscribers = new ArrayList<>();

  public static void initialize(Reducer reducer) {
    Log.d(STATE_MANAGER, "initialize() " + reducer);
    StateManager.reducer = reducer;
  }

  public static void dispatch(DispatcherMiddleware r) throws Exception {
    if (StateManager.reducer == null) {
      throw new Exception("StateManager needs reducers");
    }

    r.run(StateManager::_dispatch);
  }

  private static void _dispatch(Action action) {
    Log.d(STATE_MANAGER, action.getType());

    state = reducer.run(state, action);
    emit(state);
  }

  public static void emit(HashMap<String, Object> state) {
    for (int i = 0; i < StateManager.subscribers.size(); i++) {
      StateManager.subscribers.get(i).apply(state);
    }
  }

  public static void subscribe(Function<HashMap, Object> f) {
    StateManager.subscribers.add(f);
  }

  public static void printMap(HashMap<String, Object> map) {
    System.out.println(STATE_MANAGER + " printMap()" + map);
    for (String key : map.keySet()) {
      System.out.println(STATE_MANAGER + " " + key + " " + map.get(key));
    }
  }

  public interface DispatcherMiddleware {
    void run(Dispatcher r);
  }

  public interface Dispatcher {
    void run(Action action);
  }

  public interface Reducer {
    HashMap<String, Object> run(HashMap<String, Object> state, Action action);

  }

}