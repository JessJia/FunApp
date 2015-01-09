package com.smu.funapp.thread;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;

import com.smu.funapp.MainActivity;
import com.smu.funapp.message.callback.MessageResponse;

public class CallBackMessage implements MessageResponse {
	
	Context context = null;
	
	public CallBackMessage(Context context) {
		this.context = context;
	}
	
	@Override
	public void onSuccess(Object... obj) {
		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putCharSequence("title", "发送工程");
		bundle.putCharSequence("message", "消息发送成功^_^");
		msg.setData(bundle);
		MainActivity.handler.sendMessage(msg);
	}

	@Override
	public void onFailed(Object... obj) {
		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putCharSequence("title", "发送失败");
		bundle.putCharSequence("message", "消息发送失败，请重试");
		msg.setData(bundle);
		MainActivity.handler.sendMessage(msg);
		
	}
	
}
