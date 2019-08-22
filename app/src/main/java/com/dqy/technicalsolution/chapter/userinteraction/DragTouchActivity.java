package com.dqy.technicalsolution.chapter.userinteraction;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.dqy.technicalsolution.R;
import com.dqy.technicalsolution.chapter.userinteraction.custom.DrawableDragShadowBuilder;

public class DragTouchActivity extends AppCompatActivity implements View.OnLongClickListener {
    private int a;
    private Drawable drawable;
    private View.DragShadowBuilder shadowBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_touch);
        findViewById(R.id.image1).setOnLongClickListener(this);
        findViewById(R.id.image2).setOnLongClickListener(this);
        findViewById(R.id.image3).setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.image1:
                drawable = getResources().getDrawable(R.drawable.ic_send);
                break;
            case R.id.image2:
                drawable = getResources().getDrawable(R.drawable.ic_share);
                break;
            case R.id.image3:
                drawable = getResources().getDrawable(R.drawable.ic_favorite);
                break;
        }
        a=(int)(Math.random()*(2))+1;
        if(a == 1 ) {
             shadowBuilder = new View.DragShadowBuilder(v);
        }
        if(a == 2){
             shadowBuilder = new DrawableDragShadowBuilder(v,drawable);
        }
        v.startDrag(null,shadowBuilder,((ImageView) v).getDrawable(),0);
        return true;
    }
}
