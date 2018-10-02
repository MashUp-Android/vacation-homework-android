package com.chatty.android.chattyClient.externalModules.ReduxJava;

import android.util.Log;

import java.util.ArrayList;
import java.util.function.Consumer;

public class ReduxJava<State> {
  private Reducer reducer = null;
  private State state;
  private final ArrayList<Consumer<State>> subscribers = new ArrayList<>();

  public void initialize(Reducer reducer, State state) {
    Log.i(ReduxJava.class.getName(), "initialize() " + reducer + " " + state);
    this.reducer = reducer;
    this.state = state;
  }

  public boolean dispatch(DispatcherMiddleware r) {
    if (this.reducer == null) {
      Log.e(ReduxJava.class.getName(), "ReduxJava needs reducers to dispatch");
      return false;
    }

    r.run(this::_dispatch);
    return true;
  }

  @SuppressWarnings("unchecked")
  private void _dispatch(Action action) {
    Log.d("[ReduxJava]", action.getType());
    this.state = (State) reducer.run(state, action);
    this.emit(state);
  }

  private void emit(State state) {
    for (int i = 0; i < this.subscribers.size(); i++) {
      this.subscribers.get(i).accept(state);
    }
  }

  public State getState() {
    return this.state;
  }

  public void subscribe(Consumer<State> f) {
    this.subscribers.add(f);
  }

  public interface DispatcherMiddleware {
    void run(Dispatcher r);
  }

  public interface Dispatcher {
    void run(Action action);
  }

//  public interface Reducer<State> {
//    State run(State state, Action action);
//  }
}
