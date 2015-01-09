package com.smu.funapp.thread;

import android.content.Context;

import com.smu.funapp.message.SendMessage;
import com.smu.funapp.message.SendMessageApache;

public class ExecuRequest implements Runnable {
	
	Context context = null;
	
	SendMessage sendMessage = new SendMessage(new CallBackMessage(context));
	SendMessageApache apacheMessage = new SendMessageApache(new CallBackMessage(context));
	
	String message = null;
	String url = null;
	
	public ExecuRequest (String message, String url, Context context) {
		this.message = message;
		this.url = url;
		this.context = context;
	}
	
	@Override
	public void run() {
		sendMessage.PSendMessage(message+"JDK-POST", url);
		sendMessage.GSendMessage(message+"JDK-GET", url);
		
		apacheMessage.PMessageSend(message+"APACHE-POST", url);
		apacheMessage.GMessageSend(message+"APACHE-GET", url);

	}

}
