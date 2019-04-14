package com.zhangli.test.material_design_application.Fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangli.test.material_design_application.Adapter.ViewPagerAdapter;
import com.zhangli.test.material_design_application.Bean.NewsBean;
import com.zhangli.test.material_design_application.News_Details_Activity;
import com.zhangli.test.material_design_application.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FocusFragment extends Fragment{
    private ViewPager viewPager;
    private View news_items;
    private List<View> viewlist = new ArrayList<View>();
    private List<NewsBean> newsList = new ArrayList<NewsBean>();
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_focus,null);
        sendRequestWithOkHttp("http://api.jisuapi.com/news/get?channel=头条&start=0&num=10&appkey=dc3bd6edb7e4fca7");
        return view;
    }

    private void initView(final List<NewsBean> data){
        viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        LayoutInflater inflater = getLayoutInflater();
        Log.d("FocusFragment", "initView: "+newsList.size());
        Log.d("FocusFragment", "initView: "+view);
        for (int i=0; i<data.size(); i++) {
            news_items = inflater.inflate(R.layout.news_items, null);
            TextView title_tv = news_items.findViewById(R.id.news_title);
            title_tv.setText(data.get(i).getTitle());
            TextView src_tv = news_items.findViewById(R.id.news_src);
            src_tv.setText(data.get(i).getSrc());
            TextView time_tv = news_items.findViewById(R.id.news_time);
            time_tv.setText(data.get(i).getTime());
            TextView category_tv = news_items.findViewById(R.id.news_category);
            category_tv.setText(data.get(i).getCategory());
            ImageView pic_iv = news_items.findViewById(R.id.news_pic);
            Glide.with(news_items.getContext()).load(data.get(i).getPic()).into(pic_iv);
            final int position = i;
            pic_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),News_Details_Activity.class);
                    intent.putExtra("weburl",data.get(position).getWeburl());
                    startActivity(intent);
                }
            });
            viewlist.add(news_items);
        }
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(viewlist);
        viewPager.setAdapter(viewPagerAdapter);

    }


    private void sendRequestWithOkHttp(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        }

        private void showResponse(final String responseData){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try{
                        JSONObject json1 = new JSONObject(responseData);
                        JSONArray jsonArray = json1.getJSONObject("result").getJSONArray("list");
                        for(int i=0;i<jsonArray.length();i++){
                            String title = jsonArray.getJSONObject(i).getString("title");
                            String time = jsonArray.getJSONObject(i).getString("time");
                            String src = jsonArray.getJSONObject(i).getString("src");
                            String category = jsonArray.getJSONObject(i).getString("category");
                            String pic = jsonArray.getJSONObject(i).getString("pic");
                            final String weburl = jsonArray.getJSONObject(i).getString("weburl");

                            newsList.add(new NewsBean(title,time,src,category,pic,weburl));
                            Log.d("FocusFragment", "run: "+i+newsList.toString());
                        }
                        initView(newsList);
                    }catch (Exception e){
                       e.printStackTrace();
                    }
                }
            });

            }


}