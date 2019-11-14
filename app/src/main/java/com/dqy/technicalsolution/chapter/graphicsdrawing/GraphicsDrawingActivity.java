package com.dqy.technicalsolution.chapter.graphicsdrawing;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dqy.technicalsolution.R;
import com.dqy.technicalsolution.chapter.datapersistence.DataPersistenceActivity;

public class GraphicsDrawingActivity extends AppCompatActivity {
    private Context mContext;
    private ListView lv_graphics_drawing;
    private ArrayAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics_drawing);
        mContext= GraphicsDrawingActivity.this;
        lv_graphics_drawing=findViewById(R.id.lv_graphics_drawing);
        mAdapter = ArrayAdapter.createFromResource(mContext,R.array.graphicsDrawing_array,android.R.layout.simple_list_item_1);
        lv_graphics_drawing.setAdapter(mAdapter);
    }
}
