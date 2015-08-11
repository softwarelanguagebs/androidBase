package com.example.testbase.recyclerviewdemo;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by baoyz on 2014/6/29.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    // ���ݼ�
    private String[] mDataset;

    public MyAdapter(String[] dataset) {
        super();
        mDataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // ����һ��View�������ֱ��ʹ��ϵͳ�ṩ�Ĳ��֣�����һ��TextView
        View view = View.inflate(viewGroup.getContext(), android.R.layout.simple_list_item_1, null);
        // ����һ��ViewHolder
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        // �����ݵ�ViewHolder��
        viewHolder.mTextView.setText(mDataset[i]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
        }
    }
}
