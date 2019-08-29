package com.dqy.technicalsolution.chapter.communicationnetwork;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.dqy.technicalsolution.R;

public class ShowWebInfoActivity extends AppCompatActivity {
    private WebView webView;

    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_web_info);
        webView = findViewById(R.id.WV_ID);

        progressBar = findViewById(R.id.progressBar);

        webView.getSettings().setJavaScriptEnabled(true);


        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setBlockNetworkImage(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }


        webView.getSettings().setSupportMultipleWindows(true);
        webView.setHorizontalScrollBarEnabled(false);//水平不显示
        webView.setVerticalScrollBarEnabled(false); //垂直不显示
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);//滚动条在WebView内侧显示
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//滚动条在WebView外侧显示

        webView.getSettings().setDomStorageEnabled(true);//有可能是DOM储存API没有打开


        // 设置可以支持缩放
        //webView.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
		/*webView.getSettings().setBuiltInZoomControls(true);
		//扩大比例的缩放
		webView.getSettings().setUseWideViewPort(true);  */
        //自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //webView.getSettings().setLoadWithOverviewMode(true);
        //webView.setInitialScale(100);





        webView.loadUrl("http://www.zinglizingli.xyz");

        webView.getSettings().setBlockNetworkImage(false);

        //设置在当前WebView继续加载网页
        webView.setWebViewClient(new MyWebViewClient());


        webView.setWebChromeClient(new MyWebChromeClient());
        /**
        WebView webView = new WebView(this);
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
        webView.loadUrl("http://www.qq.com");
        setContentView(webView);
         **/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    class MyWebViewClient extends WebViewClient{
        @Override  //WebView代表是当前的WebView
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            //表示在当前的WebView继续打开网页
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.d("WebView","开始访问网页");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d("WebView","访问网页结束");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack();
            return true;

        }
        // TODO Auto-generated method stub
        return super.onKeyDown(keyCode, event);
    }
    class MyWebChromeClient extends WebChromeClient {
        @Override //监听加载进度
        public void onProgressChanged(WebView view, int newProgress) {
            //显示进度条
            progressBar.setProgress(newProgress);
            if (newProgress == 100) {
                //加载完毕隐藏进度条
                progressBar.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }
        @Override//接受网页标题
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            //把当前的Title设置到Activity的title上显示
            setTitle(title);
        }
    }
}
