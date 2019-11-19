package com.dqy.technicalsolution.chapter.communicationnetwork;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dqy.technicalsolution.R;

public class BeamActivity extends AppCompatActivity implements NfcAdapter.CreateBeamUrisCallback, NfcAdapter.OnNdefPushCompleteCallback {
    private static final String TAG = "NfcBeam";
    private static final int PICK_IMAGE = 100;

    private NfcAdapter mNfcAdapter;
    private Uri mSelectedImage;

    private TextView mUriName;
    private ImageView mPreviewImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beam);

        mUriName = findViewById(R.id.text_uri);
        mPreviewImage = findViewById(R.id.image_preview);

        //check whether nfc adapter available
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(mNfcAdapter == null){
            mUriName.setText("NFC is not available on this devices");
        }else {
            //register callback to set NDEF message
            mNfcAdapter.setBeamPushUrisCallback(this,this);
            mNfcAdapter.setOnNdefPushCompleteCallback(this,this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PICK_IMAGE && requestCode == RESULT_OK && data != null){
            mUriName.setText(data.getData().toString());
            mSelectedImage = data.getData();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //check Activity started due to Android Beam
        if(Intent.ACTION_VIEW.equals(getIntent().getAction())){
            processIntent(getIntent());
        }
    }

    private void processIntent(Intent intent) {
        Uri data = intent.getData();
        if(data != null){
            mPreviewImage.setImageURI(data);
        }else {
            mUriName.setText("Received Invalid Image Uri");
        }
    }

    public void onSelectClick(View v){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,PICK_IMAGE);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
    }


    @Override
    public Uri[] createBeamUris(NfcEvent nfcEvent) {
        if(mSelectedImage == null){
            return  null;
        }
        return new Uri[]{mSelectedImage};
    }

    @Override
    public void onNdefPushComplete(NfcEvent nfcEvent) {
        Log.i(TAG,"Push Complete!");
    }
}
