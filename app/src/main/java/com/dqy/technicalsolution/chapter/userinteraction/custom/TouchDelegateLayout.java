package com.dqy.technicalsolution.chapter.userinteraction.custom;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.TouchDelegate;
import android.widget.CheckBox;
import android.widget.FrameLayout;

public class TouchDelegateLayout extends FrameLayout {
    private CheckBox mButton;
    public TouchDelegateLayout(Context context) {
        super(context);
        init(context);
    }

    public TouchDelegateLayout(Context context,AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TouchDelegateLayout(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mButton = new CheckBox(context);
        mButton.setText("任意触摸");
        LayoutParams lp = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        addView(mButton,lp);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if(w != oldw || h != oldh){
            Rect bounds = new Rect(0,0,w,h);
            TouchDelegate delegate = new TouchDelegate(bounds,mButton);
            setTouchDelegate(delegate);
        }
    }
}
