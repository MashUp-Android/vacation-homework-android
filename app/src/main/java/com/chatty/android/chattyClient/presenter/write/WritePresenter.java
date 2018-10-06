package com.chatty.android.chattyClient.presenter.write;

import android.Manifest;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.EditText;

import com.chatty.android.chattyClient.externalModules.AndroidExtended.ExtendedPresenter;
import com.chatty.android.chattyClient.model.ChatBalloon;
import com.chatty.android.chattyClient.model.State;
import com.chatty.android.chattyClient.state.Store;
import com.chatty.android.chattyClient.state.action.ChatAction;
import com.chatty.android.chattyClient.view.write.WriteActivityProps;
import com.chatty.android.chattyClient.view.write.WriteActivityState;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class WritePresenter extends ExtendedPresenter<WriteActivityProps, WriteActivityState, State> {
  private static final String FALSE= "FALSE";
  private static final String IS_INITIALIZED = "IS_INITIALIZED";
  private static final String TRUE = "TRUE";
  private static int SELECT = 0;
  private Uri uri=null;

  File sendImage = new File(uri.getPath());

  RequestBody currentImage = RequestBody.create(MediaType.parse("multipart/*"), this.sendImage);
  MultipartBody.Part requestImage = MultipartBody.Part.createFormData("profile_image", this.sendImage.getName(), currentImage);

  @Override
  public WriteActivityProps initiate() {
    try {
      Store.dispatch(ChatAction.requestStartChat());
    } catch (Exception e) {
      e.printStackTrace();
    }
    WriteActivityProps writeActivityProps = new WriteActivityProps();
    writeActivityProps.handleClickWriteSubmitButton = this::handleClickWriteSubmit;
    writeActivityProps.handleClickSelectImageButton = this::handleClickSelectImage;
    return writeActivityProps;
  }

  @Override
  public WriteActivityProps stateListener(State state) {
    WriteActivityProps props = new WriteActivityProps();
    props.chatBalloons = state.chat.chatBalloons;
    props.writeDiaryId = state.chat.writeDiaryId;
    return props;
  }
  public void handleClickWriteSubmit(EditText editText) {
    try {
      String writeDiaryId = Store.getState().chat.writeDiaryId;
      Store.dispatch(ChatAction.requestAppendChat(
        writeDiaryId,
        editText.getText().toString(),
        toString(this.uri),
        requestImage));
      this.uri = null;
      editText.setText(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void handleClickSelectImage(Context context, FragmentManager fragmentManager) {
    SELECT=1;
    PermissionListener permissionListener = new PermissionListener() {
      @Override
      public void onPermissionGranted() {
        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(context)
          .setOnImageSelectedListener(uri ->
            selectImage(uri)
          ).create();
        tedBottomPicker.show(fragmentManager);
      }

      @Override
      public void onPermissionDenied(List<String> deniedPermissions) {
//        Toast.makeText(WriteActivity, "Not allowed\n.", Toast.LENGTH_SHORT).show();fd f
      }
    };

    TedPermission.with(context)
      .setPermissionListener(permissionListener)
      .setDeniedMessage("If you reject permission,you can not use this service\\n\\nPlease turn on permissions at [Setting] > [Permission]")
      .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
      .check();
  }

  private void selectImage(Uri uri) {
    this.uri = uri;
    Log.e("2", String.valueOf(this.uri));
      }
}
