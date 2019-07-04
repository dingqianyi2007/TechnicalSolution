package com.dqy.technicalsolution.chapter.layoutview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.dqy.technicalsolution.R;

public class FlipperActivity extends AppCompatActivity {

    private boolean mIsHeads;
    private ObjectAnimator mFlipper;
    private Bitmap mHeadsImage,mTailImage;
    private ImageView mFlipImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipper);
        mHeadsImage = BitmapFactory.decodeResource(getResources(),R.drawable.heads);
        mTailImage  = BitmapFactory.decodeResource(getResources(),R.drawable.tails);
        mFlipImage = findViewById(R.id.flip_image);
        mFlipImage.setImageBitmap(mHeadsImage);
        mIsHeads = true;
        mFlipper = ObjectAnimator.ofFloat(mFlipImage,"rotationY",0f,360f);
        mFlipper.setDuration(500);
        mFlipper.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
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
