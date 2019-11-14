package com.dqy.technicalsolution.chapter.hardwaremultimediainteraction;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dqy.technicalsolution.R;
import com.dqy.technicalsolution.chapter.communicationnetwork.CommunicationNetworkActivity;

public class HardwareMultimediaInteractionActivity extends AppCompatActivity {
    private Context mContext;
    private ListView lv_hardware_multimedia_interaction;
    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hardware_multimedia_interaction);
        mContext= HardwareMultimediaInteractionActivity.this;
        lv_hardware_multimedia_interaction=findViewById(R.id.lv_hardware_multimedia_interaction);
        mAdapter = ArrayAdapter.createFromResource(mContext,R.array.hardwareMultimediaInteraction_array,android.R.layout.simple_list_item_1);
        lv_hardware_multimedia_interaction.setAdapter(mAdapter);
    }
}
