package com.example.testbase.voicedemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.util.Log;

public class FileUtils {

	private static final String TAG = FileUtils.class.getSimpleName();

	/**
	 * 创建录音的音频文件
	 * 
	 * @return
	 */
	public static File createAudioFile(String rootPath) {
		File file = new File(rootPath);
		File audioFile = null;
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			audioFile = File.createTempFile(
					"record_" + System.currentTimeMillis(), "", file);
		} catch (IOException e) {
			Log.w(TAG, e);
			e.printStackTrace();
		}
		return audioFile;
	}

	public static void writeFile(String path, String name, byte[] data) {
		if (data == null || data.length <= 0) {
			return;
		}
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}

		File _file = new File(path, name);
		if (_file.exists()) {
			_file.delete();
		}

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(_file);
			fos.write(data);
			fos.flush();
		} catch (FileNotFoundException e) {
			Log.w(TAG, e);
			e.printStackTrace();
		} catch (IOException e) {
			Log.w(TAG, e);
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
