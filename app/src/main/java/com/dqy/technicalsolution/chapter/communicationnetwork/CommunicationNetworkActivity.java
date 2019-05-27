package com.dqy.technicalsolution.chapter.communicationnetwork;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dqy.technicalsolution.MainActivity;
import com.dqy.technicalsolution.R;
import com.dqy.technicalsolution.chapter.layoutview.LayoutViewActivity;

public class CommunicationNetworkActivity extends AppCompatActivity {
    private Context mContext;
    private ListView lv_communication_network;
    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication_network);
        mContext = CommunicationNetworkActivity.this;
        lv_communication_network = findViewById(R.id.lv_communication_network);
        mAdapter = ArrayAdapter.createFromResource(mContext, R.array.communicationNetwork_array, android.R.layout.simple_list_item_1);
        lv_communication_network.setAdapter(mAdapter);
        lv_communication_network.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent layoutViewIntent = new Intent(CommunicationNetworkActivity.this, ShowWebInfoActivity.class);
                        startActivity(layoutViewIntent);
                        break;
                }
            }
        });
    }
}
