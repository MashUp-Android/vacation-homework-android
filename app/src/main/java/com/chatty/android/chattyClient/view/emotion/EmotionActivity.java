package com.chatty.android.chattyClient.view.emotion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.model.response.Emotion;
import com.chatty.android.chattyClient.presenter.write.emotion.EmotionRecyclerAdapter;
import com.chatty.android.chattyClient.view.main.MainActivity;

import java.util.ArrayList;

public class EmotionActivity extends Activity {
  public static final String EMOTION = "EOMTION";
  public static String emotion;
  RecyclerView mRecyclerView;
//  RecyclerView.LayoutManager mLayoutManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_emotion);
//    UI 객체생성
//    emotionText = (TextView)findViewById(R.id.txtText);
    //데이터 가져오기
//    Intent intent = getIntent();
//    String data = intent.getStringExtra("data");
//    txtText.setText(data);
// init LayoutManager

//
//    recyclerView.setLayoutManager(layoutManager);

    mRecyclerView = findViewById(R.id.emotion_recycler_view);
    mRecyclerView.setHasFixedSize(true);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
    mRecyclerView.setLayoutManager(layoutManager);
//    mLayoutManager = new L
// inearLayoutManager(this);
//    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//    layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//    mRecyclerView.setLayoutManager(mLayoutManager);

    ArrayList<Emotion> foodInfoArrayList = new ArrayList<>();
    foodInfoArrayList.add(new Emotion(R.drawable.ic_icon_emotion_joy, "joy"));
    foodInfoArrayList.add(new Emotion(R.drawable.ic_icon_emotion_soso, "soso"));
    foodInfoArrayList.add(new Emotion(R.drawable.ic_icon_emotion_sentimental, "sentimental"));
    foodInfoArrayList.add(new Emotion(R.drawable.ic_icon_emotion_sad, "sad"));
    foodInfoArrayList.add(new Emotion(R.drawable.ic_icon_emotion_angry, "angry"));

    EmotionRecyclerAdapter emotionRecyclerAdapter = new EmotionRecyclerAdapter(foodInfoArrayList);

    mRecyclerView.setAdapter(emotionRecyclerAdapter);
  }

  //확인 버튼 클릭
  public void ViewClose(View v){
    //데이터 전달하기
    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
    intent.putExtra(EMOTION,emotion);
    intent.putExtra("result", "Close emotion");
    setResult(RESULT_OK, intent);
    //액티비티(팝업)
    startActivity(intent);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    //바깥레이어 클릭시 안닫히게
    if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
      return false;
    }
    return true;
  }

//  @Override
//  public void onBackPressed() {
//    //안드로이드 백버튼 막기
//    return;
//  }
}
