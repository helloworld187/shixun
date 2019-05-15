package com.zhangli.test.material_design_application;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class TestSrceen_Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView textview;
    private int colornum = 0;
    private int mColors[] = new int[]{R.color.zhenghong,R.color.chunbai,R.color.lanse,R.color.huangse,R.color.chunhei};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_srceen_);
        initView();
    }

    private void initView() {
        textview = (TextView) findViewById(R.id.textview);
        textview.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textview:
                changeColor();
                break;
        }
    }

    public void changeColor() {
        Log.d("a", "changeColor: "+colornum);
        if (colornum == mColors.length) {
            Intent intent = new Intent(TestSrceen_Activity.this,SystemActivity.class);
            startActivity(intent);
            return;
        }
        textview.setBackground(getResources().getDrawable(mColors[colornum]));
//        Drawable drawable = getResources().getDrawable(R.color.chunbai, null);
        Drawable drawable = textview.getBackground();
        ColorDrawable colorDrawable = (ColorDrawable) drawable;
        int color = colorDrawable.getColor();
        Log.d("a", "changeColor: "+String.valueOf(color == getResources().getColor(R.color.chunbai)));
        if (color == getResources().getColor(R.color.chunbai)) {
            textview.setTextColor(Color.BLACK);
        } else {
            textview.setTextColor(Color.WHITE);
        }
        colornum++;
        Log.d("a", "onClick: " + colornum);
    }

}
