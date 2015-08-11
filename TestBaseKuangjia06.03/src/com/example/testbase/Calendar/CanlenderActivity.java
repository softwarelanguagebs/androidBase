package com.example.testbase.Calendar;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.testbase.file.T;
import com.example.testbase.kuangjia.R;
import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


public class CanlenderActivity extends FragmentActivity implements OnClickListener {

	public static final String TAG = CanlenderActivity.class.getSimpleName();

	Context context = CanlenderActivity.this;
	TextView tv_title;
	ImageView imv_back;

	Button btn;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calender);
		initView();
		setLinstener();
		initData();
		fillData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		
		 case R.id.btn:
			 /*
				 * Calendar c = null; c = Calendar.getInstance(); final int hour =
				 * c.get(Calendar.HOUR_OF_DAY); final int minute =
				 * c.get(Calendar.MINUTE); final int second =
				 * c.get(Calendar.SECOND); DatePickerDialog picker = new
				 * DatePickerDialog(cxt, new DatePickerDialog.OnDateSetListener() {
				 * 
				 * @Override public void onDateSet(DatePicker view, int year, int
				 * monthOfYear, int dayOfMonth) {
				 * 
				 * // tv_time.setText(year + "Äê" + monthOfYear + "ÔÂ" // + dayOfMonth
				 * + "ÈÕ"); // tv_time.setText(year + "-" + (monthOfYear+1) + // "-"
				 * // + dayOfMonth+" "+"12:00:00"); // tv_time.setText(year + "-" +
				 * (monthOfYear + 1) + "-" + dayOfMonth + " " + hour + ":" + minute
				 * + ":" + second);
				 * 
				 * // 2015-04-03 16:59:10
				 * 
				 * }
				 * 
				 * }, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				 * c.get(Calendar.DAY_OF_MONTH)); picker.setCancelable(true);
				 * picker.setCanceledOnTouchOutside(true);
				 * 
				 * picker.show();
				 */

				final SimpleDateFormat mFormatter = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");

				SlideDateTimeListener listener = new SlideDateTimeListener() {

					@Override
					public void onDateTimeSet(Date date) {

						//tv_time.setText(mFormatter.format(date));
						T.showLong(getApplicationContext(),mFormatter.format(date));
					}

					// Optional cancel listener
					@Override
					public void onDateTimeCancel() {

					}
				};
				new SlideDateTimePicker.Builder(getSupportFragmentManager())
						.setListener(listener).setInitialDate(new Date())
						// .setMinDate(minDate)
						// .setMaxDate(maxDate)
						// .setIs24HourTime(true)
						// .setTheme(SlideDateTimePicker.HOLO_DARK)
						// .setIndicatorColor(Color.parseColor("#990000"))
						.build().show();
		 break;
		default:
			break;
		}

	}

	
	protected void initData() {
		// TODO Auto-generated method stub
	//	tv_title.setText("×¢²á");

	}


	protected void initView() {
		
		btn = (Button) this.findViewById(R.id.btn);
		
	}

	
	protected void setLinstener() {
		
		btn.setOnClickListener(this);

	}


	protected void fillData() {
		// TODO Auto-generated method stub

	}

}
