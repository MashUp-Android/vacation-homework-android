package com.chatty.android.chattyClient.presenter.main;

import android.content.Intent;
import android.view.View;

import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.model.TimelineEntry;
import com.chatty.android.chattyClient.module.Logger;
import com.chatty.android.chattyClient.module.StateManagerWrapper;
import com.chatty.android.chattyClient.state.action.DiaryAction;
import com.chatty.android.chattyClient.view.main.MainActivity;
import com.chatty.android.chattyClient.view.write.WriteActivity;

import java.util.ArrayList;

public class MainPresenter {
  private MainActivity view;

  public MainPresenter(MainActivity view) {
    this.view = view;
  }

  public void construct() {
    StateManagerWrapper.subscribe(this::stateListener);
    ArrayList<TimelineEntry> timeline = StateManagerWrapper.getInitialState().getTimeline();

    this.view.render(
      this::handleClickWriteButton,
      timeline
    );
    presenterDidMount();
  }

  public void presenterDidMount() {
    try {
      StateManagerWrapper.dispatch(DiaryAction.requestGetDiaries());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void handleClickWriteButton(View v) {
    Intent intent = new Intent(view, WriteActivity.class);
    view.startActivity(intent);
  }

  public Object stateListener(State state) {
    StateManagerWrapper.log(this.getClass().getSimpleName(), state);

    ArrayList<TimelineEntry> timeline = state.getTimeline();
    System.out.println("12123123" + timeline);

    this.view.render(
      this::handleClickWriteButton,
      timeline
    );

    return null;
  }
}
