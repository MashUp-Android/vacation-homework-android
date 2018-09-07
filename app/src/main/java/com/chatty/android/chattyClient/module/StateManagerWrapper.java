package com.chatty.android.chattyClient.module;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.Props;
import com.chatty.android.chattyClient.externalModules.StateManager.StateManager;
import com.chatty.android.chattyClient.model.State;

import java.util.function.Consumer;
import java.util.function.Function;

public class StateManagerWrapper {
  public static StateManager<State> stateManager;
  private static final String STATE_MANAGER_WRAPPER = "STATE_MANAGER_WRAPPER";

  public static void initialize(StateManager.Reducer reducer, State initialState) {
    StateManagerWrapper.stateManager = new StateManager<>();
    StateManagerWrapper.stateManager.initialize(reducer, initialState);
  }

  public static void dispatch(StateManager.DispatcherMiddleware r) throws Exception {
    StateManagerWrapper.stateManager.dispatch(r);
  }

  public static void subscribe(Function<State, Object> subscriber, Consumer<Props> updater) {
    Consumer<State> enhancedSubscriber = (state) -> {
      Props p = (Props) subscriber.apply(state);
      updater.accept(p);
    };
    StateManagerWrapper.stateManager.subscribe(enhancedSubscriber);
  }

  public static void log(String className, State state) {
    System.out.println(STATE_MANAGER_WRAPPER + " " + className + " " + state);
  }

  public static State getState() {
    return StateManagerWrapper.stateManager.getState();
  }

}
