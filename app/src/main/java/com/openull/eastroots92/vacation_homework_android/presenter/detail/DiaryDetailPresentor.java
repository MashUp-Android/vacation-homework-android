package com.openull.eastroots92.vacation_homework_android.presenter.detail;



public class DiaryDetailPresentor implements DiaryDetailContract.Presentor{
  private  DiaryDetailContract.View view;

  @Override
  public void init(DiaryDetailContract.View view) {
    this.view = view;

  }

}
