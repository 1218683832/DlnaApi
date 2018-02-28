package com.author.lipin.dlna.dmc.actioncallback;

import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.support.avtransport.callback.GetTransportInfo;
import org.fourthline.cling.support.model.TransportInfo;

import com.author.lipin.dlna.model.AbstractDMCServer.OnTransportInfoListener;

public class GetTransportInfoCallback1 extends GetTransportInfo {

	private OnTransportInfoListener mOnTransportInfoListener;

	public GetTransportInfoCallback1(Service<?, ?> service,
			OnTransportInfoListener listener) {
		super(service);
		this.mOnTransportInfoListener = listener;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void received(ActionInvocation invocation,
			TransportInfo transportInfo) {
		if (null != this.mOnTransportInfoListener) {
			this.mOnTransportInfoListener.received(invocation, transportInfo);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void success(ActionInvocation invocation) {
		if (null != this.mOnTransportInfoListener) {
			this.mOnTransportInfoListener.success(invocation);
		}
		super.success(invocation);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void failure(ActionInvocation invocation, UpnpResponse operation,
			String defaultMsg) {
		if (null != this.mOnTransportInfoListener) {
			this.mOnTransportInfoListener.failure(invocation, operation,
					defaultMsg);
		}
	}
}
