package com.author.lipin.dlna.dmc.actioncallback;

import org.fourthline.cling.controlpoint.ControlPoint;
import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.support.connectionmanager.callback.GetCurrentConnectionInfo;
import org.fourthline.cling.support.model.ConnectionInfo;

import com.author.lipin.dlna.model.AbstractDMCServer.OnCurrentConnectionInfoListener;

public class GetCurrentConnectionInfoCallback1 extends GetCurrentConnectionInfo {
	
	private OnCurrentConnectionInfoListener onCurrentConnectionInfoListener;
	
    public GetCurrentConnectionInfoCallback1(Service<?, ?> service, ControlPoint controlPoint,
            int connectionID, OnCurrentConnectionInfoListener listener) {
        super(service, controlPoint, connectionID);
        this.onCurrentConnectionInfoListener = listener;
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public void failure(ActionInvocation invocation, UpnpResponse operation,
            String defaultMsg) {
    	if (null != this.onCurrentConnectionInfoListener) {
			this.onCurrentConnectionInfoListener
					.failure(invocation, operation, defaultMsg);
		}
    }

    @SuppressWarnings("rawtypes")
	@Override
	public void received(ActionInvocation invocation, ConnectionInfo connectionInfo) {
    	if (null != this.onCurrentConnectionInfoListener) {
			this.onCurrentConnectionInfoListener.received(invocation, connectionInfo);
		}
    }

	@SuppressWarnings("rawtypes")
	@Override
	public void success(ActionInvocation invocation) {
		if (null != this.onCurrentConnectionInfoListener) {
			this.onCurrentConnectionInfoListener.success(invocation);
		}
		super.success(invocation);
	}
}
