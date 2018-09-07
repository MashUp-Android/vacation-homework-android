package com.chatty.android.chattyClient.presenter.friendsSetting;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedPresenter;
import com.chatty.android.chattyClient.model.PartnerProfileDetailEntry;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.module.StateManagerWrapper;
import com.chatty.android.chattyClient.view.friendsSetting.FriendsSettingActivityProps;
import com.chatty.android.chattyClient.view.friendsSetting.FriendsSettingActivityState;

public class FriendsSettingPresenter
  extends ExtendedPresenter<FriendsSettingActivityProps, FriendsSettingActivityState, State> {

  @Override
  public FriendsSettingActivityProps initiate() {
    return null;
  }

  public FriendsSettingActivityProps stateListener(State state) {
    StateManagerWrapper.log(this.getClass().getSimpleName(), state);

    PartnerProfileDetailEntry partnerProfileDetail = state.getPartnerProfileDetail();
    return null;
  }
}
