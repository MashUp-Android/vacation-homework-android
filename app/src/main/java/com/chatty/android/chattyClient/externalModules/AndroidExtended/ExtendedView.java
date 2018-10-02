package com.chatty.android.chattyClient.externalModules.AndroidExtended;

public interface ExtendedView<Props> {
  void initialRender(Props p);
  void update(Props props);
}
