package com.author.lipin.dlna.dmc.actioncallback;

import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.support.renderingcontrol.callback.SetMute;

import com.author.lipin.dlna.model.AbstractDMCServer.OnSetMuteListener;

public class SetMuteCalllback1 extends SetMute {

	private OnSetMuteListener mOnSetMuteListener;

	public SetMuteCalllback1(Service<?, ?> service, boolean desiredMute,
			OnSetMuteListener listener) {
		super(service, desiredMute);
		this.mOnSetMuteListener = listener;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void success(ActionInvocation invocation) {
		if (null != this.mOnSetMuteListener) {
			this.mOnSetMuteListener.success(invocation);
		}
		super.success(invocation);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void failure(ActionInvocation invocation, UpnpResponse operation,
			String defaultMsg) {
		if (null != this.mOnSetMuteListener) {
			this.mOnSetMuteListener.failure(invocation, operation, defaultMsg);
		}
	}
}
