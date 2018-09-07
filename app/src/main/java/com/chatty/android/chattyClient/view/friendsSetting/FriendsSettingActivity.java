package com.chatty.android.chattyClient.view.friendsSetting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedView;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.Props;
import com.chatty.android.chattyClient.model.PartnerProfileDetailEntry;
import com.chatty.android.chattyClient.presenter.friendsList.FriendsListPresenter;
import com.chatty.android.chattyClient.presenter.friendsSetting.FriendsSettingPresenter;
import com.chatty.android.chattyClient.view.addFriend.AddFriendActivity;
import com.chatty.android.chattyClient.view.friendsList.FriendsListActivity;


import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendsSettingActivity extends AppCompatActivity implements ExtendedView<FriendsSettingActivityProps> {
  private static String HEADER_TITLE = "Friends Setting";
  private FriendsSettingPresenter presenter;

  @BindView(R.id.button_timeline_left)
  public ImageButton imageButtonBack;

  @BindView(R.id.button_edit_profile)
  public Button buttonEditProfile;

  @BindView(R.id.imageView_profile)
  public ImageView imageViewProfileImage;

  @BindView(R.id.textView_profile_name)
  public TextView textViewProfileName;

  @BindView(R.id.textView_profile_bio)
  public TextView textViewProfileBio;

  @BindView(R.id.textView_profile_diary_answer)
  public TextView textViewProfileDiary;

  @BindView(R.id.textView_profile_during_answer)
  public TextView textViewProfileDuring;

  @BindView(R.id.textView_profile_startdate_answer)
  public TextView textViewProfileStartDate;

  @BindView(R.id.button_timeline_right)
  public ImageButton imageButtonList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  private void renderHeader() {
    this.setHeaderTitle();
    this.renderBackButton();
    this.renderEditProfileButton();
    this.renderListButton();
  }

  private void renderEditProfileButton() {
    this.buttonEditProfile.setOnClickListener((__) -> {
      Log.e("버튼 클릭", "다음 화면으로 이동!");
    });
  }

  private void renderBackButton() {
    this.imageButtonBack.setOnClickListener((__) -> {
      finish();
    });
  }

  private void renderListButton() {
    this.imageButtonList.setImageResource(R.drawable.ic_icon_setting_friendslist);
    this.imageButtonList.setOnClickListener((__) -> {
      this.startActivity(new Intent(this, FriendsListActivity.class));
    });
  }

  private void setHeaderTitle() {
    TextView textView = findViewById(R.id.textView_timeline_title);
    textView.setText(HEADER_TITLE);
  }

  public void renderPartnerProfile(PartnerProfileDetailEntry partnerProfileDetail) {
    PartnerProfileDetailEntry currentPartnerProfileDetail = partnerProfileDetail;
    String profileImage = partnerProfileDetail.getProfileImage();

    this.textViewProfileName.setText(currentPartnerProfileDetail.getName());
    this.textViewProfileBio.setText(currentPartnerProfileDetail.getBio());
    this.textViewProfileDiary.setText(String.valueOf(currentPartnerProfileDetail.getDiaryCount()));
    this.textViewProfileDuring.setText(String.valueOf(currentPartnerProfileDetail.getDaysTogether()));
    this.textViewProfileStartDate.setText(currentPartnerProfileDetail.getCreateDate());

    if (!TextUtils.isEmpty(profileImage)) {
      String imageUrl = "http://13.125.168.50:1432" + profileImage;
      Glide.with(getApplicationContext())
        .load(imageUrl)
        .into(this.imageViewProfileImage);
    }
  }

  @Override
  public void initialRender(FriendsSettingActivityProps friendsSettingActivityProps) {
    this.setContentView(R.layout.activity_friends_setting);
    ButterKnife.bind(this);
  }

  @Override
  public void update(Props props) {
    this.renderHeader();
  }
}
