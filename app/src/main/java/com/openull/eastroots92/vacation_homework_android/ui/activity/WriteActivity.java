package com.openull.eastroots92.vacation_homework_android.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.openull.eastroots92.vacation_homework_android.R;
import com.openull.eastroots92.vacation_homework_android.apis.HomeworkApis;
import com.openull.eastroots92.vacation_homework_android.models.requests.ChatRequest;
import com.openull.eastroots92.vacation_homework_android.models.responses.ChatResponse;
import com.openull.eastroots92.vacation_homework_android.presenter.write.WriteContract;
import com.openull.eastroots92.vacation_homework_android.presenter.write.WritePresenter;
import com.openull.eastroots92.vacation_homework_android.ui.adapter.DialogueAdapter;
import com.openull.eastroots92.vacation_homework_android.utils.ApiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteActivity extends AppCompatActivity implements WriteContract.View {
  HomeworkApis homeworkApis;

  @BindView(R.id.recyclerView_dialogue)
  RecyclerView recyclerView;
  RecyclerView.Adapter dialogueAdapter;

  WritePresenter presenter;

  @BindView(R.id.editText_writeInput)
  EditText writeInputEditText;

  @BindView(R.id.button_writeSubmit)
  Button writeSubmitButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_write);

    loadDependencies();
    System.out.println("123123 " + recyclerView);
    presenter.init();
  }

  private void loadDependencies() {
    homeworkApis = ApiUtils.getHomeworkApis();
    presenter = new WritePresenter(this);
    ButterKnife.bind(this);
  }

  @Override
  public void initView() {
    System.out.println("init view");
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    dialogueAdapter = new DialogueAdapter();
    recyclerView.setAdapter(dialogueAdapter);

    writeSubmitButton.setOnClickListener((__) -> {
      String speech = writeInputEditText.getText()
        .toString();

      dispatchSpeech(speech);
    });
  }

  private void dispatchSpeech(String contents) {
    String _contents = "contents " + contents;
    System.out.println("_contents " + _contents);

    homeworkApis.postChat(new ChatRequest(_contents))
      .enqueue(new Callback<ChatResponse>() {
        @Override
        public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
          System.out.println("123123 aab");
        }
        @Override
        public void onFailure(Call<ChatResponse> call, Throwable t) {
          System.out.println("123123 bbc");
        }
      });
  }
}
