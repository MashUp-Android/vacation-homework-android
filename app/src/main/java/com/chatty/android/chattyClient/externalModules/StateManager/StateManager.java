package com.chatty.android.chattyClient.externalModules.StateManager;

import android.util.Log;

import com.chatty.android.chattyClient.model.State;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

public class StateManager<T> {
  private Reducer reducer = null;
  private T state;
  private final ArrayList<Consumer<T>> subscribers = new ArrayList<>();

  public void initialize(Reducer reducer, T state) {
    Log.d("[StateManager]", "initialize() " + reducer);
    this.reducer = reducer;
    this.state = state;
  }

  public void dispatch(DispatcherMiddleware r) throws Exception {
    if (this.reducer == null) {
      throw new Exception("StateManager needs reducers");
    }

    r.run(this::_dispatch);
  }

  @SuppressWarnings("unchecked")
  private void _dispatch(Action action) {
    Log.d("[StateManager]", action.getType());
    this.state = (T) reducer.run(state, action);
    this.emit(state);
  }

  private void emit(T state) {
    for (int i = 0; i < this.subscribers.size(); i++) {
      this.subscribers.get(i).accept(state);
    }
  }

  public void subscribe(Consumer<T> f) {
    this.subscribers.add(f);
  }

  public interface DispatcherMiddleware {
    void run(Dispatcher r);
  }

  public interface Dispatcher {
    void run(Action action);
  }

  public interface Reducer<T> {
    T run(T state, Action action);
  }

  public T getState() {
    return this.state;
  }
}
