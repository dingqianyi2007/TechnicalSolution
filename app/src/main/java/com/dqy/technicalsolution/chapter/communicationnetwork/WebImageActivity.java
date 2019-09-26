package com.dqy.technicalsolution.chapter.communicationnetwork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dqy.technicalsolution.R;
import com.dqy.technicalsolution.chapter.communicationnetwork.custom.WebImageView;

public class WebImageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_image);
        WebImageView imageView = findViewById(R.id.webImage);
        imageView.setPlaceholderImage(R.drawable.ic_launcher);
        imageView.setImageUrl("http://image.ngchina.com.cn/2019/0903/20190903022841552.jpg");
    }
}
