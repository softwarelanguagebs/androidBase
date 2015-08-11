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
    private LayoutInflater mInflater;// 得到一个LayoutInfalter对象用来导入布局
    
   // List<Cargos> list;
    ArrayList<String> list = new ArrayList<String>();
   
   
    
    /* 构造函数 */
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
        return list.size();//返回数组的长度
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
        //观察convertView随ListView滚动情况
        Log.v("MyListViewBase", "getView " + position + " " + convertView);
        if (convertView == null)
        {
            
            convertView = mInflater.inflate(R.layout.item_search_origin, null);
            holder = new ViewHolder();
            
          
            //得到各个控件的对象
            holder.tv_address = (TextView)convertView.findViewById(R.id.tv_address);
        
           
          
           
            convertView.setTag(holder);//绑定ViewHolder对象
            
        }
        else{
      
           
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
          
        }
       
        holder.tv_address.setText(list.get(position)); 
                
    //    holder.tv_address.setText(list.get(position).getAddress());
        return convertView;
    }
    
    /*存放控件*/
    public final class ViewHolder
    {
        public TextView tv_address;
        
       
        
    }
    
}
