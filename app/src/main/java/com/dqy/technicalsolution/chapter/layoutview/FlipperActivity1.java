package com.dqy.technicalsolution.chapter.layoutview;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.dqy.technicalsolution.R;

public class FlipperActivity1 extends AppCompatActivity {
    private boolean mIsHeads;
    private AnimatorSet mFlipper;
    private Bitmap mHeadsImage,mTailImage;
    private ImageView mFlipImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipper1);

        mHeadsImage = BitmapFactory.decodeResource(getResources(),R.drawable.heads);
        mTailImage  = BitmapFactory.decodeResource(getResources(),R.drawable.tails);
        mFlipImage = findViewById(R.id.flip_image1);
        mFlipImage.setImageResource(R.drawable.heads);
        mIsHeads = true;

        mFlipper = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.flip);
        mFlipper.setTarget(mFlipImage);

        ObjectAnimator flipAnimator = (ObjectAnimator) mFlipper.getChildAnimations().get(0);

        flipAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if(animation.getAnimatedFraction() >= 0.25f && mIsHeads){
                    mFlipImage.setImageBitmap(mTailImage);
                    mIsHeads = false;
                }
                if(animation.getAnimatedFraction() >= 0.75f && !mIsHeads){
                    mFlipImage.setImageBitmap(mHeadsImage);
                    mIsHeads = true;
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            mFlipper.start();
            return true;
        }
        return super.onTouchEvent(event);
    }
}
