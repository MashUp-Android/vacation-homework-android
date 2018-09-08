package com.chatty.android.chattyClient.view.write;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.Props;
import com.chatty.android.chattyClient.model.ChatBalloon;
import com.chatty.android.chattyClient.model.TimelineEntry;
import com.chatty.android.chattyClient.presenter.main.TimelineRecyclerViewAdapter;
import com.chatty.android.chattyClient.presenter.write.WritePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class WriteActivityProps extends Props {
  public String writeDiaryId = "";
  public List<ChatBalloon> chatBalloons = new ArrayList<>();
  public Consumer<String> handleClickWriteSubmitButton;
}
