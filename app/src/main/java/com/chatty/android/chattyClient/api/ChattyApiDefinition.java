package com.chatty.android.chattyClient.api;

import com.chatty.android.chattyClient.model.request.ChatRequest;
import com.chatty.android.chattyClient.model.request.NewPartnerRequest;
import com.chatty.android.chattyClient.model.response.ChatResponse;
import com.chatty.android.chattyClient.model.response.FriendItemResponse;
import com.chatty.android.chattyClient.model.response.PartnerProfileDetailResponse;
import com.chatty.android.chattyClient.model.response.DiaryResponse;
import com.chatty.android.chattyClient.model.response.TimelineResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ChattyApiDefinition {
  @POST("/chat")
  Call<ChatResponse> postChat(
    @Body
      ChatRequest chatRequest
  );

  @GET("/chatty_users/")
  Call<TimelineResponse> getTimeline();

  @POST("diary/startchat/")
  Call<ChatResponse> postStartChat();

  @GET("/diary/detail/{diary_id}")
  Call<DiaryResponse> getDiaryDetail(
    @Path("diary_id") int diary_id
  );

  @GET("/chatty_users/partners/")
  Call<FriendItemResponse> getFriendsList();

  @GET("/partners/profile/{partner_id}")
  Call<PartnerProfileDetailResponse> getPartnerProfileDetail(
    @Path("partner_id") int partner_id
  );

  @Multipart
  @POST("/partners/partner")
  Call<ChatResponse> postNewPartner(
    @Body
      NewPartnerRequest newPartnerRequest,
    @Part
      MultipartBody.Part profile_image
  );

}
