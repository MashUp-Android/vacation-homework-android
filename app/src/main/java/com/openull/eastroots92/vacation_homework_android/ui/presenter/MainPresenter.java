package com.openull.eastroots92.vacation_homework_android.ui.presenter;

public class MainPresenter implements MainContract.Presenter {
    MainContract.View view;
    @Override
    public void init() {
        view.initView();
    }
}
