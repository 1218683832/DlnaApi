package com.author.lipin.dlna.dmr.instance;

import org.fourthline.cling.android.AndroidUpnpService;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.meta.LocalDevice;
import org.fourthline.cling.model.meta.RemoteDevice;
import org.fourthline.cling.model.types.DeviceType;
import org.fourthline.cling.registry.DefaultRegistryListener;
import org.fourthline.cling.registry.Registry;

import com.author.lipin.dlna.LiUpnpService;
import com.author.lipin.dlna.bean.DeviceItem;
import com.author.lipin.dlna.dms.constant.Constant;
import com.author.lipin.dlna.model.DMRServer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.StrictMode;

public abstract class AbsDMR implements IDMR {

	protected Context mContext;
	/**
	 * 安卓upnp服务
	 */
	protected AndroidUpnpService upnpService;
	/**
	 * DMR服务设备
	 */
	protected DMRServer<?, ?, ?> dmrServer;
	/**
	 * 设备注册监听器
	 */
	protected DefaultRegistryListener deviceRegistryListener;
	/**
	 * 服务连接
	 */
	private ServiceConnection serviceConnection;

	/**
	 * 需要手动初始化其他属性
	 * 
	 * @param context
	 */
	public AbsDMR(Context context) {
		super();
		this.mContext = context;
		// 1、继承DefaultRegistryListener并实现它
		this.deviceRegistryListener = new DeviceRegistryListener();
		// 2、连接服务ServiceConnection
		this.serviceConnection = new ServiceConnection() {

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// 服务启动成功!
				upnpService = (AndroidUpnpService) service;
				if (null == upnpService) {
					// 服务启动失败!
					return;
				}
				// 3、将本机设置为DMR
				initMediaRenderer();
				// 4、本地DMR加入LAN
				youCanYouUp();
				// 5、搜索设备
				searchDevice();
			}

			@Override
			public void onServiceDisconnected(ComponentName name) {
				// 服务启动失败!
				upnpService = null;
			}
		};
	}

	/**
	 * 搜索设备
	 */
	@Override
	public final void searchDevice() {
		// Search asynchronously for all devices, they will respond soon
		upnpService.getControlPoint().search();
		// upnpService.getControlPoint().search(15);
	}

	/**
	 * 绑定upnp服务
	 */
	@Override
	public final void bind() {
		this.mContext.getApplicationContext().bindService(
				new Intent(this.mContext, LiUpnpService.class),
				serviceConnection, Context.BIND_AUTO_CREATE);
	}

	/**
	 * 解绑upnp服务
	 */
	@Override
	public final void unbind() {
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		if (upnpService != null) {
			upnpService.getRegistry().removeAllRemoteDevices();
		}
		// 加上这句可以让搜到的设备知道你退出了
		this.mContext.getApplicationContext().unbindService(serviceConnection);
	}

	private class DeviceRegistryListener extends DefaultRegistryListener {

		@Override
		public void remoteDeviceDiscoveryStarted(Registry registry,
				RemoteDevice device) {
			AbsDMR.this.remoteDeviceDiscoveryStarted(registry, device);
			super.remoteDeviceDiscoveryStarted(registry, device);
		}

		@Override
		public void remoteDeviceDiscoveryFailed(Registry registry,
				RemoteDevice device, Exception ex) {
			AbsDMR.this.remoteDeviceDiscoveryFailed(registry, device, ex);
			super.remoteDeviceDiscoveryFailed(registry, device, ex);
		}

		@Override
		public void remoteDeviceUpdated(Registry registry, RemoteDevice device) {
			AbsDMR.this.remoteDeviceUpdated(registry, device);
			super.remoteDeviceUpdated(registry, device);
		}

		@Override
		public void remoteDeviceAdded(Registry registry, RemoteDevice device) {
			AbsDMR.this.dmsAdded(device);
			super.remoteDeviceAdded(registry, device);
		}

		@Override
		public void remoteDeviceRemoved(Registry registry, RemoteDevice device) {
			AbsDMR.this.dmsRemoved(device);
			super.remoteDeviceRemoved(registry, device);
		}

		@Override
		public void localDeviceAdded(Registry registry, LocalDevice device) {
			AbsDMR.this.dmsAdded(device);
			super.localDeviceAdded(registry, device);
		}

		@Override
		public void localDeviceRemoved(Registry registry, LocalDevice device) {
			AbsDMR.this.dmsRemoved(device);
			super.localDeviceRemoved(registry, device);
		}

		@Override
		public void beforeShutdown(Registry registry) {
			AbsDMR.this.beforeShutdown(registry);
			super.beforeShutdown(registry);
		}

		@Override
		public void afterShutdown() {
			AbsDMR.this.afterShutdown();
			super.afterShutdown();
		}
	}

	@Override
	public void remoteDeviceDiscoveryStarted(Registry registry,
			RemoteDevice device) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remoteDeviceDiscoveryFailed(Registry registry,
			RemoteDevice device, Exception ex) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remoteDeviceUpdated(Registry registry, RemoteDevice device) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeShutdown(Registry registry) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterShutdown() {
		// TODO Auto-generated method stub
	}

	@Override
	public final void dmsAdded(final Device<?, ?, ?> device) {
		if (null == device) {
			return;
		}
		DeviceType deviceType = device.getType();
		if (null == deviceType) {
			return;
		}
		// 对设备类型进行过滤
		if (deviceType.getNamespace().equals(Constant.DEFAULT_DEVICE_NAMESPACE)
				&& deviceType.getType().equals(Constant.DEFAULT_DEVICE_TYPE)) {
			DeviceItem deviceItem = new DeviceItem(device);
			if (device instanceof RemoteDevice) {
				remoteDMSAddedLogic(deviceItem);
			} else if (device instanceof LocalDevice) {
				localDMSAddedLogic(deviceItem);
			}
		}
	}

	@Override
	public final void dmsRemoved(final Device<?, ?, ?> device) {
		if (null == device) {
			return;
		}
		DeviceType deviceType = device.getType();
		if (null == deviceType) {
			return;
		}
		// 对设备类型进行过滤
		if (deviceType.getNamespace().equals(Constant.DEFAULT_DEVICE_NAMESPACE)
				&& deviceType.getType().equals(Constant.DEFAULT_DEVICE_TYPE)) {
			DeviceItem deviceItem = new DeviceItem(device);
			if (device instanceof RemoteDevice) {
				remoteDMSRemovedLogic(deviceItem);
			} else if (device instanceof LocalDevice) {
				localDMSRemovedLogic(deviceItem);
			}
		}
	}
}
