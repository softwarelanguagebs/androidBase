package com.example.testbase.file;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testbase.file.utils.UtilsDevice;
import com.example.testbase.file.utils.utilfile;
import com.example.testbase.file.utils.UtilsNumeric;
import com.example.testbase.kuangjia.R;


public class FileActivity extends Activity implements OnClickListener{

	public static final String TAG = FileActivity.class.getSimpleName();

	Context context = FileActivity.this;
	TextView tv,tv2,tv3;
	ImageView imv_back;
	
	Button btn,btn2;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myfile);
		initView();
		setLinstener();
		initData();
		fillData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
	
		 case R.id.btn:
		 
		 createFile();
		 break;
		 
		 case R.id.btn2:
			 
				Intent intent12 = new Intent(FileActivity.this,
						FileDowloadActivity.class);
				startActivity(intent12);
			 break;
		default:
			break;
		}

	}


	private void createFile() {
		
//        utilfile.createDIR("AAAAA");//OK
//        
//        utilfile.createDIR("BBBB"+File.separator+"CCCC"+File.separator);//    BBB/CCC/
  
        String FirstFolder="AA";//一级目录  
        
        String SecondFolder="BB";//二级目录  
        
        String TFolder="CC";//二级目录  
        
        String A_PATH=UtilsDevice.getSDCardPath()+FirstFolder+File.separator; 
        
        String Second_PATH=A_PATH+SecondFolder+File.separator;  
        
        String T_PATH=Second_PATH+TFolder+File.separator;  
        
        //Android创建目录文件夹，多级目录逐一创建
        if(UtilsDevice.isSDCardEnable()==false){
        	 Toast.makeText(this, "请插入外部SD存储卡", Toast.LENGTH_SHORT).show(); 
        }else{
       	File dir1 = utilfile.createDIR(A_PATH);
       	File dir2 = utilfile.createDIR(Second_PATH);
        	File dir3 = utilfile.createDIR(T_PATH);
        	
        	
     // 	utilfile.delFile(T_PATH);// bu能删除CC
        	
      //  		utilfile.delFile1(T_PATH);// 能删除CC
//        	utilfile.setDeteleDir(T_PATH); // 只能删除CC
//        	utilfile.deleteDir();
        	
       // 	utilfile.delete(dir1);//OK
       
        	
        	String Name1 = dir1.getName();  //获得文件或文件夹的名称：  
        	String parentPath1 = dir1.getParent();  //获得文件或文件夹的父目录  
        	String path1 = dir1.getAbsolutePath();//绝对路经  
        	String path11 = dir1.getPath();//相对路经   
        	
        	String Name2 = dir2.getName();  //获得文件或文件夹的名称：  
        	String parentPath2 = dir2.getParent();  //获得文件或文件夹的父目录  
        	String path2 = dir2.getAbsolutePath();//绝对路经  
        	String path22 = dir2.getPath();//相对路经   
        	
        	
        	
        	tv3.setText("dir1:"+"\n"
    				+"获得文件或文件夹的名称="+Name1+"\n"
    				+"文件或文件夹的父目录  ="+parentPath1+"\n"
    				+"绝对路经="+path1+"\n"
    				+"相对路经 ="+path11+"\n"
    				+"dir2:"+"\n"
					+"获得文件或文件夹的名称="+Name2+"\n"
					+"文件或文件夹的父目录  ="+parentPath2+"\n"
					+"绝对路经="+path2+"\n"
					+"相对路经 ="+path22+"\n"
        			
        			
        			);
        	
        	utilfile.delete(dir1);//OK
        	
        }
        
        
	}

	protected void initData() {
	
		String version = UtilsNumeric.getVersion(context);
		int ScreenWidth = UtilsNumeric.getScreenWidth(context);
		int ScreenHeight = UtilsNumeric.getScreenHeight(context);
		int getStatusHeight = UtilsNumeric.getStatusHeight(context);
		
		int ScreenHeight1 = UtilsNumeric.getSreenHeight(FileActivity.this);
		int ScreenWidth1 = UtilsNumeric.getScreenWidth(FileActivity.this);
		
		int getScreenDensity = UtilsNumeric.getScreenDensity(FileActivity.this);
		
		
		tv.setText("Version="+version+"\n"
				+"获得屏幕高度="+ScreenWidth+"\n"
				+"获得屏幕宽度="+ScreenHeight+"\n"
				+"获得状态栏的高度="+getStatusHeight+"\n"
				+"获得屏幕高度dm1="+ScreenWidth1+"\n"
				+"获得屏幕宽度dm1="+ScreenHeight1+"\n"
				+"得到屏幕的密度="+getScreenDensity+"\n"
				
				);
		
		
		
		long getAvailMemory = UtilsDevice.getAvailMemory(context);
		
		long getTotalMemory = UtilsDevice.getTotalMemory(context);
		
		long getSDAvailaSize = UtilsDevice.getSDAvailaSize();
		
		String s =UtilsDevice.isSDCardEnable()==true?"SDCard可用":"SDCard不可用";
		
		
		String getSDCardPath = UtilsDevice.getSDCardPath();
		
		long getSDCardAllSize = UtilsDevice.getSDCardAllSize();
		
		String getRootDirectoryPath = UtilsDevice.getRootDirectoryPath();
		
		
		tv2.setText("当前可用内存大小="+getAvailMemory+"\n"
				+"获取系统总共内存大小="+getTotalMemory+"\n"
				+s+"\n"
				+"SD剩余空间="+getSDAvailaSize+"\n"
				+"SD路径="+getSDCardPath+"\n"
				+"获取SD卡的剩余容量 单位byte="+getSDCardAllSize+"\n"
				+UtilsDevice.byte2Oral(getSDCardAllSize)+"\n"
				+"获取系统存储路径="+getRootDirectoryPath+"\n"
				
				);

	}

	
	protected void initView() {
		tv = (TextView) this.findViewById(R.id.tv);
		tv2 = (TextView) this.findViewById(R.id.tv2);
		tv3 = (TextView) this.findViewById(R.id.tv3);
		btn = (Button) this.findViewById(R.id.btn);
		btn2 = (Button) this.findViewById(R.id.btn2);
	}

	
	protected void setLinstener() {
		
		btn.setOnClickListener(this);
		btn2.setOnClickListener(this);
	

	}


	protected void fillData() {
		// TODO Auto-generated method stub

	}

	
	
	
	
}
