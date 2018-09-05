package com.chatty.android.chattyClient.presenter.addFriend;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.chatty.android.chattyClient.App;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedPresenter;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.model.request.NewPartnerRequest;
import com.chatty.android.chattyClient.module.StateManagerWrapper;
import com.chatty.android.chattyClient.state.action.DiaryAction;
import com.chatty.android.chattyClient.state.action.PartnerAction;
import com.chatty.android.chattyClient.view.addFriend.AddFriendActivity;
import com.chatty.android.chattyClient.view.addFriend.AddFriendActivityProps;
import com.chatty.android.chattyClient.view.friendsSetting.FriendsSettingActivity;

import java.util.function.Supplier;

import okhttp3.MultipartBody;

public class AddFriendPresenter implements ExtendedPresenter<State> {
//  private boolean isProfile = false;
//  private boolean isName = false;
//  private MultipartBody.Part profileImage;

  public AddFriendPresenter(AddFriendActivity addFriendActivity) {
    StateManagerWrapper.subscribe(this::stateListener);
    AddFriendActivityProps props = new AddFriendActivityProps();
    props.handleClickImageButtonBack = this::handleClickImageButtonBack;
    props.handleClickImageViewProfile = this::handleClickImageViewProfile;
    props.handleClickButtonAddProfile = this::handleClickButtonAddProfile;

    addFriendActivity.initialRender(props);
  }

  public Object stateListener(State state) {
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

  private TextWatcher handleChangeText() {
    return new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().length() > 0) {

        }
      }

      @Override
      public void afterTextChanged(Editable s) {
      }
    };
  }

  public void validateName(EditText editTextProfileName) {
//    EditText profileName = editTextProfileName;
//    profileName.addTextChangedListener(new TextWatcher() {
//      @Override
//      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//      }
//
//      private void checkSubmitButton() {
//        if(isProfile && isName) {
//          view.activateSubmitButton(true);
//        } else {
//          view.activateSubmitButton(false);
//        }
//      }
//
//      @Override
//      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//        if (charSequence.toString().length() > 0) {
//          isName = true;
//        } else {
//          isName = false;
//        }
//        this.checkSubmitButton();
//      }
//
//      @Override
//      public void afterTextChanged(Editable editable) {
//      }
//    });
  }

  class LocalState {

  }

  public void selectImage(Uri uri) {
//    Uri currentImage = uri;
//    view.updateProfile(uri);
  }
}
