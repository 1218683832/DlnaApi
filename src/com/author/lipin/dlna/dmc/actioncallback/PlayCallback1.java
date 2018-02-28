package com.author.lipin.dlna.dmc.actioncallback;

import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.support.avtransport.callback.Play;

import com.author.lipin.dlna.model.AbstractDMCServer.OnPlayListener;

public class PlayCallback1 extends Play {

	private OnPlayListener mOnPlayListener;

	public PlayCallback1(Service<?, ?> service, OnPlayListener listener) {
		super(service);
		this.mOnPlayListener = listener;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void failure(ActionInvocation invocation, UpnpResponse operation,
			String defaultMsg) {
		if (null != this.mOnPlayListener) {
			this.mOnPlayListener.failure(invocation, operation, defaultMsg);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void success(ActionInvocation invocation) {
		if (null != this.mOnPlayListener) {
			this.mOnPlayListener.success(invocation);
		}
		super.success(invocation);
	}
}
