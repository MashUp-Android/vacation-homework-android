package com.chatty.android.chattyClient.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// https://code.tutsplus.com/tutorials/getting-started-with-retrofit-2--cms-27792
public class RetrofitClient {
  private static Retrofit retrofit = null;

  public static Retrofit getClient(String baseUrl) {
    Gson gson = new GsonBuilder()
      .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
      .create();

    if (retrofit == null) {
      retrofit = new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();
    }
    return retrofit;
  }
}
