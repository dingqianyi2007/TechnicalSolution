package com.dqy.technicalsolution.chapter.layoutview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        lv_layout_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(LayoutViewActivity.this,StyledComponentsActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
