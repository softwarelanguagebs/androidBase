package com.example.testbase.listviewfragmentdemo;


import com.example.testbase.kuangjia.R;
import com.example.testbase.listviewfragmentdemo.adapter.MyAdapter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 
 * @author qdwang
 *
 */
public class MainActivity extends FragmentActivity implements
OnItemClickListener {

private String[] strs = { "���÷���", "��������", "Ьѥ", "�ֻ�", "���õ���", "����", "���԰칫",
	"������ױ", "ͼ��" };
private ListView listView;
private MyAdapter adapter;
private MyFragment myFragment;
public static int mPosition;

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main_listv);

initView();
}

/**
* ��ʼ��view
*/
private void initView() {
// TODO Auto-generated method stub
listView = (ListView) findViewById(R.id.listview);

adapter = new MyAdapter(this, strs);
listView.setAdapter(adapter);

listView.setOnItemClickListener(this);

//����MyFragment����
myFragment = new MyFragment();
FragmentTransaction fragmentTransaction = getSupportFragmentManager()
		.beginTransaction();
fragmentTransaction.replace(R.id.fragment_container, myFragment);
//ͨ��bundle��ֵ��MyFragment
Bundle bundle = new Bundle();
bundle.putString(MyFragment.TAG, strs[mPosition]);
myFragment.setArguments(bundle);
fragmentTransaction.commit();
}

@Override
public void onItemClick(AdapterView<?> parent, View view, int position,
	long id) {
// TODO Auto-generated method stub
//�õ���ǰλ��
mPosition = position;
//��ʹˢ��adapter
adapter.notifyDataSetChanged();
for (int i = 0; i < strs.length; i++) {
	myFragment = new MyFragment();
	FragmentTransaction fragmentTransaction = getSupportFragmentManager()
			.beginTransaction();
	fragmentTransaction.replace(R.id.fragment_container, myFragment);
	Bundle bundle = new Bundle();
	bundle.putString(MyFragment.TAG, strs[position]);
	myFragment.setArguments(bundle);
	fragmentTransaction.commit();
}
}
}
