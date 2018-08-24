package com.chatty.android.chattyClient.view.diaryDetail;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.model.Diary;
import com.chatty.android.chattyClient.presenter.diaryDetail.DiaryAdapter;
import com.chatty.android.chattyClient.presenter.diaryDetail.DiaryDetailPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiaryDetailActivity extends AppCompatActivity {
  private DiaryDetailPresenter presenter;
  private static final String DIARY_TITLE = "Diary";

  @BindView(R.id.button_timeline_left)
  public ImageButton backButton;

  @BindView(R.id.profile_avatar_img)
  public ImageView profileAvatarImg;

  @BindView(R.id.textView_diary_partner_name)
  public TextView name;

  @BindView(R.id.textView_diary_date)
  public TextView date;

  @BindView(R.id.imageView_diary_weather)
  public ImageView weather;

  @BindView(R.id.imageView_diary_emotion)
  public ImageView emotions;

  @BindView(R.id.recyclerView_diary)
  RecyclerView recyclerView;
  private RecyclerView.Adapter diaryAdapter;

  private List<Diary> diaries;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.item_diary_detail);

    loadDependencies();

    construct();
    initView();
  }

  private void construct() {
    circleImageView();
    viewBackButton();

    TextView textView = findViewById(R.id.textView_timeline_title);
    textView.setText(DIARY_TITLE);
  }

  private void viewBackButton() {
    backButton.setOnClickListener((view) -> finish());
  }

  private void loadDependencies() {
    presenter = new DiaryDetailPresenter(this);
    ButterKnife.bind(this);
  }

  public void initView() {
    diaries = new ArrayList<>();
    setDummyData();

    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    diaryAdapter = new DiaryAdapter(getApplicationContext(), diaries);
    recyclerView.setAdapter(diaryAdapter);

    initHeader();
  }

  private void circleImageView() {
    this.profileAvatarImg.setBackground(new ShapeDrawable(new OvalShape()));
    this.profileAvatarImg.setClipToOutline(true);
  }

  private void initHeader() {
    Diary currentDiary = diaries.get(0);

    name.setText(currentDiary.getUsername());
    date.setText(currentDiary.getDate());
//    weather.setText(currentDiary.getWeather());
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
