package com.dqy.technicalsolution.chapter.communicationnetwork;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.dqy.technicalsolution.R;

public class InterceptWebEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(mClient);
        webView.loadUrl("https://www.baidu.com/");
        setContentView(webView);
        //setContentView(R.layout.activity_intercept_web_event);
    }

    private WebViewClient mClient = new WebViewClient(){
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Uri request = Uri.parse(url);
            if(TextUtils.equals(request.getAuthority(),"https://www.baidu.com/")){
                return false;
            }
            Toast.makeText(InterceptWebEventActivity.this,"Sorry,buddy",Toast.LENGTH_SHORT).show();
            return true;
        }
    };
}
