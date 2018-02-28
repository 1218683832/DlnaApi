package com.author.lipin.dlna.dmc.actioncallback;

import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.support.avtransport.callback.Stop;

import com.author.lipin.dlna.model.AbstractDMCServer.OnStopListener;

public class StopCallback1 extends Stop {

	private OnStopListener mOnStopListener;

	public StopCallback1(Service<?, ?> service, OnStopListener listener) {
		super(service);
		this.mOnStopListener = listener;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void success(ActionInvocation invocation) {
		if (null != this.mOnStopListener) {
			this.mOnStopListener.success(invocation);
		}
		super.success(invocation);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void failure(ActionInvocation invocation, UpnpResponse operation,
			String defaultMsg) {
		if (null != this.mOnStopListener) {
			this.mOnStopListener.failure(invocation, operation, defaultMsg);
		}
	}
}
