package com.dqy.technicalsolution.chapter.communicationnetwork;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.dqy.technicalsolution.R;

import org.json.JSONException;
import org.json.JSONObject;

public class DecodeJSONActivity extends AppCompatActivity {
    private static final String JSON_STRING =
            "{\"person\":"
            + "{\"name\":\"John\",\"age\":30,\"children\":["
            + "{\"name\":\"Billy\",\"age\":5},"
            + "{\"name\":\"Sarah\",\"age\":7},"
            + "{\"name\":\"Tommy\",\"age\":9},"
            +"]}}";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decode_json);
        TextView line0 = findViewById(R.id.line0);
        line0.setText(JSON_STRING);
        TextView line1 = findViewById(R.id.line1);
        TextView line2 = findViewById(R.id.line2);
        TextView line3 = findViewById(R.id.line3);
        try{
            JSONObject person = new JSONObject(JSON_STRING).getJSONObject("person");
            String name = person.getString("name");
            line1.setText("This person's name is" + name);
            line2.setText("This person's age is" + person.getInt("age") + "years old.");
            line3.setText(name + " has" + person.getJSONArray("children").length() + "children.");
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
