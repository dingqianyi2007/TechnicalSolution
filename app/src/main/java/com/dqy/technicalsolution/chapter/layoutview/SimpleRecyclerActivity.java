package com.dqy.technicalsolution.chapter.layoutview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dqy.technicalsolution.R;
import com.dqy.technicalsolution.chapter.layoutview.custom.ConnectorDecoration;
import com.dqy.technicalsolution.chapter.layoutview.custom.GridStaggerLookup;
import com.dqy.technicalsolution.chapter.layoutview.custom.InsetDecoration;
import com.dqy.technicalsolution.chapter.layoutview.custom.SimpleItemAdapter;

public class SimpleRecyclerActivity extends AppCompatActivity implements SimpleItemAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private SimpleItemAdapter mSimpleItemAdapter;

    /**/
    private LinearLayoutManager mHorizontalManager;
    private LinearLayoutManager mVerticalManager;
    private GridLayoutManager mVerticalGridLayoutManager;
    private GridLayoutManager mHorizontalGridLayoutManager;

    /**/
    private ConnectorDecoration mConnectors;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerView = new RecyclerView(this);
        mHorizontalManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mVerticalManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mHorizontalGridLayoutManager = new GridLayoutManager(this,3,LinearLayoutManager.HORIZONTAL,false);
        mVerticalGridLayoutManager = new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false);
        /**/
        mConnectors = new ConnectorDecoration(this);

        //
        mVerticalGridLayoutManager.setSpanSizeLookup(new GridStaggerLookup());

        //
        mSimpleItemAdapter = new SimpleItemAdapter(this);
        mSimpleItemAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mSimpleItemAdapter);

        mRecyclerView.addItemDecoration(new InsetDecoration(this));

        selectLayoutManager(R.id.action_vertical);
        setContentView(mRecyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_options,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return selectLayoutManager(item.getItemId());
    }

    @Override
    public void onItemClick(SimpleItemAdapter.ItemHolder item, int position) {
        Toast.makeText(this,item.getSummary(),Toast.LENGTH_SHORT).show();
    }

    private boolean selectLayoutManager(int id){
       switch (id){
           case R.id.action_vertical:
               mRecyclerView.setLayoutManager(mVerticalManager);
               mRecyclerView.removeItemDecoration(mConnectors);
               return true;
           case R.id.action_horizontal:
                mRecyclerView.setLayoutManager(mHorizontalManager);
                mRecyclerView.removeItemDecoration(mConnectors);
                return true;
           case R.id.action_grid_vertical:
               mRecyclerView.setLayoutManager(mVerticalGridLayoutManager);
               mRecyclerView.addItemDecoration(mConnectors);
               return true;
           case R.id.action_grid_horizontal:
               mRecyclerView.setLayoutManager(mHorizontalGridLayoutManager);
               mRecyclerView.removeItemDecoration(mConnectors);
               return true;
           case R.id.action_add_item:
               mSimpleItemAdapter.insertItemAtIndex("Android Recipes",1);
               return true;
           case R.id.action_remove_item:
               mSimpleItemAdapter.removeItemAtIndex(1);
               return true;
           default:
               return false;
       }

    }
}
