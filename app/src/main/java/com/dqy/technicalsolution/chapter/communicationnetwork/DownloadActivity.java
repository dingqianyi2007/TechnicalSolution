package com.dqy.technicalsolution.chapter.communicationnetwork;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.dqy.technicalsolution.R;

import java.io.FileInputStream;

public class DownloadActivity extends AppCompatActivity {
    private static final String DL_ID = "downloadId";
    private SharedPreferences prefs;
    private DownloadManager dm;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageView = new ImageView(this);
        //setContentView(R.layout.activity_download);
        setContentView(imageView);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!prefs.contains(DL_ID)){
            Uri resource = Uri.parse("http://www.bigfoto.com/dog-animal.jpg");
            DownloadManager.Request request = new DownloadManager.Request(resource);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
            request.setAllowedOverRoaming(false);
            //
            request.setTitle("Download Sample");
            long id = dm.enqueue(request);
            prefs.edit().putLong(DL_ID,id).commit();
        }else {
            queryDownloadStatus();
        }
        registerReceiver(receiver,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            queryDownloadStatus();
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    private void queryDownloadStatus() {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(prefs.getLong(DL_ID,0));
        Cursor c = dm.query(query);
        if(c.moveToFirst()){
            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            switch (status){
                case DownloadManager.STATUS_PAUSED:
                case DownloadManager.STATUS_PENDING:
                case DownloadManager.STATUS_RUNNING:
                    //
                    break;
                case DownloadManager.STATUS_SUCCESSFUL:
                    try {
                        ParcelFileDescriptor file = dm.openDownloadedFile(prefs.getLong(DL_ID,0));
                        FileInputStream fis = new ParcelFileDescriptor.AutoCloseInputStream(file);
                        imageView.setImageBitmap(BitmapFactory.decodeStream(fis));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case DownloadManager.STATUS_FAILED:
                    dm.remove(prefs.getLong(DL_ID,0));
                    prefs.edit().clear().commit();
                    break;
            }
        }
    }
}
