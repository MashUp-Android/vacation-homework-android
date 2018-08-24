package com.chatty.android.chattyClient.view.app;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.widget.ImageView;

public class ProfileAvatarImage {
  public static void CUSTOMVIEW__initRender(ImageView imageView) {
    ShapeDrawable sd = new ShapeDrawable(new OvalShape());
    imageView.setBackground(sd);
    imageView.setClipToOutline(true);
  }
}
