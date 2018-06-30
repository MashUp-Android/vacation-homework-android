package com.openull.eastroots92.vacation_homework_android.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.openull.eastroots92.vacation_homework_android.R;
import com.openull.eastroots92.vacation_homework_android.apis.HomeworkApis;
import com.openull.eastroots92.vacation_homework_android.models.ChatBalloon;
import com.openull.eastroots92.vacation_homework_android.models.requests.ChatRequest;
import com.openull.eastroots92.vacation_homework_android.models.responses.ChatResponse;
import com.openull.eastroots92.vacation_homework_android.presenter.write.WriteContract;
import com.openull.eastroots92.vacation_homework_android.presenter.write.WritePresenter;
import com.openull.eastroots92.vacation_homework_android.ui.adapter.DialogueAdapter;
import com.openull.eastroots92.vacation_homework_android.utils.ApiUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteActivity extends AppCompatActivity implements WriteContract.View {
  WritePresenter presenter;

  @BindView(R.id.recyclerView_dialogue)
  public RecyclerView recyclerView;
  public RecyclerView.Adapter dialogueAdapter;

  @BindView(R.id.editText_writeInput)
  public EditText writeInputEditText;

  @BindView(R.id.button_writeSubmit)
  public Button writeSubmitButton;

  public List<ChatBalloon> chatBalloons;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_write);

    loadDependencies();

    presenter.init();
  }

  private void loadDependencies() {
    presenter = new WritePresenter(this);
    ButterKnife.bind(this);
  }

  @Override
  public void initView() {
    chatBalloons = new ArrayList<>();

    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    dialogueAdapter = new DialogueAdapter(getApplicationContext(), chatBalloons);
    recyclerView.setAdapter(dialogueAdapter);

    writeSubmitButton.setOnClickListener(presenter.handleClickWriteSubmit());

//    writeSubmitButton.setOnClickListener((__) -> {
//      String speech = writeInputEditText.getText()
//        .toString();
//
//      System.out.println(speech);
//
//      Calendar calendar = Calendar.getInstance();
//
//      ChatBalloon chatBalloon = new ChatBalloon();
//      chatBalloon.setSpeech(speech);
//      chatBalloon.setCalendar(calendar);
//
//      chatBalloons.add(chatBalloon);
//      dialogueAdapter.notifyItemInserted(chatBalloons.size() - 1);
////      presenter.dispatchSpeech(speech);
//    });
  }

  public void appendChatBalloon(ChatBalloon chatBalloon) {
    this.chatBalloons.add(chatBalloon);
    this.dialogueAdapter.notifyItemInserted(this.chatBalloons.size() - 1);
  }
}

