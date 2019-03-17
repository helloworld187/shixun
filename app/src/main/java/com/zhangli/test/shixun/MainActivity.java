package com.zhangli.test.shixun;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhangli.test.shixun.Base.BaseActivity;
import com.zhangli.test.shixun.utils.LogUtils;
import com.zhangli.test.shixun.utils.ScreenUtils;
import com.zhangli.test.shixun.utils.SharedPreUtils;
import com.zhangli.test.shixun.utils.VersionUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button sharedpre;
    private Button screen;
    private Button system;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            Toast.makeText(this,"你点击了返回键",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        sharedpre = (Button) findViewById(R.id.sharedpre);
        screen = (Button) findViewById(R.id.screen);
        system = (Button) findViewById(R.id.system);

        sharedpre.setOnClickListener(this);
        screen.setOnClickListener(this);
        system.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sharedpre:
                SharedPreUtils.putString(MainActivity.this,"账号","870629252");
                SharedPreUtils.putString(MainActivity.this,"密码","123456");
                LogUtils.d("账号："+SharedPreUtils.getString(MainActivity.this,"账号",""));
                LogUtils.d("密码："+SharedPreUtils.getString(MainActivity.this,"密码",""));
                break;
            case R.id.screen:
                ScreenUtils.init(MainActivity.this);
                int width = ScreenUtils.width;
                int height = ScreenUtils.height;
                Toast.makeText(MainActivity.this,"宽："+width+"高："+height,Toast.LENGTH_LONG).show();
                LogUtils.d("宽："+width+"高："+height);
//                Log.d("MainActivity",String.valueOf(width)); //width+""  这样也可以
//                Log.d("MainActivity", ""+height);
                break;
            case R.id.system:
                VersionUtils.getVersion();
                Toast.makeText(MainActivity.this,"当前系统版本为："+ Build.VERSION.SDK_INT,Toast.LENGTH_LONG).show();
                LogUtils.d("当前系统版本为："+ Build.VERSION.SDK_INT);

                break;
        }
    }
}
