package com.chatty.android.chattyClient.model.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimelineResponse {
  public Partner partner;
  public List<Diary> diaries;

  public Partner getPartner() {
    return partner;
  }

  public List<Diary> getDiaries() {
    return diaries;
  }

  public class Partner {
    public Integer id;
    public String name;
    public Integer days_together;
    public Integer diary_count;
  }

  public class Diary {
    public Integer id;
    public Date created_at;
    public List<Answer> answer;
  }

  public class Answer {
    public Integer id;
    public String image;
    public String label;
  }
}
