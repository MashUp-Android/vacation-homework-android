package com.chatty.android.chattyClient.view.calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedView;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.Props;
import com.chatty.android.chattyClient.presenter.Contract;
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
    Contract.connect(this, CalendarPresenter.class);
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

    String message = this.getIntent().getStringExtra(MainActivity.HEADER_TITLE);
    TextView textView = findViewById(R.id.textView_timeline_title);
    textView.setText(message);

    renderBackButton();
  }

  @Override
  public void update(CalendarActivityProps calendarActivityProps) {

  }
}
