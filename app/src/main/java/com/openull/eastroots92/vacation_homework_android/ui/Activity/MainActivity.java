package com.openull.eastroots92.vacation_homework_android.ui.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.openull.eastroots92.vacation_homework_android.R;
import com.openull.eastroots92.vacation_homework_android.presenter.Main.MainContract;
import com.openull.eastroots92.vacation_homework_android.ui.Adapter.UserInfoAdapter;

public class MainActivity extends AppCompatActivity implements MainContract.View {

  private RecyclerView recyclerView;
  private RecyclerView.Adapter userInfoAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initView();
  }

  @Override
  public void initView() {
    recyclerView = findViewById(R.id.recyclerView_list);

    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    userInfoAdapter = new UserInfoAdapter();

    recyclerView.setAdapter(userInfoAdapter);
  }

}
