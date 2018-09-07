package com.chatty.android.chattyClient.view.calendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedView;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.Props;
import com.chatty.android.chattyClient.presenter.calendar.CalendarPresenter;
import com.chatty.android.chattyClient.view.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalendarActivity extends AppCompatActivity implements ExtendedView<CalendarActivityProps> {
  private CalendarPresenter presenter;

  @BindView(R.id.button_timeline_left)
  public ImageButton backButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  public void render() {
    renderBackButton();
  }

  private void renderBackButton() {
    backButton.setOnClickListener(view -> {
        finish();
    });
  }

  @Override
  public void initialRender(CalendarActivityProps calendarActivityProps) {
    setContentView(R.layout.activity_calendar);
    ButterKnife.bind(this);

    Intent intent = this.getIntent();
    String message = intent.getStringExtra(MainActivity.HEADER_TITLE);
    TextView textView = findViewById(R.id.textView_timeline_title);
    textView.setText(message);
  }

  @Override
  public void update(Props props) {

  }
}
