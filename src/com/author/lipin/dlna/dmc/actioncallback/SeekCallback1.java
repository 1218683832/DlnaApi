package com.author.lipin.dlna.dmc.actioncallback;

import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.support.avtransport.callback.Seek;

import com.author.lipin.dlna.model.AbstractDMCServer.OnSeekListener;

public class SeekCallback1 extends Seek {

	private OnSeekListener mOnSeekListener;

	public SeekCallback1(Service<?, ?> service, String relativeTimeTarget,
			OnSeekListener listener) {
		super(service, relativeTimeTarget);
		this.mOnSeekListener = listener;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void success(ActionInvocation invocation) {
		if (null != this.mOnSeekListener) {
			this.mOnSeekListener.success(invocation);
		}
		super.success(invocation);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void failure(ActionInvocation invocation, UpnpResponse operation,
			String defaultMsg) {
		if (null != this.mOnSeekListener) {
			this.mOnSeekListener.failure(invocation, operation, defaultMsg);
		}
	}

}
