package com.author.lipin.dlna;

import org.fourthline.cling.android.AndroidUpnpServiceConfiguration;
import org.fourthline.cling.android.AndroidUpnpServiceImpl;
import org.fourthline.cling.model.types.ServiceType;
import org.fourthline.cling.model.types.UDAServiceType;

import com.author.lipin.dlna.constant.IServiceType;

public final class LiUpnpService extends AndroidUpnpServiceImpl {

	protected AndroidUpnpServiceConfiguration createConfiguration() {
		return new AndroidUpnpServiceConfiguration(){
			
			/**
			 * 订制的注册机维护间隔时间
			 */
			@Override
			public int getRegistryMaintenanceIntervalMillis() {
				return 5000;
			}
			
			/**
			 * 订制的配置发现
			 */
			@Override
            public ServiceType[] getExclusiveServiceTypes() {
                return new ServiceType[] { 
                		new UDAServiceType(IServiceType.AVTransport), 
                		new UDAServiceType(IServiceType.ContentDirectory),
                        new UDAServiceType(IServiceType.RenderingControl),
                        new UDAServiceType(IServiceType.ConnectionManager) };
            }
		};
	}
}
