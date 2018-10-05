package com.chatty.android.chattyClient.view.diaryDetail;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedView;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.Props;
import com.chatty.android.chattyClient.externalModules.Renderer.Renderer;
import com.chatty.android.chattyClient.model.Diary;
import com.chatty.android.chattyClient.presenter.Contract;
import com.chatty.android.chattyClient.presenter.diaryDetail.DiaryAdapter;
import com.chatty.android.chattyClient.presenter.diaryDetail.DiaryDetailPresenter;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class  DiaryDetailActivity extends AppCompatActivity implements ExtendedView<DiaryDetailActivityProps> {
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

  private DiaryDetailActivityProps props;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Contract.connect(this, DiaryDetailPresenter.class);
  }

  @Override
  public void initialRender(DiaryDetailActivityProps props) {
    setContentView(R.layout.item_diary_detail);
    ButterKnife.bind(this);
    this.props = props;
    TextView textView = findViewById(R.id.textView_timeline_title);
    textView.setText(DIARY_TITLE);

    this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    this.diaryAdapter = new DiaryAdapter(this, this.props.diaries);
    this.recyclerView.setAdapter(diaryAdapter);
    this.name.setText(this.props.partner.name);

    viewBackButton();
    textView.setText(DIARY_TITLE);

    deleteButton.setImageResource(R.drawable.ic_icon_delete);

    Log.e("dd",this.props.partner.imageUrl);
    if (!TextUtils.isEmpty(this.props.partner.imageUrl)) {
      String imageUrl = "http://13.125.168.50:1432" + this.props.partner.imageUrl;
      Glide.with(getApplicationContext())
        .load(imageUrl)
        .into(this.profileAvatarImg);
    }

    this.profileAvatarImg.setBackground(new ShapeDrawable(new OvalShape()));
    this.profileAvatarImg.setClipToOutline(true);
  }

  @Override
  public void update(DiaryDetailActivityProps diaryDetailActivityProps) {
    Renderer.render(
      this,
      Arrays.asList(diaryDetailActivityProps.diaries),
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
