package com.dqy.technicalsolution.chapter.layoutview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dqy.technicalsolution.R;

public class CreateAnimatorViewActivity extends AppCompatActivity implements View.OnClickListener{

    View viewToAnimate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_animator_view);
        Button button = findViewById(R.id.toggleButton);
        button.setOnClickListener(this);

        viewToAnimate = findViewById(R.id.theView);

    }

    @Override
    public void onClick(View v) {
        if(viewToAnimate.getAlpha() > 0f){
            viewToAnimate.animate().alpha(0f).translationX(1000f);
        }else {
            viewToAnimate.setTranslationX(0f);
            viewToAnimate.animate().alpha(1f);
        }

    }
}
