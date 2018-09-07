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
//    this.state.put(IS_INITIALIZED, FALSE);
    try {
      StateManagerWrapper.dispatch(ChatAction.requestStartChat());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public WriteActivityProps stateListener(State state) {
    System.out.println("123123123" + state.chatBalloons.size());
    WriteActivityProps props = new WriteActivityProps();
    props.chatBalloons = state.chatBalloons;
    return props;
  }

  private void dispatchInitChat() {
//    chattyApiDefinition.postStartChat()
//      .enqueue(new Callback<ChatResponse>() {
//        @Override
//        public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
//          Calendar calendar = Calendar.getInstance();
//          ChatBalloon chatBalloon = new ChatBalloon();
//          chatBalloon.setSpeech("Initial question from server (not yet available)");
//          chatBalloon.setCalendar(calendar);
//          view.appendChatBalloon(chatBalloon);
//        }
//
//        @Override
//        public void onFailure(Call<ChatResponse> call, Throwable t) {
//          System.out.println("dispatch speech error");
//        }
//      });
//
//    this.state.put(IS_INITIALIZED, TRUE);
  }

  public void dispatchSpeech(String contents) {
    String _contents = "contents " + contents;
    System.out.println("_contents " + _contents);

//    chattyApiDefinition.postChat(new ChatRequest(_contents))
//      .enqueue(new Callback<ChatResponse>() {
//        @Override
//        public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
//          // todo: make sure the response has the right value,
//          Calendar calendar = Calendar.getInstance();
//          ChatBalloon chatBalloon = new ChatBalloon();
//          chatBalloon.setSpeech("Question from server (not yet available)");
//          chatBalloon.setCalendar(calendar);
//          view.appendChatBalloon(chatBalloon);
//        }
//
//        @Override
//        public void onFailure(Call<ChatResponse> call, Throwable t) {
//          System.out.println("dispatch speech error");
//        }
//      });
  }

  public View.OnClickListener handleClickWriteSubmit() {
    return null;
//    WriteActivity view = (WriteActivity) this.view;
//    WritePresenter that = this;
//
//    return new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        if (that.state.get(IS_INITIALIZED) == FALSE) return;
//
//        String speech = view.writeInputEditText.getText()
//          .toString();
//
//        Calendar calendar = Calendar.getInstance();
//        ChatBalloon chatBalloon = new ChatBalloon();
//        chatBalloon.setSpeech(speech);
//        chatBalloon.setCalendar(calendar);
//        view.appendChatBalloon(chatBalloon);
//
//        dispatchSpeech(speech);
//      }
//    };
  }


}
