package com.smu.funapp.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.smu.funapp.message.callback.MessageResponse;

/**
 * Apache http请求访问网络资源
 * @author smu
 *
 */
public class SendMessageApache {
	
	MessageResponse messageResponse = null;
	public SendMessageApache(MessageResponse messageResponse) {
		this.messageResponse = messageResponse;
	}
	
	/**
	 * 编码方式没有问题
	 * Apache post请求
	 * @param mess 消息内容
	 * @param uri 请求路径
	 * @return
	 */
	public boolean PMessageSend(String mess, String url) {
		
		BufferedReader bReader = null;
		
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost();
			request.setURI(new URI(url));
			List<NameValuePair> postParms = new ArrayList<NameValuePair>();
			postParms.add(new BasicNameValuePair("mess", mess));
			
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postParms, HTTP.UTF_8);
			request.setEntity(entity);
			
			HttpResponse response = client.execute(request);
			System.out.println("apache post responseCode = " + response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode() == 200) {
				bReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				
				StringBuffer sb = new StringBuffer();
				String line = null;
				
				while((line = bReader.readLine()) != null) {
					sb.append(line);
				}
				
				System.out.println(sb.toString());
				
				messageResponse.onSuccess(null, null);
			} else {
				messageResponse.onFailed(null, null);
			}
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(bReader != null) {
					bReader.close();					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	/**
	 * Apache get请求
	 * @param mess 消息内容
	 * @param uri 请求路径
	 * @return
	 */
	public boolean GMessageSend(String mess, String url) {
		
		BufferedReader bReader = null;
		
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();

			request.setURI(new URI(url+"?mess="+mess));
			
			HttpResponse response = client.execute(request);
			
			System.out.println("apache get responseCode = " + response.getStatusLine().getStatusCode());
			
			if(response.getStatusLine().getStatusCode() == 200) {
				bReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				StringBuffer sb = new StringBuffer();
				
				String line = null;
				
				while((line = bReader.readLine()) != null) {
					sb.append(line);
				}
				
				System.out.println(sb.toString());
				
				messageResponse.onSuccess(null, null);
			} else {
				messageResponse.onFailed(null, null);
			}
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(bReader != null) {
					bReader.close();					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
}
