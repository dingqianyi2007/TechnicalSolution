package com.dqy.technicalsolution.chapter.layoutview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.dqy.technicalsolution.R;
import com.dqy.technicalsolution.chapter.layoutview.custom.SimpleSectionsAdapter;

public class CustomHeaderAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView list = new ListView(this);

        SimpleSectionsAdapter<String> adapter = new SimpleSectionsAdapter<String>(list
        ,R.layout.list_header,android.R.layout.simple_list_item_1) {
            @Override
            public void onSectionItemClick(String item) {
                Toast.makeText(CustomHeaderAdapterActivity.this,item,Toast.LENGTH_LONG).show();
            }
        };
        adapter.addSection("Fruits",new String[]{"Apples","Oranges","Bananas","Mangos"});
        adapter.addSection("Vegetables",new String[]{"Carrots","Peas","Broccoli"});
        adapter.addSection("Meats",new String[]{"Pork","Chicken","Beef","Lamb"});
        list.setAdapter(adapter);
        setContentView(list);
    }
}
