package com.chatty.android.chattyClient;

import android.app.Application;
import android.content.SharedPreferences;

import com.chatty.android.chattyClient.api.ChattyApi;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.externalModules.ReduxJava.ReduxJavaAndroidConnector;
import com.chatty.android.chattyClient.state.Store;
import com.chatty.android.chattyClient.state.reducers.Reducers;

public class App extends Application {
  public static final String USER_DATA = "USER_DATA";
  public static final String HAS_FRIEND = "HAS_FRIEND";
  public static SharedPreferences userPreference;
  public static boolean isFriend;

  @Override
  public void onCreate() {
    super.onCreate();

    ChattyApi.initialize();
    this.initializeReduxJava();
  }

  private void initializeReduxJava() {
    SharedPreferences userPreference = getSharedPreferences(App.USER_DATA, MODE_PRIVATE);
    boolean hasFriend = userPreference.getBoolean(App.HAS_FRIEND, false);
    State state = new State();
    state.friend.hasFriend = hasFriend;

    Store.configureStore(state);
//    ReduxJavaAndroidConnector.initialize(Reducers::reduce, state);
  }
}
