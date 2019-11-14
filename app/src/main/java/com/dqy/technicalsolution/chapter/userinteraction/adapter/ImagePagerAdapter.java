package com.dqy.technicalsolution.chapter.userinteraction.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImagePagerAdapter extends PagerAdapter {
    private Context mContext;
    private static final int[] IMAGES={
            android.R.drawable.ic_menu_camera,
            android.R.drawable.ic_menu_add,
            android.R.drawable.ic_menu_delete,
            android.R.drawable.ic_menu_share,
            android.R.drawable.ic_menu_edit
    };
    private static final int[] COLORS= {
            Color.RED,
            Color.BLUE,
            Color.GREEN,
            Color.GRAY,
            Color.MAGENTA
    };

    public ImagePagerAdapter(Context context){
        super();
        mContext = context;
    }
    @Override
    public int getCount() {
        return IMAGES.length;
    }

    @Override
    public float getPageWidth(int position) {
        return 1f;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView iv = new ImageView(mContext);
        iv.setImageResource(IMAGES[position]);
        iv.setBackgroundColor(COLORS[position]);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == o);
    }
}
