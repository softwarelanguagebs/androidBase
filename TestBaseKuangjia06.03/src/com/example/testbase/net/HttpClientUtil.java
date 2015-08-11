package com.example.testbase.net;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @author Mr.傅
 */
public class HttpClientUtil {
	/**
	 * 执行HTTP方法，Request 设置请求类型
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static HttpResponse excute(Request request) throws Exception {
		switch (request.requestMethod) {
		case GET:
			return get(request);
		case POST:
			return post(request);
		default:
			// 这里没有定义 DELETE 和 PUT 操作
			throw new IllegalStateException(
					"you doesn't define this requestmethod");
		}
	}

	private static HttpResponse get(Request request) throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(request.url);
		addHeader(get, request.headers);
		// 返回的结果放到上一层进行处理
		HttpResponse response = client.execute(get);
		return response;
	}

	private static HttpResponse post(Request request) throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(request.url);
		addHeader(post, request.headers);
		// post的请求参数在 Request 中定义，如果为空，则没有定义
		if (request.entity == null) {
			throw new IllegalStateException(
					"you forget to set post content to the httpost");
		} else {
			post.setEntity(request.entity);
		}
		HttpResponse response = client.execute(post);
		return response;
	}

	/**
	 * 请求头
	 * 
	 * @param request
	 * @param headers
	 */
	public static void addHeader(HttpUriRequest request,
			Map<String, String> headers) {
		if (headers != null && headers.size() > 0) {
			for (Entry<String, String> entry : headers.entrySet()) {
				request.addHeader(entry.getKey(), entry.getValue());
			}
		}
	}
}
