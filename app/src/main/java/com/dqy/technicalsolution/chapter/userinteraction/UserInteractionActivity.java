package com.dqy.technicalsolution.chapter.userinteraction;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dqy.technicalsolution.R;
import com.dqy.technicalsolution.chapter.userinteraction.custom.PanScrollView;

public class UserInteractionActivity extends AppCompatActivity {
    private Context mContext;
    private ListView lv_user_interaction;
    private ArrayAdapter mAdapter;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interaction);
        mContext= UserInteractionActivity.this;
        lv_user_interaction=findViewById(R.id.lv_user_interaction);
        mAdapter = ArrayAdapter.createFromResource(mContext,R.array.userInteraction_array,android.R.layout.simple_list_item_1);
        lv_user_interaction.setAdapter(mAdapter);
        lv_user_interaction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(UserInteractionActivity.this, SupportActionActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(UserInteractionActivity.this, LockActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(UserInteractionActivity.this, HandControlRotationActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(UserInteractionActivity.this, ActionActivity.class));
                        break;
                    case 5:
                        a=(int)(Math.random()*(2))+1;
                        if(a == 1 ) {
                            startActivity(new Intent(UserInteractionActivity.this, DialogActivity.class));
                        }
                        if(a == 2){
                            startActivity(new Intent(UserInteractionActivity.this, CustomDialogActivity.class));
                        }
                        break;
                    case 6:
                        startActivity(new Intent(UserInteractionActivity.this,OptionsActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(UserInteractionActivity.this,CustomBackActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(UserInteractionActivity.this,CustomHomeActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(UserInteractionActivity.this,TextWatcherActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(UserInteractionActivity.this, CustomKeyboardEnterActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(UserInteractionActivity.this, HideSoftInputActivity.class));
                        break;
                    case 12:
                        a=(int)(Math.random()*(3))+1;
                        if(a == 1 ) {
                            startActivity(new Intent(UserInteractionActivity.this, PanGestureScrollViewActivity.class));
                        }
                        if(a == 2){
                            startActivity(new Intent(UserInteractionActivity.this, PanScrollViewActivity.class));
                        }
                        if(a == 3){
                            startActivity(new Intent(UserInteractionActivity.this, RotateZoomImageViewActivity.class));
                        }
                        break;
                    case 13:
                        a=(int)(Math.random()*(2))+1;
                        if(a == 1 ) {
                            startActivity(new Intent(UserInteractionActivity.this, DelegateActivity.class));
                        }
                        if(a == 2){
                            startActivity(new Intent(UserInteractionActivity.this, RemoteScrollActivity.class));
                        }
                        break;
                    case 14:
                            startActivity(new Intent(UserInteractionActivity.this, DisallowActivity.class));
                        break;
                    case 15:
                        startActivity(new Intent(UserInteractionActivity.this, DragTouchActivity.class));
                        break;
                    case 16:
                        startActivity(new Intent(UserInteractionActivity.this, NativeActivity.class));
                        break;
                    case 17:
                        a=(int)(Math.random()*(2))+1;
                        if(a == 1 ) {
                            startActivity(new Intent(UserInteractionActivity.this, PagerActivity.class));
                        }
                        if(a == 2){
                            startActivity(new Intent(UserInteractionActivity.this, FragmentPagerActivity.class));
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
