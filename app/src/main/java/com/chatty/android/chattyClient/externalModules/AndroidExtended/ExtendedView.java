package com.chatty.android.chattyClient.externalModules.AndroidExtended;

import android.content.Intent;
import android.view.View;

public interface ExtendedView<P> {
  void initialRender(P p);
  void update(Props props);

  default void startOtherActivity(Intent intent) {

  }
}
