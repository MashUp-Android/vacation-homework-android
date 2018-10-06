package com.chatty.android.chattyClient.state.reducers;

import com.chatty.android.chattyClient.constants.ActionType;
import com.chatty.android.chattyClient.externalModules.ReduxJava.Action;
import com.chatty.android.chattyClient.externalModules.ReduxJava.Reducer;
import com.chatty.android.chattyClient.externalModules.ReduxJava.ReduxJava;
import com.chatty.android.chattyClient.model.ChatBalloon;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.model.request.ChatRequest;
import com.chatty.android.chattyClient.model.response.AppendChatResponse;
import com.chatty.android.chattyClient.model.response.ChatResponse;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatReducer implements Reducer<State.Chat> {
  public State.Chat run(State.Chat chat, Action action) {
    switch (action.getType()) {
      case ActionType.REQUEST_APPEND_CHAT:
        ChatRequest chatRequest = (ChatRequest) action.getPayload().get("chat");
        ChatBalloon chatBalloon = new ChatBalloon();
        chatBalloon.speech =chatRequest.label;
        chatBalloon.selectImage = chatRequest.image;
        chat.chatBalloons.add(chatBalloon);

        ArrayList<ChatBalloon> newChatBalloons = new ArrayList<>();
        newChatBalloons.addAll(chat.chatBalloons);

        chat.chatBalloons = newChatBalloons;
        return chat;

      case ActionType.REQUEST_APPEND_CHAT_SUCCESS:
        AppendChatResponse appendChatResponse = (AppendChatResponse) action.getPayload().get("chat");
        ChatBalloon chatBalloon2 = new ChatBalloon();
        chatBalloon2.speech = appendChatResponse.message;
        chat.chatBalloons.add(chatBalloon2);

        ArrayList<ChatBalloon> newChatBalloons2 = new ArrayList<>();
        newChatBalloons2.addAll(chat.chatBalloons);

        chat.chatBalloons = newChatBalloons2;
        return chat;

      case ActionType.REQUEST_START_CHAT_SUCCESS:
        ChatResponse chatResponse = (ChatResponse) action.getPayload().get("chat");
        ChatBalloon chatBalloon3 = new ChatBalloon();
        chatBalloon3.speech = chatResponse.question.message;
        chat.chatBalloons.add(chatBalloon3);
        chat.writeDiaryId = chatResponse.diary_id;
        return chat;

      default:
       return chat;
    }
  }
}
