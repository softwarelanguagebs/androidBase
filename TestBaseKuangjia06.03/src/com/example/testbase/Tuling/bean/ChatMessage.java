package com.example.testbase.Tuling.bean;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatMessage
{

	/**
	 * ��Ϣ����
	 */
	private Type type ;
	/**
	 * ��Ϣ����
	 */
	private String msg;
	/**
	 * ����
	 */
	private Date date;
	/**
	 * ���ڵ��ַ�����ʽ
	 */
	private String dateStr;
	/**
	 * ������
	 */
	private String name;

	public enum Type
	{
		INPUT, OUTPUT
	}

	public ChatMessage()
	{
	}

	public ChatMessage(Type type, String msg)
	{
		super();
		this.type = type;
		this.msg = msg;
		setDate(new Date());
	}

	public String getDateStr()
	{
		return dateStr;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.dateStr = df.format(date);

	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

}
