package com.chatty.android.chattyClient.state;

import android.util.Log;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.Props;
import com.chatty.android.chattyClient.externalModules.ReduxJava.ReduxJava;
import com.chatty.android.chattyClient.externalModules.ReduxJava.ReduxJavaAndroidConnector;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.state.reducers.Reducers;

import java.lang.reflect.Field;
import java.util.function.Consumer;
import java.util.function.Function;

public class Store {
  private static boolean isInitialized = false;
  private static final ReduxJavaAndroidConnector<State, Props> reduxJavaAndroidConnector
    = new ReduxJavaAndroidConnector<>();

  public static void configureStore(State state) {
    Store.isInitialized = Store.reduxJavaAndroidConnector.initialize(Reducers::reduce, state);
  }

  public static State getState() {
    if (Store.isInitialized) {
      return Store.reduxJavaAndroidConnector.getState();
    } else {
      Log.e(Store.class.getName(), "store cannot get state before initializing");
      return null;
    }
  }

  public static boolean dispatch(ReduxJava.DispatcherMiddleware r) {
    return Store.reduxJavaAndroidConnector.dispatch(r);
  }

  public static void subscribe(Function<State, Object> subscriber, Consumer<Props> updater) {
    Store.reduxJavaAndroidConnector.subscribe(subscriber, updater);
  }

  public static void printState(String tag, Object obj) {
    Log.i(Store.class.getSimpleName(), tag + " printState()");

    Class<? extends Object> clazz = obj.getClass();
    for (Field f : clazz.getDeclaredFields()) {
      try {
        f.setAccessible(true);
        Object value = f.get(obj);
        System.out.println(f.getName() + " " + value);
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
  }

  public static ReduxJavaAndroidConnector getConnector() {
    return Store.reduxJavaAndroidConnector;
  }
}
