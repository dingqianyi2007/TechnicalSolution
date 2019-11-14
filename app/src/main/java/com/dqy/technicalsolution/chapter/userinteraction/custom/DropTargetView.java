package com.dqy.technicalsolution.chapter.userinteraction.custom;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.widget.ImageView;
import android.view.View;

public class DropTargetView extends android.support.v7.widget.AppCompatImageView implements View.OnDragListener {
    boolean mDropped;

    public DropTargetView(Context context) {
        super(context);
        init();
    }

    public DropTargetView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DropTargetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public  void init(){
        setOnDragListener(this);
    }
    @Override
    public boolean onDrag(View v, DragEvent event) {
        PropertyValuesHolder pvhX,pvhY;
        switch (event.getAction()){
            case DragEvent.ACTION_DRAG_STARTED:
                pvhX = PropertyValuesHolder.ofFloat("scaleX",0.5f);
                pvhY = PropertyValuesHolder.ofFloat("scaleY",0.5f);
                ObjectAnimator.ofPropertyValuesHolder(this,pvhX,pvhY).start();
                setImageDrawable(null);
                mDropped = false;
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                if(!mDropped){
                    pvhX = PropertyValuesHolder.ofFloat("scaleX",1f);
                    pvhY = PropertyValuesHolder.ofFloat("scaleY",1f);
                    ObjectAnimator.ofPropertyValuesHolder(this,pvhX,pvhY).start();
                    mDropped = false;
                }
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                pvhX = PropertyValuesHolder.ofFloat("scaleX",0.75f);
                pvhY = PropertyValuesHolder.ofFloat("scaleY",0.75f);
                ObjectAnimator.ofPropertyValuesHolder(this,pvhX,pvhY).start();
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                pvhX = PropertyValuesHolder.ofFloat("scaleX",0.5f);
                pvhY = PropertyValuesHolder.ofFloat("scaleY",0.5f);
                ObjectAnimator.ofPropertyValuesHolder(this,pvhX,pvhY).start();
                break;
            case DragEvent.ACTION_DROP:

                Keyframe frame0 = Keyframe.ofFloat(0f,0.75f);
                Keyframe frame1 = Keyframe.ofFloat(0.5f,0f);
                Keyframe frame2 = Keyframe.ofFloat(1f,0.75f);
                pvhX = PropertyValuesHolder.ofKeyframe("scaleX",frame0,frame1,frame2);
                pvhY = PropertyValuesHolder.ofKeyframe("scaleY",frame0,frame1,frame2);
                ObjectAnimator.ofPropertyValuesHolder(this,pvhX,pvhY).start();
                setImageDrawable((Drawable) event.getLocalState());
                mDropped = true;
                break;
            default:
                break;

        }
        return true;
    }
}
