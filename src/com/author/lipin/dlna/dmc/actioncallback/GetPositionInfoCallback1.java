package com.author.lipin.dlna.dmc.actioncallback;

import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.support.avtransport.callback.GetPositionInfo;
import org.fourthline.cling.support.model.PositionInfo;

import com.author.lipin.dlna.model.AbstractDMCServer.OnPositionInfoListener;

public class GetPositionInfoCallback1 extends GetPositionInfo {

	private OnPositionInfoListener mOnPositionInfoListener;

	public GetPositionInfoCallback1(Service<?, ?> service,
			OnPositionInfoListener listener) {
		super(service);
		this.mOnPositionInfoListener = listener;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void received(ActionInvocation invocation, PositionInfo positionInfo) {
		if (null != this.mOnPositionInfoListener) {
			this.mOnPositionInfoListener.received(invocation, positionInfo);
		}
		return;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void failure(ActionInvocation invocation, UpnpResponse operation,
			String defaultMsg) {
		if (null != this.mOnPositionInfoListener) {
			this.mOnPositionInfoListener.failure(invocation, operation,
					defaultMsg);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void success(ActionInvocation invocation) {
		if (null != this.mOnPositionInfoListener) {
			this.mOnPositionInfoListener.success(invocation);
			super.success(invocation);
		}
	}
}
