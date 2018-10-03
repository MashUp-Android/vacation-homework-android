package com.chatty.android.chattyClient.model;

import com.chatty.android.chattyClient.model.response.DiaryResponse;
import com.chatty.android.chattyClient.model.Diary;

import java.util.ArrayList;
import java.util.List;

public class State {
  public Chat chat = new Chat();
  public Diary diary = new Diary();
  public Friend friend = new Friend();

  public class Chat {
    public ArrayList<ChatBalloon> chatBalloons = new ArrayList<>();
    public String writeDiaryId = "";
  }

  public class Diary {
    public ArrayList<TimelineEntry> timeline = new ArrayList<>();
    public ArrayList<com.chatty.android.chattyClient.model.Diary> diaries = new ArrayList<>();
    public PartnerProfileDetailEntry partnerProfileDetail = new PartnerProfileDetailEntry();
  }

  public class Friend {
    public boolean hasFriend = false;
    public ArrayList<FriendItemEntry> friends = new ArrayList<>();
    public Partner partner = new Partner();
    public PartnerProfileDetailEntry partnerProfileDetail = new PartnerProfileDetailEntry();
  }
}
