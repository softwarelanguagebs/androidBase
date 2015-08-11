package com.example.testbase.util;

import java.io.File;
import java.io.IOException;
 
import android.os.Environment;
 
public class FileUtils {
    /**
     * ���SD���Ƿ����
     */
    public static boolean checkSDcard() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
    }
 
    /**
     * ��ȡ�ļ������
     */
    public static File getSaveFile(String fileNmae) {
        File file = null;
        try {
            file = new File(Environment.getExternalStorageDirectory()
                    .getCanonicalFile() + "/" + fileNmae);
        } catch (IOException e) {
        }
        return file;
    }
 
    /**
     * ��ָ���ļ��л�ȡ�ļ�
     */
    public static File getSaveFile(String folder, String fileNmae) {
        File file = new File(getSavePath(folder), fileNmae);
        return file;
    }
 
    /**
     * ��ȡ�ļ�����·��
     */
    public static String getSavePath(String folder) {
        return Environment.getExternalStorageDirectory() + "/" + folder;
    }
}