package com.chatty.android.chattyClient.presenter.diaryDetail;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedPresenter;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.externalModules.ReduxJava.ReduxJavaAndroidConnector;
import com.chatty.android.chattyClient.state.Store;
import com.chatty.android.chattyClient.state.action.DiaryAction;
import com.chatty.android.chattyClient.view.diaryDetail.DiaryDetailActivity;
import com.chatty.android.chattyClient.view.diaryDetail.DiaryDetailActivityProps;
import com.chatty.android.chattyClient.view.diaryDetail.DiaryDetailActivityState;

public class DiaryDetailPresenter
  extends ExtendedPresenter<DiaryDetailActivityProps, DiaryDetailActivityState, State> {
  private DiaryDetailActivity view;

  @Override
  public DiaryDetailActivityProps initiate() {
    int diaryId = this.activity.getIntent().getIntExtra("diaryId", 0);
    
    try {
      Store.dispatch(
        DiaryAction.requestGetDiaryDetail(diaryId)
      );
    } catch (Exception e) {
      e.printStackTrace();
    }

    DiaryDetailActivityProps props = new DiaryDetailActivityProps();
    props.diaries = Store.getState().diary.diaries;
    return props;
  }

  public DiaryDetailActivityProps stateListener(State state) {
    Store.printState(this.getClass().getSimpleName(), state);

    DiaryDetailActivityProps props = new DiaryDetailActivityProps();
    props.diaries = state.diary.diaries;
    return props;
  }
}
