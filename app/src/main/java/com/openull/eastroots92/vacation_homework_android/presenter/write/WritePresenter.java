package com.openull.eastroots92.vacation_homework_android.presenter.write;

import com.openull.eastroots92.vacation_homework_android.apis.HomeworkApis;
import com.openull.eastroots92.vacation_homework_android.models.requests.ChatRequest;
import com.openull.eastroots92.vacation_homework_android.models.responses.ChatResponse;
import com.openull.eastroots92.vacation_homework_android.utils.ApiUtils;

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


}
