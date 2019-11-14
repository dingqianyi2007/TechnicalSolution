package com.dqy.technicalsolution.chapter.userinteraction;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dqy.technicalsolution.R;

import java.util.List;

public class DisallowActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private static final String[] ITEMS = {"Row One","Row Two","Row Three","Row Four","Row Five"
            ,"Row Six" ,"Row Seven","Row Eight","Row Nine","Row Ten"};
    private ViewPager mViewPager;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setLayoutParams(new ListView.LayoutParams(
                ListView.LayoutParams.MATCH_PARENT,getResources().getDimensionPixelSize(R.dimen.header_height)));
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setAdapter(new HeaderAdapter(this));

        mListView = new ListView(this);
        mListView.addHeaderView(mViewPager);

        mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ITEMS));
        setContentView(mListView);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {
        boolean isScrolling = i != ViewPager.SCROLL_STATE_IDLE;
        mListView.requestDisallowInterceptTouchEvent(isScrolling);
    }

    private static class  HeaderAdapter extends PagerAdapter{
        private Context mContext;

        public HeaderAdapter(Context context){
            mContext = context;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return (view == o);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            TextView tv = new TextView(mContext);
            tv.setText(String.format("Page %d",position + 1));
            tv.setBackgroundColor((position % 2 == 0) ? Color.RED : Color.GREEN);
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(Color.BLACK);
            container.addView(tv);
            return tv;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View page = (View) object;
            container.removeView(page);
        }
    }
}
