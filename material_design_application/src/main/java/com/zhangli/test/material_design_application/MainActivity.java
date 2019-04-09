package com.zhangli.test.material_design_application;

import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

import com.zhangli.test.material_design_application.Fragement.DressedFragment;
import com.zhangli.test.material_design_application.Fragement.FocusFragment;
import com.zhangli.test.material_design_application.Fragement.MemberFragment;
import com.zhangli.test.material_design_application.Fragement.MessageFragment;
import com.zhangli.test.material_design_application.Fragement.PeopleFragment;
import com.zhangli.test.material_design_application.Fragement.StarFragment;
import com.zhangli.test.material_design_application.Fragement.WalletFragment;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    //定义FragmentTabHost对象
    private FragmentTabHost mTabHost;

    //定义一个布局
    private LayoutInflater layoutInflater;

    //定义数组来存放Fragment界面
    private Class fragmentArray[] = {MessageFragment.class, PeopleFragment.class,FocusFragment.class, StarFragment.class};

    //定义数组来存放导航图标
    private int mImageViewArray[] = {R.animator.message,R.animator.people,R.animator.focus,R.animator.star};

    //Tab选项卡的文字
    private String mTextviewArray[] = {"消息", "联系人", "看点","动态"};

    private ColorStateList colorStateList;



    private Fragment showFragment;
    private int preFragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();


    }
    private void initView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);

        }
        navigationView.setCheckedItem(R.id.member);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.member:
                        showHomeFragement(R.id.member);
                        break;
                    case R.id.wallet:
                        showHomeFragement(R.id.wallet);
                        break;
                    case R.id.decorate:
                        showHomeFragement(R.id.decorate);
                        break;
                    default:
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });


        //实例化布局对象
        layoutInflater = LayoutInflater.from(this);

        //实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost)findViewById(R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.content);

        //得到fragment的个数
        int count = fragmentArray.length;

        for(int i = 0; i < count; i++){
        //为每一个Tab按钮设置图标、文字和内容
            TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
        //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
        }
    }


    private void showHomeFragement(int id){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        hideAllFragement(fragmentTransaction);
        String tag = mTabHost.getCurrentTabTag();
        Log.d("MainActivity", "showHomeFragement: "+tag);
        if (mTextviewArray[0].equals(tag)) {
            Fragment fragment = fragmentManager.findFragmentByTag(tag);
            fragmentTransaction.hide(fragment);
        }
        Fragment prefragment = fragmentManager.findFragmentByTag(getTag(preFragment));
        if (prefragment !=null ){
            fragmentTransaction.hide(prefragment);
        }
        Fragment fragment = getFragment(id);
        fragmentTransaction.add(R.id.content, fragment, getTag(id));
        preFragment =  id;
        fragmentTransaction.commitAllowingStateLoss();
    }
    private String getTag(int i) {
        return "tag"+i;
    }


    private Fragment getFragment(int i) {
        switch (i) {
            case R.id.member:
                showFragment = new MemberFragment();
                break;
            case R.id.wallet:
                showFragment = new WalletFragment();
                break;
            case R.id.decorate:
                showFragment = new DressedFragment();
                break;
        }
        return showFragment;
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index){
        View view = layoutInflater.inflate(R.layout.tabhost_item, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);

        if(colorStateList == null){
            colorStateList = getResources().getColorStateList(R.color.change_color_text, null);

        }

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);
        textView.setTextColor(colorStateList);
        return view;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.upload:
                Toast.makeText(this,"upload",Toast.LENGTH_SHORT).show();
            case R.id.delete:
                Toast.makeText(this,"delete",Toast.LENGTH_SHORT).show();
            case R.id.setting:
                Toast.makeText(this,"more",Toast.LENGTH_SHORT).show();
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
        }
        return true;
    }
}
