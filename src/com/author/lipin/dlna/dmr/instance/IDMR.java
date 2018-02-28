package com.author.lipin.dlna.dmr.instance;

import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.meta.RemoteDevice;
import org.fourthline.cling.registry.Registry;

import com.author.lipin.dlna.bean.DeviceItem;

public interface IDMR {
	/**
	 * 绑定upnp服务
	 */
	public void bind();
	/**
	 * 解绑upnp服务
	 */
	public void unbind();
	/**
	 * dms设备添加
	 * @param device
	 */
	public void dmsAdded(final Device<?, ?, ?> device);
	/**
	 * dms设备移除
	 * @param device
	 */
	public void dmsRemoved(final Device<?, ?, ?> device);
	/**
	 * 将本机设置为DMR
	 */
	public void initMediaRenderer();
	/**
	 * 搜索设备
	 */
	public void searchDevice();
	/**
	 * 远程设备探索开始
	 * @param registry
	 * @param device
	 */
	public void remoteDeviceDiscoveryStarted(Registry registry,
			RemoteDevice device);
	/**
	 * 远程设备探索失败
	 * @param registry
	 * @param device
	 * @param ex
	 */
	public void remoteDeviceDiscoveryFailed(Registry registry,
			RemoteDevice device, Exception ex);
	/**
	 * 远程设备更新
	 * @param registry
	 * @param device
	 */
	public void remoteDeviceUpdated(Registry registry, RemoteDevice device);
	
	public void beforeShutdown(Registry registry);
	
	public void afterShutdown();
	/**
	 * 添加本地DMS的逻辑
	 * @param deviceItem
	 */
	public void localDMSAddedLogic(DeviceItem deviceItem);
	/**
	 * 添加远程DMS的逻辑
	 * @param deviceItem
	 */
	public void remoteDMSAddedLogic(DeviceItem deviceItem);
	/**
	 * 移除本地DMS的逻辑
	 * @param deviceItem
	 */
	public void localDMSRemovedLogic(DeviceItem deviceItem);
	/**
	 * 移除远程DMS的逻辑
	 * @param deviceItem
	 */
	public void remoteDMSRemovedLogic(DeviceItem deviceItem);
	/**
	 * 本地DMR加入LAN
	 */
	public void youCanYouUp();
}
