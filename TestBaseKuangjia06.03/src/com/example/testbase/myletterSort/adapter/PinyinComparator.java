package com.example.testbase.myletterSort.adapter;

import java.util.Comparator;

import android.annotation.SuppressLint;

public class PinyinComparator implements Comparator<UserBean> {

    @SuppressLint("DefaultLocale")
    @Override
    public int compare(UserBean o1, UserBean o2) {
        // TODO Auto-generated method stub
    	
//        String py1 = o1.getName();
//        String py2 = o2.getName();
    	
    	
        String py1 = CharacterParser.getInstance().getSelling(o1.getName());
        String py2 = CharacterParser.getInstance().getSelling(o2.getName());
        // 判断是否为空""
        if (isEmpty(py1) && isEmpty(py2))
            return 0;
        if (isEmpty(py1))
            return -1;
        if (isEmpty(py2))
            return 1;
        String str1 = "";
        String str2 = "";
        try {
//            str1 = ((o1.getName()).toUpperCase()).substring(0, 1);
//            str2 = ((o2.getName()).toUpperCase()).substring(0, 1);
            str1 = ((CharacterParser.getInstance().getSelling(o1.getName())).toUpperCase()).substring(0, 1);
            str2 = ((CharacterParser.getInstance().getSelling(o2.getName())).toUpperCase()).substring(0, 1);
        } catch (Exception e) {
            System.out.println("某个str为\" \" 空");
        }
        return str1.compareTo(str2);
    }

    private boolean isEmpty(String str) {
        return "".equals(str.trim());
    }
}