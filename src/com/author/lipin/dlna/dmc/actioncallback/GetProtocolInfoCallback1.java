package com.author.lipin.dlna.dmc.actioncallback;

import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.support.connectionmanager.callback.GetProtocolInfo;
import org.fourthline.cling.support.model.ProtocolInfos;

import com.author.lipin.dlna.model.AbstractDMCServer.OnProtocolInfoListener;

public class GetProtocolInfoCallback1 extends GetProtocolInfo {

	private OnProtocolInfoListener mOnProtocolInfoListener;

	public GetProtocolInfoCallback1(Service<?, ?> service,
			OnProtocolInfoListener listener) {
		super(service);
		this.mOnProtocolInfoListener = listener;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void success(ActionInvocation invocation) {
		if (null != this.mOnProtocolInfoListener) {
			this.mOnProtocolInfoListener.success(invocation);
		}
		super.success(invocation);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void received(ActionInvocation actionInvocation,
			ProtocolInfos sinkProtocolInfos, ProtocolInfos sourceProtocolInfos) {
		if (null != this.mOnProtocolInfoListener) {
			this.mOnProtocolInfoListener.received(actionInvocation,
					sinkProtocolInfos, sourceProtocolInfos);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void failure(ActionInvocation invocation, UpnpResponse operation,
			String defaultMsg) {
		if (null != this.mOnProtocolInfoListener) {
			this.mOnProtocolInfoListener.failure(invocation, operation,
					defaultMsg);
		}
	}
}
