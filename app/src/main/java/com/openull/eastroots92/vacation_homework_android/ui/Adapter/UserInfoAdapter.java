package com.openull.eastroots92.vacation_homework_android.ui.Adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openull.eastroots92.vacation_homework_android.R;

import java.util.List;

public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.UserInfoViewHolder> {

  // 만약 Model이 있을 경우 해당 주석을 활성화 하여 값을 넘겨줘야 함
/*  List<Object> userInfo;

  public UserInfoAdapter(List<Object> items) {
    userInfo = items;
  }*/

  @NonNull
  @Override
  public UserInfoAdapter.UserInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);

    return new UserInfoViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull UserInfoViewHolder holder, int position) {
    // 값을 넣어주는 곳

    holder.item_name.setText("John Jr." + position);
    holder.item_age.setText("28");
  }

  @Override
  public int getItemCount() {
    // 값 Count  List면 List.size()가 들어가야함

   return 10;
  }


  public class UserInfoViewHolder extends RecyclerView.ViewHolder {

    TextView item_name;
    TextView item_age;

    public UserInfoViewHolder(@NonNull View itemView) {
      super(itemView);

      item_name = itemView.findViewById(R.id.textView_userName);
      item_age = itemView.findViewById(R.id.textView_userAge);
    }
  }
}
