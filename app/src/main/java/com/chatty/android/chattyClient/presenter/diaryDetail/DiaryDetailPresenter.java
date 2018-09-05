package com.chatty.android.chattyClient.presenter.diaryDetail;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.chatty.android.chattyClient.model.Diary;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.model.TimelineEntry;
import com.chatty.android.chattyClient.module.StateManagerWrapper;
import com.chatty.android.chattyClient.presenter.main.MainPresenter;
import com.chatty.android.chattyClient.state.action.DiaryAction;
import com.chatty.android.chattyClient.view.diaryDetail.DiaryDetailActivity;
import com.chatty.android.chattyClient.view.diaryDetail.DiaryDetailActivityProps;

import java.util.ArrayList;

public class DiaryDetailPresenter {
  private DiaryDetailActivity view;

  public DiaryDetailPresenter(DiaryDetailActivity diaryDetailActivity) {
    this.view = diaryDetailActivity;
    StateManagerWrapper.subscribe(this::stateListener);

    DiaryDetailActivityProps props = new DiaryDetailActivityProps();
    props.diaries = StateManagerWrapper.getState()
      .getDiaries();

    this.view.initialRender(props);

    Intent intent = this.view.getIntent();
    int diaryId = intent.getIntExtra("diaryId", 0);

    try {
      StateManagerWrapper.dispatch(DiaryAction.requestGetDiaryDetail(diaryId));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Object stateListener(State state) {
    StateManagerWrapper.log(this.getClass().getSimpleName(), state);

    DiaryDetailActivityProps props = new DiaryDetailActivityProps();
    props.diaries = state.getDiaries();

    this.view.update(props);
    return null;
  }
}
