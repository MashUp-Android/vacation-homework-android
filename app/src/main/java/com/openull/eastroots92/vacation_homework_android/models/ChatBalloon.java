package com.openull.eastroots92.vacation_homework_android.models;

import java.util.Calendar;

public class ChatBalloon {
  private String speech;
  private String username;
  private boolean isRead;

  public Calendar getCalendar() {
    return calendar;
  }

  public void setCalendar(Calendar calendar) {
    this.calendar = calendar;
  }

  private Calendar calendar;

  public String getSpeech() {
    return speech;
  }

  public void setSpeech(String speech) {
    this.speech = speech;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public boolean isRead() {
    return isRead;
  }

  public void setRead(boolean read) {
    isRead = read;
  }
}
