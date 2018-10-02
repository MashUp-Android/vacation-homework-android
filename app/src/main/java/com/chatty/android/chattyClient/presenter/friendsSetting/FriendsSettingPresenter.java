package com.chatty.android.chattyClient.presenter.friendsSetting;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedPresenter;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.externalModules.ReduxJava.ReduxJavaAndroidConnector;
import com.chatty.android.chattyClient.state.Store;
import com.chatty.android.chattyClient.state.action.PartnerAction;
import com.chatty.android.chattyClient.view.friendsSetting.FriendsSettingActivityProps;
import com.chatty.android.chattyClient.view.friendsSetting.FriendsSettingActivityState;

public class FriendsSettingPresenter
  extends ExtendedPresenter<FriendsSettingActivityProps, FriendsSettingActivityState, State> {

  @Override
  public FriendsSettingActivityProps initiate() {
    try {
      Store.dispatch(PartnerAction.requestGetPartnerProfileDetail());
    } catch (Exception e) {
      e.printStackTrace();
    }

    FriendsSettingActivityProps props = new FriendsSettingActivityProps();
    props.friendDetail = Store.getState().diary.partnerProfileDetail;
    return props;
  }

  public FriendsSettingActivityProps stateListener(State state) {
    ReduxJavaAndroidConnector.log(this.getClass().getSimpleName(), state);

    FriendsSettingActivityProps props = new FriendsSettingActivityProps();
    props.friendDetail = state.diary.partnerProfileDetail;

    return props;
  }
}
