package com.dqy.technicalsolution.chapter.layoutview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.dqy.technicalsolution.R;


public class LayoutViewActivity extends AppCompatActivity {
    private Context mContext;
    private ListView lv_layout_view;
    private ArrayAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_view);
        mContext= LayoutViewActivity.this;
        lv_layout_view=findViewById(R.id.lv_layout_view);
        mAdapter = ArrayAdapter.createFromResource(mContext,R.array.layoutViewData_array,android.R.layout.simple_list_item_1);
        lv_layout_view.setAdapter(mAdapter);
    }
}
