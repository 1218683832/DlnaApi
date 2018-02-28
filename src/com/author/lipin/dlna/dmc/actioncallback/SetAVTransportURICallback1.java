package com.author.lipin.dlna.dmc.actioncallback;

import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.support.avtransport.callback.SetAVTransportURI;

import com.author.lipin.dlna.model.AbstractDMCServer.OnAVTransportURIListener;

public class SetAVTransportURICallback1 extends SetAVTransportURI {

	private OnAVTransportURIListener mOnAVTransportURIListener;

	public SetAVTransportURICallback1(Service<?, ?> service, String uri,
			String metadata, OnAVTransportURIListener listener) {
		super(service, uri, metadata);
		this.mOnAVTransportURIListener = listener;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void failure(ActionInvocation invocation, UpnpResponse operation,
			String defaultMsg) {
		if (null != this.mOnAVTransportURIListener) {
			this.mOnAVTransportURIListener.failure(invocation, operation,
					defaultMsg);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void success(ActionInvocation invocation) {
		if (null != this.mOnAVTransportURIListener) {
			this.mOnAVTransportURIListener.success(invocation);
		}
		super.success(invocation);
	}
}
