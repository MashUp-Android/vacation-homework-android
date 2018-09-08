package com.chatty.android.chattyClient.view.main;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.api.ChattyApi;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedView;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.Props;
import com.chatty.android.chattyClient.externalModules.Renderer.Renderer;
import com.chatty.android.chattyClient.model.Partner;
import com.chatty.android.chattyClient.model.TimelineEntry;
import com.chatty.android.chattyClient.module.Contract;
import com.chatty.android.chattyClient.presenter.main.MainPresenter;
import com.chatty.android.chattyClient.presenter.main.TimelineRecyclerViewAdapter;
import com.chatty.android.chattyClient.view.app.ProfileAvatarImage;
import com.chatty.android.chattyClient.view.calendar.CalendarActivity;
import com.chatty.android.chattyClient.view.setting.SettingActivity;
import com.chatty.android.chattyClient.view.write.WriteActivity;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ExtendedView<MainActivityProps> {
  public static final String HEADER_TITLE = "HEADER_TITLE";
  public static final String CALENDAR = "Calendar";
  public static final String SETTING = "Setting";
  public static Integer floatingCheckeNum = 0;

  @BindView(R.id.btn_start_chatting)
  public FloatingActionButton writeButton;

  @BindView(R.id.btn_main_header_setting)
  public ImageButton settingButton;

  @BindView(R.id.btn_main_header_calendar)
  public ImageButton calenderButton;

  @BindView(R.id.recyclerView_timeline)
  public RecyclerView recyclerView;
  private TimelineRecyclerViewAdapter recyclerViewAdapter;

  @BindView(R.id.profile_avatar_img)
  public ImageView profilerAvatarImage;

  @BindView(R.id.partner_name)
  public TextView partnerName;

  @BindView(R.id.dialog_count)
  public TextView dialogCount;

  @BindView(R.id.day_count)
  public TextView dayCount;

  @BindView(R.id.current_date)
  public TextView currentDate;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Contract.connect(this, MainPresenter.class);
  }

  public void StartfloatingBtn() {
    if (floatingCheckeNum == 0) {
      this.writeButton.setVisibility(View.VISIBLE);
    }
    else {
      Log.e("floating Close check", String.valueOf(floatingCheckeNum));
    }
  }

  @Override
  public void initialRender(MainActivityProps props) {
    this.setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    StartfloatingBtn();

    ProfileAvatarImage.CUSTOMVIEW__initRender(this.profilerAvatarImage);

    this.calenderButton.setOnClickListener((view) -> {
      Intent intent = new Intent(this, CalendarActivity.class);
      intent.putExtra(HEADER_TITLE, CALENDAR);
      startActivity(intent);
    });

    this.settingButton.setOnClickListener((view) -> {
      Intent intent = new Intent(this, SettingActivity.class);
      intent.putExtra(HEADER_TITLE, SETTING);
      startActivity(intent);
    });

    this.writeButton.setOnClickListener((__) -> {
      Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
      this.startActivity(intent);
    });

    this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    this.recyclerViewAdapter = new TimelineRecyclerViewAdapter(
      props.timeline,
      props.handleClickTimelineEntry);
    this.recyclerView.setAdapter(this.recyclerViewAdapter);
  }

  @Override
  public void update(Props _props) {
    MainActivityProps props = (MainActivityProps) _props;
    Renderer.render(
      this,
      Arrays.asList(props.timeline, props.partner),
      this::updateTimeLineView,
      this::updatePartner);
  }

  private void updatePartner(Object _partner) {
    Partner partner = (Partner) _partner;
    this.dayCount.setText(partner.days_together.toString());
    this.dialogCount.setText(partner.diary_count.toString());
    this.partnerName.setText(partner.name);
    Glide.with(this)
      .load(ChattyApi.BASE_URL.substring(0, ChattyApi.BASE_URL.length() - 1) + partner.imageUrl)
      .into(this.profilerAvatarImage);

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    this.currentDate.setText(formatter.format(new Date()));
  }

  private void updateTimeLineView(
    Object timeline
  ) {
    this.recyclerViewAdapter.update((ArrayList<TimelineEntry>) timeline);
  }
}
