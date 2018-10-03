package com.chatty.android.chattyClient.externalModules.ReduxJava;

import android.util.Log;

import java.util.function.Consumer;
import java.util.function.Function;

public class ReduxJavaAndroidConnector<State, Props> {
  private ReduxJava<State> reduxJava;

  public boolean initialize(Reducer<State> reducer, State initialState) {
    this.reduxJava = new ReduxJava<>();
    this.reduxJava.initialize(reducer, initialState);
    Log.i(
      ReduxJavaAndroidConnector.class.getName(),
      "initialize done with: " + this.reduxJava.getState());
    return true;
  }

  public boolean dispatch(ReduxJava.DispatcherMiddleware r) {
    return this.reduxJava.dispatch(r);
  }

  public void subscribe(Function<State, Object> subscriber, Consumer<Props> updater) {
    Consumer<State> enhancedSubscriber = (state) -> {
      Props p = (Props) subscriber.apply(state);
      updater.accept(p);
    };
    this.reduxJava.subscribe(enhancedSubscriber);
  }

  public State getState() {
    return this.reduxJava.getState();
  }
}
