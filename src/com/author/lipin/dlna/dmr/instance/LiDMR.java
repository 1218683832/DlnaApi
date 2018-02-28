package com.author.lipin.dlna.dmr.instance;

import java.util.ArrayList;

import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.meta.LocalDevice;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;

import com.author.lipin.dlna.bean.DeviceItem;
import com.author.lipin.dlna.dmr.action.LiMediaPlayerControl;
import com.author.lipin.dlna.dmr.support.avtransport.LiAVTransportService;
import com.author.lipin.dlna.dmr.support.connectionmanager.LiConnectionManagerService;
import com.author.lipin.dlna.dmr.support.renderingcontrol.LiAudioRenderingControlService;
import com.author.lipin.dlna.model.DMRServer;

import android.app.Activity;
import android.content.Context;

public class LiDMR extends AbsDMR {
	/**
	 * 搜索到的设备列表
	 */
	protected ArrayList<DeviceItem> deviceList = new ArrayList<DeviceItem>();

	private static LiDMR instance;// 导致重启搜索不到的问题点?

	public static LiDMR getInstance(Activity activity) {
		if (null == instance) {
			instance = new LiDMR(activity);
		}
		return instance;
	}

	private LiDMR(Context context) {
		super(context);
	}

	@Override
	public void initMediaRenderer() {
		if (this.dmrServer == null) {
			try {
				LiMediaPlayerControl player = new LiMediaPlayerControl(
						new UnsignedIntegerFourBytes(0), this.mContext);
				/**
				 * 最后三位参数可以不指定
				 */
				this.dmrServer = new DMRServer<LiConnectionManagerService, LiAVTransportService, LiAudioRenderingControlService>(
						LiConnectionManagerService.class,
						LiAVTransportService.class,
						LiAudioRenderingControlService.class,
						new LiConnectionManagerService(),
						new LiAVTransportService(player),
						new LiAudioRenderingControlService(player), null, null,
						null);
			} catch (Exception ex) {
			}
		}
	}

	@Override
	public void youCanYouUp() {
		// TODO Auto-generated method stub
		// 在这里加入你的逻辑处理

		// Clear the list
		if (null != deviceList) {
			deviceList.clear();
		}
		// 本地DMR设备加入局域网中
		if (null != this.dmrServer) {
			LocalDevice localMediaRendererDevice = this.dmrServer.getDevice();
			if (localMediaRendererDevice != null) {
				this.upnpService.getRegistry().addDevice(
						localMediaRendererDevice);
			}
		}
		// Get ready for future device advertisements
		upnpService.getRegistry().addListener(deviceRegistryListener);
		// Now add all devices to the list we already know about. Add LAN all
		// DMS devices
		for (Device<?, ?, ?> device : upnpService.getRegistry().getDevices()) {
			dmsAdded(device);
		}
	}

	@Override
	public void localDMSAddedLogic(DeviceItem deviceItem) {
		// TODO Auto-generated method stub
		// 在这里实现你的逻辑处理
	}

	@Override
	public void remoteDMSAddedLogic(DeviceItem deviceItem) {
		// TODO Auto-generated method stub
		// 在这里实现你的逻辑处理
	}

	@Override
	public void localDMSRemovedLogic(DeviceItem deviceItem) {
		// TODO Auto-generated method stub
		// 在这里实现你的逻辑处理
	}

	@Override
	public void remoteDMSRemovedLogic(DeviceItem deviceItem) {
		// TODO Auto-generated method stub
		// 在这里实现你的逻辑处理
	}
}
