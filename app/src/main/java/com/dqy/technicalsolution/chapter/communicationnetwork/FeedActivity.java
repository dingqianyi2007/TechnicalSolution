package com.dqy.technicalsolution.chapter.communicationnetwork;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dqy.technicalsolution.R;
import com.dqy.technicalsolution.chapter.communicationnetwork.custom.RSSHandler;
import com.dqy.technicalsolution.chapter.communicationnetwork.custom.RestTask;
import com.dqy.technicalsolution.chapter.communicationnetwork.custom.RestUtil;

import org.xml.sax.InputSource;

import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class FeedActivity extends AppCompatActivity implements RestTask.ResponseCallback {
    private static final String TAG = "FeedReader";
    private static final String FEED_URI = "http://news.google.com/?output=rss";

    private ListView mList;
    private ArrayAdapter<RSSHandler.NewsItem> mAdapter;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_feed);

        mList = new ListView(this);
        mAdapter = new ArrayAdapter<RSSHandler.NewsItem>(this,android.R.layout.simple_list_item_1
                ,android.R.id.text1);
        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RSSHandler.NewsItem item = mAdapter.getItem(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(item.link));
                startActivity(intent);
            }
        });

        setContentView(mList);

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            RestTask task = RestUtil.obtainGetTask(FEED_URI);
            task.setResponseCallback(this);
            task.execute();
            mProgress = ProgressDialog.show(this,"Searching","waiting For Results ...",true);
        }catch (Exception e){
            Log.w(TAG,e);
        }
    }

    @Override
    public void onRequestSuccess(String response) {
        if(mProgress != null){
            mProgress.dismiss();
            mProgress = null;
        }

        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser p = factory.newSAXParser();
            RSSHandler parser = new RSSHandler();
            p.parse(new InputSource(new StringReader(response)),parser);

            mAdapter.clear();
            for(RSSHandler.NewsItem item:parser.getParsedItems()){
                mAdapter.add(item);
            }
            mAdapter.notifyDataSetChanged();
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
