package com.example.testbase.recyclerviewdemo2;



import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;


public class RecycleViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        initHorizaontal();
        initVertical();
    }

    private void initHorizaontal() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_horizontal);

        // ����һ�����Բ��ֹ�����
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        // ���ò��ֹ�����
        recyclerView.setLayoutManager(layoutManager);

        // �������ݼ�
        String[] dataset = new String[100];
        for (int i = 0; i < dataset.length; i++){
            dataset[i] = "item" + i;
        }
        // ����Adapter����ָ�����ݼ�
        MyAdapter adapter = new MyAdapter(dataset);
        // ����Adapter
        recyclerView.setAdapter(adapter);
    }

    public void initVertical(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_vertical);

        // ����һ�����Բ��ֹ�����
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // Ĭ����Vertical�����Բ�д
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // ���ò��ֹ�����
        recyclerView.setLayoutManager(layoutManager);

        // �������ݼ�
        String[] dataset = new String[100];
        for (int i = 0; i < dataset.length; i++){
            dataset[i] = "item" + i;
        }
        // ����Adapter����ָ�����ݼ�
        MyAdapter adapter = new MyAdapter(dataset);
        // ����Adapter
        recyclerView.setAdapter(adapter);
    }

 
}
