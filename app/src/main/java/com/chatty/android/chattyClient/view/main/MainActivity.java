package com.chatty.android.chattyClient.view.main;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.externalModules.Renderer.Renderer;
import com.chatty.android.chattyClient.model.TimelineEntry;
import com.chatty.android.chattyClient.presenter.main.MainPresenter;
import com.chatty.android.chattyClient.presenter.main.TimelineRecyclerViewAdapter;
import com.chatty.android.chattyClient.view.calendar.CalendarActivity;
import com.chatty.android.chattyClient.view.setting.SettingActivity;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
  public static final String HEADER_TITLE = "HEADER_TITLE";
  public static final String CALENDAR = "Calendar";
  public static final String SETTING = "Setting";
  private MainPresenter presenter;

  @BindView(R.id.btn_start_chatting)
  public FloatingActionButton writeButton;

  @BindView(R.id.btn_main_header_setting)
  public ImageButton settingButton;

  @BindView(R.id.btn_main_header_calendar)
  public ImageButton calenderButton;

  @BindView(R.id.recyclerView_timeline)
  public RecyclerView recyclerView;
  private TimelineRecyclerViewAdapter recyclerViewAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.construct();
  }

  private void construct() {
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    this.presenter = new MainPresenter(this);
    this.presenter.construct();
  }

  public void initRender(
    View.OnClickListener handleClickWriteButton
    , ArrayList<TimelineEntry> timeline
  ) {
      this.calenderButton.setOnClickListener(view -> {
        Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
        String message = CALENDAR;
        intent.putExtra(HEADER_TITLE, message);
        startActivity(intent);
      });

      this.settingButton.setOnClickListener(view -> {
        Intent intent = new Intent(this, SettingActivity.class);
        String message = SETTING;
        intent.putExtra(HEADER_TITLE, message);
        startActivity(intent);
      });

      writeButton.setOnClickListener(handleClickWriteButton);

      this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
      this.recyclerViewAdapter = new TimelineRecyclerViewAdapter(this, timeline);
      this.recyclerView.setAdapter(this.recyclerViewAdapter);
  }

  public void render(
    ArrayList<TimelineEntry> timeline
  ) {
    Renderer.render(
      this,
      Arrays.asList(timeline),
      this::renderTimeLineView);
  }

  private void renderMainHeader() {
  }

  private void renderTimeLineView(
    Object timeline
  ) {
    this.recyclerViewAdapter.update((ArrayList<TimelineEntry>) timeline);
  }

  private void renderWriteButton() {
  }
}
