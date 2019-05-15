package com.zhangli.test.material_design_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView category_star;
    private ImageView catrgory_beauty;
    private ImageView category_pet;
    private ImageView catrgory_dongman;
    private ImageView category_bizhi;
    private ImageView catrgory_gaoxiao;
    private ImageView category_sheying;
    private ImageView category_sheji;
    private Toolbar toolbar;

    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initView();
    }

    private void initView() {
        category_star = (ImageView) findViewById(R.id.category_star);
        catrgory_beauty = (ImageView) findViewById(R.id.catrgory_beauty);
        category_pet = (ImageView) findViewById(R.id.category_pet);
        catrgory_dongman = (ImageView) findViewById(R.id.catrgory_dongman);
        category_bizhi = (ImageView) findViewById(R.id.category_bizhi);
        catrgory_gaoxiao = (ImageView) findViewById(R.id.catrgory_gaoxiao);
        category_sheying = (ImageView) findViewById(R.id.category_sheying);
        category_sheji = (ImageView) findViewById(R.id.category_sheji);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        category_star.setOnClickListener(this);
        catrgory_beauty.setOnClickListener(this);
        category_pet.setOnClickListener(this);
        catrgory_dongman.setOnClickListener(this);
        category_bizhi.setOnClickListener(this);
        catrgory_gaoxiao.setOnClickListener(this);
        category_sheying.setOnClickListener(this);
        category_sheji.setOnClickListener(this);




//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_back2);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_grey, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(CategoryActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.add_grey:
                Toast.makeText(this,"more",Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.category_star:
                intent= new Intent(this,WallPaper_Activity.class);
                intent.putExtra("category","明星");
                startActivity(intent);
                break;
            case R.id.catrgory_beauty:
               intent= new Intent(this,WallPaper_Activity.class);
                intent.putExtra("category","美女");
                startActivity(intent);
                break;
            case R.id.category_pet:
                intent= new Intent(this,WallPaper_Activity.class);
                intent.putExtra("category","宠物");
                startActivity(intent);
                break;
            case R.id.catrgory_dongman:
                intent= new Intent(this,WallPaper_Activity.class);
                intent.putExtra("category","动漫");
                startActivity(intent);
                break;
            case R.id.category_bizhi:
                intent= new Intent(this,WallPaper_Activity.class);
                intent.putExtra("category","壁纸");
                startActivity(intent);
                break;
            case R.id.catrgory_gaoxiao:
                intent= new Intent(this,WallPaper_Activity.class);
                intent.putExtra("category","搞笑");
                startActivity(intent);
                break;
            case R.id.category_sheying:
                intent= new Intent(this,WallPaper_Activity.class);
                intent.putExtra("category","摄影");
                startActivity(intent);
                break;
            case R.id.category_sheji:
                intent= new Intent(this,WallPaper_Activity.class);
                intent.putExtra("category","设计");
                startActivity(intent);
                break;
        }
    }
}
