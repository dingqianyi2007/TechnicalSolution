package com.dqy.technicalsolution.chapter.userinteraction;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.dqy.technicalsolution.R;

import static android.view.inputmethod.EditorInfo.IME_ACTION_GO;
import static android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH;

public class CustomKeyboardEnterActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_keyboard_enter);
        EditText text1 = findViewById(R.id.text1);
        text1.setOnEditorActionListener(this);
        EditText text2 = findViewById(R.id.text2);
        text2.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == IME_ACTION_SEARCH){
            return true;
        }
        if(actionId == IME_ACTION_GO){
            return true;
        }
        return false;
    }
}
