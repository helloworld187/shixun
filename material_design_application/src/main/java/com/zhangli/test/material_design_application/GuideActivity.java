package com.zhangli.test.material_design_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.zhangli.test.material_design_application.Adapter.ViewPagerAdapter;
import com.zhangli.test.material_design_application.Utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<View> mList= new ArrayList<>();
    private  View viewOne,viewTwo,viewThree;
    private int currentPage;
    private int startX;
    private int endX;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);
        ScreenUtils.init(this);
        initView();

    }


    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.vp);
        viewOne = View.inflate(this,R.layout.guide,null);
        viewTwo = View.inflate(this,R.layout.guide2,null);
        viewThree = View.inflate(this,R.layout.guide3,null);
        mList.add(viewOne);
        mList.add(viewTwo);
        mList.add(viewThree);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(mList);
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX = (int)motionEvent.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        endX = (int)motionEvent.getX();
                        if(currentPage == mList.size()-1){
                            if((startX-endX)> ScreenUtils.width/4){
                                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                                finish();
                            }
                        }
                        break;
                }
                return false;
            }
        });

    }
}
