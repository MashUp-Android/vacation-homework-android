package com.chatty.android.chattyClient.view.main;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.presenter.main.MainPresenter;
import com.chatty.android.chattyClient.presenter.main.MainFragmentAdapter;
import com.chatty.android.chattyClient.view.calendar.CalendarFragment;
import com.chatty.android.chattyClient.view.setting.SettingFragment;
import com.chatty.android.chattyClient.view.timeLine.TimelineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
  private MainPresenter presenter;
  private CalendarFragment calendarFragment;
  private SettingFragment settingFragment;
  private TimelineFragment timelineFragment;


  @BindView(R.id.floatingActionButton_write)
  public FloatingActionButton writeButton;

  @BindView(R.id.frameLayout_main)
  public ViewPager mainFrameLayout;

  @BindView(R.id.tabLayout_navBar)
  public TabLayout tabLayout;

  private MainFragmentAdapter mainFragmentAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    construct();
  }

  private void construct() {
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    presenter = new MainPresenter(this);
    presenter.construct();
  }

  public void render(
    View.OnClickListener handleClickWriteButton
  ) {
    calendarFragment = new CalendarFragment();
    settingFragment = new SettingFragment();
    timelineFragment = new TimelineFragment();

    renderGlobalHeader();
    renderViewPager();
    renderTabLayout();
    renderWriteButton(handleClickWriteButton);
  }

  private void renderGlobalHeader() {
  }

  private void renderWriteButton(View.OnClickListener handleClickWriteButton) {
    writeButton.setOnClickListener(handleClickWriteButton);
  }

  private void renderViewPager() {
    Fragment[] fragments = new Fragment[3];
    fragments[0] = timelineFragment;
    fragments[1] = calendarFragment;
    fragments[2] = settingFragment;

    mainFrameLayout.setAdapter(
      new MainFragmentAdapter(getSupportFragmentManager(), fragments)
    );
  }

  private void renderTabLayout() {
    tabLayout.setupWithViewPager(mainFrameLayout);
  }
}
