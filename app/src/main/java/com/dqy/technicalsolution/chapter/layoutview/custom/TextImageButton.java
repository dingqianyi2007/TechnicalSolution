package com.dqy.technicalsolution.chapter.layoutview.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class TextImageButton extends FrameLayout {
    private ImageView imageView;
    private TextView  textView;
    public TextImageButton( Context context) {
        this(context,null);
    }

    public TextImageButton( Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TextImageButton( Context context, AttributeSet attrs, int defStyleAttr) {
        //
        //
        super(context, attrs, android.R.attr.buttonStyle);
        imageView = new ImageView(context,attrs,defStyleAttr);
        textView = new TextView(context,attrs,defStyleAttr);
        //
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT
                ,LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        //
        this.addView(imageView,params);
        this.addView(textView,params);

        //
        if(imageView.getDrawable() != null){
            textView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
        }else {
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
        }
    }

    //
    public void setText(CharSequence text){
        textView.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);
        textView.setText(text);
    }

    public void setImageResource(int resId){
        textView.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(resId);
    }

    public void setImageDrawable(Drawable drawable){
        textView.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageDrawable(drawable);
    }
}
