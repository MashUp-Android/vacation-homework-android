package com.chatty.android.chattyClient.view.calendar;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedView;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.Props;
import com.chatty.android.chattyClient.model.TimelineEntry;
import com.chatty.android.chattyClient.presenter.Contract;
import com.chatty.android.chattyClient.presenter.calendar.CalendarPresenter;
import com.chatty.android.chattyClient.view.main.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalendarActivity extends AppCompatActivity implements ExtendedView<CalendarActivityProps> {
  @BindView(R.id.calendarView)
  public CalendarView calendarView;

  @BindView(R.id.button_timeline_left)
  public ImageButton backButton;

  public static int[] ICONS = {
    R.drawable.ic_icon_emotion_angry,
    R.drawable.ic_icon_emotion_joy,
    R.drawable.ic_icon_emotion_sad,
    R.drawable.ic_icon_emotion_sentimental,
    R.drawable.ic_icon_emotion_soso,
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Contract.connect(this, CalendarPresenter.class);
  }

  @Override
  public void initialRender(CalendarActivityProps calendarActivityProps) {
    setContentView(R.layout.activity_calendar);
    ButterKnife.bind(this);

    String message = this.getIntent().getStringExtra(MainActivity.HEADER_TITLE);
    TextView textView = findViewById(R.id.textView_timeline_title);
    textView.setText(message);

    renderBackButton();
    renderCalendar(calendarActivityProps.timelineEntries);
  }

  private void renderBackButton() {
    backButton.setOnClickListener(view -> {
      finish();
    });
  }

  private void renderCalendar(ArrayList<TimelineEntry> timelineEntries) {
    List<EventDay> events = new ArrayList<>();
    Random random = new Random();
    for (TimelineEntry entry : timelineEntries) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(entry.date);
      int icon = random.nextInt(5);
      events.add(new EventDay(calendar, ICONS[icon]));
    }
    this.calendarView.setEvents(events);
  }

  @Override
  public void update(CalendarActivityProps calendarActivityProps) {
  }
}
