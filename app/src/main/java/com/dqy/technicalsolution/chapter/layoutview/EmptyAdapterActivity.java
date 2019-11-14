package com.dqy.technicalsolution.chapter.layoutview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dqy.technicalsolution.R;

public class EmptyAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_adapter);

        ListView list = findViewById(R.id.mylist);

        LinearLayout empty = findViewById(R.id.myempty);

        list.setEmptyView(empty);
    }
}
