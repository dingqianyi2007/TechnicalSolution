package com.dqy.technicalsolution.chapter.communicationnetwork;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.dqy.technicalsolution.chapter.communicationnetwork.custom.RestTask;
import com.dqy.technicalsolution.chapter.communicationnetwork.custom.RestUtil;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class AuthActivity extends AppCompatActivity  implements RestTask.ResponseCallback{
    private static final String URI = "http://httpbin.org/basic-auth/android/recipes";
    private static final String USERNAME = "android";
    private static final String PASSWORD = "recipes";
    private TextView mResult;
    private ProgressDialog mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mResult = new TextView(this);
        setContentView(mResult);

        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME,PASSWORD.toCharArray());
            }
        });

        try{
            RestTask task = RestUtil.obtainGetTask(URI);
            task.setResponseCallback(this);
            task.execute();
        }catch (Exception e){

        }
    }

    @Override
    public void onRequestSuccess(String response) {
        if(mProgress != null){
            mProgress.dismiss();
            mProgress = null;
        }

        mResult.setText(response);
    }

    @Override
    public void onRequestError(Exception error) {
        if(mProgress != null){
            mProgress.dismiss();
            mProgress = null;
        }

        mResult.setText("An Error Occurred"+error.getMessage());
    }

}
