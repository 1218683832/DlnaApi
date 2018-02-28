package com.author.lipin.dlna.dms.constant;

import org.fourthline.cling.model.types.UDN;

public abstract class Constant {
	/**
	 * 服务器提供媒体服务的端口:默认端口号
	 */
	public final static int PORT = 8090;
	/**
	 * 默认的mediaServer udn 字符串
	 */
	public final static String DEFAULT_SALT = "LP_MediaServer";
	/**
	 * 默认的mediaserver udn
	 */
	public final static UDN UDN_MEDIASERVER = UDN
			.uniqueSystemIdentifier(Constant.DEFAULT_SALT);
	/**
	 * 默认服务(设备)类型-MediaServer
	 */
	public final static String DEFAULT_DEVICE_TYPE = "MediaServer";
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
	public final static String DEFAULT_MODEL_DESCRIPTION = "LPClingDLNA MediaServer for Android";
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
