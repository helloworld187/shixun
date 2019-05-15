package com.zhangli.test.material_design_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class SystemActivity extends AppCompatActivity implements View.OnClickListener {

    private Button test_screeen;
    private Button system_info;
    private Button compass;
    private Intent intent;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);
        initView();
    }

    private void initView() {
        test_screeen = (Button) findViewById(R.id.test_screeen);
        system_info = (Button) findViewById(R.id.system_info);
        compass = (Button) findViewById(R.id.compass);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        test_screeen.setOnClickListener(this);
        system_info.setOnClickListener(this);
        compass.setOnClickListener(this);
        toolbar.setOnClickListener(this);

        toolbar.setNavigationIcon(R.drawable.ic_back2);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_screeen:
                intent = new Intent(SystemActivity.this, TestSrceen_Activity.class);
                startActivity(intent);
                break;
            case R.id.system_info:
                intent = new Intent(SystemActivity.this, SystemInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.compass:

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_grey, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(SystemActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.add_grey:
                Toast.makeText(this, "more", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
