package com.chatty.android.chattyClient.view.write;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedView;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.Props;
import com.chatty.android.chattyClient.externalModules.Renderer.Renderer;
import com.chatty.android.chattyClient.model.ChatBalloon;
import com.chatty.android.chattyClient.module.Contract;
import com.chatty.android.chattyClient.presenter.write.ChatDialogueAdapter;
import com.chatty.android.chattyClient.presenter.write.WritePresenter;
import com.chatty.android.chattyClient.view.emotion.EmotionActivity;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WriteActivity extends AppCompatActivity implements ExtendedView<WriteActivityProps> {
  private static final String WRITE_TITLE = "Write";

  @BindView(R.id.button_timeline_left)
  public ImageButton backButton;

  @BindView(R.id.button_timeline_right)
  public ImageButton doneButton;

  @BindView(R.id.recyclerView_dialogue)
  public RecyclerView recyclerView;

  public ChatDialogueAdapter chatDialogueAdapter;

  @BindView(R.id.editText_writeInput)
  public EditText writeInputEditText;

  @BindView(R.id.button_writeSubmit)
  public ImageButton writeSubmitButton;

  @BindView(R.id.textView_timeline_title)
  public TextView timelineTitle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Contract.connect(this, WritePresenter.class);
  }

  public void initView() {
//    writeSubmitButton.setOnClickListener(presenter.handleClickWriteSubmit());
  }

  public void appendChatBalloon(ChatBalloon chatBalloon) {
//    this.dialogueAdapter.notifyItemInserted(this.chatBalloons.size() - 1);
//    this.recyclerView.scrollToPosition(this.chatBalloons.size() -1);
  }

  private void renderBackButton() {
    this.backButton.setOnClickListener(view -> {
      finish();
    });
    this.doneButton.setOnClickListener((view) -> {
      Intent intent = new Intent(this, EmotionActivity.class);
      startActivity(intent);
    });
  }

  @Override
  public void initialRender(WriteActivityProps writeActivityProps) {
    setContentView(R.layout.activity_write);
    ButterKnife.bind(this);

    this.renderBackButton();

    this.timelineTitle.setText(WRITE_TITLE);
    this.doneButton.setImageResource(R.drawable.ic_icon_done);
    this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    this.chatDialogueAdapter = new ChatDialogueAdapter(writeActivityProps.chatBalloons);
    this.recyclerView.setAdapter(chatDialogueAdapter);
    this.writeSubmitButton.setOnClickListener((__) -> {
      writeActivityProps.handleClickWriteSubmitButton.accept(
        writeInputEditText.getText().toString()
      );
    });
  }

  @Override
  public void update(Props _props) {
    WriteActivityProps props = (WriteActivityProps) _props;
    Renderer.render(
      this,
      Arrays.asList(props.chatBalloons),
      this::updateChatBalloons
    );
  }

  private void updateChatBalloons(Object o) {
    ArrayList<ChatBalloon> chatBalloons = (ArrayList<ChatBalloon>) o;
    this.chatDialogueAdapter.update(chatBalloons);
    this.recyclerView.scrollToPosition(chatBalloons.size() -1);
  }
}

