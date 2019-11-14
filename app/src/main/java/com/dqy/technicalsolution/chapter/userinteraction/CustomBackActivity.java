package com.dqy.technicalsolution.chapter.userinteraction;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dqy.technicalsolution.R;

public class CustomBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_back);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container_fragment,MyFragment.newInstance("First Fragment"));
        ft.commit();
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container_fragment,MyFragment.newInstance("Second Fragment"));
        ft.addToBackStack("second");
        ft.commit();

        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container_fragment,MyFragment.newInstance("Third Fragment"));
        ft.addToBackStack("third");
        ft.commit();

        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container_fragment,MyFragment.newInstance("Fourth Fragment"));
        ft.addToBackStack("fourth");
        ft.commit();
    }

    public void onHomeClick(View v){
        getSupportFragmentManager().popBackStack("second", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }


    public static class MyFragment extends Fragment{
        private CharSequence mTitle;

        public static MyFragment newInstance(String title) {

            //Bundle args = new Bundle();

            MyFragment fragment = new MyFragment();
            //fragment.setArguments(args);
            fragment.setTitle(title);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            TextView text = new TextView(getActivity());
            text.setText(mTitle);
            text.setBackgroundColor(Color.WHITE);
            return text;
        }

        public void setTitle(CharSequence title){
            this.mTitle = title;
        }

    }
}
