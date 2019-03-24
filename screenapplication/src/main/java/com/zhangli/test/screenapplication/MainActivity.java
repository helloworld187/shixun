package com.zhangli.test.screenapplication;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.zhangli.test.screenapplication.utils.SharedPreUtils;
import com.zhangli.test.screenapplication.utils.VersionUtils;

public class MainActivity extends AppCompatActivity {
    private String[] permissions = new String[]{
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_SMS};
    private Message msg = new Message();
    String TAG = "测试值----";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkVersion();
    }

    private void checkVersion(){
        if(VersionUtils.isM()){
            requestPermissions();
            Log.d(TAG, "checkVersion: isM");
        }else{
            Log.d(TAG, "checkVersion: no");
            isFirsRuntApp();
        }
    }

    private void isFirsRuntApp(){
        boolean isFirst = SharedPreUtils.getBoolean(MainActivity.this,"isFirst",true);
        Log.d(TAG, "isFirsRuntApp: "+isFirst);
        if(isFirst){
            msg.what = 1;
            mHandler.sendMessageDelayed(msg,2000);
        }else{
            msg.what = 2;
            mHandler.sendMessageDelayed(msg,2000);
        }
    }

    public void requestPermissions(){
        ActivityCompat.requestPermissions(this, permissions,100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 100:
//                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    isFirsRuntApp();
//                }else{
//                    isFirsRuntApp();
//            }
                isFirsRuntApp();
        }

    }
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what){
                case 1:
                    Toast.makeText(MainActivity.this,"hander",Toast.LENGTH_LONG).show();
                    SharedPreUtils.putBoolean(MainActivity.this,"isFirst",false);
                    startActivity(new Intent(MainActivity.this,GuideActivity.class));
                    finish();
                    break;
                case 2:
                    startActivity(new Intent(MainActivity.this,IndexActivity.class));
                    finish();
                    break;
            }
            return false;
        }
    });

}
