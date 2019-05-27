package com.dqy.technicalsolution.chapter.systeminteraction;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dqy.technicalsolution.R;

public class SystemInteractionActivity extends AppCompatActivity {
    private Context mContext;
    private ListView lv_system_interaction;
    private ArrayAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_interaction);
        mContext= SystemInteractionActivity.this;
        lv_system_interaction=findViewById(R.id.lv_system_interaction);
        mAdapter = ArrayAdapter.createFromResource(mContext,R.array.systemInteraction_array,android.R.layout.simple_list_item_1);
        lv_system_interaction.setAdapter(mAdapter);
    }
}
