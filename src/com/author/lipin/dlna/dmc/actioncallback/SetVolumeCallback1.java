package com.author.lipin.dlna.dmc.actioncallback;

import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.support.renderingcontrol.callback.SetVolume;

import com.author.lipin.dlna.model.AbstractDMCServer.OnSetVolumeListener;

public class SetVolumeCallback1 extends SetVolume {

	private OnSetVolumeListener mOnSetVolumeListener;

	public SetVolumeCallback1(Service<?, ?> service, long newVolume,
			OnSetVolumeListener listener) {
		super(service, newVolume);
		this.mOnSetVolumeListener = listener;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void success(ActionInvocation invocation) {
		if (null != this.mOnSetVolumeListener) {
			this.mOnSetVolumeListener.success(invocation);
		}
		super.success(invocation);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void failure(ActionInvocation invocation, UpnpResponse operation,
			String defaultMsg) {
		if (null != this.mOnSetVolumeListener) {
			this.mOnSetVolumeListener
					.failure(invocation, operation, defaultMsg);
		}
	}

}
