package com.openull.eastroots92.vacation_homework_android.presenter.write;

import android.view.View;

import com.openull.eastroots92.vacation_homework_android.apis.HomeworkApis;
import com.openull.eastroots92.vacation_homework_android.models.ChatBalloon;
import com.openull.eastroots92.vacation_homework_android.models.requests.ChatRequest;
import com.openull.eastroots92.vacation_homework_android.models.responses.ChatResponse;
import com.openull.eastroots92.vacation_homework_android.ui.activity.WriteActivity;
import com.openull.eastroots92.vacation_homework_android.utils.ApiUtils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WritePresenter implements WriteContract.Presenter {
  final String FALSE= "FALSE";
  final String IS_INITIALIZED = "IS_INITIALIZED";
  final String TRUE = "TRUE";

  WriteActivity view;
  HomeworkApis homeworkApis;
  Map<String, String> state;

  public WritePresenter(WriteActivity v) {
    this.view = v;
    this.state = new HashMap<>();
    this.state.put(IS_INITIALIZED, FALSE);
  }

  @Override
  public void init() {
    homeworkApis = ApiUtils.getHomeworkApis();
    this.view.initView();
    dispatchInitChat();
  }

  private void dispatchInitChat() {
    homeworkApis.postInitChat()
      .enqueue(new Callback<ChatResponse>() {
        @Override
        public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
          Calendar calendar = Calendar.getInstance();
          ChatBalloon chatBalloon = new ChatBalloon();
          chatBalloon.setSpeech("Initial question from server (not yet available)");
          chatBalloon.setCalendar(calendar);
          view.appendChatBalloon(chatBalloon);
        }

        @Override
        public void onFailure(Call<ChatResponse> call, Throwable t) {
          System.out.println("dispatch speech error");
        }
      });

    this.state.put(IS_INITIALIZED, TRUE);
  }

  public void dispatchSpeech(String contents) {
    String _contents = "contents " + contents;
    System.out.println("_contents " + _contents);

    homeworkApis.postChat(new ChatRequest(_contents))
      .enqueue(new Callback<ChatResponse>() {
        @Override
        public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
          // todo: make sure the response has the right value,
          Calendar calendar = Calendar.getInstance();
          ChatBalloon chatBalloon = new ChatBalloon();
          chatBalloon.setSpeech("Question from server (not yet available)");
          chatBalloon.setCalendar(calendar);
          view.appendChatBalloon(chatBalloon);
        }

        @Override
        public void onFailure(Call<ChatResponse> call, Throwable t) {
          System.out.println("dispatch speech error");
        }
      });
  }

  public View.OnClickListener handleClickWriteSubmit() {
    WriteActivity view = (WriteActivity) this.view;
    WritePresenter that = this;

    return new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (that.state.get(IS_INITIALIZED) == FALSE) return;

        String speech = view.writeInputEditText.getText()
          .toString();

        Calendar calendar = Calendar.getInstance();
        ChatBalloon chatBalloon = new ChatBalloon();
        chatBalloon.setSpeech(speech);
        chatBalloon.setCalendar(calendar);
        view.appendChatBalloon(chatBalloon);

        dispatchSpeech(speech);
      }
    };
  }

}
