package com.dqy.technicalsolution.chapter.communicationnetwork;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dqy.technicalsolution.R;

public class CommunicationNetworkActivity extends AppCompatActivity {
    private Context mContext;
    private ListView lv_communication_network;
    private ArrayAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication_network);
        mContext= CommunicationNetworkActivity.this;
        lv_communication_network=findViewById(R.id.lv_communication_network);
        mAdapter = ArrayAdapter.createFromResource(mContext,R.array.communicationNetwork_array,android.R.layout.simple_list_item_1);
        lv_communication_network.setAdapter(mAdapter);
    }
}
