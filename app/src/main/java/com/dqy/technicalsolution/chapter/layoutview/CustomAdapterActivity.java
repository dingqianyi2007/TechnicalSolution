package com.dqy.technicalsolution.chapter.layoutview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dqy.technicalsolution.R;

import java.util.List;

public class CustomAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView list = new ListView(this);
        setContentView(list);
        CustomAdapter adapter = new CustomAdapter(this,R.layout.custom_row,R.id.line1,
                new String[] {"Bill","Tom","Sally","Jenny"});
        list.setAdapter(adapter);

    }

    public static class CustomAdapter extends ArrayAdapter<String>{

        public CustomAdapter( Context context, int layout, int resourceId,  String[] items) {
            super(context, layout, resourceId, items);
        }


        @Override
        public View getView(int position,  View convertView, ViewGroup parent) {

            View row = convertView;
            //
            if(row == null){
                row = LayoutInflater.from(getContext()).inflate(R.layout.custom_row,parent,false);
            }
            String item = getItem(position);
            ImageView left = row.findViewById(R.id.left_image);
            ImageView right = row.findViewById(R.id.right_image);
            TextView text = row.findViewById(R.id.line1);
            left.setImageResource(R.drawable.ic_launcher);
            right.setImageResource(R.drawable.ic_launcher);
            text.setText(item);
            return row;
        }

    }
}
