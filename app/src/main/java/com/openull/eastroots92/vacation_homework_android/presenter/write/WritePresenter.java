package com.openull.eastroots92.vacation_homework_android.presenter.write;

import android.view.View;

import com.openull.eastroots92.vacation_homework_android.apis.HomeworkApis;
import com.openull.eastroots92.vacation_homework_android.models.ChatBalloon;
import com.openull.eastroots92.vacation_homework_android.models.requests.ChatRequest;
import com.openull.eastroots92.vacation_homework_android.models.responses.ChatResponse;
import com.openull.eastroots92.vacation_homework_android.ui.activity.WriteActivity;
import com.openull.eastroots92.vacation_homework_android.utils.ApiUtils;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WritePresenter implements WriteContract.Presenter {
  WriteContract.View view;
  HomeworkApis homeworkApis;

  public WritePresenter(WriteContract.View v) {
    view = v;
  }

  @Override
  public void init() {
    homeworkApis = ApiUtils.getHomeworkApis();
    view.initView();
  }

  public void dispatchSpeech(String contents) {
    String _contents = "contents " + contents;
    System.out.println("_contents " + _contents);

    homeworkApis.postChat(new ChatRequest(_contents))
      .enqueue(new Callback<ChatResponse>() {
        @Override
        public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
          System.out.println("123123 aab");
        }
        @Override
        public void onFailure(Call<ChatResponse> call, Throwable t) {
          System.out.println("123123 bbc");
        }
      });
  }

  public View.OnClickListener handleClickWriteSubmit(WriteActivity activity) {
    return new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String speech = activity.writeInputEditText.getText()
          .toString();

      System.out.println(speech);

      Calendar calendar = Calendar.getInstance();
      ChatBalloon chatBalloon = new ChatBalloon();
      chatBalloon.setSpeech(speech);
      chatBalloon.setCalendar(calendar);

      activity.chatBalloons.add(chatBalloon);
      activity.dialogueAdapter.notifyItemInserted(activity.chatBalloons.size() - 1);
//      presenter.dispatchSpeech(speech);
      }
    };
  }

}
