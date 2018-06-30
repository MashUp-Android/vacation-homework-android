package com.openull.eastroots92.vacation_homework_android.presenter.write;

public class WritePresenter implements WriteContract.Presenter {
  WriteContract.View view;

  public WritePresenter(WriteContract.View v) {
    view = v;
  }

  @Override
  public void init() {
    System.out.println("init");
    view.initView();
  }
}
