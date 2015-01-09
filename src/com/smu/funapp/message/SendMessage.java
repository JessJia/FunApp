package com.smu.funapp.message;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.smu.funapp.message.callback.MessageResponse;

/**
 * JDK HttpURLConnection 访问网络资源
 * 
 * @author smu
 *
 */
public class SendMessage {
	
	MessageResponse messageResponse = null;
	
	public SendMessage(MessageResponse messageResponse) {
		this.messageResponse = messageResponse;
	}
	
	/**
	 * post方法发送消息
	 * @param message 消息内容
	 * @param uri 请求路径
	 * @return 消息是否发送成功
	 */
	public boolean PSendMessage(String message, String url) {
		
		URL path = null;
		HttpURLConnection conn = null;
		InputStreamReader iReader = null;
		
		try {
			path = new URL(url);
			conn = (HttpURLConnection)path.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=UTF-8s");
			conn.setRequestProperty("Accept-Charset", "UTF-8");

			
			OutputStream os = conn.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			
			dos.writeChars("mess="+message);
			dos.flush();
			dos.close();
			
			System.out.println("post responseCode = "+conn.getResponseCode());
			
			if(conn.getResponseCode() == 200) {
				iReader = new InputStreamReader(conn.getInputStream());
				
				BufferedReader bReader = new BufferedReader(iReader);
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
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(conn != null) {
				conn.disconnect();
			}
			
			if(iReader != null) {
				try {
					iReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}
	
	/**
	 * get方法发送消息
	 * @param message 消息路径
	 * @param uri 请求内容
	 * @return 消息是否发送成功
	 */
	public boolean GSendMessage(String message, String url) {
		InputStreamReader iReader = null;
		HttpURLConnection conn = null;
		URL path = null;
		
		try {
			path = new URL(url+"?mess="+message);
			conn = (HttpURLConnection) path.openConnection();
//			conn.setRequestProperty("Content-type", "text/html;charset=utf-8");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			
			System.out.println("get responseCode = "+conn.getResponseCode());
			
			if(conn.getResponseCode() == 200) {
				iReader = new InputStreamReader(conn.getInputStream());
				
				BufferedReader bReader = new BufferedReader(iReader);
				StringBuffer sb = new StringBuffer();
				String line = null;
				while((line = bReader.readLine()) != null) {
					sb.append(line);
				}
				
				messageResponse.onSuccess(null, null);
				
			} else {
				
				messageResponse.onFailed(null, null);
			
			}
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.disconnect();					
				}
				if(iReader != null) {
					iReader.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return false;
	}
	
}
