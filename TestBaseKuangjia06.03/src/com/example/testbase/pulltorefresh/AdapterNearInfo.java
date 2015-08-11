package com.example.testbase.pulltorefresh;

import java.util.ArrayList;
import java.util.List;















import com.example.testbase.kuangjia.R;


import android.R.integer;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterNearInfo extends BaseAdapter
{
    private LayoutInflater mInflater;// �õ�һ��LayoutInfalter�����������벼��
    
   // List<Cargos> list;
    ArrayList<String> list = new ArrayList<String>();
   
   
    
    /* ���캯�� */
    public AdapterNearInfo(Context context,  ArrayList<String> list)
    {
        
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
        
    }
    
   
    
    public void onDateChange(ArrayList<String> list)
    {
        this.list = list;
        this.notifyDataSetChanged();
    }
    
    @Override
    public int getCount()
    {
        // TODO Auto-generated method stub
        return list.size();//��������ĳ���
    }
    
   
    
    @Override
    public Object getItem(int position)
    {
        
        return list.get(position);
        
    }
    
    @Override
    public long getItemId(int position)
    {
        // TODO Auto-generated method stub
        return position;
    }
    
    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        //�۲�convertView��ListView�������
        Log.v("MyListViewBase", "getView " + position + " " + convertView);
        if (convertView == null)
        {
            
            convertView = mInflater.inflate(R.layout.item_search_origin, null);
            holder = new ViewHolder();
            
          
            //�õ������ؼ��Ķ���
            holder.tv_address = (TextView)convertView.findViewById(R.id.tv_address);
        
           
          
           
            convertView.setTag(holder);//��ViewHolder����
            
        }
        else{
      
           
            holder = (ViewHolder)convertView.getTag();//ȡ��ViewHolder����
          
        }
       
        holder.tv_address.setText(list.get(position)); 
                
    //    holder.tv_address.setText(list.get(position).getAddress());
        return convertView;
    }
    
    /*��ſؼ�*/
    public final class ViewHolder
    {
        public TextView tv_address;
        
       
        
    }
    
}
