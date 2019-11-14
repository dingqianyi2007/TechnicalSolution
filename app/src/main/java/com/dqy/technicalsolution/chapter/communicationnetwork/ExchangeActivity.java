package com.dqy.technicalsolution.chapter.communicationnetwork;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dqy.technicalsolution.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class ExchangeActivity extends AppCompatActivity {

    //
    private static final UUID MY_UUID = UUID.fromString("d0da4f8c-068b-11ea-ae33-0221860e9b7e");
    //
    private static final String SEARCH_NAME="bluetooth.recipe";

    private static final int REQUEST_ENABLE = 0x11;
    private static final int REQUEST_DISCOVERABLE = 0X12;

    BluetoothAdapter mBtAdapter;
    BluetoothSocket mBtSocket;
    Button scanButton,listenButton;
    EditText emailField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_exchange);

        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBtAdapter == null){
            Toast.makeText(this,"Bluetooth is not supported.",Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        if(!mBtAdapter.isEnabled()){
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent,REQUEST_ENABLE);
        }

        emailField = findViewById(R.id.emailField);
        listenButton = findViewById(R.id.listenButton);
        listenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mBtAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE){
                    Intent discoverableIntent =  new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,300);
                    startActivityForResult(discoverableIntent,REQUEST_DISCOVERABLE);
                    return;
                }
                startListening();
            }
        });
        scanButton = findViewById(R.id.scanButton);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBtAdapter.startDiscovery();
                setProgressBarIndeterminateVisibility(true);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver,filter);
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if(mBtSocket != null){
                mBtSocket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_ENABLE:
                if(requestCode != Activity.RESULT_OK){
                    Toast.makeText(this,"Bluetooth Not Enable",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            case REQUEST_DISCOVERABLE:
                if(requestCode ==Activity.RESULT_CANCELED){
                    Toast.makeText(this,"Must be discoverable.",Toast.LENGTH_SHORT).show();
                }else {
                    startListening();
                }
                break;
            default:
                break;
        }

    }

    private void startListening() {
        AcceptTask task = new AcceptTask();
        task.execute(MY_UUID);
        setProgressBarIndeterminateVisibility(true);
    }


    private class AcceptTask extends AsyncTask<UUID,Void,BluetoothSocket> {
        @Override
        protected BluetoothSocket doInBackground(UUID... uuids) {
            String name = mBtAdapter.getName();
            try{
                mBtAdapter.setName(SEARCH_NAME);
                BluetoothServerSocket socket = mBtAdapter.listenUsingRfcommWithServiceRecord("BluetoothRecipe",uuids[0]);
                BluetoothSocket connected = socket.accept();
                mBtAdapter.setName(name);
                return connected;
            }catch (IOException e){
                e.printStackTrace();
                mBtAdapter.setName(name);
                return null;
            }
        }

        @Override
        protected void onPostExecute(BluetoothSocket bluetoothSocket) {
            if(bluetoothSocket ==null){
                return;
            }
            mBtSocket = bluetoothSocket;
            ConnectedTask task = new ConnectedTask();
            task.execute(mBtSocket);
        }
    }
    private class ConnectedTask extends AsyncTask<BluetoothSocket,Void,String>{
        @Override
        protected String doInBackground(BluetoothSocket... params) {
            InputStream in = null;
            OutputStream out = null;
            try {
                //
                out = params[0].getOutputStream();
                String email = emailField.getText().toString();
                out.write(email.getBytes());
                //
                in = params[0].getInputStream();
                byte[] buffer = new byte[1024];
                in.read(buffer);
                String result = new String(buffer);
                //
                mBtSocket.close();
                return result.trim();
            }catch (Exception e){
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(ExchangeActivity.this,result,Toast.LENGTH_SHORT).show();
            setProgressBarIndeterminateVisibility(false);
        }
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            //
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if(TextUtils.equals(device.getName(),SEARCH_NAME)){
                    mBtAdapter.cancelDiscovery();
                    try {
                        mBtSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
                        mBtSocket.connect();
                        ConnectedTask task = new ConnectedTask();
                        task.execute(mBtSocket);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }else if(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE.equals(action)){
                setProgressBarIndeterminateVisibility(false);
            }
        }
    };
}
