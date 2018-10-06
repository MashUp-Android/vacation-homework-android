package com.chatty.android.chattyClient.view.write;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.constants.Header;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedView;
import com.chatty.android.chattyClient.externalModules.Renderer.Renderer;
import com.chatty.android.chattyClient.model.ChatBalloon;
import com.chatty.android.chattyClient.presenter.Contract;
import com.chatty.android.chattyClient.presenter.write.ChatDialogueAdapter;
import com.chatty.android.chattyClient.presenter.write.WritePresenter;
import com.chatty.android.chattyClient.view.emotion.EmotionActivity;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WriteActivity extends AppCompatActivity implements ExtendedView<WriteActivityProps> {
  @BindView(R.id.button_timeline_left)
  public ImageButton backButton;

  @BindView(R.id.button_timeline_right)
  public ImageButton doneButton;

  @BindView(R.id.recyclerView_dialogue)
  public RecyclerView dialogueRecyclerView;
  public ChatDialogueAdapter dialogueAdapter;

  @BindView(R.id.editText_writeInput)
  public EditText writeInputEditText;

  @BindView(R.id.btn_write_plus)
  public ImageButton imagePlusButton;

  @BindView(R.id.button_writeSubmit)
  public ImageButton writeSubmitButton;

  @BindView(R.id.textView_timeline_title)
  public TextView timelineTitle;

  @BindView(R.id.imageView_send_image)
  public ImageView writeInputImageView;
  public FragmentManager writeContext = this.getSupportFragmentManager();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Contract.connect(this, WritePresenter.class);
  }

  private void renderBackButton() {
    this.backButton.setOnClickListener(view -> {
      finish();
    });
  }

  @Override
  public void initialRender(WriteActivityProps writeActivityProps) {
    this.setContentView(R.layout.activity_write);
    ButterKnife.bind(this);

    this.renderBackButton();
    this.renderDoneButton();
    this.renderDialogueRecyclerView(writeActivityProps);
    this.renderSetTitle();
    this.renderWriteSubmitButton(writeActivityProps);
    this.renderSelectImageButton(writeActivityProps);
  }

  @Override
  public void update(WriteActivityProps writeActivityProps) {
    Renderer.render(
      this,
      Arrays.asList(writeActivityProps.chatBalloons),
      this::updateChatBalloons
    );
  }

  private void renderDoneButton() {
    this.doneButton.setImageResource(R.drawable.ic_icon_done);
    this.doneButton.setOnClickListener((view) -> {
      Intent intent = new Intent(this, EmotionActivity.class);
      startActivity(intent);
    });
  }

  private void renderDialogueRecyclerView(WriteActivityProps writeActivityProps) {
    this.dialogueRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    this.dialogueAdapter = new ChatDialogueAdapter(writeActivityProps.chatBalloons);
    this.dialogueRecyclerView.setAdapter(dialogueAdapter);
  }

  private void renderSetTitle() {
    this.timelineTitle.setText(Header.WRITE);
  }

  private void renderWriteSubmitButton(WriteActivityProps writeActivityProps) {
    this.writeSubmitButton.setOnClickListener((__) -> {
      writeActivityProps.handleClickWriteSubmitButton.accept(this.writeInputEditText);
    });
  }

  private void updateChatBalloons(Object o) {
    ArrayList<ChatBalloon> chatBalloons = (ArrayList<ChatBalloon>) o;
    this.dialogueAdapter.update(chatBalloons,getApplicationContext());
    this.dialogueRecyclerView.scrollToPosition(chatBalloons.size() -1);
  }

  private void renderSelectImageButton(WriteActivityProps writeActivityProps) {
    this.imagePlusButton.setOnClickListener((__) -> {
      writeActivityProps.handleClickSelectImageButton.accept(this.getApplicationContext(),this.getSupportFragmentManager());
    });
  }

}
