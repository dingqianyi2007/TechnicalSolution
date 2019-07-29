package com.dqy.technicalsolution.chapter.layoutview.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dqy.technicalsolution.R;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleItemAdapter extends RecyclerView.Adapter<SimpleItemAdapter.ItemHolder> {
    /*
    *
     */
    public interface OnItemClickListener{
        public void onItemClick(ItemHolder item,int position);
    }

    private static final  String[] ITEMS = {
            "Apples","Oranges","Banas","Mangos","Carrots","Peas","Broccoli","Pork","Chicken","Beef","Lamb"
    };

    private List<String> mItems;
    private OnItemClickListener mOnItemClickListener;
    private LayoutInflater mLayoutInflater;

    public SimpleItemAdapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);
        mItems = new ArrayList<String>();
        mItems.addAll(Arrays.asList(ITEMS));
        mItems.addAll(Arrays.asList(ITEMS));
    }
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = mLayoutInflater.inflate(R.layout.collection_item,parent,false);
        return new ItemHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(ItemHolder itemHolder, int position) {
        itemHolder.setTitle("item #"+(position+1));
        itemHolder.setSummary(mItems.get(position));
    }



    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public OnItemClickListener getOnItemClickListener(){
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }
    //
    public void insertItemAtIndex(String item,int position){
        mItems.add(position,item);
        notifyItemChanged(position);

    }

    public void removeItemAtIndex(int position){
        if(position >= mItems.size()) return;

        mItems.remove(position);
        notifyItemRemoved(position);
    }

    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private SimpleItemAdapter mParent;
        private TextView mTitleView,mSummaryView;

        public ItemHolder(View itemView,SimpleItemAdapter parent) {
            super(itemView);
            itemView.setOnClickListener(this);
            mParent = parent;

            mTitleView = itemView.findViewById(R.id.text_title);
            mSummaryView = itemView.findViewById(R.id.text_summary);
        }

        public void setTitle(CharSequence title){
            mTitleView.setText(title);
        }

        public void setSummary(CharSequence summary){
            mSummaryView.setText(summary);
        }

        public CharSequence getSummary(){
            return mSummaryView.getText();
        }
        @Override
        public void onClick(View v) {
            final OnItemClickListener listener = mParent.getOnItemClickListener();
            if(listener != null){
                listener.onItemClick(this,getAdapterPosition());
            }
        }
    }
}
