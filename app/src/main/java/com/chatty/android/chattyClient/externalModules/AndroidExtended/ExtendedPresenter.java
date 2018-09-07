package com.chatty.android.chattyClient.externalModules.AndroidExtended;

import android.support.v7.app.AppCompatActivity;

import com.chatty.android.chattyClient.model.State;

import java.util.function.Function;

public abstract class ExtendedPresenter<Props, LocalState, State> {
  public AppCompatActivity activity;
  public LocalState localState;

  public abstract Props initiate();
  public abstract Props stateListener(State state);
}
