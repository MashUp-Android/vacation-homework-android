package com.chatty.android.chattyClient.presenter;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.Contractor;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedView;
import com.chatty.android.chattyClient.externalModules.ReduxJava.ReduxJavaAndroidConnector;
import com.chatty.android.chattyClient.state.Store;

public class Contract {
  public static void connect(
    ExtendedView view,
    Class<?> clazz
  ) {
    Contractor.connect(
      view,
      clazz,
      Store.getConnector()
    );
  }
}
