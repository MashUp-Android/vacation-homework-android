package com.chatty.android.chattyClient.state.action;

import android.util.Log;

import com.chatty.android.chattyClient.api.ChattyApi;
import com.chatty.android.chattyClient.constants.ActionType;
import com.chatty.android.chattyClient.externalModules.StateManager.Action;
import com.chatty.android.chattyClient.externalModules.StateManager.Payload;
import com.chatty.android.chattyClient.externalModules.StateManager.StateManager;
import com.chatty.android.chattyClient.model.PartnerProfileDetailEntry;
import com.chatty.android.chattyClient.model.response.PartnerProfileDetailResponse;

import java.util.HashMap;

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
//        TODO: new Parter에 매개변수가 call.getId 이런식으로 처리가 되어야 한다.
//        PartnerProfileDetailResponse partnerProfile = response.body();
//        TODO: 서버와 연결 후에는 아래의 getDummyProfileDetail() 메서드와 관련된 코드들을 모두 지울 것
            PartnerProfileDetailResponse partnerProfile = getDummyProfileDetail();

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
}
