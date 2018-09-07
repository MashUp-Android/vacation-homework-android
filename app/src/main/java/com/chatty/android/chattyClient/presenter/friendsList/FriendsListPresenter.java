package com.chatty.android.chattyClient.presenter.friendsList;

import android.os.Bundle;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedPresenter;
import com.chatty.android.chattyClient.model.FriendItemEntry;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.module.StateManagerWrapper;
import com.chatty.android.chattyClient.view.friendsList.FriendsListActivityProps;
import com.chatty.android.chattyClient.view.friendsList.FriendsListActivityState;

import java.util.ArrayList;

public class FriendsListPresenter extends ExtendedPresenter<FriendsListActivityProps, FriendsListActivityState, State> {
  @Override
  public FriendsListActivityProps initiate() {
    return null;
  }

  @Override
  public FriendsListActivityProps stateListener(State state) {
    StateManagerWrapper.log(this.getClass().getSimpleName(), state);

    FriendsListActivityProps props = new FriendsListActivityProps();
    props.friendsList = (ArrayList<FriendItemEntry>) state.getFriends();
    return props;
  }
}
