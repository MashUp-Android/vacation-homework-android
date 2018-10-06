package com.chatty.android.chattyClient.view.write;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.widget.EditText;

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
  public Consumer<EditText> handleClickWriteSubmitButton;
  public SelectImageButton<Context,FragmentManager> handleClickSelectImageButton;

  @FunctionalInterface
  public interface SelectImageButton<T1, T2> {
    /**
     * @param var1
     * @param var2
     */
    void accept(T1 var1, T2 var2);
  }
}
