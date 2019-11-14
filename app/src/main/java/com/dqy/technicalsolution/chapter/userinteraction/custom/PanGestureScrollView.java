package com.dqy.technicalsolution.chapter.userinteraction.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.Scroller;

public class PanGestureScrollView extends FrameLayout {
    private GestureDetector mDetector;
    private Scroller mScroller;

    //
    private float mInitialX,mInitialY;
    //
    private int mTouchSlop;

    public PanGestureScrollView( Context context) {
        super(context);
        init(context);
    }

    public PanGestureScrollView( Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PanGestureScrollView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context) {
        mDetector = new GestureDetector(context,mListener);
        mScroller = new Scroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
        int childWidthMeasureSpec;
        int childHeightMeasureSpec;
        childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
        childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
        child.measure(childWidthMeasureSpec,childHeightMeasureSpec);
    }

    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        final int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(lp.leftMargin + lp.rightMargin,MeasureSpec.UNSPECIFIED);
        final int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(lp.topMargin + lp.bottomMargin,MeasureSpec.UNSPECIFIED);

        child.measure(childWidthMeasureSpec,childHeightMeasureSpec);
    }

    /**
     *
     */

    private GestureDetector.SimpleOnGestureListener mListener = new GestureDetector.SimpleOnGestureListener(){

        @Override
        public boolean onDown(MotionEvent e) {
            //取消当前急速动画
            if(!mScroller.isFinished()){
                mScroller.abortAnimation();
            }
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            fling((int)-velocityX / 3,(int)-velocityY / 3);
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            //任意视图都可以调用它来进行滚动
            scrollBy((int)distanceX,(int)distanceY);
            return true;
        }
    };

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            int oldX = getScrollX();
            int oldY = getScrollY();
            int x = mScroller.getCurrX();
            int y = mScroller.getCurrY();

            if(getChildCount() > 0){
                View child = getChildAt(0);
                x = clamp(x,getWidth() - getPaddingRight() - getPaddingLeft(),child.getWidth());
                y = clamp(y,getHeight() - getPaddingBottom() - getPaddingTop(),child.getHeight());
                if(x != oldX || y != oldY){
                    scrollTo(x,y);
                }
            }
            //在动画完成前一直绘制
            postInvalidate();
        }
    }

    @Override
    public void scrollTo(int x, int y) {
        if(getChildCount() > 0){
            View child = getChildAt(0);
            x = clamp(x,getWidth() - getPaddingRight() - getPaddingLeft(),child.getWidth());
            y = clamp(y,getHeight() - getPaddingBottom() - getPaddingTop(),child.getHeight());
            if( x != getScrollX() || y != getScrollY()){
                super.scrollTo(x, y);
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mInitialX = ev.getX();
                mInitialY = ev.getY();
                mDetector.onTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                final float x = ev.getX();
                final float y = ev.getY();
                final int yDiff= (int) Math.abs(y-mInitialY);
                final int xDiff = (int) Math.abs(x-mInitialX);
                if(yDiff > mTouchSlop || xDiff > mTouchSlop){
                    return  true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    private void fling(int velocityX, int velocityY) {
        if(getChildCount() > 0){
            int height = getHeight() - getPaddingBottom() -getPaddingTop();
            int width = getWidth() - getPaddingLeft() - getPaddingRight();
            int bottom = getChildAt(0).getHeight();
            int right = getChildAt(0).getWidth();
            mScroller.fling(getScrollX(),getScrollY(),velocityX,velocityY,0
                    ,Math.max(0,right-width),0,Math.max(0,bottom-height));
            invalidate();
        }
    }

    private int clamp(int n, int my, int child) {
        if(my >= child || n <0){
            return  0;
        }

        if((my + n) >child){
            return child - my;
        }
        return  n;
    }
}
