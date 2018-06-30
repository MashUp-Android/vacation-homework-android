package com.openull.eastroots92.vacation_homework_android.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.openull.eastroots92.vacation_homework_android.R;
import com.openull.eastroots92.vacation_homework_android.presenter.write.WriteContract;
import com.openull.eastroots92.vacation_homework_android.presenter.write.WritePresenter;
import com.openull.eastroots92.vacation_homework_android.ui.adapter.DialogueAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WriteActivity extends AppCompatActivity implements WriteContract.View {
  @BindView(R.id.recyclerView_dialogue)
  RecyclerView recyclerView;
  RecyclerView.Adapter dialogueAdapter;

  WritePresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_write);

    loadDependencies();
    System.out.println("123123 " + recyclerView);
    presenter.init();
  }

  private void loadDependencies() {
    presenter = new WritePresenter(this);
    ButterKnife.bind(this);
  }

  @Override
  public void initView() {
    System.out.println("init view");
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    dialogueAdapter = new DialogueAdapter();
    recyclerView.setAdapter(dialogueAdapter);
  }
}
