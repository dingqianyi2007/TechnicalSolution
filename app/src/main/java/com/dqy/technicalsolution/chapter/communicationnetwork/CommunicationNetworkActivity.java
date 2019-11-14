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
    private int a;

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
                        a=(int)(Math.random()*(2))+1;
                        if(a == 1 ) {
                            startActivity(new Intent(CommunicationNetworkActivity.this, ShowWebInfoActivity.class));
                        }
                        if(a == 2){
                            startActivity(new Intent(CommunicationNetworkActivity.this, LocalWebInfoActivity.class));
                        }
                        break;
                    case 1:
                            startActivity(new Intent(CommunicationNetworkActivity.this, InterceptWebEventActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(CommunicationNetworkActivity.this, AccessWebViewJSActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(CommunicationNetworkActivity.this, WebImageActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(CommunicationNetworkActivity.this, DownloadActivity.class));
                        break;
                    case 5:
                        a=(int)(Math.random()*(2))+2;
                        if(a == 1 ) {
                            startActivity(new Intent(CommunicationNetworkActivity.this, SearchActivity.class));
                        }
                        if(a == 2){
                            startActivity(new Intent(CommunicationNetworkActivity.this, Search1Activity.class));
                        }
                        if(a == 3){
                            startActivity(new Intent(CommunicationNetworkActivity.this, AuthActivity.class));
                        }
                        break;
                    case 6:
                        startActivity(new Intent(CommunicationNetworkActivity.this, DecodeJSONActivity.class));
                        break;
                    case 7:
                        a=(int)(Math.random()*(2))+1;
                        if(a == 1 ) {
                            startActivity(new Intent(CommunicationNetworkActivity.this, FeedActivity.class));
                        }
                        if(a == 2){
                            startActivity(new Intent(CommunicationNetworkActivity.this, PullFeedActivity.class));
                        }
                        break;
                    case 9:
                        startActivity(new Intent(CommunicationNetworkActivity.this,SmsActivity.class));
                }
            }
        });
    }
}
