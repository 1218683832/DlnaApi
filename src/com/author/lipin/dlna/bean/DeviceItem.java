package com.author.lipin.dlna.bean;

import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.types.UDN;

/**
 * 
 * @author lipin
 * 
 * @version 1.0
 * 
 */
public class DeviceItem {
	
	private UDN udn;
	
	private Device<?, ?, ?> device;
	
	private String ip;
	
	public String getIp() {
		return ip;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		DeviceItem that = (DeviceItem) o;
		if (!this.udn.equals(that.udn))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		return this.udn.hashCode();
	}

	@Override
	public String toString() {
		String name;
		if (this.device.getDetails().getFriendlyName() != null)
			name = this.device.getDetails().getFriendlyName();
		else
			name = this.device.getDisplayString();
	    // Display a little star while the device is being loaded (see performance optimization earlier)
		return this.device.isFullyHydrated() ? name : name + " *";
	}

	public UDN getUdn() {
		return udn;
	}

	public void setUdn(UDN udn) {
		this.udn = udn;
	}

	public Device<?, ?, ?> getDevice() {
		return device;
	}
	
	public void setDevice(Device<?, ?, ?> device) {
		this.device = device;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}

	public DeviceItem(Device<?, ?, ?> device) {
		super();
		if (null != device) {
			this.device = device;
		}
		if (null != device.getIdentity()) {
			this.udn = device.getIdentity().getUdn();
		}
	}
}
