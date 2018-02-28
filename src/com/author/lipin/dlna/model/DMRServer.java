package com.author.lipin.dlna.model;

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
import org.fourthline.cling.support.avtransport.lastchange.AVTransportLastChangeParser;
import org.fourthline.cling.support.lastchange.LastChangeAwareServiceManager;
import org.fourthline.cling.support.renderingcontrol.lastchange.RenderingControlLastChangeParser;

import com.author.lipin.dlna.dmr.action.AbstractMediaPlayerControl;
import com.author.lipin.dlna.dmr.constant.Constant;
import com.author.lipin.dlna.dmr.support.avtransport.AbstractLiAVTransportService;
import com.author.lipin.dlna.dmr.support.connectionmanager.LiConnectionManagerService;
import com.author.lipin.dlna.dmr.support.renderingcontrol.LiAudioRenderingControlService;

import android.content.Context;

/**
 * 
 * @author lipin
 * 
 * @version 1.0
 * 
 */
public class DMRServer<A extends LiConnectionManagerService, B extends AbstractLiAVTransportService, C extends LiAudioRenderingControlService>
		extends AbstractServer {

	protected static final Logger log = Logger.getLogger(DMRServer.class
			.getName());

	protected long LAST_CHANGE_FIRING_INTERVAL_MILLISECONDS = 2000;

	protected boolean isRunLastChangePushThread = false;

	protected Context context;

	protected AbstractMediaPlayerControl playerControl;
	/**
	 * 服务绑定
	 */
	protected final LocalServiceBinder binder = new AnnotationLocalServiceBinder();

	// managers
	/**
	 * 连接管理服务管理器
	 */
	protected ServiceManager<A> connectionManager;

	/**
	 * AV传输服务管理器
	 */
	protected LastChangeAwareServiceManager<B> avTransportManager;

	/**
	 * 渲染控制服务管理器
	 */
	protected LastChangeAwareServiceManager<C> renderingControlManager;

	// services
	/**
	 * 连接管理服务
	 */
	protected LocalService<A> connectionManagerService;
	/**
	 * AV传输服务
	 */
	protected LocalService<B> avTransportService;
	/**
	 * 渲染控制服务
	 */
	protected LocalService<C> renderingControlService;

	protected Class<A> c1;

	protected Class<B> c2;

	protected Class<C> c3;

	protected A a;

	protected B b;

	protected C c;

	/**
	 * 构建媒体渲染器
	 * 
	 * @param c1
	 * @param c2
	 * @param c3
	 * @param a
	 * @param b
	 * @param c
	 * @param deviceIdentity
	 * @param deviceType
	 * @param deviceDetails
	 */
	public DMRServer(Class<A> c1, Class<B> c2, Class<C> c3, A a, B b, C c,
			DeviceIdentity deviceIdentity, DeviceType deviceType,
			DeviceDetails deviceDetails) {
		super();
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.a = a;
		this.b = b;
		this.c = c;
		this.deviceIdentity = deviceIdentity;
		this.deviceType = deviceType;
		this.deviceDetails = deviceDetails;
		createLoaclServices();
		createLocalDevice();
		// runLastChangePushThread();
	}

	private void createLocalDevice() {
		if (null == this.udn) {
			this.udn = Constant.UDN_RENDERER;
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
			this.services = new LocalService[] { connectionManagerService,
					avTransportService, renderingControlService };
			localDevice = new LocalDevice(this.deviceIdentity, this.deviceType,
					this.deviceDetails, this.services);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void createLoaclServices() {
		try {
			// The connection manager doesn't have to do much, HTTP is stateless
			connectionManagerService = binder.read(c1);
			connectionManager = new DefaultServiceManager<A>(
					connectionManagerService, c1) {
				@Override
				protected A createServiceInstance() throws Exception {
					return a;
				}
			};
			connectionManagerService.setManager(connectionManager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// try {
		// The AVTransport just passes the calls on to the backend players
		avTransportService = binder.read(c2);
		avTransportManager = new LastChangeAwareServiceManager<B>(
				avTransportService, new AVTransportLastChangeParser()) {
			@Override
			protected B createServiceInstance() throws Exception {
				return b;
			}
		};
		avTransportService.setManager(avTransportManager);
		try {
			renderingControlService = binder.read(c3);
			renderingControlManager = new LastChangeAwareServiceManager<C>(
					renderingControlService,
					new RenderingControlLastChangeParser()) {
				@Override
				protected C createServiceInstance() throws Exception {
					return c;
				}
			};
			renderingControlService.setManager(renderingControlManager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// The backend player instances will fill the LastChange whenever something
	// happens with
	// whatever event messages are appropriate. This loop will periodically
	// flush these changes
	// to subscribers of the LastChange state variable of each service.
	protected void runLastChangePushThread() {
		// We should only run this if we actually have event subscribers
		new Thread() {
			@Override
			public void run() {
				try {
					while (true) {
						// These operations will NOT block and wait for network
						// responses
						avTransportManager.fireLastChange();
						renderingControlManager.fireLastChange();
						Thread.sleep(LAST_CHANGE_FIRING_INTERVAL_MILLISECONDS);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			}
		}.start();
	}
}
