package com.dqy.technicalsolution.chapter.userinteraction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.dqy.technicalsolution.R;
import com.dqy.technicalsolution.chapter.userinteraction.custom.CurrencyTextWatcher;

import org.w3c.dom.Text;

public class TextWatcherActivity extends AppCompatActivity implements TextWatcher {
    EditText text1,text2;
    int textCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_watcher);
        text1 = findViewById(R.id.text1);
        text1.addTextChangedListener(this);
        text2 = findViewById(R.id.text2);
        text2.addTextChangedListener(new CurrencyTextWatcher());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
            textCount = text1.getText().length();
            setTitle(String.valueOf(textCount));
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
