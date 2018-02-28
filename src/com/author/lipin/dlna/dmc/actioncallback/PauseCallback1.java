package com.author.lipin.dlna.dmc.actioncallback;

import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.support.avtransport.callback.Pause;

import com.author.lipin.dlna.model.AbstractDMCServer.OnPauseListener;

public class PauseCallback1 extends Pause {

	private OnPauseListener mOnPauseListener;

	public PauseCallback1(Service<?, ?> service, OnPauseListener listener) {
		super(service);
		this.mOnPauseListener = listener;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void failure(ActionInvocation invocation, UpnpResponse operation,
			String defaultMsg) {
		if (null != this.mOnPauseListener) {
			this.mOnPauseListener.failure(invocation, operation, defaultMsg);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void success(ActionInvocation invocation) {
		if (null != this.mOnPauseListener) {
			this.mOnPauseListener.success(invocation);
		}
		super.success(invocation);
	}
}
