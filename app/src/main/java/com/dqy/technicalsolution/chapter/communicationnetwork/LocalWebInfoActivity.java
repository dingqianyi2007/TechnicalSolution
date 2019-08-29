package com.dqy.technicalsolution.chapter.communicationnetwork;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.dqy.technicalsolution.R;

public class LocalWebInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_web_info);

        WebView upperView = findViewById(R.id.upperview);
        upperView.getSettings().setBuiltInZoomControls(true);
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB){
            upperView.getSettings().setBuiltInZoomControls(false);
            upperView.loadUrl("file:///android_asset/ic_launcher.png");
            WebView lowerView = findViewById(R.id.lowerview);
            String htmlString = "<h1>Header</h1><p>This is a html text <br/>"
                    + "<i>Formatted in italics</i><p>";
            lowerView.loadData(htmlString,"text/html","utf-8");
        }
    }
}
