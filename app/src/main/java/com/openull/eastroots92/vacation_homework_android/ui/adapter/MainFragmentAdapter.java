package com.openull.eastroots92.vacation_homework_android.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainFragmentAdapter extends FragmentPagerAdapter {
  private static final String CALENDAR = "CALENDAR";
  private static final String SETTING = "SETTING";
  private static final String TIMELINE = "TIMELINE";

  private Fragment[] fragments;

  public MainFragmentAdapter(FragmentManager supportFragmentManager, Fragment[] arrayFragments) {
    super(supportFragmentManager);

    this.fragments = arrayFragments;
  }

  @Override
  public Fragment getItem(int position) {
    return fragments[position];
  }

  @Override
  public int getCount() {
    return fragments.length;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return TIMELINE;
      case 1:
        return CALENDAR;
      case 2:
        return SETTING;
      default:
        return "";
    }
  }
}
