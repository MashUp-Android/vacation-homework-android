package com.openull.eastroots92.vacation_homework_android.utils;

import com.openull.eastroots92.vacation_homework_android.apis.HomeworkApis;
import com.openull.eastroots92.vacation_homework_android.modules.RetrofitClient;

public class ApiUtils {
  public static final String BASE_URL = "http://httpbin.org/";

  public static HomeworkApis getHomeworkApis() {
    return RetrofitClient.getClient(BASE_URL)
      .create(HomeworkApis.class);
  }
}
