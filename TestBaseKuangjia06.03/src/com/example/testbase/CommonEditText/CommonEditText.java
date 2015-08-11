/*
 * 文件名：CommonEditText.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：admin
 * 修改时间：2015年2月4日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
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
 * @Description: 自定义的EditText，自带清空按钮（关于密码模式，需要设置password属性为TRUE，单纯设置inputType为textPassword不起作用）
 * @author gaoshunsheng 794419070@qq.com
 * @date 2014-3-6 下午1:44:30
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

		// 这里处理自定义的属性
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.CommonEditText);
		// 设置默认文本
		CharSequence hint = a.getText(R.styleable.CommonEditText_hint);
		editText.setHint(hint);
		// 设置文字大小
		float textsize = a.getDimensionPixelSize(R.styleable.CommonEditText_textSize, -1);
		if(-1 != textsize)
		{
			editText.setTextSize(textsize);
			//这个很重要，根据TextView的setRawTextSize方法源代码获得
			editText.getPaint().setTextSize(textsize);
			editText.invalidate();
		}
		
		// 设置EditText文字颜色
		ColorStateList textColor = a
				.getColorStateList(R.styleable.CommonEditText_textColor);
		if (null != textColor) {
			editText.setTextColor(textColor);
		}
		//设置EditText的Hint的文字颜色
		ColorStateList textColorHint = a
				.getColorStateList(R.styleable.CommonEditText_textColorHint);
		if (null != textColorHint) {
			editText.setHintTextColor(textColorHint);
		}
		// 设置EditText是否单行显示
		boolean singleLine = a.getBoolean(
				R.styleable.CommonEditText_singleLine, true);
		editText.setSingleLine(singleLine);
		// 设置InputType
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

		//设置MaxLength属性
		Integer maxLength = a.getInteger(R.styleable.CommonEditText_maxLength, 0);
		if(0 != maxLength)
		{
			InputFilter[] filters = {new InputFilter.LengthFilter(maxLength.intValue())};
			editText.setFilters(filters);
		}
		
		// 设置清空按钮的宽高
		int clearH = a.getDimensionPixelSize(
				R.styleable.CommonEditText_clearButtonHeight, -1);
		int clearW = a.getDimensionPixelSize(
				R.styleable.CommonEditText_clearButtonWidth, -1);
		if (-1 != clearH && -1 != clearW) {
			imgClear.setLayoutParams(new LayoutParams(clearH, clearW));
		}

		// 设置按钮的Padding
		int padding = a.getDimensionPixelSize(
				R.styleable.CommonEditText_clearButtonPadding, -1);
		if (-1 != padding) {
			imgClear.setPadding(padding, padding, padding, padding);
		}
		
		//设置密码模式
		boolean password = a.getBoolean(R.styleable.CommonEditText_password, false);
		if(password)
		{
			editText.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
		}
		//设置清空按钮图标
		Drawable drawableClear = a.getDrawable(R.styleable.CommonEditText_drawableClearButton);
		if(null != drawableClear)
		{
			imgClear.setImageDrawable(drawableClear);
		}
		//设置清空按钮显示状态
		boolean enableClearFunction = a.getBoolean(R.styleable.CommonEditText_enableClearFunction, true);
		isClearFunctionWork = enableClearFunction;
		//设置EditText监听
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
	 * 切换清空按钮
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
	 * 聚焦处理事件
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
	 * 设置密码模式
	 */
	public void setPasswordMode()
	{
		editText.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
	}
	
	/**
	 * 编辑动作监听器，对键盘上的操作作监听
	 * 
	 * @param onEditorActionListener
	 */
	public void setOnEditorActionListener(
			OnEditorActionListener onEditorActionListener) {
		editText.setOnEditorActionListener(onEditorActionListener);
	}

	/**
	 * 文本输入框内容改变事件
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
	 * //设置清空按钮显示状态
	 * @param showClearButton
	 */
	public void showClearButton(boolean showClearButton)
	{
		imgClear.setVisibility(showClearButton ? View.VISIBLE : View.GONE);
	}
	
	/*
	 * 返回EditText对象
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
	 * 设置文本输入框提示文本
	 * 
	 * @param hint
	 */
	public void setHint(String hint) {
		editText.setHint(hint);
	}

	/**
	 * 设置清空按钮Drawable对象
	 * @param drawable
	 */
	public void setClearButtonDrawable(Drawable drawable) {
		imgClear.setImageDrawable(drawable);
	}
}