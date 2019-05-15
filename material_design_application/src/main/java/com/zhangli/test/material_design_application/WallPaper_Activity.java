package com.zhangli.test.material_design_application;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.zhangli.test.material_design_application.Adapter.WallPaperAdapter;
import com.zhangli.test.material_design_application.Bean.WallPaperBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WallPaper_Activity extends AppCompatActivity {

    private List<WallPaperBean> wallPaperBeanList = new ArrayList<>();
    private RecyclerView recyclerView;
    private String Tag ="WallPaper_Activity";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall_paper_);
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        sendRequestWithOkHttp("http://image.baidu.com/channel/listjson?pn=0&rn=30&tag1="+category+"&ie=utf8");
    }


    private void initView(List<WallPaperBean> wallPaperBeans) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back2);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        Log.d(Tag,"数据信息"+wallPaperBeans);
        recyclerView = (RecyclerView) findViewById(R.id.rv_wallPaper);
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//不设置的话，图片闪烁错位，有可能有整列错位的情况。
        recyclerView.setLayoutManager(layoutManager);
        WallPaperAdapter wallPaperAdapter = new WallPaperAdapter(wallPaperBeans,this);
        recyclerView.setAdapter(wallPaperAdapter);
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
                Intent intent = new Intent(WallPaper_Activity.this, CategoryActivity.class);
                startActivity(intent);
                break;
            case R.id.add:
                Toast.makeText(this, "more", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private  void sendRequestWithOkHttp(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showResponse(final String responseData){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json1 = new JSONObject(responseData);
                    JSONArray json2 = json1.getJSONArray("data");
                    for(int i=0; i<json2.length(); i++){
                        JSONObject jsonObject = json2.getJSONObject(i);
                        String image_url = jsonObject.getString("image_url");
                        Log.d("WallPaper_Activity","图片"+image_url);
                        WallPaperBean wallPaperBean = new WallPaperBean();
                        wallPaperBean.setImage_url(image_url);
                        Log.d("Tag","图片对象"+wallPaperBean);
                        wallPaperBeanList.add(wallPaperBean);
                        initView(wallPaperBeanList);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
