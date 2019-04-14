package com.zhangli.test.material_design_application.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangli.test.material_design_application.Bean.NewsBean;
import com.zhangli.test.material_design_application.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter{

    private List<View> viewlist;

    public ViewPagerAdapter(List<View> viewlist) {
        this.viewlist = viewlist;
    }


    //getCount():返回要滑动的VIew的个数
    @Override
    public int getCount() {
        return viewlist.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }


    //instantiateItem()：做了两件事，第一：将当前视图添加到container中，第二：返回当前View
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = viewlist.get(position);
        container.addView(view);
        return view;
    }


    //destroyItem（）：从当前container中删除指定位置（position）的View;
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(viewlist.get(position));
    }
}
