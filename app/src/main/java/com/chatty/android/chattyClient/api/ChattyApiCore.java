package com.chatty.android.chattyClient.api;

import com.chatty.android.chattyClient.module.RetrofitClient;

public class ChattyApiCore {
  public static final String BASE_URL = "http://httpbin.org/";

  public static ChattyApi getApi() {
    return RetrofitClient.getClient(BASE_URL)
      .create(ChattyApi.class);
  }
}
