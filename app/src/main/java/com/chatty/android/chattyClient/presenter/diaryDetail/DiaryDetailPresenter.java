package com.chatty.android.chattyClient.presenter.diaryDetail;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedPresenter;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.module.StateManagerWrapper;
import com.chatty.android.chattyClient.view.diaryDetail.DiaryDetailActivity;
import com.chatty.android.chattyClient.view.diaryDetail.DiaryDetailActivityProps;
import com.chatty.android.chattyClient.view.diaryDetail.DiaryDetailActivityState;

public class DiaryDetailPresenter
  extends ExtendedPresenter<DiaryDetailActivityProps, DiaryDetailActivityState, State> {
  private DiaryDetailActivity view;

  @Override
  public DiaryDetailActivityProps initiate() {
    DiaryDetailActivityProps props = new DiaryDetailActivityProps();
    props.diaries = StateManagerWrapper.getState()
      .getDiaries();
    return props;
  }

  public DiaryDetailActivityProps stateListener(State state) {
    StateManagerWrapper.log(this.getClass().getSimpleName(), state);

    DiaryDetailActivityProps props = new DiaryDetailActivityProps();
    props.diaries = state.getDiaries();
    return props;
  }
}
