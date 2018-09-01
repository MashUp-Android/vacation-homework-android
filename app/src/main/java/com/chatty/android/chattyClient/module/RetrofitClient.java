package com.chatty.android.chattyClient.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// https://code.tutsplus.com/tutorials/getting-started-with-retrofit-2--cms-27792
public class RetrofitClient {
  private static Retrofit retrofit = null;
  private final static String SOON_TO_BE_REMOVED_HASH_FOR_DEV = "b2FQxG4bHTKJpg3ciug0vLjd4vuHTNciOg19919l";

  public static Retrofit getClient(String baseUrl) {
    if (retrofit == null) {
      retrofit = RetrofitClient.createRetrofitClient(baseUrl);
    }

    return retrofit;
  }

  private static Retrofit createRetrofitClient(String baseUrl) {
    Gson gson = new GsonBuilder()
      .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
      .create();

    OkHttpClient.Builder httpClientbuilder = new OkHttpClient.Builder();
    httpClientbuilder.addInterceptor(new Interceptor() {
      @Override
      public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request request = originalRequest.newBuilder()
          .header("HASH", SOON_TO_BE_REMOVED_HASH_FOR_DEV)
          .build();

        System.out.println("[Retrofit] url: " + request.url() + " " + request.body());

        Response response = chain.proceed(request);
        if (response.code() != 200) {
          throw new IOException(response.toString());
        }

        return response;
      }
    });

    return new Retrofit.Builder()
      .baseUrl(baseUrl)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .client(httpClientbuilder.build())
      .build();
  }
}
