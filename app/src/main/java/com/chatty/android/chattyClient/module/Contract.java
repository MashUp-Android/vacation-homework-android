package com.chatty.android.chattyClient.module;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedPresenter;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedView;
import com.chatty.android.chattyClient.model.State;

import java.util.ArrayList;

public class Contract {
  protected static ArrayList<Entry> entries;

  public static void connect(ExtendedView view, Class<?> clazz) {
    System.out.println("[Contract] connect " + view + " " + clazz);

    Entry entry = new Contract().new Entry();
    entry.connect(view, clazz);
  }

  class Entry<Props, LocalState> {
    void connect(
      ExtendedView<Props> view,
      Class<ExtendedPresenter<Props, LocalState, State>> clazz
    ) {
      try {
        ExtendedPresenter<Props, LocalState, State> presenter = clazz.newInstance();
        StateManagerWrapper.subscribe(presenter::stateListener, view::update);

        Props props = presenter.initiate();
        view.initialRender(props);
      } catch (InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }
  }
}
