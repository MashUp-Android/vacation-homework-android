package com.chatty.android.chattyClient.presenter.main;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.chatty.android.chattyClient.module.StateManager.StateManager;
import com.chatty.android.chattyClient.state.action.DiaryAction;
import com.chatty.android.chattyClient.state.reducers.Reducers;
import com.chatty.android.chattyClient.view.main.MainActivity;
import com.chatty.android.chattyClient.view.write.WriteActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainPresenter {
  private static final String MAIN_PRESENTER = "MAIN_PRESENTER";
  private MainActivity view;
  private ArrayList<String> diaryList;

  public MainPresenter(MainActivity view) {
    this.view = view;
  }

  public void construct() {
    StateManager.initialize(Reducers::reduce);
    StateManager.subscribe(MainPresenter::stateListener);

    view.render(this::handleClickWriteButton);
    presenterDidMount();
  }

  public void presenterDidMount() {
    try {
      StateManager.dispatch(DiaryAction.requestGetDiaries());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void handleClickWriteButton(View v) {
    Intent intent = new Intent(view, WriteActivity.class);
    view.startActivity(intent);
  }

  public static Object stateListener(HashMap state) {
    StateManager.printMap(state);
    return null;
  }
}
