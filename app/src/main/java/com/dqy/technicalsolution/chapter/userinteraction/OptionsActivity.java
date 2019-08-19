package com.dqy.technicalsolution.chapter.userinteraction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupMenu;

import com.dqy.technicalsolution.R;

public class OptionsActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, CompoundButton.OnCheckedChangeListener {
    private MenuItem mOptionItem;
    private CheckBox mFirstOption,mSecondOption;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options,menu);

        mOptionItem = menu.findItem(R.id.menu_add);
        mFirstOption = MenuItemCompat.getActionView(mOptionItem).findViewById(R.id.option_first);
        mFirstOption.setOnCheckedChangeListener(this);
        mSecondOption = MenuItemCompat.getActionView(mOptionItem).findViewById(R.id.option_second);
        mSecondOption.setOnCheckedChangeListener(this);
        MenuItemCompat.setOnActionExpandListener(mOptionItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                mFirstOption.setChecked(false);
                mSecondOption.setChecked(false);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        menuItemSelected(item);
        return true;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(mFirstOption.isChecked()){
            MenuItemCompat.collapseActionView(mOptionItem);
        }
    }

    private void menuItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_add:
                break;
            case R.id.menu_remove:
                break;
            case R.id.menu_edit:
                break;
            case R.id.menu_settings:
                break;
        }
    }
}
