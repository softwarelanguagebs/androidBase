/*
 * �ļ�����CommonEditText.java
 * ��Ȩ��Copyright by www.huawei.com
 * ������
 * �޸��ˣ�admin
 * �޸�ʱ�䣺2015��2��4��
 * ���ٵ��ţ�
 * �޸ĵ��ţ�
 * �޸����ݣ�
 */

package com.example.testbase.CommonEditText;

import com.example.testbase.kuangjia.R;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView.OnEditorActionListener;



/**
 * 
 * @ClassName: CommonEditText
 * @Description: �Զ����EditText���Դ���հ�ť����������ģʽ����Ҫ����password����ΪTRUE����������inputTypeΪtextPassword�������ã�
 * @author gaoshunsheng 794419070@qq.com
 * @date 2014-3-6 ����1:44:30
 * 
 */
public class CommonEditText extends LinearLayout {

	private EditText editText;
	private ImageView imgClear;

	private TextWatcher textWatcher;

	private boolean isClearFunctionWork = true; 
	
	public CommonEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(getContext()).inflate(
				R.layout.layout_common_edit_text, this);
		editText = (EditText) findViewById(R.id.editText);
		
		imgClear = (ImageView) findViewById(R.id.imageView);
		imgClear.setVisibility(View.GONE);
		imgClear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				editText.setText("");
				imgClear.setVisibility(View.GONE);
			}
		});

		// ���ﴦ���Զ��������
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.CommonEditText);
		// ����Ĭ���ı�
		CharSequence hint = a.getText(R.styleable.CommonEditText_hint);
		editText.setHint(hint);
		// �������ִ�С
		float textsize = a.getDimensionPixelSize(R.styleable.CommonEditText_textSize, -1);
		if(-1 != textsize)
		{
			editText.setTextSize(textsize);
			//�������Ҫ������TextView��setRawTextSize����Դ������
			editText.getPaint().setTextSize(textsize);
			editText.invalidate();
		}
		
		// ����EditText������ɫ
		ColorStateList textColor = a
				.getColorStateList(R.styleable.CommonEditText_textColor);
		if (null != textColor) {
			editText.setTextColor(textColor);
		}
		//����EditText��Hint��������ɫ
		ColorStateList textColorHint = a
				.getColorStateList(R.styleable.CommonEditText_textColorHint);
		if (null != textColorHint) {
			editText.setHintTextColor(textColorHint);
		}
		// ����EditText�Ƿ�����ʾ
		boolean singleLine = a.getBoolean(
				R.styleable.CommonEditText_singleLine, true);
		editText.setSingleLine(singleLine);
		// ����InputType
		int inputType = a.getInt(R.styleable.CommonEditText_inputType,
				EditorInfo.TYPE_NULL);
		Log.i("InputType", inputType + "");
		if(EditorInfo.TYPE_NULL != inputType)
		{
			editText.setInputType(inputType);
		}
		else
		{
			editText.setInputType(EditorInfo.TYPE_CLASS_TEXT);
		}

		//����MaxLength����
		Integer maxLength = a.getInteger(R.styleable.CommonEditText_maxLength, 0);
		if(0 != maxLength)
		{
			InputFilter[] filters = {new InputFilter.LengthFilter(maxLength.intValue())};
			editText.setFilters(filters);
		}
		
		// ������հ�ť�Ŀ��
		int clearH = a.getDimensionPixelSize(
				R.styleable.CommonEditText_clearButtonHeight, -1);
		int clearW = a.getDimensionPixelSize(
				R.styleable.CommonEditText_clearButtonWidth, -1);
		if (-1 != clearH && -1 != clearW) {
			imgClear.setLayoutParams(new LayoutParams(clearH, clearW));
		}

		// ���ð�ť��Padding
		int padding = a.getDimensionPixelSize(
				R.styleable.CommonEditText_clearButtonPadding, -1);
		if (-1 != padding) {
			imgClear.setPadding(padding, padding, padding, padding);
		}
		
		//��������ģʽ
		boolean password = a.getBoolean(R.styleable.CommonEditText_password, false);
		if(password)
		{
			editText.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
		}
		//������հ�ťͼ��
		Drawable drawableClear = a.getDrawable(R.styleable.CommonEditText_drawableClearButton);
		if(null != drawableClear)
		{
			imgClear.setImageDrawable(drawableClear);
		}
		//������հ�ť��ʾ״̬
		boolean enableClearFunction = a.getBoolean(R.styleable.CommonEditText_enableClearFunction, true);
		isClearFunctionWork = enableClearFunction;
		//����EditText����
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if(isClearFunctionWork)
				{
					toggleClearButton(s);
				}

			}

		});
		editText.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(isClearFunctionWork)
				{
					toggleClearButtonOnFocus(hasFocus);
				}
			}

		});
		a.recycle();
	}

	/**
	 * �л���հ�ť
	 * 
	 * @param s
	 */
	private void toggleClearButton(CharSequence s) {
		if (s.length() > 0) {
			imgClear.setVisibility(View.VISIBLE);
		} else {
			imgClear.setVisibility(View.GONE);
		}
	}

	/**
	 * �۽������¼�
	 * 
	 * @param onFocusChangeListener
	 */
	public void setOnFocusChangeListener(
			final OnFocusChangeListener onFocusChangeListener) {
		editText.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(isClearFunctionWork)
				{
					toggleClearButtonOnFocus(hasFocus);
				}
				onFocusChangeListener.onFocusChange(v, hasFocus);
			}

		});
	}

	private void toggleClearButtonOnFocus(boolean hasFocus) {
		if (!hasFocus) {
			imgClear.setVisibility(View.GONE);
		} 
		else if(hasFocus && editText.getText().length() > 0)
		{
			imgClear.setVisibility(View.VISIBLE);
		}
	}

	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		if(gainFocus)
		{
			editText.requestFocus();
		}
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}
	
	/**
	 * ��������ģʽ
	 */
	public void setPasswordMode()
	{
		editText.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
	}
	
	/**
	 * �༭�������������Լ����ϵĲ���������
	 * 
	 * @param onEditorActionListener
	 */
	public void setOnEditorActionListener(
			OnEditorActionListener onEditorActionListener) {
		editText.setOnEditorActionListener(onEditorActionListener);
	}

	/**
	 * �ı���������ݸı��¼�
	 * 
	 * @param textWatcherImpl
	 */
	public void addTextChangedListener(TextWatcher textWatcherImpl) {
		this.textWatcher = textWatcherImpl;
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				textWatcher.onTextChanged(s, start, before, count);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				textWatcher.beforeTextChanged(s, start, count, after);
			}

			@Override
			public void afterTextChanged(Editable s) {
				if(isClearFunctionWork)
				{
					toggleClearButton(s);
				}
				textWatcher.afterTextChanged(s);
			}
		});
	}

	public CommonEditText(Context context) {
		super(context);
	}

	/**
	 * //������հ�ť��ʾ״̬
	 * @param showClearButton
	 */
	public void showClearButton(boolean showClearButton)
	{
		imgClear.setVisibility(showClearButton ? View.VISIBLE : View.GONE);
	}
	
	/*
	 * ����EditText����
	 */
	public EditText getEditText()
	{
		return editText;
	}
	
	public int getSelectionStart()
	{
		return editText.getSelectionStart();
	}
	
	public int getSelectionEnd()
	{
		return editText.getSelectionEnd();
	}
	
	public void setSelection(int selection)
	{
		editText.setSelection(selection);
	}
	
	public void setText(CharSequence charSequence)
	{
		editText.setText(charSequence);
	}
	
	public CharSequence getText()
	{
		return editText.getText();
	}
	
	public void setInputType(int inputType){
		editText.setInputType(inputType);
	}

	/**
	 * �����ı��������ʾ�ı�
	 * 
	 * @param hint
	 */
	public void setHint(String hint) {
		editText.setHint(hint);
	}

	/**
	 * ������հ�ťDrawable����
	 * @param drawable
	 */
	public void setClearButtonDrawable(Drawable drawable) {
		imgClear.setImageDrawable(drawable);
	}
}