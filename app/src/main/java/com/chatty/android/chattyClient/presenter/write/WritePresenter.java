package com.chatty.android.chattyClient.presenter.write;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chatty.android.chattyClient.api.ChattyApiDefinition;
import com.chatty.android.chattyClient.api.ChattyApi;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedPresenter;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.externalModules.ReduxJava.ReduxJavaAndroidConnector;
import com.chatty.android.chattyClient.state.Store;
import com.chatty.android.chattyClient.state.action.ChatAction;
import com.chatty.android.chattyClient.view.write.WriteActivityProps;
import com.chatty.android.chattyClient.view.write.WriteActivityState;

public class WritePresenter extends ExtendedPresenter<WriteActivityProps, WriteActivityState, State> {
  private static final String FALSE= "FALSE";
  private static final String IS_INITIALIZED = "IS_INITIALIZED";
  private static final String TRUE = "TRUE";

  @Override
  public WriteActivityProps initiate() {
    try {
      Store.dispatch(ChatAction.requestStartChat());
    } catch (Exception e) {
      e.printStackTrace();
    }
    WriteActivityProps writeActivityProps = new WriteActivityProps();
    writeActivityProps.handleClickWriteSubmitButton = this::handleClickWriteSubmit;
    return writeActivityProps;
  }

  @Override
  public WriteActivityProps stateListener(State state) {
    WriteActivityProps props = new WriteActivityProps();
    props.chatBalloons = state.chat.chatBalloons;
    props.writeDiaryId = state.chat.writeDiaryId;
    return props;
  }

  public void handleClickWriteSubmit(EditText editText) {
    try {
      String writeDiaryId = Store.getState().chat.writeDiaryId;
      Store.dispatch(ChatAction.requestAppendChat(writeDiaryId, editText.getText().toString()));
      editText.setText(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
