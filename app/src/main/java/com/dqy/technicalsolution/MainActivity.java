package com.dqy.technicalsolution;

import android.content.Context;
import android.content.Intent;
import android.os.UserManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dqy.technicalsolution.chapter.communicationnetwork.CommunicationNetworkActivity;
import com.dqy.technicalsolution.chapter.layoutview.LayoutViewActivity;
import com.dqy.technicalsolution.chapter.uerinteraction.UserInteractionActivity;


public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private ListView lv_main;
    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=MainActivity.this;
        lv_main=findViewById(R.id.lv_main);
        mAdapter = ArrayAdapter.createFromResource(mContext,R.array.mainData_array,android.R.layout.simple_list_item_1);
        lv_main.setAdapter(mAdapter);
        lv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent layoutViewIntent = new Intent(MainActivity.this, LayoutViewActivity.class);
                        startActivity(layoutViewIntent);
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, UserInteractionActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, CommunicationNetworkActivity.class));

                }
            }
        });

    }
}
