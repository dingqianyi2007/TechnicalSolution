package com.dqy.technicalsolution.chapter.communicationnetwork;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dqy.technicalsolution.R;

public class ShowWebInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webView = new WebView(this);
        //
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.startsWith("http:")||url.startsWith("https:")){
                    return false;
                }
                try{
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }catch (Exception e){
                    return true;
                }
               view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl("http://www.baidu.com");
        setContentView(webView);
    }
}
