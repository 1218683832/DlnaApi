package com.author.lipin.dlna.model;

import org.fourthline.cling.android.AndroidUpnpService;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.model.types.UDAServiceType;

import com.author.lipin.dlna.bean.DeviceItem;
import com.author.lipin.dlna.constant.IServiceType;
import com.author.lipin.dlna.dmc.actioncallback.GetCurrentConnectionInfoCallback1;
import com.author.lipin.dlna.dmc.actioncallback.GetDeviceCapabilitiesCallback1;
import com.author.lipin.dlna.dmc.actioncallback.GetMediaInfoCallback1;
import com.author.lipin.dlna.dmc.actioncallback.GetMuteCallback1;
import com.author.lipin.dlna.dmc.actioncallback.GetPositionInfoCallback1;
import com.author.lipin.dlna.dmc.actioncallback.GetProtocolInfoCallback1;
import com.author.lipin.dlna.dmc.actioncallback.GetSubscriptionCallback1;
import com.author.lipin.dlna.dmc.actioncallback.GetTransportInfoCallback1;
import com.author.lipin.dlna.dmc.actioncallback.GetVolumeCallback1;
import com.author.lipin.dlna.dmc.actioncallback.PauseCallback1;
import com.author.lipin.dlna.dmc.actioncallback.PlayCallback1;
import com.author.lipin.dlna.dmc.actioncallback.SeekCallback1;
import com.author.lipin.dlna.dmc.actioncallback.SetAVTransportURICallback1;
import com.author.lipin.dlna.dmc.actioncallback.SetMuteCalllback1;
import com.author.lipin.dlna.dmc.actioncallback.SetVolumeCallback1;
import com.author.lipin.dlna.dmc.actioncallback.StopCallback1;

public class DMCServer extends AbstractDMCServer{
	
	private GetSubscriptionCallback1 subscriptionCallback1;

	public DMCServer(AndroidUpnpService upnpService,
			DeviceItem executeDeviceItem) {
		super(upnpService, executeDeviceItem);
	}

	@Override
	public void setCurrentPlayUri(String currentPlayUri, int type) {
		this.uri = currentPlayUri;
//		this.mimeType = currentPlayUri.substring(
//				currentPlayUri.lastIndexOf("=") + 1).toLowerCase();
		this.type = type;
	}

	@Override
	public void setCurrentPlayUri(String currentPlayUri, String metaData,
			int type) {
		this.uri = currentPlayUri;
		this.metaData = metaData;
//		this.mimeType = currentPlayUri.substring(
//				currentPlayUri.lastIndexOf("=") + 1).toLowerCase();
		this.type = type;
	}

	@Override
	public void getPositionInfo(OnPositionInfoListener listener) {
		Service<?, ?> service = this.executeDeviceItem.getDevice().findService(
				new UDAServiceType(IServiceType.AVTransport));
		if (service == null) {
			return;
		}
		if (null == this.upnpService) {
			return;
		}
		this.upnpService.getControlPoint().execute(
				new GetPositionInfoCallback1(service, listener));
	}

	@Override
	public void stop(OnStopListener listener) {
		Service<?, ?> service = this.executeDeviceItem.getDevice().findService(
				new UDAServiceType(IServiceType.AVTransport));
		if (service == null) {
			return;
		}
		if (null == this.upnpService) {
			return;
		}
		this.upnpService.getControlPoint().execute(
				new StopCallback1(service, listener));
	}

	@Override
	public void pause(OnPauseListener listener) {
		Service<?, ?> service = this.executeDeviceItem.getDevice().findService(
				new UDAServiceType(IServiceType.AVTransport));
		if (service == null) {
			return;
		}
		if (null == this.upnpService) {
			return;
		}
		this.upnpService.getControlPoint().execute(
				new PauseCallback1(service, listener));
	}

	@Override
	public void play(OnPlayListener listener) {
		Service<?, ?> service = this.executeDeviceItem.getDevice().findService(
				new UDAServiceType(IServiceType.AVTransport));
		if (service == null) {
			return;
		}
		if (null == this.upnpService) {
			return;
		}
		this.upnpService.getControlPoint().execute(
				new PlayCallback1(service, listener));
	}

	@Override
	public void setVolume(long volume, int cutOrAdd,
			OnSetVolumeListener listener) {
		Service<?, ?> service = this.executeDeviceItem.getDevice().findService(
				new UDAServiceType(IServiceType.RenderingControl));
		if (service == null) {
			return;
		}
		if (null == this.upnpService) {
			return;
		}
		if (cutOrAdd == CUT_VOC) {
			if (volume > 0L) {
				volume -= 1L;
			} else {
			}
		} else if (volume < 100L) {
			volume += 1L;
		}
		this.upnpService.getControlPoint().execute(
				new SetVolumeCallback1(service, volume, listener));
	}

	@Override
	public void getVolume(int cutOrAdd, OnGetVolumeListener listener) {
		Service<?, ?> service = this.executeDeviceItem.getDevice().findService(
				new UDAServiceType(IServiceType.RenderingControl));
		if (null == service) {
			return;
		}
		if (null == this.upnpService) {
			return;
		}
		this.upnpService.getControlPoint().execute(
				new GetVolumeCallback1(service, cutOrAdd, listener));
	}

	@Override
	public void setMute(boolean desiredMute, OnSetMuteListener listener) {
		Service<?, ?> service = this.executeDeviceItem.getDevice().findService(
				new UDAServiceType(IServiceType.RenderingControl));
		if (service == null) {
			return;
		}
		if (null == this.upnpService) {
			return;
		}
		this.upnpService.getControlPoint().execute(
				new SetMuteCalllback1(service, desiredMute, listener));
	}

	@Override
	public void getMute(OnGetMuteListener listener) {
		Service<?, ?> service = this.executeDeviceItem.getDevice().findService(
				new UDAServiceType(IServiceType.RenderingControl));
		if (service == null) {
			return;
		}
		if (null == this.upnpService) {
			return;
		}
		this.upnpService.getControlPoint().execute(
				new GetMuteCallback1(service, listener));
	}

	@Override
	public void getTransportInfo(OnTransportInfoListener listener) {
		Service<?, ?> service = this.executeDeviceItem.getDevice().findService(
				new UDAServiceType(IServiceType.AVTransport));
		if (service == null) {
			return;
		}
		if (null == this.upnpService) {
			return;
		}
		this.upnpService.getControlPoint().execute(
				new GetTransportInfoCallback1(service, listener));
	}

	@Override
	public void getMediaInfo(OnMediaInfoListener listener) {
		Service<?, ?> service = this.executeDeviceItem.getDevice().findService(
				new UDAServiceType(IServiceType.AVTransport));
		if (service == null) {
			return;
		}
		if (null == this.upnpService) {
			return;
		}
		this.upnpService.getControlPoint().equals(
				new GetMediaInfoCallback1(service, listener));
	}

	@Override
	public void getProtocolInfo(OnProtocolInfoListener listener) {
		Service<?, ?> service = this.executeDeviceItem.getDevice().findService(
				new UDAServiceType(IServiceType.ConnectionManager));
		if (service == null) {
			return;
		}
		if (null == this.upnpService) {
			return;
		}
		this.upnpService.getControlPoint().execute(
				new GetProtocolInfoCallback1(service, listener));
	}

	@Override
	public void seekToPosition(String relativeTimeTarget,
			OnSeekListener listener) {
		Service<?, ?> service = this.executeDeviceItem.getDevice().findService(
				new UDAServiceType(IServiceType.AVTransport));
		if (service == null) {
			return;
		}
		if (null == this.upnpService) {
			return;
		}
		this.upnpService.getControlPoint().execute(
				new SeekCallback1(service, relativeTimeTarget, listener));
	}

	@Override
	public void setAVURL(String uri, String metadata,
			OnAVTransportURIListener listener) {
		Service<?, ?> service = this.executeDeviceItem.getDevice().findService(
				new UDAServiceType(IServiceType.AVTransport));
		if (service == null) {
			return;
		}
		if (null == this.upnpService) {
			return;
		}
		this.upnpService.getControlPoint()
				.execute(
						new SetAVTransportURICallback1(service, uri, metaData,
								listener));
	}

	@Override
	public void getSubscriptionInfo(OnSubscriptionInfoListener listener) {
		Service<?, ?> service = this.executeDeviceItem.getDevice().findService(
				new UDAServiceType(IServiceType.AVTransport));
		if (service == null) {
			return;
		}
		if (null == this.upnpService) {
			return;
		}
		if (subscriptionCallback1 == null) {
			subscriptionCallback1 = new GetSubscriptionCallback1(service, 600,
					listener);
		}
		this.upnpService.getControlPoint().execute(subscriptionCallback1);
	}

	@Override
	public void GetDeviceCapabilitiesInfo(
			OnGetDeviceCapabilitiesInfoListener listener) {
		Service<?, ?> service = this.executeDeviceItem.getDevice().findService(
				new UDAServiceType(IServiceType.AVTransport));
		if (service == null) {
			return;
		}
		if (null == this.upnpService) {
			return;
		}
		this.upnpService.getControlPoint().execute(
				new GetDeviceCapabilitiesCallback1(service, listener));
	}

	@Override
	public void getCurrentConnectionInfo(OnCurrentConnectionInfoListener listener) {
		Service<?, ?> service = this.executeDeviceItem.getDevice().findService(
				new UDAServiceType(IServiceType.ConnectionManager));
		if (service == null) {
			return;
		}
		this.upnpService.getControlPoint().execute(
				new GetCurrentConnectionInfoCallback1(service, this.upnpService
						.getControlPoint(), 0, listener));
	}

}
