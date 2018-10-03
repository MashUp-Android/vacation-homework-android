package com.chatty.android.chattyClient.externalModules.AndroidExtended;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.chatty.android.chattyClient.externalModules.ReduxJava.ReduxJavaAndroidConnector;

import java.util.ArrayList;

public class Contractor {
  private static ArrayList<Entry> entries = new ArrayList<>();

  public static void connect(
    ExtendedView view,
    Class<?> clazz,
    ReduxJavaAndroidConnector reduxJavaAndroidConnector
  ) {
    Log.i(Contractor.class.getSimpleName(), " connect() " + view + " " + clazz);

    Entry entry = new Contractor().new Entry();
    entry.connect(view, clazz, reduxJavaAndroidConnector);
    Contractor.entries.add(entry);
  }

  class Entry<Props, LocalState, State> {
    void connect(
      ExtendedView<Props> view,
      Class<ExtendedPresenter<Props, LocalState, State>> clazz,
      ReduxJavaAndroidConnector<State, Props> reduxJavaAndroidConnector
    ) {
      try {
        ExtendedPresenter<Props, LocalState, State> presenter = clazz.newInstance();
        presenter.activity = (AppCompatActivity) view;
        Props props = presenter.initiate();
        view.initialRender(props);

        reduxJavaAndroidConnector.subscribe(presenter::stateListener, view::update);
      } catch (InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }

  }
}
