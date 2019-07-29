package com.dqy.technicalsolution.chapter.userinteraction;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dqy.technicalsolution.R;
import com.dqy.technicalsolution.chapter.layoutview.LayoutViewActivity;

public class UserInteractionActivity extends AppCompatActivity {
    private Context mContext;
    private ListView lv_user_interaction;
    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interaction);
        mContext= UserInteractionActivity.this;
        lv_user_interaction=findViewById(R.id.lv_user_interaction);
        mAdapter = ArrayAdapter.createFromResource(mContext,R.array.userInteraction_array,android.R.layout.simple_list_item_1);
        lv_user_interaction.setAdapter(mAdapter);
        lv_user_interaction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(UserInteractionActivity.this, SupportActionActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
