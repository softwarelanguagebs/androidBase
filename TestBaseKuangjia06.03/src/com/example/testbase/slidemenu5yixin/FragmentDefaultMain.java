package com.example.testbase.slidemenu5yixin;



import com.example.testbase.kuangjia.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
/**
 * ����ҳ��
 * @author Administrator
 *
 */
public class FragmentDefaultMain extends Fragment implements OnClickListener{
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.fragment_yixin, container, false);
    	Button button = (Button)view.findViewById(R.id.TestBtn);
    	button.setOnClickListener(this);
    	return view;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity().getApplicationContext(), "�ܺã�", Toast.LENGTH_SHORT).show();
	}
}
