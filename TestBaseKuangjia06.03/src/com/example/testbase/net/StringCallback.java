package com.example.testbase.net;

public abstract class StringCallback extends AbstractCallback {
	@Override
	protected Object bindData(String content) {
		// ���·�����ڣ������½����ݴ��ļ��ж�ȡ����
		if (TextUtil.isValidate(path)) {
			// return IOUtiliteies.readFromFile(path);
			return null;
		}
		return content;
	}
}
