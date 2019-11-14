package com.dqy.technicalsolution.chapter.userinteraction.custom;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

public class DrawableDragShadowBuilder extends View.DragShadowBuilder {
    private Drawable mDrawable;

    public DrawableDragShadowBuilder(View view,Drawable drawable){
        mDrawable = drawable;
        mDrawable.setColorFilter(new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY));
    }
    @Override
    public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
        outShadowSize.x = mDrawable.getIntrinsicWidth();
        outShadowSize.y = mDrawable.getIntrinsicHeight();

        outShadowTouchPoint.x = mDrawable.getIntrinsicWidth() / 2;
        outShadowTouchPoint.y = mDrawable.getIntrinsicHeight() / 2;

        mDrawable.setBounds(new Rect(0,0,outShadowSize.x,outShadowSize.y));
    }

    @Override
    public void onDrawShadow(Canvas canvas) {
        mDrawable.draw(canvas);
    }
}
