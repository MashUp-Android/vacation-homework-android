package com.chatty.android.chattyClient.presenter.setting;

import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.module.StateManagerWrapper;
import com.chatty.android.chattyClient.view.setting.SettingActivity;

public class SettingPresenter {
  private SettingActivity view;

  public SettingPresenter(SettingActivity view) {
    this.view = view;
  }

  public void construct() {
    StateManagerWrapper.subscribe(this::stateListener);
    view.render();
  }

  private Object stateListener(State state) {
    return null;
  }
}
