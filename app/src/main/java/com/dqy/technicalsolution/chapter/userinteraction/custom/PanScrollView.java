package com.dqy.technicalsolution.chapter.userinteraction.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.Scroller;

public class PanScrollView extends FrameLayout {
    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;
    //上一个事件移动的位置
    private float mLastTouchX,mLastTouchY;
    /*拖动阀值*/
    private int mTouchSlop;
    /*急滑的速度*/
    private int mMaximumVelocity,mMinimumVelocity;
    /*拖动锁*/
    private boolean mDragging = false;

    public PanScrollView(Context context) {
        super(context);
        init(context);
    }

    public PanScrollView(Context context,AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PanScrollView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
        mVelocityTracker = VelocityTracker.obtain();
        //获得触摸阀值的系统常量
        mMaximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        mMinimumVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
    }

    @Override
    protected void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
        int childWidthMeasureSpec,childHeightMeasureSpec;
        childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
        childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
        child.measure(childWidthMeasureSpec,childHeightMeasureSpec);
    }

    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        final  MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

        final int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(lp.topMargin + lp.bottomMargin,MeasureSpec.UNSPECIFIED);
        final int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(lp.leftMargin + lp.rightMargin,MeasureSpec.UNSPECIFIED);
        child.measure(childWidthMeasureSpec,childHeightMeasureSpec);
    }

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
                //终止所有正在进行的急速滑行
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                //还原速度追踪器
                mVelocityTracker.clear();
                mVelocityTracker.addMovement(ev);
                //保存初始触点
                mLastTouchX = ev.getX();
                mLastTouchY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float x = ev.getX();
                final float y = ev.getY();
                final int xDiff = (int) Math.abs(x-mLastTouchX);
                final int yDiff = (int) Math.abs(y-mLastTouchY);
                if(xDiff > mTouchSlop || yDiff > mTouchSlop){
                    mDragging = true;
                    mVelocityTracker.addMovement(ev);
                    return true; //返回true表明当前view捕捉事件,调用当前view的onTouchEvent
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mDragging = false;
                mVelocityTracker.clear();
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                final float x = event.getX();
                final float y = event.getY();
                float deltax = mLastTouchX - x;
                float deltay = mLastTouchY - y;
                if(Math.abs(deltax) > mTouchSlop || Math.abs(deltay) > mTouchSlop && !mDragging){
                    mDragging = true;
                }
                if(mDragging){
                    scrollBy((int)deltax,(int)deltay);
                    mLastTouchX = x;
                    mLastTouchY = y;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                mDragging = false;
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_UP:
                mDragging = false;
                //计算当前的速度，如果高于最小阀值，则启动一个急滑
                int velocityX = (int) mVelocityTracker.getXVelocity();
                int velocityY = (int) mVelocityTracker.getYVelocity();
                if(Math.abs(velocityX) > mMinimumVelocity || Math.abs(velocityY) > mMinimumVelocity){
                    fling(-velocityX,-velocityY);
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void fling(int velocityX,int velocityY){
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

    private int clamp(int n,int my,int child){
        if(my >= child || n <0){
            //子视图超过父视图的边界或者小于父视图，不能滚动
            return  0;
        }

        if((my + n) >child){
            //请求的滚动超出了子视图的右边界
            return child - my;
        }
        return  n;
    }
}
