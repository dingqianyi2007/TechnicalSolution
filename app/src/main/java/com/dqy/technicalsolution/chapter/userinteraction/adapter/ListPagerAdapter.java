package com.dqy.technicalsolution.chapter.userinteraction.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListPagerAdapter extends FragmentPagerAdapter {
    private static final int ITEMS_PER_PAGE = 2;
    private List<String> mItems;
    public ListPagerAdapter(FragmentManager fm,List<String> items) {
        super(fm);
        mItems = items;
    }

    @Override
    public Fragment getItem(int i) {
        int start = i * ITEMS_PER_PAGE;

        return ArrayListFragment.newInstance(getPageList(i),start);
    }

    @Override
    public int getCount() {
        int pages = mItems.size() / ITEMS_PER_PAGE;
        int excess = mItems.size() % ITEMS_PER_PAGE;
        if(excess > 0){
            pages++;
        }
        return pages;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ArrayListFragment fragment = (ArrayListFragment) super.instantiateItem(container,position);
        fragment.updateListItems(getPageList(position));
        return fragment;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        ArrayListFragment fragment = (ArrayListFragment) object;
        int position = fragment.getBaseIndex() / ITEMS_PER_PAGE;
        if(position >= getCount()){
            return POSITION_NONE;
        }else {
            fragment.updateListItems(getPageList(position));
        }
        return position;
    }

    private List<String> getPageList(int position){
        int start = position * ITEMS_PER_PAGE;
        int end = Math.min(start + ITEMS_PER_PAGE,mItems.size());
        List<String> itemPage = mItems.subList(start,end);

        return itemPage;
    }

    public static class ArrayListFragment extends Fragment{
        private ArrayList<String> mItems;
        private ArrayAdapter<String> mAdapter;
        private int mBaseIndex;

        static ArrayListFragment newInstance(List<String> page,int baseIndex){
            ArrayListFragment fragment = new ArrayListFragment();
            fragment.updateListItems(page);
            fragment.setBaseIndex(baseIndex);
            return fragment;
        }

        public ArrayListFragment(){
            super();
            mItems = new ArrayList<String>();
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,mItems
            );
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            ListView listView = new ListView(getActivity());
            listView.setAdapter(mAdapter);
            return listView;
        }

        public void setBaseIndex(int index){
            mBaseIndex = index;
        }

        public int getBaseIndex(){
            return mBaseIndex;
        }

        public void updateListItems(List<String> items){
            mItems.clear();
            for(String pices : items){
                mItems.add(pices);
            }
            if(mAdapter != null){
                mAdapter.notifyDataSetChanged();
            }

        }

    }
}
