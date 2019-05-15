package com.zhangli.test.material_design_application;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.zhangli.test.material_design_application.Adapter.AppManager;
import com.zhangli.test.material_design_application.Adapter.AppManagerAdapter;
import com.zhangli.test.material_design_application.Bean.AppModel;

import java.util.ArrayList;
import java.util.List;

public class AppActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{
    private TabLayout tab;
    private RecyclerView mRecyclerView;
    private String[] mStrTitle = {"已安装", "系统应用", "全部"};
    //给适配器使用
    private List<AppModel> mList = new ArrayList<>();
    //所有的应用信息
    private List<AppModel> mAllList = new ArrayList<>();
    private int currentTab = 0;
    private AppManagerAdapter mAppManagerAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        InitView();
    }

    private void InitView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back2);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        tab= (TabLayout) findViewById(R.id.app_mytab);
        mRecyclerView = (RecyclerView) findViewById(R.id.app_rv);
        mAppManagerAdapter = new AppManagerAdapter(this, mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAppManagerAdapter);
        mAllList = AppManager.getInstance(this).getAllApp();
        for (int i = 0; i < mStrTitle.length; i++) {
            tab.addTab(tab.newTab().setText(mStrTitle[i]));
        }
        // tab.addOnTabSelectedListener(this);
        tab.addOnTabSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(AppActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.add:
                Toast.makeText(this, "more", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        // Toast.makeText(getActivity(),tab.getText(),Toast.LENGTH_LONG).show();
        parsingApp(tab.getPosition());
    }

    private void parsingApp(int currentTab) {
        this.currentTab = currentTab;
        //防止数据重复
        if (mList != null) {
            if (mList.size() > 0) {
                mList.clear();
            }
        }
        for (int i = 0; i < mAllList.size(); i++) {
            AppModel appModel = mAllList.get(i);
            switch (currentTab) {
                case 0:
                    if (!appModel.isSystem()) {
                        mList.add(appModel);
                    }
                    break;
                case 1:
                    if (appModel.isSystem()) {
                        mList.add(appModel);
                    }
                    break;
                case 2:
                    mList.addAll(mAllList);
                    break;
            }
        }
        //刷新适配器
        mAppManagerAdapter.notifyDataSetChanged();

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
