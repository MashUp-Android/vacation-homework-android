package com.chatty.android.chattyClient.view.main;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.presenter.main.MainPresenter;
import com.chatty.android.chattyClient.module.StateManager.StateManager;
import com.chatty.android.chattyClient.view.write.WriteActivity;
import com.chatty.android.chattyClient.presenter.main.MainFragmentAdapter;
import com.chatty.android.chattyClient.view.calendar.CalendarFragment;
import com.chatty.android.chattyClient.view.setting.SettingFragment;
import com.chatty.android.chattyClient.view.timeLine.TimelineFragment;

import java.util.HashMap;

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
    setContentView(R.layout.activity_main);

    // state configuration
    StateManager.initialize();
    StateManager.subscribe((state) -> this.stateListener(state));

    loadDependencies();
    presenter.init();
  }

  private Object stateListener(HashMap state) {
    System.out.println("123123123 " + state);
    return null;
  }

  private void loadDependencies() {
    presenter = new MainPresenter(this);
    ButterKnife.bind(this);
  }

  public void initView() {
    calendarFragment = new CalendarFragment();
    settingFragment = new SettingFragment();
    timelineFragment = new TimelineFragment();

    initGlobalHeader();
    initViewPager();
    initTabLayout();
    initDefault();
  }

  private void initGlobalHeader() {
  }

  private void initDefault() {
    writeButton.setOnClickListener((__) -> {
      Intent intent = new Intent(this, WriteActivity.class);
      startActivity(intent);
    });
  }

  private void initViewPager() {
    Fragment[] fragments = new Fragment[3];
    fragments[0] = timelineFragment;
    fragments[1] = calendarFragment;
    fragments[2] = settingFragment;

    mainFrameLayout.setAdapter(new MainFragmentAdapter(getSupportFragmentManager(), fragments));
  }

  private void initTabLayout() {
    tabLayout.setupWithViewPager(mainFrameLayout);
  }
}
