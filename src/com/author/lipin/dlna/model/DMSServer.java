package com.author.lipin.dlna.model;

import java.io.IOException;
import java.util.logging.Logger;

import org.fourthline.cling.binding.LocalServiceBinder;
import org.fourthline.cling.binding.annotations.AnnotationLocalServiceBinder;
import org.fourthline.cling.model.DefaultServiceManager;
import org.fourthline.cling.model.ServiceManager;
import org.fourthline.cling.model.ValidationException;
import org.fourthline.cling.model.meta.DeviceDetails;
import org.fourthline.cling.model.meta.DeviceIdentity;
import org.fourthline.cling.model.meta.LocalDevice;
import org.fourthline.cling.model.meta.LocalService;
import org.fourthline.cling.model.meta.ManufacturerDetails;
import org.fourthline.cling.model.meta.ModelDetails;
import org.fourthline.cling.model.types.DeviceType;
import org.fourthline.cling.model.types.UDADeviceType;

import android.content.Context;

import com.author.lipin.dlna.dms.constant.Constant;
import com.author.lipin.dlna.dms.service.HttpServer;
import com.author.lipin.dlna.dms.support.contentdirectory.LiContentDirectoryService;
import com.author.lipin.dlna.model.AbstractServer;

/**
 * 
 * @author lipin
 * 
 * @version 1.0
 * 
 */
public class DMSServer<A extends LiContentDirectoryService> extends
		AbstractServer {

	protected static final Logger log = Logger.getLogger(DMSServer.class
			.getName());
	
	protected Context context;

	// managers
	/**
	 * 内容目录管理器
	 */
	protected ServiceManager<A> contentDirectoryManager;
	/**
	 * 服务绑定
	 */
	protected final LocalServiceBinder binder = new AnnotationLocalServiceBinder();
	/**
	 * 内容目录服务
	 */
	protected LocalService<A> contentDirectoryService;

	protected Class<A> c1;

	protected A a;
	
	protected String localaddress;
	/**
	 * 服务器提供媒体服务的端口:默认端口号
	 */
	protected int port = Constant.PORT;

	/**
	 * 构建媒体服务器(默认服务器端口:8090)
	 * 
	 * @param context
	 * @param c1
	 * @param a
	 * @param deviceIdentity
	 * @param deviceType
	 * @param deviceDetails
	 * @param ip
	 */
	public DMSServer(Context context, Class<A> c1, A a,
			DeviceIdentity deviceIdentity, DeviceType deviceType, DeviceDetails deviceDetails,
			String ip) {
		this.context = context;
		this.c1 = c1;
		this.a = a;
		this.deviceIdentity = deviceIdentity;
		this.deviceType = deviceType;
		this.deviceDetails = deviceDetails;
		this.localaddress = ip;
		createLocalService();
		createLocalDevice();
		startHttpServer();
	}
	
	/**
	 * 构建媒体服务器(指定服务器端口)
	 * 
	 * @param context
	 * @param c1
	 * @param a
	 * @param deviceIdentity
	 * @param deviceType
	 * @param deviceDetails
	 * @param ip
	 * @param port
	 */
	public DMSServer(Context context, Class<A> c1, A a, DeviceIdentity deviceIdentity,
			DeviceType deviceType, DeviceDetails deviceDetails, String ip, int port) {
		super();
		this.context = context;
		this.c1 = c1;
		this.a = a;
		this.deviceIdentity = deviceIdentity;
		this.deviceType = deviceType;
		this.deviceDetails = deviceDetails;
		this.localaddress = ip;
		this.port = port;
		createLocalService();
		createLocalDevice();
		startHttpServer();
	}

	private void createLocalDevice() {
		if (null == this.udn) {
			this.udn = Constant.UDN_MEDIASERVER;
		}
		if (null == this.deviceIdentity) {
			this.deviceIdentity = new DeviceIdentity(this.udn);
		}
		if (null == this.deviceType) {
			this.deviceType = new UDADeviceType(Constant.DEFAULT_DEVICE_TYPE,
					Constant.DEFAULT_VERSION);
		}
		if (null == this.deviceDetails) {
			this.deviceDetails = new DeviceDetails(android.os.Build.MODEL,
					new ManufacturerDetails(android.os.Build.MANUFACTURER),
					new ModelDetails(Constant.DEFAULT_MODEL_NAME,
							Constant.DEFAULT_MODEL_DESCRIPTION,
							Constant.DEFAULT_MODEL_NUMBER));
		}
		try {
			this.services = new LocalService[] { contentDirectoryService };
			this.localDevice = new LocalDevice(this.deviceIdentity,
					this.deviceType, this.deviceDetails, this.services);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}

	private void startHttpServer() {
		// start http server
		try {
			new HttpServer(port);
		} catch (IOException ioe) {
			System.err.println("Couldn't start server:\n" + ioe);
			System.exit(-1);
		}
	}

	@SuppressWarnings("unchecked")
	private void createLocalService() {
		if (null == c1 || null == a) {
			return;
		}
		contentDirectoryService = binder.read(c1);
		contentDirectoryManager = new DefaultServiceManager<A>(
				contentDirectoryService, c1) {
			@Override
			protected A createServiceInstance() throws Exception {
				return a;
			}
		};
		contentDirectoryService.setManager(contentDirectoryManager);
	}
	
	protected OnImageResPreparedListener imageResPreparedListener;

	public void setImageRes(OnImageResPreparedListener listener) {
		this.imageResPreparedListener = listener;
		this.imageResPreparedListener.onPrepare();
	}

	public interface OnImageResPreparedListener {
		/**
		 * 这个方法实现具体的图片资源操作，为了不阻塞线程最好在子线程中进行
		 * 
		 * @return
		 */
		boolean onPrepare();
	}

	protected OnAudioResPreparedListener audioResPreparedListener;

	public void setAudioRes(OnAudioResPreparedListener listener) {
		this.audioResPreparedListener = listener;
		this.audioResPreparedListener.onPrepare();
	}

	public interface OnAudioResPreparedListener {
		/**
		 * 这个方法实现具体的音频资源操作，为了不阻塞线程最好在子线程中进行
		 * 
		 * @return
		 */
		boolean onPrepare();
	}

	protected OnVideoResPreparedListener videoResPreparedListener;

	public void setVideoRes(OnVideoResPreparedListener listener) {
		this.videoResPreparedListener = listener;
		this.videoResPreparedListener.onPrepare();
	}

	public interface OnVideoResPreparedListener {
		/**
		 * 这个方法实现具体的视频资源操作，为了不阻塞线程最好在子线程中进行
		 * 
		 * @return
		 */
		boolean onPrepare();
	}

	public String getLocaladdress() {
		return localaddress;
	}

	public void setLocaladdress(String localaddress) {
		this.localaddress = localaddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
