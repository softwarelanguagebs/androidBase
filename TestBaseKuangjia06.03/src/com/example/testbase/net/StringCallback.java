package com.example.testbase.net;

public abstract class StringCallback extends AbstractCallback {
	@Override
	protected Object bindData(String content) {
		// 如果路径存在，则重新讲数据从文件中读取出来
		if (TextUtil.isValidate(path)) {
			// return IOUtiliteies.readFromFile(path);
			return null;
		}
		return content;
	}
}
