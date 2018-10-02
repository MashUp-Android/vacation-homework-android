package com.chatty.android.chattyClient.state.reducers;

import com.chatty.android.chattyClient.constants.ActionType;
import com.chatty.android.chattyClient.externalModules.ReduxJava.Action;
import com.chatty.android.chattyClient.externalModules.ReduxJava.Reducer;
import com.chatty.android.chattyClient.model.Partner;
import com.chatty.android.chattyClient.model.PartnerProfileDetailEntry;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.model.response.PartnerProfileDetailResponse;
import com.chatty.android.chattyClient.model.response.TimelineResponse;

public class FriendReducer implements Reducer<State.Friend> {
  @Override
  public State.Friend run(State.Friend friend, Action action) {
    switch (action.getType()) {
      case ActionType.REQUEST_GET_DIARIES_SUCCESS:
        TimelineResponse timelineResponse = (TimelineResponse) action.getPayload().get("timeline");

        Partner partner = new Partner();
        partner.diary_count = timelineResponse.partner.diary_count;
        partner.days_together = timelineResponse.partner.days_together;
        partner.imageUrl = timelineResponse.partner.profile_image;
        partner.name = timelineResponse.partner.name;
        friend.partner = partner;
        return friend;

      case ActionType.REQUEST_GET_PARTNER_PROFILE_DETAIL_SUCCESS:
        PartnerProfileDetailResponse partnerProfileDetailResponse = (PartnerProfileDetailResponse) action.getPayload().get("partnerProfileDetail");
        PartnerProfileDetailEntry partnerProfileDetailEntry = makePartnerProfile(partnerProfileDetailResponse);
        friend.partnerProfileDetail = partnerProfileDetailEntry;
        return friend;

      default:
        return friend;
    }
  }

  private static PartnerProfileDetailEntry makePartnerProfile(PartnerProfileDetailResponse partnerProfileDetailResponse) {
    PartnerProfileDetailEntry partnerProfileDetailEntry = new PartnerProfileDetailEntry(
      partnerProfileDetailResponse.getId(),
      partnerProfileDetailResponse.getProfileImage(),
      partnerProfileDetailResponse.getName(),
      partnerProfileDetailResponse.getBio(),
      partnerProfileDetailResponse.getDiaryCount(),
      partnerProfileDetailResponse.getDaysTogether(),
      partnerProfileDetailResponse.getCreateDate()
    );
    return partnerProfileDetailEntry;
  }
}
