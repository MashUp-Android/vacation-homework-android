package com.openull.eastroots92.vacation_homework_android.presenter.write;

import android.view.View;

import com.openull.eastroots92.vacation_homework_android.apis.HomeworkApis;
import com.openull.eastroots92.vacation_homework_android.models.ChatBalloon;
import com.openull.eastroots92.vacation_homework_android.models.requests.ChatRequest;
import com.openull.eastroots92.vacation_homework_android.models.responses.ChatResponse;
import com.openull.eastroots92.vacation_homework_android.ui.activity.WriteActivity;
import com.openull.eastroots92.vacation_homework_android.utils.ApiUtils;

import java.util.Calendar;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WritePresenter implements WriteContract.Presenter {
  WriteActivity view;
  HomeworkApis homeworkApis;

  public WritePresenter(WriteActivity v) {
    this.view = v;
  }

  @Override
  public void init() {
    homeworkApis = ApiUtils.getHomeworkApis();
    this.view.initView();
  }

  public void dispatchSpeech(String contents) {
    String _contents = "contents " + contents;
    System.out.println("_contents " + _contents);

    homeworkApis.postChat(new ChatRequest(_contents))
      .enqueue(new Callback<ChatResponse>() {
        @Override
        public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
          System.out.println("dispatch speech success");
          // todo: make sure the response has the right value,

          Calendar calendar = Calendar.getInstance();
          ChatBalloon chatBalloon = new ChatBalloon();
          chatBalloon.setSpeech("server response");
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

    return new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String speech = view.writeInputEditText.getText()
          .toString();

        System.out.println(speech);

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
