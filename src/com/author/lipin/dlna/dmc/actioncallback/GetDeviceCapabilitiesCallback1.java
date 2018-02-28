
package com.author.lipin.dlna.dmc.actioncallback;

import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.support.avtransport.callback.GetDeviceCapabilities;
import org.fourthline.cling.support.model.DeviceCapabilities;

import com.author.lipin.dlna.model.AbstractDMCServer.OnGetDeviceCapabilitiesInfoListener;

public class GetDeviceCapabilitiesCallback1 extends GetDeviceCapabilities {
	
	private OnGetDeviceCapabilitiesInfoListener mOnGetDeviceCapabilitiesInfoListener;
	
    public GetDeviceCapabilitiesCallback1(Service<?, ?> service, OnGetDeviceCapabilitiesInfoListener listener) {
        super(service);
        this.mOnGetDeviceCapabilitiesInfoListener = listener;
    }

    @SuppressWarnings("rawtypes")
    @Override
	public void failure(ActionInvocation invocation, UpnpResponse operation,
            String defaultMsg) {
    	if (null != this.mOnGetDeviceCapabilitiesInfoListener) {
    		this.mOnGetDeviceCapabilitiesInfoListener.failure(invocation, operation, defaultMsg);
		}
    }

	@SuppressWarnings("rawtypes")
	@Override
	public void success(ActionInvocation invocation) {
		if (null != this.mOnGetDeviceCapabilitiesInfoListener) {
			this.mOnGetDeviceCapabilitiesInfoListener.success(invocation);
		}
		super.success(invocation);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void received(ActionInvocation actionInvocation,
			DeviceCapabilities caps) {
		if (null != this.mOnGetDeviceCapabilitiesInfoListener) {
			this.mOnGetDeviceCapabilitiesInfoListener.received(actionInvocation, caps);
		}
	}

}
