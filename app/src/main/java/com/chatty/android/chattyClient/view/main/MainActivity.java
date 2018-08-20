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
import com.chatty.android.chattyClient.model.TimelineEntry;
import com.chatty.android.chattyClient.presenter.main.MainPresenter;
import com.chatty.android.chattyClient.presenter.main.TimelineRecyclerViewAdapter;
import com.chatty.android.chattyClient.view.calendar.CalendarActivity;
import com.chatty.android.chattyClient.view.setting.SettingActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
  public static final String HEADER_TITLE = "HEADER_TITLE";
  public static final String CALENDAR = "Calendar";
  public static final String SETTING = "Setting";
  private MainPresenter presenter;

  private boolean isInitialized = false;

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
    this.isInitialized = construct();
  }

  private boolean construct() {
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    this.presenter = new MainPresenter(this);
    this.presenter.construct();

    return true;
  }

  public void render(
    View.OnClickListener handleClickWriteButton,
    ArrayList<TimelineEntry> timeline
  ) {
    renderWriteButton(handleClickWriteButton);
    renderTimeLineView(timeline);
    renderMainHeader();
  }

  private void renderMainHeader() {
    if (!this.isInitialized) {
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
    }
  }

  private void renderTimeLineView(
    ArrayList<TimelineEntry> timeline
  ) {
    if (!this.isInitialized && timeline != null) {
      this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
      this.recyclerViewAdapter = new TimelineRecyclerViewAdapter(this, timeline);
      this.recyclerView.setAdapter(this.recyclerViewAdapter);
    }

    this.recyclerViewAdapter.update(timeline);
  }

  private void renderWriteButton(View.OnClickListener handleClickWriteButton) {
    writeButton.setOnClickListener(handleClickWriteButton);
  }
}
