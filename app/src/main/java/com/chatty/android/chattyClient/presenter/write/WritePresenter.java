package com.chatty.android.chattyClient.presenter.write;

import android.view.View;

import com.chatty.android.chattyClient.api.ChattyApiDefinition;
import com.chatty.android.chattyClient.api.ChattyApi;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedPresenter;
import com.chatty.android.chattyClient.model.ChatBalloon;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.model.request.ChatRequest;
import com.chatty.android.chattyClient.model.response.ChatResponse;
import com.chatty.android.chattyClient.module.StateManagerWrapper;
import com.chatty.android.chattyClient.state.action.ChatAction;
import com.chatty.android.chattyClient.state.action.DiaryAction;
import com.chatty.android.chattyClient.view.write.WriteActivity;
import com.chatty.android.chattyClient.view.write.WriteActivityProps;
import com.chatty.android.chattyClient.view.write.WriteActivityState;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WritePresenter extends ExtendedPresenter<WriteActivityProps, WriteActivityState, State> {
  private static final String FALSE= "FALSE";
  private static final String IS_INITIALIZED = "IS_INITIALIZED";
  private static final String TRUE = "TRUE";

  @Override
  public WriteActivityProps initiate() {
    try {
      StateManagerWrapper.dispatch(ChatAction.requestStartChat());
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
    props.chatBalloons = state.chatBalloons;
    props.writeDiaryId = state.writeDiaryId;
    return props;
  }

  public void handleClickWriteSubmit(String text) {
    try {
      String writeDiaryId = StateManagerWrapper.getState().writeDiaryId;
      StateManagerWrapper.dispatch(ChatAction.requestAppendChat(writeDiaryId, text));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
