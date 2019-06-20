package com.zhangli.test.material_design_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class News_Details_Activity extends AppCompatActivity {

    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");  toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        url = intent.getStringExtra("weburl");
        WebView webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        MenuItem menuItem =  menu.findItem(R.id.setting);
        menuItem.setIcon(R.drawable.ic_share);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.setting:
                shareBySystem();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareBySystem(){

//        Intent intent = new Intent(Intent.ACTION_SEND);
//
//        intent.setType("image/*");
//
//        intent.putExtra(Intent.EXTRA_SUBJECT,"Share");
//
//        //设置分享的内容
//
//        intent.putExtra(Intent.EXTRA_TEXT, url);
//
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        startActivity(Intent.createChooser(intent, getTitle()));

        Intent textIntent = new Intent(Intent.ACTION_SEND);
        textIntent.setType("text/plain");
        textIntent.putExtra(Intent.EXTRA_TEXT, url);
        startActivity(Intent.createChooser(textIntent, "分享"));

    }
}
