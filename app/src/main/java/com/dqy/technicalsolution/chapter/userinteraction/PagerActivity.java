package com.dqy.technicalsolution.chapter.userinteraction;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dqy.technicalsolution.R;
import com.dqy.technicalsolution.chapter.userinteraction.adapter.ImagePagerAdapter;

public class PagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        ViewPager viewPager = new ViewPager(this);
        viewPager.setAdapter(new ImagePagerAdapter(this));

        setContentView(viewPager);

    }
}
