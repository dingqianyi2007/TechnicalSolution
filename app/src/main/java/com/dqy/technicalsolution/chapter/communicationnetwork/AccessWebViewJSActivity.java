package com.dqy.technicalsolution.chapter.communicationnetwork;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class AccessWebViewJSActivity extends AppCompatActivity {
    //@JavascriptInterface
    @SuppressLint("JavascriptInterface")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webview = new WebView(this);
        //Javascript is not enabled by default
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(mClient);
        //Attach the custom interface to the view
        webview.addJavascriptInterface(new MyJavaScriptInterface(), "BRIDGE");

        setContentView(webview);

        webview.loadUrl("file:///android_asset/form.html");
    }

    private static final String JS_SETELEMENT =
            "javascript:document.getElementById('%s').value='%s'";
    private static final String JS_GETELEMENT =
            "javascript:window.BRIDGE.storeElement('%s',document.getElementById('%s').value)";
    private static final String ELEMENTID = "emailAddress";

    private WebViewClient mClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //Before leaving the page, attempt to retrieve the email using JavaScript
            executeJavascript(view,
                    String.format(JS_GETELEMENT, ELEMENTID, ELEMENTID) );
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //When page loads, inject address into page using JavaScript
            SharedPreferences prefs = getPreferences(Activity.MODE_PRIVATE);
            executeJavascript(view,
                    String.format(JS_SETELEMENT, ELEMENTID, prefs.getString(ELEMENTID, "")) );
        }
    };

    private void executeJavascript(WebView view, String script) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            view.evaluateJavascript(script, null);
        } else {
            view.loadUrl(script);
        }
    }

    private class MyJavaScriptInterface {
        //Store an element in preferences

        public void storeElement(String id, String element) {
            SharedPreferences.Editor edit = getPreferences(Activity.MODE_PRIVATE).edit();
            edit.putString(id, element);
            edit.commit();
            //If element is valid, raise a Toast
            if(!TextUtils.isEmpty(element)) {
                Toast.makeText(AccessWebViewJSActivity.this, element, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
