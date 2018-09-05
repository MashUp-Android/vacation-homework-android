package com.chatty.android.chattyClient.externalModules.AndroidExtended;

import android.support.v7.app.AppCompatActivity;

import java.util.function.Function;

public interface ExtendedPresenter<S> {
  Object stateListener(S state);
}
