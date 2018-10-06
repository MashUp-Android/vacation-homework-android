package com.chatty.android.chattyClient.model;

import android.net.Uri;

import java.util.Calendar;

public class ChatBalloon {
  private Calendar calendar;
  public String speech;
  public String username;
  public boolean isRead;
  public String selectImage;

  public void setSelectImage(String selectImage) {
    this.selectImage = selectImage;
  }

  public String getSelectImage() {
    return selectImage;
  }
}
