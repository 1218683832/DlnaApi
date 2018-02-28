package com.author.lipin.dlna.model;

import java.util.logging.Logger;

import org.fourthline.cling.model.meta.DeviceDetails;
import org.fourthline.cling.model.meta.DeviceIdentity;
import org.fourthline.cling.model.meta.LocalDevice;
import org.fourthline.cling.model.meta.LocalService;
import org.fourthline.cling.model.types.DeviceType;
import org.fourthline.cling.model.types.UDN;

/**
 * 抽象服务类 子类有DMSServer、DMRServer
 * 
 * @author lipin
 * 
 */
public abstract class AbstractServer {

	protected static final Logger log = Logger.getLogger(AbstractServer.class
			.getName());

	protected UDN udn;

	protected DeviceIdentity deviceIdentity;

	protected DeviceType deviceType;

	protected DeviceDetails deviceDetails;

	/**
	 * 提供一种或多种服务的本地设备
	 */
	protected LocalDevice localDevice;

	/**
	 * 设备可提供的服务列表
	 */
	@SuppressWarnings("rawtypes")
	protected LocalService[] services;

	public UDN getUdn() {
		return udn;
	}

	public DeviceIdentity getDeviceIdentity() {
		return deviceIdentity;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public DeviceDetails getDeviceDetails() {
		return deviceDetails;
	}

	public LocalDevice getDevice() {
		return localDevice;
	}

	@SuppressWarnings("rawtypes")
	public LocalService[] getServices() {
		return services;
	}
}
