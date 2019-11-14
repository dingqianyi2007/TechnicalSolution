package com.dqy.technicalsolution.chapter.userinteraction.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.dqy.technicalsolution.R;

public class ContextListItem extends LinearLayout implements PopupMenu.OnMenuItemClickListener, View.OnClickListener {
    private PopupMenu mPopupMenu;
    private TextView mTextView;

    public ContextListItem(Context context) {
        super(context);
    }

    public ContextListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContextListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTextView = findViewById(R.id.text);

        View contextButton = findViewById(R.id.context);
        contextButton.setOnClickListener(this);

        mPopupMenu = new PopupMenu(getContext(),contextButton);
        mPopupMenu.setOnMenuItemClickListener(this);
        mPopupMenu.inflate(R.menu.context_menu);
    }

    @Override
    public void onClick(View v) {
        mPopupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        String itemText = mTextView.getText().toString();

        switch (item.getItemId()){
            case R.id.menu_edit:
                Toast.makeText(getContext(),"Edit " + itemText,Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_delete:
                Toast.makeText(getContext(),"Delete " + itemText,Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
