package com.dqy.technicalsolution.chapter.ndkrenderscript;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dqy.technicalsolution.R;

public class NdkRenderscriptActivity extends AppCompatActivity {
    private Context mContext;
    private ListView lv_ndk_renderscript;
    private ArrayAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk_renderscript);
        mContext= NdkRenderscriptActivity.this;
        lv_ndk_renderscript=findViewById(R.id.lv_ndk_renderscript);
        mAdapter = ArrayAdapter.createFromResource(mContext,R.array.ndkRenderscript_array,android.R.layout.simple_list_item_1);
        lv_ndk_renderscript.setAdapter(mAdapter);
    }
}
