package com.zhangli.test.applicationtest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button call;
    private EditText tele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        call = (Button) findViewById(R.id.call);

        call.setOnClickListener(this);
        tele = (EditText) findViewById(R.id.tele);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.call:
                submit();
                break;
        }
    }
    private void submit() {
        // validate
        String teleString = tele.getText().toString().trim();
        if (TextUtils.isEmpty(teleString)) {
            Toast.makeText(this, "teleString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        callPhone();
        // TODO validate success, do something
        //checkPermission();
    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Log.w("tag", "没有权限");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            Log.w("tag", "开始授权");
        } else {
            Toast.makeText(this, "你已经申请了打电话权限", Toast.LENGTH_LONG).show();
            Log.w("tag", "你已经申请了权限");
            callPhone();

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "权限" + permissions[i] + "申请成功", Toast.LENGTH_LONG).show();
                    callPhone();
                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                        Log.w("tag", "开始授权");
                    } else {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                }
            }
        }
    }

    public void callPhone() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + tele.getText()));
            startActivity(intent);

        }
    }

}
