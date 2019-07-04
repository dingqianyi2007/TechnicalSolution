package com.dqy.technicalsolution.chapter.layoutview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dqy.technicalsolution.R;

public class LayoutChangeAnimationActivity extends AppCompatActivity {
    LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_change_animation);
        mContainer = findViewById(R.id.verticalContainer);
    }

    public void onAddClick(View v){
        Button button = new Button(this);
        button.setText("Click To Remove");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContainer.removeView(v);
            }
        });

        mContainer.addView(button,new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
