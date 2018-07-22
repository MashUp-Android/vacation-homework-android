package com.openull.eastroots92.vacation_homework_android.ui.write;

import android.view.View;

import com.openull.eastroots92.vacation_homework_android.api.HomeworkApis;
import com.openull.eastroots92.vacation_homework_android.model.ChatBalloon;
import com.openull.eastroots92.vacation_homework_android.model.requests.ChatRequest;
import com.openull.eastroots92.vacation_homework_android.model.responses.ChatResponse;
import com.openull.eastroots92.vacation_homework_android.util.ApiUtils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WritePresenter {
  private static final String FALSE= "FALSE";
  private static final String IS_INITIALIZED = "IS_INITIALIZED";
  private static final String TRUE = "TRUE";

  private WriteActivity view;
  private HomeworkApis homeworkApis;
  private Map<String, String> state;

  public WritePresenter(WriteActivity v) {
    this.view = v;
    this.state = new HashMap<>();
    this.state.put(IS_INITIALIZED, FALSE);
  }

  public void init() {
    homeworkApis = ApiUtils.getHomeworkApis();
    view.initView();
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
