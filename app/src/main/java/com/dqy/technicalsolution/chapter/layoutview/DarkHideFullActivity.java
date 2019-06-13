package com.dqy.technicalsolution.chapter.layoutview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.dqy.technicalsolution.R;

public class DarkHideFullActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_dark_hide_full);
    }
    //night mode
    public void onToggleClick(View v){
        int currentVis = v.getSystemUiVisibility();
        int newVis;
        if((currentVis & View.SYSTEM_UI_FLAG_LOW_PROFILE) == View.SYSTEM_UI_FLAG_LOW_PROFILE){
            newVis = View.SYSTEM_UI_FLAG_VISIBLE;
        }else {
            newVis = View.SYSTEM_UI_FLAG_LOW_PROFILE;
        }
        v.setSystemUiVisibility(newVis);

    }
    //navigation bar
    public void onToggleClick1(View v){
        v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
    //full screen
    public void onToggleClick2(View v){
        v.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );

    }
}
