package com.openull.eastroots92.vacation_homework_android.ui.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.openull.eastroots92.vacation_homework_android.R;
import com.openull.eastroots92.vacation_homework_android.apis.HomeworkApis;
import com.openull.eastroots92.vacation_homework_android.models.response.TempResponse;
import com.openull.eastroots92.vacation_homework_android.presenter.Main.MainContract;
import com.openull.eastroots92.vacation_homework_android.utils.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainContract.View {

  private MainContract.Presenter presenter;
  private HomeworkApis homeworkApis;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    Log.e("1", "power");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    homeworkApis = ApiUtils.getHomeworkApis();

    tempRequest();
  }

  @Override
  public void initView() {

  }

  public void tempRequest() {
    Log.e("1", "tempRequest");
    homeworkApis.getTemp()
      .enqueue(new Callback<TempResponse>() {
        @Override
        public void onResponse(Call<TempResponse> call, Response<TempResponse> response) {
//          System.out.println("123 " + response);
          Log.e("1", "inner ");
          if (response.isSuccessful()) {
//            mAdapter.updateAnswers(response.body().getItems());
//            Log.d("MainActivity", "posts loaded from API");
          } else {
            int statusCode = response.code();
            // handle request errors depending on status code
          }
        }

      @Override
      public void onFailure(Call<TempResponse> call, Throwable t) {
          Log.e("1", "fail" + t);
//        showErrorMessage();
//        Log.d("MainActivity", "error loading from API");
      }
    });
  }
}
