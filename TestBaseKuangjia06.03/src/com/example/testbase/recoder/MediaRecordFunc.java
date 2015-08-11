package com.example.testbase.recoder;

 
import java.io.File;
import java.io.IOException;
 
import android.media.MediaRecorder;
 
public class MediaRecordFunc {  
    private boolean isRecord = false;
     
    private MediaRecorder mMediaRecorder;
    private MediaRecordFunc(){
    }
     
    private static MediaRecordFunc mInstance;
    public synchronized static MediaRecordFunc getInstance(){
        if(mInstance == null)
            mInstance = new MediaRecordFunc();
        return mInstance;
    }
     
    public int startRecordAndFile(){
        //�ж��Ƿ����ⲿ�洢�豸sdcard
        if(AudioFileFunc.isSdcardExit())
        {
            if(isRecord)
            {
                return ErrorCode.E_STATE_RECODING;
            }
            else
            {
                if(mMediaRecorder == null)
                    createMediaRecord();
                 
                try{
                    mMediaRecorder.prepare();
                    mMediaRecorder.start();
                    // ��¼��״̬Ϊtrue  
                    isRecord = true;
                    return ErrorCode.SUCCESS;
                }catch(IOException ex){
                    ex.printStackTrace();
                    return ErrorCode.E_UNKOWN;
                }
            }
             
        }       
        else
        {
            return ErrorCode.E_NOSDCARD;            
        }       
    }
     
     
    public void stopRecordAndFile(){
         close();
    }
     
    public long getRecordFileSize(){
        return AudioFileFunc.getFileSize(AudioFileFunc.getAMRFilePath());
    }
     
     
    private void createMediaRecord(){
         /* ��Initial��ʵ����MediaRecorder���� */
        mMediaRecorder = new MediaRecorder();
         
        /* setAudioSource/setVedioSource*/
        mMediaRecorder.setAudioSource(AudioFileFunc.AUDIO_INPUT);//������˷�
         
        /* ��������ļ��ĸ�ʽ��THREE_GPP/MPEG-4/RAW_AMR/Default
         * THREE_GPP(3gp��ʽ��H263��Ƶ/ARM��Ƶ����)��MPEG-4��RAW_AMR(ֻ֧����Ƶ����Ƶ����Ҫ��ΪAMR_NB)
         */
         mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
          
         /* ������Ƶ�ļ��ı��룺AAC/AMR_NB/AMR_MB/Default */
         mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
          
         /* ��������ļ���·�� */
         File file = new File(AudioFileFunc.getAMRFilePath());
         if (file.exists()) {  
             file.delete();  
         } 
         mMediaRecorder.setOutputFile(AudioFileFunc.getAMRFilePath());
    }
     
     
    private void close(){
        if (mMediaRecorder != null) {  
            System.out.println("stopRecord");  
            isRecord = false;
            mMediaRecorder.stop();  
            mMediaRecorder.release();  
            mMediaRecorder = null;
        }  
    }
}