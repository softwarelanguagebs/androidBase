package com.example.testbase.myletterSort2;


/** ����BmobChatUser����
  * @ClassName: TextUser
  * @Description: TODO
  * @author smile
  * @date 2014-5-29 ����6:15:45
  */
public class User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * //��ʾ����ƴ��������ĸ
	 */
	private String sortLetters;
	
	/**
	 * //�Ա�-true-��
	 */
	private boolean sex;
	
	String username;
	
	String avatar;
	
	public boolean getSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
