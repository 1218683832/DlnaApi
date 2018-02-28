package com.author.lipin.dlna.dmc.actioncallback;

import org.fourthline.cling.controlpoint.SubscriptionCallback;
import org.fourthline.cling.model.UnsupportedDataException;
import org.fourthline.cling.model.gena.CancelReason;
import org.fourthline.cling.model.gena.GENASubscription;
import org.fourthline.cling.model.gena.RemoteGENASubscription;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.Service;

import com.author.lipin.dlna.model.AbstractDMCServer.OnSubscriptionInfoListener;

public class GetSubscriptionCallback1 extends SubscriptionCallback {

	private OnSubscriptionInfoListener mOnSubscriptionInfoListener;

	public GetSubscriptionCallback1(Service<?, ?> service,
			int requestedDurationSeconds, OnSubscriptionInfoListener listener) {
		super(service, requestedDurationSeconds);
		this.mOnSubscriptionInfoListener = listener;
	}

	@Override
	public synchronized void end() {
		if (null != this.mOnSubscriptionInfoListener) {
			this.mOnSubscriptionInfoListener.end();
		}
		super.end();
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void failed(GENASubscription subscription,
			UpnpResponse responseStatus, Exception exception, String defaultMsg) {
		if (null != this.mOnSubscriptionInfoListener) {
			this.mOnSubscriptionInfoListener.failed(subscription,
					responseStatus, exception, defaultMsg);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void established(GENASubscription subscription) {
		if (null != this.mOnSubscriptionInfoListener) {
			this.mOnSubscriptionInfoListener.established(subscription);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void ended(GENASubscription subscription, CancelReason reason,
			UpnpResponse responseStatus) {
		if (null != this.mOnSubscriptionInfoListener) {
			this.mOnSubscriptionInfoListener.ended(subscription, reason,
					responseStatus);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void eventReceived(GENASubscription subscription) {
		if (null != this.mOnSubscriptionInfoListener) {
			this.mOnSubscriptionInfoListener.eventReceived(subscription);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void eventsMissed(GENASubscription subscription,
			int numberOfMissedEvents) {
		if (null != this.mOnSubscriptionInfoListener) {
			this.mOnSubscriptionInfoListener.eventsMissed(subscription,
					numberOfMissedEvents);
		}
	}

	@Override
	protected void invalidMessage(
			RemoteGENASubscription remoteGENASubscription,
			UnsupportedDataException ex) {
		if (null != this.mOnSubscriptionInfoListener) {
			this.mOnSubscriptionInfoListener.invalidMessage(
					remoteGENASubscription, ex);
		}
		super.invalidMessage(remoteGENASubscription, ex);
	}

}
