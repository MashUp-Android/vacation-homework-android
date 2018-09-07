package com.chatty.android.chattyClient.presenter.setting;

import android.os.Bundle;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedPresenter;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.view.setting.SettingActivityProps;
import com.chatty.android.chattyClient.view.setting.SettingActivityState;

public class SettingPresenter
  extends ExtendedPresenter<SettingActivityProps, SettingActivityState, State> {

  @Override
  public SettingActivityProps initiate() {
    return null;
  }

  public SettingActivityProps stateListener(State state) {
    return null;
  }
}
