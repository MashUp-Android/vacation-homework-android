package com.chatty.android.chattyClient.presenter.addFriend;

import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedPresenter;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.model.request.NewPartnerRequest;
import com.chatty.android.chattyClient.module.StateManagerWrapper;
import com.chatty.android.chattyClient.view.addFriend.AddFriendActivity;
import com.chatty.android.chattyClient.view.addFriend.AddFriendActivityProps;
import com.chatty.android.chattyClient.view.addFriend.AddFriendActivityState;
import com.chatty.android.chattyClient.view.friendsSetting.FriendsSettingActivity;
import okhttp3.MultipartBody;

public class AddFriendPresenter extends ExtendedPresenter<AddFriendActivityProps, AddFriendActivityState, State> {
  @Override
  public AddFriendActivityProps initiate() {
    AddFriendActivityProps props = new AddFriendActivityProps();
    props.handleClickImageButtonBack = this::handleClickImageButtonBack;
    props.handleClickImageViewProfile = this::handleClickImageViewProfile;
    props.handleClickButtonAddProfile = this::handleClickButtonAddProfile;
    return props;
  }

  private View.OnClickListener handleClickButtonAddProfile(Runnable r) {
    return (v) -> r.run();
  }

  public AddFriendActivityProps stateListener(State state) {
    return null;
  }

  private View.OnClickListener handleClickImageButtonBack(Runnable r) {
    return (v) -> r.run();
  }

  private View.OnClickListener handleClickImageViewProfile(Runnable r) {
    Log.v("동작", "동작완료");
    return (v) -> r.run();
  }

  private View.OnClickListener handleClickButtonAddProfile(AddFriendActivity activity) {
    return (v) -> {
      String currentName = activity.editTextProfileName.getText().toString();
      String currentBio = activity.editTextProfileBio.getText().toString();
      NewPartnerRequest currentNewPartner = new NewPartnerRequest(currentName, currentBio);
      boolean hasFreind = StateManagerWrapper.getState().hasFriend;
      activity.startActivity(new Intent(activity, FriendsSettingActivity.class));
    };
  }
}
