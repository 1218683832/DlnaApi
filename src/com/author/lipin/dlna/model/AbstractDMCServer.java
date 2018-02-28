package com.author.lipin.dlna.model;

import org.fourthline.cling.android.AndroidUpnpService;
import org.fourthline.cling.model.UnsupportedDataException;
import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.gena.CancelReason;
import org.fourthline.cling.model.gena.GENASubscription;
import org.fourthline.cling.model.gena.RemoteGENASubscription;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.support.model.ConnectionInfo;
import org.fourthline.cling.support.model.DeviceCapabilities;
import org.fourthline.cling.support.model.MediaInfo;
import org.fourthline.cling.support.model.PositionInfo;
import org.fourthline.cling.support.model.ProtocolInfos;
import org.fourthline.cling.support.model.TransportInfo;

import com.author.lipin.dlna.bean.DeviceItem;

public abstract class AbstractDMCServer {

	/**
	 * 减小音量
	 */
	protected static final int CUT_VOC = 0;

	/**
	 * 增大音量
	 */
	protected static final int ADD_VOC = 1;

	protected AndroidUpnpService upnpService;

	protected DeviceItem executeDeviceItem;

	/**
	 * 资源类型
	 */
	protected int type;

	/**
	 * 资源地址
	 */
	protected String uri;

	/**
	 * 元数据
	 */
	protected String metaData;

	// protected String mimeType;

	public AbstractDMCServer(AndroidUpnpService upnpService,
			DeviceItem executeDeviceItem) {
		super();
		this.upnpService = upnpService;
		this.executeDeviceItem = executeDeviceItem;
	}

	public abstract void setCurrentPlayUri(String currentPlayUri, int type);

	public abstract void setCurrentPlayUri(String currentPlayUri,
			String metaData, int type);

	/**
	 * 获取设备能力
	 * 
	 * @param listener
	 */
	public abstract void GetDeviceCapabilitiesInfo(
			OnGetDeviceCapabilitiesInfoListener listener);

	/**
	 * 获取设备能力监听器
	 * 
	 * @author lipin
	 * 
	 */
	public interface OnGetDeviceCapabilitiesInfoListener {
		@SuppressWarnings("rawtypes")
		public void failure(ActionInvocation invocation,
				UpnpResponse operation, String defaultMsg);

		@SuppressWarnings("rawtypes")
		public void success(ActionInvocation invocation);

		@SuppressWarnings("rawtypes")
		public void received(ActionInvocation invocation,
				DeviceCapabilities caps);
	}

	/**
	 * 订阅事件信息
	 * 
	 * @param listener
	 */
	public abstract void getSubscriptionInfo(OnSubscriptionInfoListener listener);

	/**
	 * 订阅事件信息监听器
	 * 
	 * @author lipin
	 * 
	 */
	public interface OnSubscriptionInfoListener {
		public void end();

		@SuppressWarnings("rawtypes")
		public void failed(GENASubscription subscription,
				UpnpResponse responseStatus, Exception exception,
				String defaultMsg);

		@SuppressWarnings("rawtypes")
		void established(GENASubscription subscription);

		@SuppressWarnings("rawtypes")
		void ended(GENASubscription subscription, CancelReason reason,
				UpnpResponse responseStatus);

		@SuppressWarnings("rawtypes")
		void eventReceived(GENASubscription subscription);

		@SuppressWarnings("rawtypes")
		void eventsMissed(GENASubscription subscription,
				int numberOfMissedEvents);

		void invalidMessage(RemoteGENASubscription remoteGENASubscription,
				UnsupportedDataException ex);
	}

	/**
	 * AVTransportURI设置
	 * 
	 * @param uri
	 * @param metadata
	 * @param listener
	 */
	public abstract void setAVURL(String uri, String metadata,
			OnAVTransportURIListener listener);

	/**
	 * AVTransportURI设置监听器
	 * 
	 * @author lipin
	 * 
	 */
	public interface OnAVTransportURIListener {
		@SuppressWarnings("rawtypes")
		public void failure(ActionInvocation invocation,
				UpnpResponse operation, String defaultMsg);

		@SuppressWarnings("rawtypes")
		public void success(ActionInvocation invocation);
	}

	/**
	 * 定位
	 * 
	 * @param relativeTimeTarget
	 * @param listener
	 */
	public abstract void seekToPosition(String relativeTimeTarget,
			OnSeekListener listener);

	/**
	 * 定位监听器
	 * 
	 * @author lipin
	 * 
	 */
	public interface OnSeekListener {
		@SuppressWarnings("rawtypes")
		public void failure(ActionInvocation invocation,
				UpnpResponse operation, String defaultMsg);

		@SuppressWarnings("rawtypes")
		public void success(ActionInvocation invocation);
	}

	/**
	 * 当前连接信息
	 * 
	 * @param listener
	 */
	public abstract void getCurrentConnectionInfo(
			OnCurrentConnectionInfoListener listener);

	/**
	 * 当前连接信息监听器
	 * 
	 * @author lipin
	 * 
	 */
	public interface OnCurrentConnectionInfoListener {
		@SuppressWarnings("rawtypes")
		public void received(ActionInvocation invocation,
				ConnectionInfo connectionInfo);

		@SuppressWarnings("rawtypes")
		public void failure(ActionInvocation invocation,
				UpnpResponse operation, String defaultMsg);

		@SuppressWarnings("rawtypes")
		public void success(ActionInvocation invocation);
	}

	/**
	 * 协议信息
	 * 
	 * @param listener
	 */
	public abstract void getProtocolInfo(OnProtocolInfoListener listener);

	/**
	 * 协议信息监听器
	 * 
	 * @author lipin
	 * 
	 */
	public interface OnProtocolInfoListener {
		@SuppressWarnings("rawtypes")
		public void received(ActionInvocation invocation,
				ProtocolInfos sinkProtocolInfos,
				ProtocolInfos sourceProtocolInfos);

		@SuppressWarnings("rawtypes")
		public void failure(ActionInvocation invocation,
				UpnpResponse operation, String defaultMsg);

		@SuppressWarnings("rawtypes")
		public void success(ActionInvocation invocation);
	}

	/**
	 * 媒体信息
	 * 
	 * @param listener
	 */
	public abstract void getMediaInfo(OnMediaInfoListener listener);

	/**
	 * 媒体信息监听器
	 * 
	 * @author lipin
	 * 
	 */
	public interface OnMediaInfoListener {
		@SuppressWarnings("rawtypes")
		public void received(ActionInvocation invocation, MediaInfo mediaInfo);

		@SuppressWarnings("rawtypes")
		public void failure(ActionInvocation invocation,
				UpnpResponse operation, String defaultMsg);

		@SuppressWarnings("rawtypes")
		public void success(ActionInvocation invocation);
	}

	/**
	 * 传输信息
	 * 
	 * @param listener
	 */
	public abstract void getTransportInfo(OnTransportInfoListener listener);

	/**
	 * 传输信息监听器
	 * 
	 * @author lipin
	 * 
	 */
	public interface OnTransportInfoListener {
		@SuppressWarnings("rawtypes")
		public void received(ActionInvocation invocation,
				TransportInfo transportInfo);

		@SuppressWarnings("rawtypes")
		public void failure(ActionInvocation invocation,
				UpnpResponse operation, String defaultMsg);

		@SuppressWarnings("rawtypes")
		public void success(ActionInvocation invocation);
	}

	/**
	 * 获取静音
	 * 
	 * @param listener
	 */
	public abstract void getMute(OnGetMuteListener listener);

	/**
	 * 获取静音监听器
	 * 
	 * @author lipin
	 * 
	 */
	public interface OnGetMuteListener {
		@SuppressWarnings("rawtypes")
		public void received(ActionInvocation invocation,
				boolean currentMute);

		@SuppressWarnings("rawtypes")
		public void failure(ActionInvocation invocation,
				UpnpResponse operation, String defaultMsg);

		@SuppressWarnings("rawtypes")
		public void success(ActionInvocation invocation);
	}

	/**
	 * 静音设置
	 * 
	 * @param desiredMute
	 * @param listener
	 */
	public abstract void setMute(boolean desiredMute, OnSetMuteListener listener);

	/**
	 * 静音设置监听器
	 * 
	 * @author lipin
	 * 
	 */
	public interface OnSetMuteListener {
		@SuppressWarnings("rawtypes")
		public void failure(ActionInvocation invocation,
				UpnpResponse operation, String defaultMsg);

		@SuppressWarnings("rawtypes")
		public void success(ActionInvocation invocation);
	}

	/**
	 * 音量获取
	 * 
	 * @param cutOrAdd
	 * @param listener
	 */
	public abstract void getVolume(int cutOrAdd, OnGetVolumeListener listener);

	/**
	 * 音量获取监听器
	 * 
	 * @author lipin
	 * 
	 */
	public interface OnGetVolumeListener {
		@SuppressWarnings("rawtypes")
		public void received(ActionInvocation invocation,
				int currentVolume);

		@SuppressWarnings("rawtypes")
		public void failure(ActionInvocation invocation,
				UpnpResponse operation, String defaultMsg);

		@SuppressWarnings("rawtypes")
		public void success(ActionInvocation invocation);
	}

	/**
	 * 音量设置
	 * 
	 * @param volume
	 * @param curOrAdd
	 * @param listener
	 */
	public abstract void setVolume(long volume, int curOrAdd,
			OnSetVolumeListener listener);

	/**
	 * 音量设置监听器
	 * 
	 * @author lipin
	 * 
	 */
	public interface OnSetVolumeListener {
		@SuppressWarnings("rawtypes")
		public void failure(ActionInvocation invocation,
				UpnpResponse operation, String defaultMsg);

		@SuppressWarnings("rawtypes")
		public void success(ActionInvocation invocation);
	}

	/**
	 * 播放
	 * 
	 * @param listener
	 */
	public abstract void play(OnPlayListener listener);

	/**
	 * 播放监听器
	 * 
	 * @author lipin
	 * 
	 */
	public interface OnPlayListener {
		@SuppressWarnings("rawtypes")
		public void failure(ActionInvocation invocation,
				UpnpResponse operation, String defaultMsg);

		@SuppressWarnings("rawtypes")
		public void success(ActionInvocation invocation);
	}

	/**
	 * 暂停
	 * 
	 * @param listener
	 */
	public abstract void pause(OnPauseListener listener);

	/**
	 * 暂停监听器
	 * 
	 * @author lipin
	 * 
	 */
	public interface OnPauseListener {
		@SuppressWarnings("rawtypes")
		public void failure(ActionInvocation invocation,
				UpnpResponse operation, String defaultMsg);

		@SuppressWarnings("rawtypes")
		public void success(ActionInvocation invocation);
	}

	/**
	 * 停止
	 * 
	 * @param listener
	 */
	public abstract void stop(OnStopListener listener);

	/**
	 * 停止监听器
	 * 
	 * @author lipin
	 * 
	 */
	public interface OnStopListener {
		@SuppressWarnings("rawtypes")
		public void failure(ActionInvocation invocation,
				UpnpResponse operation, String defaultMsg);

		@SuppressWarnings("rawtypes")
		public void success(ActionInvocation invocation);
	}

	/**
	 * 获取播放位置
	 * 
	 * @param listener
	 */
	public abstract void getPositionInfo(OnPositionInfoListener listener);

	/**
	 * 位置监听器
	 * 
	 * @author lipin
	 * 
	 */
	public interface OnPositionInfoListener {
		@SuppressWarnings("rawtypes")
		public void received(ActionInvocation invocation,
				PositionInfo positionInfo);

		@SuppressWarnings("rawtypes")
		public void failure(ActionInvocation invocation,
				UpnpResponse operation, String defaultMsg);

		@SuppressWarnings("rawtypes")
		public void success(ActionInvocation invocation);
	}
}
