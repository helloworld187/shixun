package com.zhangli.test.material_design_application;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.widget.Toast;


import com.zhangli.test.material_design_application.Adapter.ScreenManager;
import com.zhangli.test.material_design_application.Adapter.SystemInfoAdapter;
import com.zhangli.test.material_design_application.Bean.SystemInfoModel;
import com.zhangli.test.material_design_application.Bean.Type;
import com.zhangli.test.material_design_application.Utils.FileUtils;
import com.zhangli.test.material_design_application.Utils.FormatUtils;
import com.zhangli.test.material_design_application.Utils.MacUtils;
import com.zhangli.test.material_design_application.Utils.VersionUtils;

import java.util.ArrayList;
import java.util.List;

public class SystemInfoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SystemInfoAdapter mSystemInfoAdapter;
    private List<SystemInfoModel> mList = new ArrayList<>();

    /**
     * 手机的基本信息：
     * <p>
     * - 基本信息
     * - 品牌 Android / XIAOMI
     * - 型号 Android SDK built for x86
     * - CPU型号 Core X86
     * - CPU渲染器
     * - 分辨率
     * - 后置摄像头
     * - 设备IMEI
     * - 一键ROOT
     * - 数据同步
     * - 存储信息
     * - ROM RAM
     * - CPU信息
     * - 显示信息
     * - 相机信息
     * - 电池信息
     * - OS信息
     * - NFC信息
     * - 网络信息
     * - 传感器信息
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_info);

        initData();
        initView();
    }

    //初始化数据
    @SuppressLint("MissingPermission")
    private void initData() {

        addTitle("基本信息");

        addContentText("品牌:", Build.MANUFACTURER);
        addContentText("型号:", Build.MODEL);
        addContentText("CPU:", Build.CPU_ABI);
        addContentText("分辨率:", ScreenManager.getInstance(this).getW() + " x " + ScreenManager.getInstance(this).getH());
        addContentText("IMEI:", ((TelephonyManager) (getSystemService(TELEPHONY_SERVICE))).getDeviceId());
        addContentText("MAC:", MacUtils.getMac(this));
        addContentButton("一键ROOT", "ROOT");
        addContentButton("数据同步", "同步");

        addTitle("存储信息");
        addContentText("总大小:", FormatUtils.formatSize(FileUtils.getSDSize(0)) + "");
        addContentText("可用大小:", FormatUtils.formatSize(FileUtils.getSDSize(1)) + "");
        addContentText("运行内存:",
                FormatUtils.formatSize(FileUtils.getAvailMemory(this, 0))
                        + " / " +
                        FormatUtils.formatSize(FileUtils.getAvailMemory(this, 1)));

        addTitle("电池");
        addContentText("电量", "");
        addContentText("温度", 0 + "");
        addContentButton("一键降温", "降温");

        addTitle("OS");
        addContentText("Android版本", VersionUtils.getVersion() + "");
        addContentText("内核版本", FileUtils.getLinuxVersion());

        addTitle("传输");
        addContentText("NFC", getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC)?"支持":"不支持");

        addTitle("传感器");
        addContentButton("指南针", "使用");
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mSystemInfoAdapter = new SystemInfoAdapter(this, mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mSystemInfoAdapter);

        mSystemInfoAdapter.setOnItemClickListener(new SystemInfoAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                switch (position) {
                    case 7:
                        Toast.makeText(SystemInfoActivity.this, "ROOT", Toast.LENGTH_SHORT).show();
                        break;
                    case 8:
                        Toast.makeText(SystemInfoActivity.this, "同步", Toast.LENGTH_SHORT).show();
                        break;
                    case 16:
                        Toast.makeText(SystemInfoActivity.this, "降温", Toast.LENGTH_SHORT).show();
                        break;
                    /*case 23:
                        startActivity(new Intent(SystemInfoActivity.this,CompassActivity.class));
                        break;*/
                }
            }
        });
    }

    /**
     * 添加头部
     *
     * @param title
     */
    private void addTitle(String title) {
        SystemInfoModel model = new SystemInfoModel();
        model.setTitle(title);
        model.setType(Type.LAYOUT_TITLE);
        mList.add(model);
    }

    /**
     * 添加对应数据
     *
     * @param key
     * @param value
     */
    private void addContentText(String key, String value) {
        SystemInfoModel model = new SystemInfoModel();
        model.setKey(key);
        model.setValue(value);
        model.setType(Type.LAYOUT_CONTENT_TEXT);
        mList.add(model);
    }

    /**
     * 添加有按钮的item
     *
     * @param key
     * @param value
     */
    private void addContentButton(String key, String value) {
        SystemInfoModel model = new SystemInfoModel();
        model.setKey(key);
        model.setValue(value);
        model.setType(Type.LAYOUT_CONTENT_BUTTON);
        mList.add(model);
    }
}
