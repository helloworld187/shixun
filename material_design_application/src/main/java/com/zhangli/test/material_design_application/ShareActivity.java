package com.zhangli.test.material_design_application;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zhangli.test.material_design_application.Adapter.GridViewAdapter;
import com.zhangli.test.material_design_application.Bean.GridViewBean;
import com.zhangli.test.material_design_application.Utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShareActivity extends AppCompatActivity implements View.OnClickListener {

    private Button share;

    private Banner banner;
    private List<String> list_title;
    private List<Object> list  = new ArrayList<>();

    private Context mContext;
    private GridView gridView;
    private GridViewAdapter mAdapter;
    private List<GridViewBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_);
        initView();


    }

//    @Override
//    protected void onResume() {
//        banner.startAutoPlay();
//        super.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        banner.stopAutoPlay();
//        super.onPause();
//    }

    private void initView() {
        share = (Button) findViewById(R.id.share);

        share.setOnClickListener(this);
        testBanner();
        setGridView();

    }
    public void testBanner(){
        banner = (Banner) findViewById(R.id.banner);
        //设置图片加载器
        //banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        list.add("http://pic.netbian.com/uploads/allimg/181010/204910-153917575011b7.jpg");
        list.add(R.drawable.bizhi);
        list.add("http://pic.netbian.com/uploads/allimg/180428/163322-152490440242d7.jpg");
        list.add("http://pic.netbian.com/uploads/allimg/170626/165029-14984670296f9a.jpg");
        list_title = new ArrayList<>();
        list_title.add("来一场说走就走的旅行");
        list_title.add("享受美食的盛宴");
        list_title.add("聆听大自然的声音");
        list_title.add("感受美好的明天");
        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.ZoomOutSlide);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(list_title);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置监听
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(ShareActivity.this,"你点击的是"+position,Toast.LENGTH_SHORT).show();
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    public void setGridView(){
        gridView = (GridView) findViewById(R.id.gridView);
        data = new ArrayList<>();
        data.add(new GridViewBean(R.drawable.ic_food, "美食享受"));
        data.add(new GridViewBean(R.drawable.ic_bike, "共享单车"));
        data.add(new GridViewBean(R.drawable.ic_sun, "生活服务"));
        data.add(new GridViewBean(R.drawable.ic_house, "酒店住宿"));
        data.add(new GridViewBean(R.drawable.ic_family, "亲子乐园"));
        data.add(new GridViewBean(R.drawable.ic_park, "景点门票"));
        data.add(new GridViewBean(R.drawable.ic_learn, "学习机构"));
        data.add(new GridViewBean(R.drawable.ic_ktv, "卡拉欧克"));
        data.add(new GridViewBean(R.drawable.ic_car, "快递物流"));
        mAdapter = new GridViewAdapter(this, data);
        gridView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share:
                shareBySystem();
                break;
        }
    }


    //通过第三方组件ShareSDK插件实现多平台分享

//    通过系统的组件进行分享
    private void shareBySystem(){

        Intent textIntent = new Intent(Intent.ACTION_SEND);
        textIntent.setType("text/plain");
        textIntent.putExtra(Intent.EXTRA_TEXT, "测试分享功能");
        startActivity(Intent.createChooser(textIntent, "分享"));

    }
}
