package com.dqy.technicalsolution.chapter.layoutview;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import com.dqy.technicalsolution.R;
import com.dqy.technicalsolution.chapter.layoutview.custom.PerspectiveScrollContentView;

public class ScrollActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HorizontalScrollView parentView = new HorizontalScrollView(this);
        PerspectiveScrollContentView contentView = new PerspectiveScrollContentView(this);
        //对此视图禁用硬件加速，因为动态调整每个子视图的变换当前无法通过硬件实现
        //也可以通过在清单文件中添加android:hardwareAccelerated="false"
        //
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
//            contentView.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
//        }
        for(int i = 0; i<20;i++){
            ImageView iv = new ImageView(this);
            iv.setImageResource(R.drawable.ic_launcher);
            contentView.addView(iv);
        }
        parentView.addView(contentView);
        setContentView(parentView);
    }
}
