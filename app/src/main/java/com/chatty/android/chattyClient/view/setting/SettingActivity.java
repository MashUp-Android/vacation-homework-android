package com.chatty.android.chattyClient.view.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.presenter.setting.SettingPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends AppCompatActivity {
  private SettingPresenter presenter;

  @BindView(R.id.textView_button_link_account)
  public TextView textViewButtonLinkAccount;

  @BindView(R.id.textView_button_friends_setting)
  public TextView textViewButtonFriendsSetting;

  @BindView(R.id.textView_button_add_question)
  public TextView textViewButtonAddQuestion;

  @BindView(R.id.textView_button_notification)
  public TextView textViewButtonNotification;

  @BindView(R.id.textView_button_support)
  public TextView textViewButtonSupport;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    construct();
  }

  private void construct() {
    setContentView(R.layout.activity_setting);
    ButterKnife.bind(this);
    presenter = new SettingPresenter(this);
    presenter.construct();
  }

  public void render() {
    buttonRender();
  }

  private void buttonRender() {
    renderLinkAccountButton();
    renderFriendsSettingButton();
    renderAddQuestionButton();
    renderNotificationButton();
    renderSupportButton();
  }

  private void renderLinkAccountButton() {
    textViewButtonLinkAccount.setOnClickListener((__) -> {});
  }

  private void renderFriendsSettingButton() {
    textViewButtonFriendsSetting.setOnClickListener((__) -> {});
  }

  private void renderAddQuestionButton() {
    textViewButtonAddQuestion.setOnClickListener((__) -> {});
  }

  private void renderNotificationButton() {
    textViewButtonNotification.setOnClickListener((__) -> {});
  }

  private void renderSupportButton() {
    textViewButtonSupport.setOnClickListener((__) -> {});
  }
}
