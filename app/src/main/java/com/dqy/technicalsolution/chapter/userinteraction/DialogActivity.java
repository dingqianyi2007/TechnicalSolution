package com.dqy.technicalsolution.chapter.userinteraction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;

public class DialogActivity extends AppCompatActivity implements DialogInterface.OnClickListener, View.OnClickListener {
    private static final String[] ZONES={"Pacific Time","Mountain Time","Central Time","Eastern Time","Atlantic Time"};

    Button mButton;
    AlertDialog mActions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mButton = new Button(this);
        mButton.setText("Click for Time Zones");
        mButton.setAllCaps(false);
        mButton.setOnClickListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Time Zones");
        builder.setItems(ZONES,this);

        builder.setNegativeButton("Cancel",null);

        mActions= builder.create();
        setContentView(mButton);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        String selected = ZONES[which];
        mButton.setText(selected);
    }

    @Override
    public void onClick(View v) {
        mActions.show();
    }
}
