package com.chatty.android.chattyClient.api;

import com.chatty.android.chattyClient.model.request.ChatRequest;
import com.chatty.android.chattyClient.model.response.ChatResponse;
import com.chatty.android.chattyClient.model.response.PartnerProfileDetailResponse;
import com.chatty.android.chattyClient.model.response.DiaryResponse;
import com.chatty.android.chattyClient.model.response.TimelineResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ChattyApiDefinition {
  @GET("/chatty_users/")
  Call<TimelineResponse> getTimeline();

  @GET("/startchat")
  Call<ChatResponse> postInitChat();

  @POST("/chat")
  Call<ChatResponse> postChat(
    @Body
    ChatRequest chatRequest
  );

  @GET("/partners/profile/{partner_id}")
  Call<PartnerProfileDetailResponse> getPartnerProfileDetail(
    @Path("partner_id") int partner_id
  );

  @GET("/diary/{diary_id}")
  Call<DiaryResponse> postDiaryChat(
    @Path("diary_id") int diary_id
  );
}
