package com.chatty.android.chattyClient.presenter.setting;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedPresenter;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.module.StateManagerWrapper;
import com.chatty.android.chattyClient.view.setting.SettingActivity;

public class SettingPresenter implements ExtendedPresenter<State> {
  private SettingActivity view;

  public SettingPresenter(SettingActivity view) {
    this.view = view;
    StateManagerWrapper.subscribe(this::stateListener);
    view.render();
  }

  public Object stateListener(State state) {
    return null;
  }
}
