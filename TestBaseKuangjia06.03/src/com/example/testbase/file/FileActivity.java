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
  
        String FirstFolder="AA";//һ��Ŀ¼  
        
        String SecondFolder="BB";//����Ŀ¼  
        
        String TFolder="CC";//����Ŀ¼  
        
        String A_PATH=UtilsDevice.getSDCardPath()+FirstFolder+File.separator; 
        
        String Second_PATH=A_PATH+SecondFolder+File.separator;  
        
        String T_PATH=Second_PATH+TFolder+File.separator;  
        
        //Android����Ŀ¼�ļ��У��༶Ŀ¼��һ����
        if(UtilsDevice.isSDCardEnable()==false){
        	 Toast.makeText(this, "������ⲿSD�洢��", Toast.LENGTH_SHORT).show(); 
        }else{
       	File dir1 = utilfile.createDIR(A_PATH);
       	File dir2 = utilfile.createDIR(Second_PATH);
        	File dir3 = utilfile.createDIR(T_PATH);
        	
        	
     // 	utilfile.delFile(T_PATH);// bu��ɾ��CC
        	
      //  		utilfile.delFile1(T_PATH);// ��ɾ��CC
//        	utilfile.setDeteleDir(T_PATH); // ֻ��ɾ��CC
//        	utilfile.deleteDir();
        	
       // 	utilfile.delete(dir1);//OK
       
        	
        	String Name1 = dir1.getName();  //����ļ����ļ��е����ƣ�  
        	String parentPath1 = dir1.getParent();  //����ļ����ļ��еĸ�Ŀ¼  
        	String path1 = dir1.getAbsolutePath();//����·��  
        	String path11 = dir1.getPath();//���·��   
        	
        	String Name2 = dir2.getName();  //����ļ����ļ��е����ƣ�  
        	String parentPath2 = dir2.getParent();  //����ļ����ļ��еĸ�Ŀ¼  
        	String path2 = dir2.getAbsolutePath();//����·��  
        	String path22 = dir2.getPath();//���·��   
        	
        	
        	
        	tv3.setText("dir1:"+"\n"
    				+"����ļ����ļ��е�����="+Name1+"\n"
    				+"�ļ����ļ��еĸ�Ŀ¼  ="+parentPath1+"\n"
    				+"����·��="+path1+"\n"
    				+"���·�� ="+path11+"\n"
    				+"dir2:"+"\n"
					+"����ļ����ļ��е�����="+Name2+"\n"
					+"�ļ����ļ��еĸ�Ŀ¼  ="+parentPath2+"\n"
					+"����·��="+path2+"\n"
					+"���·�� ="+path22+"\n"
        			
        			
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
				+"�����Ļ�߶�="+ScreenWidth+"\n"
				+"�����Ļ���="+ScreenHeight+"\n"
				+"���״̬���ĸ߶�="+getStatusHeight+"\n"
				+"�����Ļ�߶�dm1="+ScreenWidth1+"\n"
				+"�����Ļ���dm1="+ScreenHeight1+"\n"
				+"�õ���Ļ���ܶ�="+getScreenDensity+"\n"
				
				);
		
		
		
		long getAvailMemory = UtilsDevice.getAvailMemory(context);
		
		long getTotalMemory = UtilsDevice.getTotalMemory(context);
		
		long getSDAvailaSize = UtilsDevice.getSDAvailaSize();
		
		String s =UtilsDevice.isSDCardEnable()==true?"SDCard����":"SDCard������";
		
		
		String getSDCardPath = UtilsDevice.getSDCardPath();
		
		long getSDCardAllSize = UtilsDevice.getSDCardAllSize();
		
		String getRootDirectoryPath = UtilsDevice.getRootDirectoryPath();
		
		
		tv2.setText("��ǰ�����ڴ��С="+getAvailMemory+"\n"
				+"��ȡϵͳ�ܹ��ڴ��С="+getTotalMemory+"\n"
				+s+"\n"
				+"SDʣ��ռ�="+getSDAvailaSize+"\n"
				+"SD·��="+getSDCardPath+"\n"
				+"��ȡSD����ʣ������ ��λbyte="+getSDCardAllSize+"\n"
				+UtilsDevice.byte2Oral(getSDCardAllSize)+"\n"
				+"��ȡϵͳ�洢·��="+getRootDirectoryPath+"\n"
				
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
