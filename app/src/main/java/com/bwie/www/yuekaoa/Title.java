package com.bwie.www.yuekaoa;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import adapter.FragmentAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import fragments.ContentFragment;
import fragments.DailyFragment;
import fragments.Zhuanlan;

public class Title extends AppCompatActivity {
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    private String path1 = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=";
    private int page = 1;
    private String path2 = "&size=10&offset=0";
    private ArrayList<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        ButterKnife.bind(this);

        fragments = new ArrayList<>();
        ContentFragment fragment1 = new ContentFragment();
        Zhuanlan zhuanlan = new Zhuanlan();
        Zhuanlan zhuanlan1 = new Zhuanlan();
        DailyFragment DailyFragment = new DailyFragment();
        fragments.add(fragment1);
        fragments.add(zhuanlan);
        fragments.add(zhuanlan1);
        fragments.add(DailyFragment);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
    }
}
