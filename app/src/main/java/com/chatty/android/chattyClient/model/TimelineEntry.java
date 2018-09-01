package com.chatty.android.chattyClient.model;

import java.util.Date;

public class TimelineEntry {
  public int getDiaryId() {
    return diaryId;
  }

  public void setDiaryId(int diaryId) {
    this.diaryId = diaryId;
  }

  public int diaryId;
  public String imgUrl;
  public Date date;
  public String content;

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
