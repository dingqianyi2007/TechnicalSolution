package com.dqy.technicalsolution.chapter.communicationnetwork;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dqy.technicalsolution.R;
import com.dqy.technicalsolution.chapter.communicationnetwork.custom.RestTask;
import com.dqy.technicalsolution.chapter.communicationnetwork.custom.RestUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Search1Activity extends AppCompatActivity  implements RestTask.ProgressCallback,RestTask.ResponseCallback {
    private static final String POST_URI = "http://httpbin.org/post";
    private TextView mResult;
    private ProgressDialog mProgress;
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scrollView = new ScrollView(this);
        mResult = new TextView(this);
        scrollView.addView(mResult,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setContentView(scrollView);
        //setContentView(R.layout.activity_search1);

        try{
            Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
            File imageFile = new File(getExternalCacheDir(),"myImage.png");
            FileOutputStream out = new FileOutputStream(imageFile);
            image.compress(Bitmap.CompressFormat.PNG,0,out);
            out.flush();
            out.close();
            List<ContentValues> fileParameters = new ArrayList<>();
            ContentValues cv = new ContentValues();
            cv.put("title","Android Recipes");
            cv.put("desc","Image File Upload");
            fileParameters.add(cv);
            RestTask uploadTask = RestUtil.obtainMultipartPostTask(POST_URI,fileParameters,imageFile,"avatarImage");
            uploadTask.setResponseCallback(this);
            uploadTask.setProgressCallback(this);
            uploadTask.execute();
            mProgress = ProgressDialog.show(this,"Searching","Waiting For Results...",true);
        }catch(Exception e){
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
        mResult.setText("An Error Occurred: "+ error.getMessage());
    }

    @Override
    public void onProgressUpdate(int progress) {
        if(progress > 0){
            if(mProgress != null){
                mProgress.dismiss();
                mProgress = null;
            }

            mResult.setText(String.format("Download Progress: %d%%",progress));
        }
    }
}
