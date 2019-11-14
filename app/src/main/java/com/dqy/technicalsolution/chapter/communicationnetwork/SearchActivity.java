package com.dqy.technicalsolution.chapter.communicationnetwork;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dqy.technicalsolution.R;
import com.dqy.technicalsolution.chapter.communicationnetwork.custom.RestTask;
import com.dqy.technicalsolution.chapter.communicationnetwork.custom.RestUtil;

public class SearchActivity extends AppCompatActivity implements RestTask.ProgressCallback
    ,RestTask.ResponseCallback {
    private static final String SEARCH_URI = "https://www.googleapis.com/customsearch/v1?key=%s&cx=%s&q=%s";
    private static final String SEARCH_KEY = "AIzaSyBbW-W1SHCK4eW0kK74VGMLJj_b-byNzkI";
    private static final String SEARCH_CX = "008212991319514020231:1mkouq8yagw";
    private static final String SEARCH_QUERY = "Android";

    //private static final String POST_URI = "http://httpbin.org/post";

    private TextView mResult;
    private ProgressDialog mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scrollView = new ScrollView(this);
        mResult = new TextView(this);
        scrollView.addView(mResult,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setContentView(scrollView);
        try {
            String url = String.format(SEARCH_URI,SEARCH_KEY,SEARCH_CX,SEARCH_QUERY);
            RestTask getTask = RestUtil.obtainGetTask(url);
            getTask.setResponseCallback(this);
            getTask.setProgressCallback(this);
            getTask.execute();
            mProgress =ProgressDialog.show(this,"Searching","Waiting For Results...",true);

        }catch (Exception e){
           mResult.setText(e.getMessage());
        }
    }

    @Override
    public void onRequestSuccess(String response) {
        if(mProgress != null){
            mProgress.dismiss();
        }

        mResult.setText(response);
    }

    @Override
    public void onRequestError(Exception error) {
        if(mProgress != null){
            mProgress.dismiss();
        }

        mResult.setText("An Error Occurred"+error.getMessage());
    }

    @Override
    public void onProgressUpdate(int progress) {
        if(progress >= 0){
            if(mProgress != null) {
                mProgress.dismiss();
                mProgress = null;
            }
            mResult.setText(String.format("Download Progress : %d%%",progress));
        }
    }
}
