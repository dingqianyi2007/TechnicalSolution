package com.dqy.technicalsolution.chapter.userinteraction;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dqy.technicalsolution.R;
import com.dqy.technicalsolution.chapter.userinteraction.adapter.ListPagerAdapter;

import java.util.ArrayList;

public class FragmentPagerActivity extends AppCompatActivity {
    private ArrayList<String> mListItems;
    private ListPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_pager);
        mListItems = new ArrayList<String>();
        mListItems.add("Mom");
        mListItems.add("Dad");
        mListItems.add("Sister");
        mListItems.add("Brother");
        mListItems.add("Cousin");
        mListItems.add("Niece");
        mListItems.add("Nephew");
        ViewPager pager = findViewById(R.id.view_pager);
        mAdapter = new ListPagerAdapter(getSupportFragmentManager(),mListItems);
        pager.setAdapter(mAdapter);
    }

    public void onAddClick(View v){
        mListItems.add("Crazy Uncle" + System.currentTimeMillis());
        mAdapter.notifyDataSetChanged();
    }

    public void onRemoveClick(View v){
        if(mListItems.isEmpty()){
            mListItems.remove(0);
        }
        mAdapter.notifyDataSetChanged();
    }
}
