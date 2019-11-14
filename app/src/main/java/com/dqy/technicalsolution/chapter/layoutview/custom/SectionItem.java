package com.dqy.technicalsolution.chapter.layoutview.custom;

public class SectionItem<T> {
    private String mTitle;
    private T[] mItems;

    public SectionItem(String title,T[] items){
        this.mTitle = title;
        this.mItems = items;
    }
    public String getTitle() {
        return mTitle;
    }
    public T getItems(int position) {
        return mItems[position];
    }

    public int getCount(){

        return (mItems == null? 1 : 1+mItems.length);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof  SectionItem){
            return ((SectionItem)obj).getTitle().equals(mTitle);
        }
        return false;
    }
}
