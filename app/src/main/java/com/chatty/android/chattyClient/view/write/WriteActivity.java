package com.chatty.android.chattyClient.view.write;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.model.ChatBalloon;
import com.chatty.android.chattyClient.module.StateManager.Action;
import com.chatty.android.chattyClient.presenter.write.DialogueAdapter;
import com.chatty.android.chattyClient.presenter.write.WritePresenter;
import com.chatty.android.chattyClient.module.StateManager.StateManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WriteActivity extends AppCompatActivity{
  private WritePresenter presenter;

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

    StateManager.dispatch(new Action("yo", null));

    loadDependencies();
    presenter.init();
  }

  private void loadDependencies() {
    presenter = new WritePresenter(this);
    ButterKnife.bind(this);

  }

  public void initView() {
    chatBalloons = new ArrayList<>();

    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    dialogueAdapter = new DialogueAdapter(getApplicationContext(), chatBalloons);
    recyclerView.setAdapter(dialogueAdapter);

    writeSubmitButton.setOnClickListener(presenter.handleClickWriteSubmit());
  }

  public void appendChatBalloon(ChatBalloon chatBalloon) {
    this.chatBalloons.add(chatBalloon);
    this.dialogueAdapter.notifyItemInserted(this.chatBalloons.size() - 1);

    recyclerView.scrollToPosition(this.chatBalloons.size() -1);
  }
}

