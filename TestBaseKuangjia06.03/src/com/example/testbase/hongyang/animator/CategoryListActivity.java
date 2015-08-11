package com.example.testbase.hongyang.animator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.ActivityOptions;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class CategoryListActivity extends ListActivity
{
	private List<String> mData = new ArrayList<String>(Arrays.asList(
			"ʹ��xml�������Զ���", "ObjectAnimator�ķ�ʽ��������", "AnimatorSet��������",
			"View��anim����", "Layout Anim", "ValueAnimator��������"));
	private ListAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mData);
		getListView().setAdapter(mAdapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{

		Intent intent = null;
		switch (position)
		{
		case 0:
			intent = new Intent(this, Xml4AnimActivity.class);
			break;
		case 1:
			intent = new Intent(this, ObjectAnimActivity.class);
			break;
		case 2:
			intent = new Intent(this, AnimatorSetActivity.class);
			break;
		case 3:
			intent = new Intent(this, ViewAnimateActivity.class);
			break;
		case 4:
			intent = new Intent(this, LayoutAnimaActivity.class);
			break;
		case 5:
			intent = new Intent(this, ValueAnimatorActivity.class);
			break;
		}
		startActivity(intent);
	}
}
