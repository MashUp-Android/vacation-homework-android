package com.chatty.android.chattyClient.model;

import java.util.Date;

public class TimelineEntry {
  private String imgUrl;
  private Date date;
  private String content;

  public TimelineEntry(String imgUrl, Date date, String content) {
    this.imgUrl = imgUrl;
    this.date = date;
    this.content = content;
  }

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
