package com.openull.eastroots92.vacation_homework_android.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.openull.eastroots92.vacation_homework_android.R;
import com.openull.eastroots92.vacation_homework_android.presenter.detail.DiaryDetailContract;

import butterknife.ButterKnife;

public class DiaryDetailActivity extends AppCompatActivity {

  private  DiaryDetailContract.Presentor presentor;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_diary_detail);
    ButterKnife.bind(this);

  }

}
