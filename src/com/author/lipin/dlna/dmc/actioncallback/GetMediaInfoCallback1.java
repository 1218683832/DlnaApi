package com.author.lipin.dlna.dmc.actioncallback;

import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.support.avtransport.callback.GetMediaInfo;
import org.fourthline.cling.support.model.MediaInfo;

import com.author.lipin.dlna.model.AbstractDMCServer.OnMediaInfoListener;

public class GetMediaInfoCallback1 extends GetMediaInfo {

	private OnMediaInfoListener mOnMediaInfoListener;

	public GetMediaInfoCallback1(Service<?, ?> service,
			OnMediaInfoListener listener) {
		super(service);
		this.mOnMediaInfoListener = listener;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void received(ActionInvocation invocation, MediaInfo mediaInfo) {
		if (null != this.mOnMediaInfoListener) {
			this.mOnMediaInfoListener.received(invocation, mediaInfo);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void success(ActionInvocation invocation) {
		if (null != this.mOnMediaInfoListener) {
			this.mOnMediaInfoListener.success(invocation);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void failure(ActionInvocation invocation, UpnpResponse operation,
			String defaultMsg) {
		if (null != this.mOnMediaInfoListener) {
			this.mOnMediaInfoListener
					.failure(invocation, operation, defaultMsg);
		}
	}
}
