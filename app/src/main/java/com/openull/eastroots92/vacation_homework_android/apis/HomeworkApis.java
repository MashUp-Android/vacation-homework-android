package com.openull.eastroots92.vacation_homework_android.apis;

import com.openull.eastroots92.vacation_homework_android.models.requests.ChatRequest;
import com.openull.eastroots92.vacation_homework_android.models.responses.ChatResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HomeworkApis {
  @GET("/startchat")
  Call<ChatResponse> postInitChat();

  @POST("/chat")
  Call<ChatResponse> postChat(
    @Body
    ChatRequest chatRequest
  );
}
