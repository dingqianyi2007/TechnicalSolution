package com.dqy.technicalsolution.chapter.communicationnetwork;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.dqy.technicalsolution.R;

import java.text.DateFormat;
import java.util.Date;

public class NfcActivity extends AppCompatActivity implements NfcAdapter.CreateNdefMessageCallback, NfcAdapter.OnNdefPushCompleteCallback {
    private static final String TAG = "NfcBeam";
    private NfcAdapter mNfcAdapter;
    private TextView mDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDisplay = new TextView(this);
        setContentView(mDisplay);
        //
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(mNfcAdapter == null){
            mDisplay.setText("NFC is not available on this devices.");
        }else {
            //注册回调来设置NDEF消息。这样做可以使Activity处于前台时，NFC数据推送处于激活状态
            mNfcAdapter.setNdefPushMessageCallback(this,this);
            //注册回调来监听消息发送成功
            mNfcAdapter.setOnNdefPushCompleteCallback(this,this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //检测是否有一个beam启动了这个activity
        if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())){
            processIntent(getIntent());
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
    }

    private void processIntent(Intent intent) {
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        //Beam期间只发送了一条消息
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        //记录0包含了MIME类型
        mDisplay.setText(new String(msg.getRecords()[0].getPayload()));
    }


    @Override
    public NdefMessage createNdefMessage(NfcEvent nfcEvent) {
        String text = String.format("Sending A Message From Android Recipes at %s", DateFormat.getTimeInstance().format(new Date()));
        NdefMessage msg = new NdefMessage(NdefRecord.createMime("application/com.example.androidrecipes.beamtext",text.getBytes()));
        return msg;
    }

    @Override
    public void onNdefPushComplete(NfcEvent nfcEvent) {
        Log.i(TAG,"Message Sent!");
    }
}
