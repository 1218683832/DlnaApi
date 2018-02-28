package com.author.lipin.dlna.dmc.actioncallback;

import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.support.renderingcontrol.callback.GetMute;

import com.author.lipin.dlna.model.AbstractDMCServer.OnGetMuteListener;

public class GetMuteCallback1 extends GetMute {

	private OnGetMuteListener mOnGetMuteListener;

	public GetMuteCallback1(Service<?, ?> service, OnGetMuteListener listener) {
		super(service);
		this.mOnGetMuteListener = listener;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void received(ActionInvocation actionInvocation, boolean currentMute) {
		if (null != this.mOnGetMuteListener) {
			this.mOnGetMuteListener.received(actionInvocation, currentMute);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void failure(ActionInvocation invocation, UpnpResponse operation,
			String defaultMsg) {
		if (null != this.mOnGetMuteListener) {
			this.mOnGetMuteListener.failure(invocation, operation, defaultMsg);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void success(ActionInvocation invocation) {
		if (null != this.mOnGetMuteListener) {
			this.mOnGetMuteListener.success(invocation);
			super.success(invocation);
		}
	}

}
