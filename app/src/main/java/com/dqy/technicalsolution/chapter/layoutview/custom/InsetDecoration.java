package com.dqy.technicalsolution.chapter.layoutview.custom;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dqy.technicalsolution.R;

public  class InsetDecoration extends RecyclerView.ItemDecoration {
    private int mInsetMargin;

    public InsetDecoration(Context context){
        super();
        mInsetMargin = context.getResources().getDimensionPixelOffset(R.dimen.inset_margin);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.set(mInsetMargin,mInsetMargin,mInsetMargin,mInsetMargin);
    }
}
