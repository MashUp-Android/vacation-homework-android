package com.chatty.android.chattyClient.externalModules.ReduxJava;

public interface Reducer<State> {
  State run(State state, Action action);
}
