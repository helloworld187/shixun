package com.zhangli.test.applicationtest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CheckActivity extends AppCompatActivity {
    //1、首先声明一个数组permissions，将需要的权限都放在里面
    private String[] permission = new String[]{
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_SMS
    };
    //2、创建一个mPermissionList，逐个判断哪些权限未授予，未授予的权限存储到mPerrrmissionList中
    public List<String> mPermissionList = new ArrayList<>();
    private final int mRequestCode = 100;//权限请求码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        checkPermission();
    }

    /**
     * 权限判断和申请
     */
    public void checkPermission(){
        //清空没有通过的权限
        mPermissionList.clear();
        //逐个判断你要的权限是否已经通过
        for(int i=0; i<permission.length; i++){
             if(ContextCompat.checkSelfPermission(this,permission[i]) != PackageManager.PERMISSION_GRANTED){
                 //添加还未授予的权限
                 mPermissionList.add(permission[i]);
             }
        }
        //申请权限
        if(mPermissionList.size()>0){
            String[] perssions = mPermissionList.toArray(new String[mPermissionList.size()]);
            ActivityCompat.requestPermissions(this,perssions,mRequestCode);
        }else{
            Toast.makeText(this,"你已申请了权限",Toast.LENGTH_LONG).show();
            load();
        }
    }

    //请求权限后回调的方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //判断权限有没有通过
        boolean hasPermissionDismiss = false;
        if(requestCode == mRequestCode){
            for(int i=0;i<grantResults.length;i++){
                if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                    boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(
                            this,permissions[i]);
                    //选择禁止
                    if(showRequestPermission){
                        checkPermission();
                        return;
                    }else{ //选择不再询问
                        Uri packageURI = Uri.parse("package:" + getPackageName());
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                        startActivity(intent);
                    }
                    hasPermissionDismiss = true;
                }

            }
            //如果有权限没有被允许
            if(!hasPermissionDismiss){
                Toast.makeText(this,"权限申请成功",Toast.LENGTH_LONG).show();
                load();
            }
        }

    }

    public void load(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

