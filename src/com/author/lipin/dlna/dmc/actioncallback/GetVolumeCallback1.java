package com.author.lipin.dlna.dmc.actioncallback;

import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.support.renderingcontrol.callback.GetVolume;

import com.author.lipin.dlna.model.AbstractDMCServer.OnGetVolumeListener;

public class GetVolumeCallback1 extends GetVolume {

	private OnGetVolumeListener mOnGetVolumeListener;

	protected int cutOrAdd;

	public GetVolumeCallback1(Service<?, ?> service, int arg0,
			OnGetVolumeListener listener) {
		super(service);
		this.mOnGetVolumeListener = listener;
		this.cutOrAdd = arg0;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void success(ActionInvocation invocation) {
		if (null != this.mOnGetVolumeListener) {
			this.mOnGetVolumeListener.success(invocation);
		}
		super.success(invocation);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void received(ActionInvocation actionInvocation, int currentVolume) {
		if (null != this.mOnGetVolumeListener) {
			this.mOnGetVolumeListener.received(actionInvocation, currentVolume);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void failure(ActionInvocation invocation, UpnpResponse operation,
			String defaultMsg) {
		if (null != this.mOnGetVolumeListener) {
			this.mOnGetVolumeListener
					.failure(invocation, operation, defaultMsg);
		}
	}
}
