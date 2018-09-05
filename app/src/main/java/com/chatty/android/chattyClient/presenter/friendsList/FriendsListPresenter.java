package com.chatty.android.chattyClient.presenter.friendsList;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedPresenter;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.module.StateManagerWrapper;
import com.chatty.android.chattyClient.view.friendsList.FriendsListActivity;

public class FriendsListPresenter implements ExtendedPresenter<State> {
  private FriendsListActivity view;
  public FriendsListPresenter(FriendsListActivity friendsListActivity) {
    this.view = friendsListActivity;
  }

  public void construct() {
    StateManagerWrapper.subscribe(this::stateListener);
    view.render();
  }

  @Override
  public Object stateListener(State state) {
    return null;
  }
}
