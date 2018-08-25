package com.chatty.android.chattyClient.view.setting;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.presenter.setting.SettingPresenter;
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
    headerRender();
    buttonRender();
  }

  private void headerRender() {
    Intent intent = getIntent();
    String message = intent.getStringExtra(MainActivity.HEADER_TITLE);
    TextView textView = findViewById(R.id.textView_timeline_title);
    textView.setText(message);
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
    textViewButtonFriendsSetting.setOnClickListener((__) -> {
      startFriendsSettingActivity();
    });
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
    textViewButtonSupport.setOnClickListener((__) -> {
      renderSupportAlert();
    });
  }

  private void renderSupportAlert() {
    alertBuilder.setTitle(SUPPORT_TITLE);
    alertBuilder.setMessage(SUPPORT_MESSAGE);
    alertBuilder.setPositiveButton(ALERT_SUBMIT, null);
    alertBuilder.create().show();
  }

  private void renderSorryAlert() {
    alertBuilder.setTitle(SORRY_TITLE);
    alertBuilder.setMessage(SORRY_MESSAGE);
    alertBuilder.setPositiveButton(ALERT_SUBMIT, null);
    alertBuilder.create().show();
  }

  private void startFriendsSettingActivity() {
    startActivity(new Intent(this, FriendsSettingActivity.class));
  }
}
