package com.smu.funapp.notification;

import com.smu.funapp.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class MyNotification {
	
	Context context = null;
	Class<?> cls = null;
	String title = "";
	String message = "";
	
	/**
	 * ����֪ͨ
	 * @param context �����Ļ���
	 * @param title ��Ϣ����
	 * @param message ��Ϣ����
	 * @param cls ��ת��Activity��Activity.class��
	 */
	public MyNotification(Context context, String title, String message) {
		this.context = context;
		this.title = title;
		this.message = message;
	}
	
	/**
	 * ������Ϣ
	 * @param cls ��������
	 */
	public void sendNotification(Class<?> cls) {
		
		NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Intent intent = new Intent(context, cls); //����Activity
		PendingIntent pIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		
		Notification notification = new Notification.Builder(context)
		.setContentTitle(title) //��Ϣ����
		.setContentText(message) //��Ϣ����
		.setTicker("�����µ���Ϣ��ע�����^_^") //չʾ��Ϣ
		.setSmallIcon(R.drawable.icon) //ͼƬ
		.setContentIntent(pIntent)
		.build();
		
		manager.notify(1, notification);
	}
	

}
