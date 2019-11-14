package com.dqy.technicalsolution.chapter.userinteraction;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.dqy.technicalsolution.R;

public class NativeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String[] ITEMS = {"White","Red","Green","Blue"};
    private static final int[] COLORS = {Color.WHITE,0xffe51c23,0xff259b24,0xff5677fc};
    private DrawerLayout mDrawerContainer;
    private View mMainContent;
    private ListView mDrawerContent;
    private ActionBarDrawerToggle mDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);

        mDrawerContainer = findViewById(R.id.container_drawer);//根视图
        mMainContent = findViewById(R.id.container_root);//主视图
        mDrawerContent = findViewById(R.id.drawer_main);//左视图
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerContainer,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                supportInvalidateOptionsMenu();
            }
        };

        ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ITEMS);
        mDrawerContent.setAdapter(adapter);
        mDrawerContent.setOnItemClickListener(this);
        mDrawerContainer.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean isOpen = mDrawerContainer.isDrawerVisible(mDrawerContent);
        menu.findItem(R.id.action_delete).setVisible(!isOpen);
        menu.findItem(R.id.action_settings).setVisible(!isOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item)){
            mDrawerContainer.post(new Runnable() {
                @Override
                public void run() {
                    supportInvalidateOptionsMenu();
                }
            });
            return true;
        }
       switch (item.getItemId()){
           case R.id.action_delete:
               return true;
           case R.id.action_settings:
               return true;
           default:
               return super.onOptionsItemSelected(item);
       }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mMainContent.setBackgroundColor(COLORS[position]);
        mDrawerContainer.closeDrawer(mDrawerContent);
    }
}
