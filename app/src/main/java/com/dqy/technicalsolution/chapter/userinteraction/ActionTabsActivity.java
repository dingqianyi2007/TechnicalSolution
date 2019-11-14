package com.dqy.technicalsolution.chapter.userinteraction;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.dqy.technicalsolution.R;
import com.dqy.technicalsolution.chapter.userinteraction.adapter.SlidingTabLayout;

public class ActionTabsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_tabs);
        ViewPager viewPager = findViewById(R.id.pager);
        SlidingTabLayout tabLayout = findViewById(R.id.tabs);
        viewPager.setAdapter(new TabsPagerAdapter(this));
        tabLayout.setViewPager(viewPager);
        tabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.WHITE;
            }

            @Override
            public int getDividerColor(int position) {
                return 0;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    private static class TabsPagerAdapter extends PagerAdapter{
        private Context mContext;

        public TabsPagerAdapter(Context context){
            mContext = context;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return  "Primary";
                case 1:
                    return  "Secondary";
                case 2:
                    return  "Tertiary";
                case 3:
                    return  "Quaternary";
                case 4:
                    return  "Quinary";
                default:
                    return "";
            }
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView pageView = new ImageView(mContext);
            pageView.setScaleType(ImageView.ScaleType.CENTER);
            pageView.setImageResource(R.drawable.ic_launcher);
            container.addView(pageView);
            return pageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
           container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return (view == o);
        }
    }
}