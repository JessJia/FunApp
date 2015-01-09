package com.smu.funapp.message.callback;

public interface MessageResponse {
	void onSuccess(Object ... obj);
	void onFailed(Object ... obj);
}
