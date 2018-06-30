package com.openull.eastroots92.vacation_homework_android.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.openull.eastroots92.vacation_homework_android.R;
import com.openull.eastroots92.vacation_homework_android.presenter.write.WriteContract;
import com.openull.eastroots92.vacation_homework_android.presenter.write.WritePresenter;

public class WriteActivity extends AppCompatActivity implements WriteContract.View {
  WritePresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_write);

    loadDependencies();

    initView();
  }

  private void loadDependencies() {
    presenter = new WritePresenter(this);
  }

  @Override
  public void initView() {
    System.out.println("123123");
  }

}
