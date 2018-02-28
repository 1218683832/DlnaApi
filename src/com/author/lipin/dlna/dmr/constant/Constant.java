package com.author.lipin.dlna.dmr.constant;

import org.fourthline.cling.model.types.UDN;

public abstract class Constant {
	/**
	 * 默认的renderer udn 字符串
	 */
	public final static String DEFAULT_SALT = "LP_MediaRenderer";
	/**
	 * 默认的renderer udn
	 */
	public final static UDN UDN_RENDERER = UDN
			.uniqueSystemIdentifier(Constant.DEFAULT_SALT);
	/**
	 * 默认服务(设备)类型-MediaRenderer
	 */
	public final static String DEFAULT_DEVICE_TYPE = "MediaRenderer";
	/**
	 * 默认的服务(设备)版本
	 */
	public final static int DEFAULT_VERSION = 1;
	/**
	 * 默认的模型名称
	 */
	public final static String DEFAULT_MODEL_NAME = "LPClingDLNA";
	/**
	 * 默认的模型描述
	 */
	public final static String DEFAULT_MODEL_DESCRIPTION = "LPClingDLNA MediaRenderer for Android";
	/**
	 * 默认的模型型号
	 */
	public final static String DEFAULT_MODEL_NUMBER = "v1";
	/**
	 * 搜到服务(设备)的默认友好名称 Android 使用建议替换为android.os.Build.MODE
	 */
	public final static String DEFAULT_FRIENDLY_NAME = "友好名称";
	/**
	 * 默认服务(设备)命名空间
	 */
	public final static String DEFAULT_DEVICE_NAMESPACE = "schemas-upnp-org";
}
