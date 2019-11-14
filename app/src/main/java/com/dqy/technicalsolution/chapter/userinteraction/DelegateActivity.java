package com.dqy.technicalsolution.chapter.userinteraction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dqy.technicalsolution.chapter.userinteraction.custom.TouchDelegateLayout;

public class DelegateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TouchDelegateLayout layout = new TouchDelegateLayout(this);
        setContentView(layout);
        //setContentView(R.layout.activity_delegate);
    }
}
