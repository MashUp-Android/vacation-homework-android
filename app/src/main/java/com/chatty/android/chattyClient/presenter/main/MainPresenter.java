package com.chatty.android.chattyClient.presenter.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedPresenter;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.model.TimelineEntry;
import com.chatty.android.chattyClient.module.StateManagerWrapper;
import com.chatty.android.chattyClient.state.action.DiaryAction;
import com.chatty.android.chattyClient.view.diaryDetail.DiaryDetailActivity;
import com.chatty.android.chattyClient.view.main.MainActivity;
import com.chatty.android.chattyClient.view.main.MainActivityProps;
import com.chatty.android.chattyClient.view.main.MainActivityState;
import com.chatty.android.chattyClient.view.write.WriteActivity;

public class MainPresenter extends ExtendedPresenter<MainActivityProps, MainActivityState, State> {
  private void handleClickWriteButton(AppCompatActivity activity, FloatingActionButton button) {
    if (MainActivity.floatingCheckeNum == 0) {
      button.setVisibility(View.INVISIBLE);
      Intent intent = new Intent(activity, WriteActivity.class);
      activity.startActivity(intent);
      MainActivity.floatingCheckeNum++;
    }
    else{
      Log.e("floating Btn Close check", String.valueOf(MainActivity.floatingCheckeNum));
    }
  }

  @Override
  public MainActivityProps initiate() {
    try {
      StateManagerWrapper.dispatch(DiaryAction.requestGetDiaries());
    } catch (Exception e) {
      e.printStackTrace();
    }

    MainActivityProps props = new MainActivityProps();
    props.timeline = StateManagerWrapper.getState().getTimeline();
//    props.handleClickWriteButton = this::handleClickWriteButton;
    props.handleClickTimelineEntry = this::handleClickTimelineEntry;
    return props;
  }

  private void handleClickTimelineEntry(View view, TimelineEntry timelineEntry) {
    Intent intent = new Intent(this.activity, DiaryDetailActivity.class);
    intent.putExtra("diaryId", timelineEntry.getDiaryId());
    this.activity.startActivity(intent);
  }

  public MainActivityProps stateListener(State state) {
    StateManagerWrapper.log(this.getClass().getSimpleName(), state);

    MainActivityProps props = new MainActivityProps();
    props.timeline = state.getTimeline();

    return props;
  }
}
