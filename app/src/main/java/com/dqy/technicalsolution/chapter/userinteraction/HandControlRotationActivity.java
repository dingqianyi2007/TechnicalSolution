package com.dqy.technicalsolution.chapter.userinteraction;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import com.dqy.technicalsolution.R;

public class HandControlRotationActivity extends AppCompatActivity {

    private EditText mEditText;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadView();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(mCheckBox.isChecked()){
            final  Bundle uiState = new Bundle();
            saveState(uiState);
            loadView();
            restoreState(uiState);
        }
    }

    private void saveState(Bundle state){
        state.putBoolean("checkbox",mCheckBox.isChecked());
        state.putString("text",mEditText.getText().toString());
    }

    private void restoreState(Bundle state){
        mCheckBox.setChecked(state.getBoolean("checkbox"));
        mEditText.setText(state.getString("text"));
    }

    private void loadView(){
        setContentView(R.layout.activity_hand_controll_rotation);
        mCheckBox = findViewById(R.id.override);
        mEditText = findViewById(R.id.text);
    }
}
