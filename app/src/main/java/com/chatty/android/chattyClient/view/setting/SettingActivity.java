package com.chatty.android.chattyClient.view.setting;

import android.support.v7.app.AlertDialog;
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

  AlertDialog.Builder alertBuilder;

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
    alertBuilder = new AlertDialog.Builder(SettingActivity.this, R.style.AlertDialogStyle);
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
    textViewButtonLinkAccount.setOnClickListener((__) -> {
      renderSorryAlert();
    });
  }

  private void renderFriendsSettingButton() {
    textViewButtonFriendsSetting.setOnClickListener((__) -> {});
  }

  private void renderAddQuestionButton() {
    textViewButtonAddQuestion.setOnClickListener((__) -> {
      renderSorryAlert();
    });
  }

  private void renderNotificationButton() {
    textViewButtonNotification.setOnClickListener((__) -> {
      renderSorryAlert();
    });
  }

  private void renderSupportButton() {
    textViewButtonSupport.setOnClickListener((__) -> {});
  }

  private void renderSorryAlert() {
    alertBuilder.setTitle("Chatty");
    alertBuilder.setMessage("준비중 입니다.");
    alertBuilder.setPositiveButton("확인", null);
    alertBuilder.create().show();
  }
}
