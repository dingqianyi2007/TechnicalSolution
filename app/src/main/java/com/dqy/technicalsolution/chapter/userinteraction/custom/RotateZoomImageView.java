package com.dqy.technicalsolution.chapter.userinteraction.custom;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

public class RotateZoomImageView extends android.support.v7.widget.AppCompatImageView {
    private ScaleGestureDetector mScaleDetector;
    private Matrix mImageMatrix;
    //
    private int mLastAngle;
    //变换时的轴点
    private int mPivoX,mPivoY;

    public RotateZoomImageView(Context context) {
        super(context);
        init(context);
    }

    public RotateZoomImageView(Context context,AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RotateZoomImageView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScaleDetector = new ScaleGestureDetector(context,mScaleListener);
        setScaleType(ScaleType.MATRIX);
        mImageMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if(w !=oldw || h !=oldh){
            int translateX = Math.abs(w-getDrawable().getIntrinsicWidth()) / 2;
            int translateY = Math.abs(h - getDrawable().getIntrinsicHeight()) / 2;
            mImageMatrix.setTranslate(translateX,translateY);
            setImageMatrix(mImageMatrix);
            mPivoX = w / 2;
            mPivoY = h / 2;
        }
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private ScaleGestureDetector.SimpleOnScaleGestureListener mScaleListener = new ScaleGestureDetector.SimpleOnScaleGestureListener(){
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            //
            float scaleFactor = detector.getScaleFactor();
            mImageMatrix.postScale(scaleFactor,scaleFactor,mPivoX,mPivoY);
            setImageMatrix(mImageMatrix);
            return true;
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            return true;
        }
        switch (event.getPointerCount()){
            case 3:
                return  mScaleDetector.onTouchEvent(event);
            case 2:
                return  doRotationEvent(event);
            default:
                return super.onTouchEvent(event);
        }

    }

    private boolean doRotationEvent(MotionEvent event) {
        float deltaX = event.getX(0) - event.getX(1);
        float deltaY = event.getY(0)- event.getY(1);
        double radians = Math.atan(deltaY/deltaX);
        //
        int degrees = (int) (radians * 180 /Math.PI);

        switch (event.getAction()){
            case  MotionEvent.ACTION_DOWN:
                mLastAngle = degrees;
                break;
            case MotionEvent.ACTION_MOVE:
                /**
                 *
                 */
                if((degrees - mLastAngle) > 45){
                    mImageMatrix.postRotate(-5,mPivoX,mPivoY);
                }else if((degrees - mLastAngle) < -45){
                    mImageMatrix.postRotate(5,mPivoX,mPivoY);
                }else {
                    mImageMatrix.postRotate(degrees-mLastAngle,mPivoX,mPivoY);
                }
                //
                setImageMatrix(mImageMatrix);
                mLastAngle = degrees;
                break;
        }
        return  true;
    }
}
