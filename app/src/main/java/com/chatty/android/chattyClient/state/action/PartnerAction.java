package com.chatty.android.chattyClient.state.action;

import android.util.Log;

import com.chatty.android.chattyClient.api.ChattyApi;
import com.chatty.android.chattyClient.constants.ActionType;
import com.chatty.android.chattyClient.externalModules.StateManager.Action;
import com.chatty.android.chattyClient.externalModules.StateManager.StateManager;
import com.chatty.android.chattyClient.model.request.NewPartnerRequest;
import com.chatty.android.chattyClient.model.response.ChatResponse;
import com.chatty.android.chattyClient.model.response.FriendItemResponse;
import com.chatty.android.chattyClient.model.response.PartnerProfileDetailResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartnerAction {
  public static StateManager.DispatcherMiddleware requestGetPartnerProfileDetail() {
    return (dispatch) -> {
      dispatch.run(Action.of(ActionType.REQUEST_GET_PARTNER_PROFILE_DETAIL));
      ChattyApi.getApi().getPartnerProfileDetail(1)
        .enqueue(new Callback<PartnerProfileDetailResponse>() {
          @Override
          public void onResponse(
            Call<PartnerProfileDetailResponse> call,
            Response<PartnerProfileDetailResponse> response
          ) {
            PartnerProfileDetailResponse partnerProfile = response.body();
            dispatch.run(Action.of(ActionType.REQUEST_GET_PARTNER_PROFILE_DETAIL_SUCCESS)
              .payloadAdd("partnerProfileDetail", partnerProfile));
          }

          @Override
          public void onFailure(Call<PartnerProfileDetailResponse> call, Throwable t) {
            dispatch.run(Action.of(ActionType.REQUEST_GET_PARTNER_PROFILE_DETAIL_ERROR));
          }
        });
    };
  }

  public static boolean requestAddNewPartnerProfile(NewPartnerRequest newPartnerRequest, MultipartBody.Part file) {
    final boolean[] isSuccess = {false};
    ChattyApi.getApi().postNewPartner(newPartnerRequest, file)
      .enqueue(new Callback<ChatResponse>() {
        @Override
        public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
          isSuccess[0] = true;
        }

        @Override
        public void onFailure(Call<ChatResponse> call, Throwable t) {

        }
      });
    return isSuccess[0];
  }

  private static PartnerProfileDetailResponse getDummyProfileDetail() {
    PartnerProfileDetailResponse partnerProfileDetailResponse = new PartnerProfileDetailResponse(
      1,
      "https://img.geocaching.com/track/display/dec778cb-f0ff-4472-a020-27b9ba8aa4f4.jpg",
      "Homer. J. Simpsons",
      "Do'h!!!!!!!",
      10,
      14,
      "2018-07-21"
    );

    return partnerProfileDetailResponse;
  }

  public static StateManager.DispatcherMiddleware requestGetFriendsList() {
    return (dispatch) -> {
      dispatch.run(Action.of(ActionType.REQUEST_GET_FRIENDS_LIST));
      ChattyApi.getApi().getFriendsList()
        .enqueue(new Callback<FriendItemResponse>() {
          @Override
          public void onResponse(Call<FriendItemResponse> call, Response<FriendItemResponse> response) {
            FriendItemResponse friendItemResponse = response.body();
            dispatch.run(Action.of(ActionType.REQUEST_GET_FRIENDS_LIST_SUCCESS)
              .payloadAdd("friendsList", friendItemResponse));
          }

          @Override
          public void onFailure(Call<FriendItemResponse> call, Throwable t) {
            dispatch.run(Action.of(ActionType.REQUEST_GET_FRIENDS_LIST_ERROR)
            .payloadAdd("error",t));

            Log.e("ERROR", String.valueOf(t));
          }
        });
    };
  }
}
