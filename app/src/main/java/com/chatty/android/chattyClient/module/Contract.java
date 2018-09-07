package com.chatty.android.chattyClient.module;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedPresenter;
import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedView;
import com.chatty.android.chattyClient.model.State;

import java.util.ArrayList;

public class Contract {
  protected static ArrayList<Entry> entries;

  public static void connect(ExtendedView view, Class<?> clazz, Bundle savedInstanceState) {
    System.out.println("[Contract] connect " + view + " " + clazz);

    Entry entry = new Contract().new Entry();
    entry.connect(view, clazz, savedInstanceState);
  }

  class Entry<Props, LocalState> {
    void connect(
      ExtendedView<Props> view,
      Class<ExtendedPresenter<Props, LocalState, State>> clazz,
      Bundle savedInstanceState
    ) {
      try {
        ExtendedPresenter<Props, LocalState, State> presenter = clazz.newInstance();
        presenter.activity = (AppCompatActivity) view;
        Props props = presenter.initiate();
        view.initialRender(props);

        StateManagerWrapper.subscribe(presenter::stateListener, view::update);
      } catch (InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }
  }
}
