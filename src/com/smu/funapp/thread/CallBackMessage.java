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
		bundle.putCharSequence("title", "���͹���");
		bundle.putCharSequence("message", "��Ϣ���ͳɹ�^_^");
		msg.setData(bundle);
		MainActivity.handler.sendMessage(msg);
	}

	@Override
	public void onFailed(Object... obj) {
		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putCharSequence("title", "����ʧ��");
		bundle.putCharSequence("message", "��Ϣ����ʧ�ܣ�������");
		msg.setData(bundle);
		MainActivity.handler.sendMessage(msg);
		
	}
	
}
