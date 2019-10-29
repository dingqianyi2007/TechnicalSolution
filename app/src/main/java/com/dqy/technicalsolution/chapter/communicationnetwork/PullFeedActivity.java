package com.dqy.technicalsolution.chapter.communicationnetwork;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dqy.technicalsolution.chapter.communicationnetwork.custom.NewsItemFactory;
import com.dqy.technicalsolution.chapter.communicationnetwork.custom.RestTask;
import com.dqy.technicalsolution.chapter.communicationnetwork.custom.RestUtil;

import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;


public class PullFeedActivity extends AppCompatActivity implements RestTask.ResponseCallback {
    private static final String TAG = "FeedReader";
    private static final String FEED_URI = "http://news.google.com/?output=rss";

    private ListView mList;
    private ArrayAdapter<NewsItemFactory.NewsItem> mAdapter;
    private ProgressDialog mProgress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList = new ListView(this);
        mAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1);
        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsItemFactory.NewsItem item = mAdapter.getItem(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(FEED_URI));
                startActivity(intent);
            }
        });
        setContentView(mList);
    }

    @Override
    public void onRequestSuccess(String response) {
        if(mProgress != null){
            mProgress.dismiss();
            mProgress = null;
        }
        try {
            XmlPullParser pullParser = Xml.newPullParser();
            pullParser.setInput(new StringReader(response));
            pullParser.nextTag();

            mAdapter.clear();
            for(NewsItemFactory.NewsItem item : NewsItemFactory.parseFeed(pullParser)){
                mAdapter.add(item);
            }
            mAdapter.notifyDataSetChanged();
        }catch (Exception e){
            Log.w(TAG,e);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            RestTask task = RestUtil.obtainGetTask(FEED_URI);
            task.setResponseCallback(this);
            task.execute();
            mProgress = ProgressDialog.show(this,"Searching","Waiting For Results...",true);
        }catch (Exception e){
            Log.w(TAG,e);
        }
    }

    @Override
    public void onRequestError(Exception error) {
        if(mProgress != null){
            mProgress.dismiss();
            mProgress = null;
        }
        mAdapter.clear();
        mAdapter.notifyDataSetChanged();
        Toast.makeText(this,error.getMessage(),Toast.LENGTH_SHORT).show();
    }
}
