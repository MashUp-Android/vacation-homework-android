package com.openull.eastroots92.vacation_homework_android.models.requests;

public class ChatRequest {
  private String contents;

  public ChatRequest(String contents) {
    this.contents = contents;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }
}
