package com.chatty.android.chattyClient.presenter.friendsList;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedPresenter;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.externalModules.ReduxJava.ReduxJavaAndroidConnector;
import com.chatty.android.chattyClient.state.Store;
import com.chatty.android.chattyClient.state.action.PartnerAction;
import com.chatty.android.chattyClient.view.friendsList.FriendsListActivityProps;
import com.chatty.android.chattyClient.view.friendsList.FriendsListActivityState;

public class FriendsListPresenter extends ExtendedPresenter<FriendsListActivityProps, FriendsListActivityState, State> {
  @Override
  public FriendsListActivityProps initiate() {
    try {
      Store.dispatch(PartnerAction.requestGetFriendsList());
    } catch (Exception e) {
      e.printStackTrace();
    }

    FriendsListActivityProps props = new FriendsListActivityProps();
    props.friendsList = Store.getState().friend.friends;
    return props;
  }

  @Override
  public FriendsListActivityProps stateListener(State state) {
    Store.printState(this.getClass().getSimpleName(), state);

    FriendsListActivityProps props = new FriendsListActivityProps();
    props.friendsList = state.friend.friends;
    return props;
  }
}
