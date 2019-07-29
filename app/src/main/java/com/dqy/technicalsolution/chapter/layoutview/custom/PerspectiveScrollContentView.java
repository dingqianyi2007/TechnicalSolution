package com.dqy.technicalsolution.chapter.layoutview.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class PerspectiveScrollContentView extends LinearLayout {
    //
    private static final float SCALE_FACTOR = 0.7f;
    //
    private static final float ANCHOR_X = 0.5f;
    private static final float ANCHOR_Y = 1.0f;
    public PerspectiveScrollContentView(Context context) {
        super(context);
        init();
    }

    public PerspectiveScrollContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PerspectiveScrollContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        //
        setStaticTransformationsEnabled(true);
    }

    private int getViewCenter(View view){
        int[] childCoords = new int[2];
        view.getLocationOnScreen(childCoords);
        int childCenter = childCoords[0] + (view.getWidth() / 2);
        return childCenter;
    }

    @Override
    protected boolean getChildStaticTransformation(View child, Transformation t) {
        HorizontalScrollView scrollView = null;
        if(getParent() instanceof HorizontalScrollView){
            scrollView = (HorizontalScrollView) getParent();
        }
        if (scrollView == null){
            return false;
        }
        int childCenter = getViewCenter(child);
        int viewCenter = getViewCenter(scrollView);
        //
        float delta = Math.min(1.0f,Math.abs(childCenter - viewCenter) / (float)viewCenter);
        //
        float scale = Math.max(0.4f,1.0f - (SCALE_FACTOR * delta));
        float xTrans = child.getWidth() * ANCHOR_X;
        float yTrans = child.getHeight() * ANCHOR_Y;
        //
        t.clear();
        //
        t.getMatrix().setScale(scale,scale,xTrans,yTrans);
        return true;
    }
}
