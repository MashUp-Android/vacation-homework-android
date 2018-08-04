package com.chatty.android.chattyClient.api;

import com.chatty.android.chattyClient.model.request.ChatRequest;
import com.chatty.android.chattyClient.model.response.ChatResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ChattyApi {
  @GET("/startchat")
  Call<ChatResponse> postInitChat();

  @POST("/chat")
  Call<ChatResponse> postChat(
    @Body
    ChatRequest chatRequest
  );
}
