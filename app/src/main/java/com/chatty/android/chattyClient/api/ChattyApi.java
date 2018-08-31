package com.chatty.android.chattyClient.api;

import com.chatty.android.chattyClient.module.RetrofitClient;

public class ChattyApi {
  public static final String BASE_URL = "http://13.125.168.50:1432/";
  private static ChattyApiDefinition apiDefinition;

  public static ChattyApiDefinition initialize() {
    return apiDefinition = RetrofitClient.getClient(BASE_URL)
      .create(ChattyApiDefinition.class);
  }

  public static ChattyApiDefinition getApi() {
    return ChattyApi.apiDefinition != null ? ChattyApi.apiDefinition : ChattyApi.initialize();
  }
}
