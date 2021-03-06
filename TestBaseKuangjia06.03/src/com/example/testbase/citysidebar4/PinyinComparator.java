package com.example.testbase.citysidebar4;

import java.util.Comparator;

import com.example.testbase.citysidebar4.db.City;

import android.annotation.SuppressLint;

public class PinyinComparator implements Comparator<City> {

    @SuppressLint("DefaultLocale")
    @Override
    public int compare(City o1, City o2) {
        // TODO Auto-generated method stub
    	
//        String py1 = o1.getName();
//        String py2 = o2.getName();
    	
    	
        String py1 = CharacterParser.getInstance().getSelling(o1.getCity());
        String py2 = CharacterParser.getInstance().getSelling(o2.getCity());
        // �ж��Ƿ�Ϊ��""
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
            str1 = ((CharacterParser.getInstance().getSelling(o1.getCity())).toUpperCase()).substring(0, 1);
            str2 = ((CharacterParser.getInstance().getSelling(o2.getCity())).toUpperCase()).substring(0, 1);
        } catch (Exception e) {
            System.out.println("ĳ��strΪ\" \" ��");
        }
        return str1.compareTo(str2);
    }

    private boolean isEmpty(String str) {
        return "".equals(str.trim());
    }
}