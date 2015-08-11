package com.example.testbase.dialog;

import com.example.testbase.kuangjia.R;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustormDialog extends Dialog implements DialogInterface {

	private String title;
	private String content;
	private DialogCallBack callback;
	private int index;

	/***
	 * @param context
	 * @param title
	 *            �Ի������
	 * @param content
	 *            �Ի�������
	 * @param theme
	 *            ��Ӧ��style ����ΪR.style.CustomDialog_1 ���Զ���style
	 * @param dialogcallback
	 *            ȷ��ȡ����ť�Ļص� �ֱ��� onCancle onOk
	 * @param index
	 *            ��ʾ����button 1 Ϊֻ��һ��ȷ����������Ϊ��ȷ��ȡ��������ť
	 * 
	 *            ����ʵ�� dialog = new CustormDialog(SettingsActivity.this,"��������",
	 *            "���ȷ��Ϊ������������ʷ��Ϣ:\nϵͳ֪ͨ������СƱ����ҵ��Ѷ��װ�䵥¼��",
	 *            R.style.CustomDialog_1, new DialogCallBack(){
	 * @Override public void OkDown() { dialog.dismiss(); //����� ȷ����ť��Ӧ }
	 * @Override public void CancleDown() { dialog.dismiss(); //�����ȡ����ť��Ӧ }
	 *           },2);
	 */
	public CustormDialog(Context context, String title, String content,
			int theme, DialogCallBack dialogcallback, int index) {
		super(context, theme);
		this.title = title;
		this.content = content;
		this.callback = dialogcallback;
		this.index = index;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dg_custormdialog);
		TextView titl = (TextView) findViewById(R.id.title);
		TextView cont = (TextView) findViewById(R.id.tv_content);

		titl.setText(title);
		cont.setText(content);

		Button cancel = (Button) findViewById(R.id.cancel);
		Button ok = (Button) findViewById(R.id.sure);
		if (index == 1) {
			cancel.setVisibility(View.GONE);
		} else {
			cancel.setOnClickListener(new android.view.View.OnClickListener() {
				public void onClick(View v) {
					CustormDialog.this.dismiss();
					callback.CancleDown();
				}
			});
		}
		ok.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CustormDialog.this.dismiss();
				callback.OkDown();
			}
		});
	}

}