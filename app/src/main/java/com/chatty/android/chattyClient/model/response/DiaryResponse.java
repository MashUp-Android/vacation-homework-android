package com.chatty.android.chattyClient.model.response;

import java.util.Date;
import java.util.List;

public class DiaryResponse {
  public List<Question> questions;
  public List<Answer> answers;

  public class Question {
    public Integer question_id;
    public String message;
  }

  public class Answer {
    public Date created_at;
    public String image;
    public String label;
    public Integer question;
  }
}
