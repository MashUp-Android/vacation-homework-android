package com.chatty.android.chattyClient.view.addFriend;

import android.view.View;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.Props;

import java.util.function.Function;

import okhttp3.MultipartBody;

public class AddFriendActivityState {
  public boolean isProfile = false;
  public boolean isName = false;
  public MultipartBody.Part profileImage;
}
