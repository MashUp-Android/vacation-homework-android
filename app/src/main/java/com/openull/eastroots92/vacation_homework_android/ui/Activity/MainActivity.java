package com.openull.eastroots92.vacation_homework_android.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.openull.eastroots92.vacation_homework_android.R;
import com.openull.eastroots92.vacation_homework_android.presenter.main.MainContract;
import com.openull.eastroots92.vacation_homework_android.ui.adapter.MainFragmentAdapter;
import com.openull.eastroots92.vacation_homework_android.ui.adapter.UserInfoAdapter;
import com.openull.eastroots92.vacation_homework_android.ui.fragments.CalendarFragment;
import com.openull.eastroots92.vacation_homework_android.ui.fragments.SettingFragment;
import com.openull.eastroots92.vacation_homework_android.ui.fragments.TimelineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

  private CalendarFragment calendarFragment;
  private SettingFragment settingFragment;
  private TimelineFragment timelineFragment;

  @BindView(R.id.frameLayout_main)
  ViewPager mainFrameLayout;

  @BindView(R.id.tabLayout_navBar)
  TabLayout tabLayout;

  private MainFragmentAdapter mainFragmentAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    initView();
  }

  @Override
  public void initView() {
    calendarFragment = new CalendarFragment();
    settingFragment = new SettingFragment();
    timelineFragment = new TimelineFragment();

    initViewPager();
    initTabLayout();

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
