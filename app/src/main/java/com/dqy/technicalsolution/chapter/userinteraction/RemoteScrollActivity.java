package com.dqy.technicalsolution.chapter.userinteraction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.dqy.technicalsolution.R;

public class RemoteScrollActivity extends AppCompatActivity implements View.OnTouchListener{
    private TextView mTouchText;
    private HorizontalScrollView mScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_scroll);
        mTouchText = findViewById(R.id.text_touch);
        mScrollView = findViewById(R.id.scroll_view);
        mTouchText.setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        event.setLocation(event.getX(),mScrollView.getHeight() / 2);

        mScrollView.dispatchTouchEvent(event);

        return true;
    }
}
