package com.dqy.technicalsolution.chapter.datapersistence;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dqy.technicalsolution.R;
import com.dqy.technicalsolution.chapter.hardwaremultimediainteraction.HardwareMultimediaInteractionActivity;

public class DataPersistenceActivity extends AppCompatActivity {
    private Context mContext;
    private ListView lv_data_persistence;
    private ArrayAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_persistence);
        mContext= DataPersistenceActivity.this;
        lv_data_persistence=findViewById(R.id.lv_data_persistence);
        mAdapter = ArrayAdapter.createFromResource(mContext,R.array.dataPersistence_array,android.R.layout.simple_list_item_1);
        lv_data_persistence.setAdapter(mAdapter);
    }
}
