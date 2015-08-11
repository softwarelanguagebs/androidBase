package com.example.testbase.hongyang.DialogFragment;



import com.example.testbase.kuangjia.R;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public class EditNameDialogFragment extends DialogFragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		if (getResources().getBoolean(R.bool.large_layout))
		{
			getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		}
		View view = inflater.inflate(R.layout.fragment_edit_name, container,
				false);
		return view;
	}

}
