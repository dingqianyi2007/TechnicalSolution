package com.dqy.technicalsolution.chapter.userinteraction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dqy.technicalsolution.R;
import com.dqy.technicalsolution.chapter.userinteraction.custom.PanGestureScrollView;

public class PanGestureScrollViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PanGestureScrollView scrollView = new PanGestureScrollView(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        for(int i =0;i < 5;i++){
            ImageView iv = new ImageButton(this);
            iv.setImageResource(R.drawable.ic_launcher);
            layout.addView(iv,new LinearLayout.LayoutParams(1000,500));
        }
        scrollView.addView(layout);
        //setContentView(R.layout.activity_pan_scroll);
        setContentView(scrollView);
    }
}
