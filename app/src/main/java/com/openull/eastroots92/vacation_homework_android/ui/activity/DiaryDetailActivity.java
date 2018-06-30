package com.openull.eastroots92.vacation_homework_android.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.openull.eastroots92.vacation_homework_android.R;
import com.openull.eastroots92.vacation_homework_android.models.Diary;
import com.openull.eastroots92.vacation_homework_android.presenter.detail.DiaryDetailContract;
import com.openull.eastroots92.vacation_homework_android.presenter.detail.DiaryDetailPresentor;
import com.openull.eastroots92.vacation_homework_android.ui.adapter.DiaryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiaryDetailActivity extends AppCompatActivity implements DiaryDetailContract.View {
  
  @BindView(R.id.textView_name)
  TextView name;

  @BindView(R.id.textView_date)
  TextView date;

  @BindView(R.id.textView_weather)
  TextView weather;

  @BindView(R.id.recyclerView_diary)
  RecyclerView recyclerView;
  private RecyclerView.Adapter diaryAdapter;


  private List<Diary> diaries;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_diary_detail);
    ButterKnife.bind(this);

    initView();
  }

  public void initView() {
    diaries = new ArrayList<>();
    setDummyData();
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    diaryAdapter = new DiaryAdapter(getApplicationContext(), diaries);
    recyclerView.setAdapter(diaryAdapter);

    initHeader();
  }

  private void initHeader() {
    Diary currentDiary = diaries.get(0);

    name.setText(currentDiary.getUsername());
    date.setText(currentDiary.getDate());
    weather.setText(currentDiary.getWeather());

  }

  private void setDummyData() {
    for(int num = 0 ; num<5 ; num++) {
      Diary diary = new Diary();
      diary.setUsername("이동근");
      diary.setDate("2018/06/30");
      diary.setWeather("비온다");
      diary.setQuestion("질문 " + num);
      diary.setAnswer("대답" + num);

      diaries.add(diary);
    }
  }
}
