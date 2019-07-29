package com.dqy.technicalsolution.chapter.layoutview.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dqy.technicalsolution.R;

public class ConnectorDecoration extends RecyclerView.ItemDecoration {

    private Paint mLinePaint;
    private int mLineLength;

    public ConnectorDecoration(Context context){
        super();
        mLineLength = context.getResources().getDimensionPixelOffset(R.dimen.inset_margin);
        int connectorStroke = context.getResources().getDimensionPixelOffset(R.dimen.connector_stroke);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(Color.BLACK);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(connectorStroke);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        final RecyclerView.LayoutManager manager = parent.getLayoutManager();

        for(int i=0; i < parent.getChildCount(); i++){
            final View child = parent.getChildAt(i);
            boolean isLarge = parent.getChildViewHolder(child).getAdapterPosition() % 3 == 0;
            if(!isLarge){
                final int childLeft = manager.getDecoratedLeft(child);
                final int childRight = manager.getDecoratedRight(child);
                final int childTop = manager.getDecoratedTop(child);
                final int x = childLeft + ((childRight-childLeft) / 2);

                c.drawLine(x,childTop - mLineLength,x,childTop + mLineLength,mLinePaint);
            }
        }
    }
}