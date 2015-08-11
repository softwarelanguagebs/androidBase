package com.example.testbase.dialog1;

import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

public class MyDlgActivity extends Activity {
    
   
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        MyDlg dlg = new MyDlg(this);
        
        dlg.setMessage("Loading¡­¡­");
        dlg.show();
      
    }
    
    
  
}