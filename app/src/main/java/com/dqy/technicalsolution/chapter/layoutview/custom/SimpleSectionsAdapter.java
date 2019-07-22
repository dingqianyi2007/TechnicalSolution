package com.dqy.technicalsolution.chapter.layoutview.custom;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public abstract class SimpleSectionsAdapter<T> extends BaseAdapter implements AdapterView.OnItemClickListener {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private LayoutInflater mLayoutInflater;
    private int mHeaderResource;
    private int mItemResource;

    private List<SectionItem<T>> mSections;

    private SparseArray<SectionItem<T>> mKeyedSections;

    public SimpleSectionsAdapter(ListView parent,int headerResId,int itemResId){
        mLayoutInflater = LayoutInflater.from(parent.getContext());
        mHeaderResource = headerResId;
        mItemResource = itemResId;

        //
        mSections = new ArrayList<SectionItem<T>>();
        mKeyedSections = new SparseArray<SectionItem<T>>();
        //
        parent.setOnItemClickListener(this);

    }

    public void addSection(String title, T[] items){
        SectionItem<T> sectionItem = new SectionItem<>(title,items);
        //
        int currentIndex = mSections.indexOf(sectionItem);
        if(currentIndex >= 0){
            mSections.remove(sectionItem);
            mSections.add(currentIndex,sectionItem);
        }else {
            mSections.add(sectionItem);
        }
        //
        reorderSections();
        notifyDataSetChanged();
    }
    //
    private void reorderSections(){
        mKeyedSections.clear();
        int startPosition = 0;
        for(SectionItem<T> item : mSections){
            mKeyedSections.put(startPosition,item);
            startPosition += item.getCount();
        }
    }

    @Override
    public int getCount() {
        int count = 0;
        for(SectionItem<T> item : mSections){
            count += item.getCount();
        }
        return count;
    }

    @Override
    public int getViewTypeCount() {
        //
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(isHeaderAtPosition(position)){
            return TYPE_HEADER;
        }else {
            return TYPE_ITEM;
        }
    }

    @Override
    public T getItem(int position) {
        return findSectionItemAtPosition(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return !isHeaderAtPosition(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)){
            case  TYPE_HEADER:
                return getHeaderView(position,convertView,parent);
            case  TYPE_ITEM:
                return getItemView(position,convertView,parent);
            default:
                return convertView;
        }
    }

    private View getHeaderView(int position,View convertView,ViewGroup parent){
        if(convertView == null){
            convertView = mLayoutInflater.inflate(mHeaderResource,parent,false);
        }
        SectionItem<T> item = mKeyedSections.get(position);
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(item.getTitle());

        return convertView;
    }

    private View getItemView(int position,View convertView,ViewGroup parent){
        if(convertView == null){
            convertView = mLayoutInflater.inflate(mItemResource,parent,false);
        }
        T item = findSectionItemAtPosition(position);
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(item.toString());
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        T item = findSectionItemAtPosition(position);
        if(item != null){
            onSectionItemClick(item);
        }
    }
    //
    public  abstract void  onSectionItemClick(T item);

    //
    private boolean isHeaderAtPosition(int position){
        for(int i = 0 ;i < mKeyedSections.size(); i++){
            if(position == mKeyedSections.keyAt(i)){
                return true;
            }
        }
        return false;
    }

    //
    private T findSectionItemAtPosition(int position){
        int firstIndex,lastIndex;
        for(int i = 0; i < mKeyedSections.size();i++){
            firstIndex = mKeyedSections.keyAt(i);
            lastIndex = firstIndex + mKeyedSections.valueAt(i).getCount();
            if(position >= firstIndex && position < lastIndex){
                int sectionPosition = position - firstIndex - 1;
                return mKeyedSections.valueAt(i).getItems(sectionPosition);
            }
        }
        return  null;
    }
}
