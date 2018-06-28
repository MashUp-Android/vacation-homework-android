package com.openull.eastroots92.vacation_homework_android.modules;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// https://code.tutsplus.com/tutorials/getting-started-with-retrofit-2--cms-27792
public class RetrofitClient {
  private static Retrofit retrofit = null;

  public static Retrofit getClient(String baseUrl) {
    if (retrofit == null) {
      retrofit = new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    }
    return retrofit;
  }
}
