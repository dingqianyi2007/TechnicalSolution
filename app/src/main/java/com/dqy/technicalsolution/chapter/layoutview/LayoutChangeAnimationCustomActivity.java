package com.dqy.technicalsolution.chapter.layoutview;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dqy.technicalsolution.R;

public class LayoutChangeAnimationCustomActivity extends AppCompatActivity {
    LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_change_animation_custom);

        mContainer = findViewById(R.id.verticalContainer);
        LayoutTransition transition = new LayoutTransition();
        mContainer.setLayoutTransition(transition);

        Animator appearAnim = ObjectAnimator
                .ofFloat(null,"rotationY",90f,0f)
                .setDuration(transition.getDuration(LayoutTransition.APPEARING));
        transition.setAnimator(LayoutTransition.APPEARING,appearAnim);

        Animator disappearAnim = ObjectAnimator
                .ofFloat(null,"rotationX",0f,90f)
                .setDuration(transition.getDuration(LayoutTransition.DISAPPEARING));
        transition.setAnimator(LayoutTransition.DISAPPEARING,disappearAnim);

        PropertyValuesHolder pvhSlide = PropertyValuesHolder.ofFloat("y",0,1);
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY",1f,0.5f,1f);
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX",1f,0.5f,1f);
        Animator changingAppearingAnim =
                ObjectAnimator.ofPropertyValuesHolder(this,pvhSlide,pvhScaleY,pvhScaleX);
        changingAppearingAnim.setDuration(transition.getDuration(LayoutTransition.CHANGE_APPEARING));
        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,changingAppearingAnim);

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
