package com.chatty.android.chattyClient.model.response;

public class ChatResponse {
  public String diary_id;
  public Question question;

  public class Question {
    public String question_id;
    public String message;
  }
}
