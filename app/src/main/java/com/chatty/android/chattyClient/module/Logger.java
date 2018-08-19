package com.chatty.android.chattyClient.module;

public class Logger {
  private static final String COMMON_MSG = "Logger123123";

  public static void log(Object obj) {
    System.out.println(COMMON_MSG + " obj");
  }
}
