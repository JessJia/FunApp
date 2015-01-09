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
	 * 发送通知
	 * @param context 上下文环境
	 * @param title 消息标题
	 * @param message 消息标题
	 * @param cls 跳转的Activity（Activity.class）
	 */
	public MyNotification(Context context, String title, String message) {
		this.context = context;
		this.title = title;
		this.message = message;
	}
	
	/**
	 * 发送消息
	 * @param cls 启动界面
	 */
	public void sendNotification(Class<?> cls) {
		
		NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Intent intent = new Intent(context, cls); //启动Activity
		PendingIntent pIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		
		Notification notification = new Notification.Builder(context)
		.setContentTitle(title) //消息标题
		.setContentText(message) //消息内容
		.setTicker("您有新的消息请注意查收^_^") //展示消息
		.setSmallIcon(R.drawable.icon) //图片
		.setContentIntent(pIntent)
		.build();
		
		manager.notify(1, notification);
	}
	

}
