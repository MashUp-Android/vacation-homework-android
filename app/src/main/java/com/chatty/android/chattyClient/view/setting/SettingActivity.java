package com.chatty.android.chattyClient.view.setting;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.chatty.android.chattyClient.App;
import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.presenter.setting.SettingPresenter;
import com.chatty.android.chattyClient.view.addFriend.AddFriendActivity;
import com.chatty.android.chattyClient.view.friendsSetting.FriendsSettingActivity;
import com.chatty.android.chattyClient.view.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends AppCompatActivity {
  private static final String ALERT_SUBMIT = "OK";
  private static final String SORRY_MESSAGE = "준비중 입니다.";
  private static final String SORRY_TITLE = "Chatty";
  private static final String SUPPORT_MESSAGE = "If you have any question, please don't hesitate to contact us.";
  private static final String SUPPORT_TITLE = "Contact Us";

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
    this.setContentView(R.layout.activity_setting);
    ButterKnife.bind(this);
    presenter = new SettingPresenter(this);
  }

  public void render() {
    this.alertBuilder = new AlertDialog.Builder(SettingActivity.this, R.style.AlertDialogStyle);
    this.renderHeader();
    this.buttonRender();
  }

  private void renderHeader() {
    Intent intent = getIntent();
    String message = intent.getStringExtra(MainActivity.HEADER_TITLE);
    TextView textView = findViewById(R.id.textView_timeline_title);
    textView.setText(message);
  }

  private void buttonRender() {
    this.renderLinkAccountButton();
    this.renderFriendsSettingButton();
    this.renderAddQuestionButton();
    this.renderNotificationButton();
    this.renderSupportButton();
  }

  private void renderLinkAccountButton() {
    this.textViewButtonLinkAccount.setOnClickListener((__) -> {
      this.renderSorryAlert();
    });
  }

  private void renderFriendsSettingButton() {
    this.textViewButtonFriendsSetting.setOnClickListener((__) -> {
      this.startFriendsSettingActivity();
    });
  }

  private void renderAddQuestionButton() {
    this.textViewButtonAddQuestion.setOnClickListener((__) -> {
      this.renderSorryAlert();
    });
  }

  private void renderNotificationButton() {
    this.textViewButtonNotification.setOnClickListener((__) -> {
      this.renderSorryAlert();
    });
  }

  private void renderSupportButton() {
    this.textViewButtonSupport.setOnClickListener((__) -> {
      this.renderSupportAlert();
    });
  }

  private void renderSupportAlert() {
    this.alertBuilder.setTitle(SUPPORT_TITLE);
    this.alertBuilder.setMessage(SUPPORT_MESSAGE);
    this.alertBuilder.setPositiveButton(ALERT_SUBMIT, null);
    this.alertBuilder.create().show();
  }

  private void renderSorryAlert() {
    this.alertBuilder.setTitle(SORRY_TITLE);
    this.alertBuilder.setMessage(SORRY_MESSAGE);
    this.alertBuilder.setPositiveButton(ALERT_SUBMIT, null);
    this.alertBuilder.create().show();
  }

  private void startFriendsSettingActivity() {
//    boolean isFriend = App.userPreference.getBoolean(App.IS_FRIEND, false);
//
//    if (isFriend) {
//      this.startActivity(new Intent(this, FriendsSettingActivity.class));
//    } else {
//      this.startActivity(new Intent(this, AddFriendActivity.class));
//    }
  }
}
