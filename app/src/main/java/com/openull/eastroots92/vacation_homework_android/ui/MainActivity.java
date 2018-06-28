package com.openull.eastroots92.vacation_homework_android.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.openull.eastroots92.vacation_homework_android.R;
import com.openull.eastroots92.vacation_homework_android.ui.presenter.MainContract;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter.init();
    }


    @Override
    public void initView() {

    }
}
