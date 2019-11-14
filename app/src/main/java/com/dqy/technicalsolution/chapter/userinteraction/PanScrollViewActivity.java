package com.dqy.technicalsolution.chapter.userinteraction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dqy.technicalsolution.R;
import com.dqy.technicalsolution.chapter.userinteraction.custom.PanScrollView;

public class PanScrollViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PanScrollView scrollView = new PanScrollView(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        for(int i=0;i < 5; i++){
            ImageView iv = new ImageView(this);
            iv.setImageResource(R.drawable.ic_launcher);
            layout.addView(iv);
        }
        scrollView.addView(layout);
        setContentView(scrollView);
        //setContentView(R.layout.activity_pan_scroll_view);
    }
}
