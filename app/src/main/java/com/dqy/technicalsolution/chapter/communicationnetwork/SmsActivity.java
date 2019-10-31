package com.dqy.technicalsolution.chapter.communicationnetwork;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class SmsActivity extends AppCompatActivity {
    private static final String RECIPIENT_ADDRESS = "18271484892";
    private static final String ACTION_SENT = "com.examples.sms.SENT";
    private static final String ACTION_DELIVERED = "com.examples.sms.DELIVERED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button sendButton = new Button(this);
        sendButton.setText("Hail the Mothership");
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS("Beam us up !");
            }
        });
        setContentView(sendButton);
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(sent,new IntentFilter(ACTION_SENT));
        registerReceiver(delivered,new IntentFilter(ACTION_DELIVERED));
    }

    @Override
    protected void onPause() {
        super.onPause();
        //
        unregisterReceiver(sent);
        unregisterReceiver(delivered);
    }

    private void sendSMS(String message){
        PendingIntent sIntent = PendingIntent.getBroadcast(this,0,new Intent(ACTION_SENT),0);
        PendingIntent dIntent = PendingIntent.getBroadcast(this,0,new Intent(ACTION_DELIVERED),0);

        SmsManager manager = SmsManager.getDefault();
        try {
            manager.sendTextMessage(RECIPIENT_ADDRESS, null, message, sIntent, dIntent);
        }catch (Exception e){
            Toast.makeText(SmsActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private BroadcastReceiver sent = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (getResultCode()){
                case Activity.RESULT_OK:
                    Toast.makeText(context,"success",Toast.LENGTH_SHORT).show();
                    //发送成功
                    break;
                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                case SmsManager.RESULT_ERROR_NO_SERVICE:
                case SmsManager.RESULT_ERROR_NULL_PDU:
                case SmsManager.RESULT_ERROR_RADIO_OFF:
                    Toast.makeText(context,"fail",Toast.LENGTH_SHORT).show();
                    //发送失败
                    break;
            }
        }
    };

    private BroadcastReceiver delivered = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (getResultCode()){
                case Activity.RESULT_OK:
                    break;
                case Activity.RESULT_CANCELED:
                    break;
            }
        }
    };
}
