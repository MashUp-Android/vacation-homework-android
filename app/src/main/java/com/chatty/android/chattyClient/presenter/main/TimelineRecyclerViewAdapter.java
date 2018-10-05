package com.chatty.android.chattyClient.presenter.main;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chatty.android.chattyClient.R;
import com.chatty.android.chattyClient.model.TimelineEntry;
import com.chatty.android.chattyClient.widget.TimelineImageView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Handler;

public class TimelineRecyclerViewAdapter
  extends RecyclerView.Adapter<TimelineRecyclerViewAdapter.ViewHolder> {

  private final static DateFormat df = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());

  private List<TimelineEntry> timelineEntries;
  private RecyclerViewClickListener recyclerViewClickListener;
  private View view;

  public TimelineRecyclerViewAdapter(
    List<TimelineEntry> timelineEntries,
    RecyclerViewClickListener recyclerViewClickListener
  ) {
    this.timelineEntries = timelineEntries;
    this.recyclerViewClickListener = recyclerViewClickListener;
  }

  public void update(List<TimelineEntry> timelineEntries) {
    this.timelineEntries.clear();
    this.timelineEntries.addAll(timelineEntries);
    this.notifyDataSetChanged();
  }

  public interface RecyclerViewClickListener {
    void onClick(View view, TimelineEntry entry);
  }

  @NonNull
  @Override
  public TimelineRecyclerViewAdapter.ViewHolder onCreateViewHolder(
    @NonNull ViewGroup parent,
    int viewType
  ) {
    this.view = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.item_timeline_entry, parent, false);

    return new TimelineRecyclerViewAdapter.ViewHolder(this.view, this.recyclerViewClickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    setFadeAnimation(holder.itemView, position);
    TimelineEntry entry = this.timelineEntries.get(position);
    String date = TimelineRecyclerViewAdapter.df.format(entry.date);

    holder.contents.setText(entry.content);
    holder.date.setText(date);
    holder.entry = entry;
    ArrayList<String> imageUrls = new ArrayList<>();
    imageUrls.add("http://www.rainbowfestival.co.kr/wp-content/uploads/2017/05/585be1aa1600002400bdf2a6-970x658.jpeg");
    imageUrls.add("http://www.bseconomy.com/news/photo/201710/25251_3430_3838.jpg");
    imageUrls.add("http://www.ccnnews.co.kr/news/photo/201803/107194_130618_2038.jpg");
    imageUrls.add("https://ppss.kr/wp-content/uploads/2017/04/%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C-8-540x245.png");
    imageUrls.add("https://pds.joins.com/news/component/htmlphoto_mmdata/201810/03/797cb2a4-5b62-4f20-b5dd-4f72113920b6.jpg");
    imageUrls.add("https://pds.joins.com/news/component/htmlphoto_mmdata/201810/03/38b35928-fb99-4f4a-920d-8d128096e630.jpg");
    imageUrls.add("https://img.insight.co.kr/static/2017/02/09/700/95L8JYXY11YK1M16M8HP.jpg");
    imageUrls.add("https://pbs.twimg.com/media/CfQH_n3WwAE7Z5B.jpg");

    Random random = new Random();
    int randomNum = random.nextInt(imageUrls.size());
    String thumbnailUrl = entry.imgUrl != null
      ? entry.imgUrl
      : imageUrls.get(randomNum);

    Glide.with(view)
      .load(thumbnailUrl)
      .into(holder.thumbnail);
  }

  public void setFadeAnimation(View view, int position) {
    int posNum = position;
    int timer = (posNum < 4)? posNum * 200 + 500 : 1000;
    YoYo.with(Techniques.FadeInUp)
      .duration(timer)
      .playOn(view);
  }

  @Override
  public int getItemCount() {
    return this.timelineEntries.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TimelineEntry entry;
    TextView contents;
    TextView date;
    TimelineImageView thumbnail;
    RecyclerViewClickListener recyclerViewClickListener;

    public ViewHolder(
      @NonNull View view,
      RecyclerViewClickListener recyclerViewClickListener
    ) {
      super(view);
      this.contents = view.findViewById(R.id.textView_contents);
      this.date = view.findViewById(R.id.textView_timeline_date);
      this.recyclerViewClickListener = recyclerViewClickListener;
      this.thumbnail = view.findViewById(R.id.imageView_timeline);
      view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      this.recyclerViewClickListener.onClick(view, entry);
    }
  }
}
