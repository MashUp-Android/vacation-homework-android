package com.chatty.android.chattyClient.presenter.main;

import android.content.Intent;
import android.view.View;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedPresenter;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.model.TimelineEntry;
import com.chatty.android.chattyClient.module.StateManagerWrapper;
import com.chatty.android.chattyClient.state.action.DiaryAction;
import com.chatty.android.chattyClient.view.diaryDetail.DiaryDetailActivity;
import com.chatty.android.chattyClient.view.main.MainActivity;
import com.chatty.android.chattyClient.view.main.MainActivityProps;
import com.chatty.android.chattyClient.view.write.WriteActivity;

public class MainPresenter implements ExtendedPresenter<State> {
  private MainActivity view;

  private MainPresenter(MainActivity view) {
    this.view = view;
  }

  public void construct() {
    StateManagerWrapper.subscribe(this::stateListener);

    MainActivityProps props = new MainActivityProps();
    props.timeline = StateManagerWrapper.getState().getTimeline();
    props.handleClickWriteButton = this::handleClickWriteButton;
    props.handleClickTimelineEntry = this::handleClickTimelineEntry;

    this.view.initialRender(props);
    presenterDidMount();
  }

  public void presenterDidMount() {
    try {
      StateManagerWrapper.dispatch(DiaryAction.requestGetDiaries());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void handleClickTimelineEntry(View view, TimelineEntry entry) {
    Intent intent = new Intent(this.view, DiaryDetailActivity.class);
    intent.putExtra("diaryId", entry.getDiaryId());
    this.view.startActivity(intent);
  }

  private void handleClickWriteButton(View v) {
    Intent intent = new Intent(this.view, WriteActivity.class);
    view.startActivity(intent);
  }

  public Object stateListener(State state) {
    StateManagerWrapper.log(this.getClass().getSimpleName(), state);

    MainActivityProps props = new MainActivityProps();
    props.timeline = state.getTimeline();

    this.view.update(
      props
    );

    return null;
  }

  public static MainPresenter of(MainActivity activity) {
    MainPresenter presenter = new MainPresenter(activity);
    presenter.construct();
    return presenter;
  }
}
