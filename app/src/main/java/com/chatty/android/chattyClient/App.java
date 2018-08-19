package com.chatty.android.chattyClient;

import android.app.Application;

import com.chatty.android.chattyClient.api.ChattyApi;
import com.chatty.android.chattyClient.module.StateManagerWrapper;
import com.chatty.android.chattyClient.state.reducers.Reducers;

public class App extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    construct();
  }

  private void construct() {
    ChattyApi.initialize();
    this.initializeStateManager();
  }

  private void initializeStateManager() {
    StateManagerWrapper.initialize(Reducers::reduce);
  }
}
