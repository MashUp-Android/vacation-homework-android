package com.chatty.android.chattyClient.view.diaryDetail;

import android.content.Intent;
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
import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedView;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.Props;
import com.chatty.android.chattyClient.externalModules.Renderer.Renderer;
import com.chatty.android.chattyClient.model.Diary;
import com.chatty.android.chattyClient.model.TimelineEntry;
import com.chatty.android.chattyClient.module.Contract;
import com.chatty.android.chattyClient.presenter.diaryDetail.DiaryAdapter;
import com.chatty.android.chattyClient.presenter.diaryDetail.DiaryDetailPresenter;
import com.chatty.android.chattyClient.presenter.main.MainPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiaryDetailActivity extends AppCompatActivity implements ExtendedView<DiaryDetailActivityProps> {
  private DiaryDetailPresenter presenter;
  private static final String DIARY_TITLE = "Diary";

  @BindView(R.id.button_timeline_left)
  public ImageButton backButton;

  @BindView(R.id.button_timeline_right)
  public ImageButton deleteButton;

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
  public RecyclerView recyclerView;
  private DiaryAdapter diaryAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Contract.connect(this, DiaryDetailPresenter.class, savedInstanceState);
  }

  @Override
  public void initialRender(DiaryDetailActivityProps props) {
    setContentView(R.layout.item_diary_detail);
    ButterKnife.bind(this);

    TextView textView = findViewById(R.id.textView_timeline_title);
    textView.setText(DIARY_TITLE);

    this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    this.diaryAdapter = new DiaryAdapter(this, props.diaries);
    this.recyclerView.setAdapter(diaryAdapter);

    viewBackButton();
    textView.setText(DIARY_TITLE);

    deleteButton.setImageResource(R.drawable.ic_icon_delete);

    this.profileAvatarImg.setBackground(new ShapeDrawable(new OvalShape()));
    this.profileAvatarImg.setClipToOutline(true);
  }

  @Override
  public void update(Props _props) {
    DiaryDetailActivityProps props = (DiaryDetailActivityProps) _props;
    Renderer.render(
      this,
      Arrays.asList(props.diaries),
      this::updateDiaryView);
  }

  private void updateDiaryView(Object diary) {
    this.diaryAdapter.update((List<Diary>) diary);
  }

  private void viewBackButton() {
    backButton.setOnClickListener(view -> {
      finish();
    });
  }
}
