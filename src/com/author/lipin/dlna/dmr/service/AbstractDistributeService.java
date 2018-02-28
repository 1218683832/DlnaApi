package com.author.lipin.dlna.dmr.service;

import com.author.lipin.dlna.constant.IntentParameter;
import com.author.lipin.dlna.constant.MediaType;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public abstract class AbstractDistributeService extends Service {
	/**
	 * 默认资源类型
	 */
	protected String type = MediaType.VIDEO;
	/**
	 * 资源名称
	 */
	protected String name;
	/**
	 * 资源URI地址
	 */
	protected String playURI;
	/**
	 * 资源携带的元数据
	 */
	protected String currentURIMetaData;
	/**
	 * 资源来源
	 */
	protected String source;
	
	protected Intent mIntent = new Intent();

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		if (null == intent) {
			return;
		}
		type = intent.getStringExtra(IntentParameter.TYPE);
		name = intent.getStringExtra(IntentParameter.NAME);
		playURI = intent.getStringExtra(IntentParameter.PLAYURI);
		currentURIMetaData = intent.getStringExtra(IntentParameter.CURRENTURIMETADATA);
		source = intent.getStringExtra(IntentParameter.SOURCE);
		if (null == mIntent) {
			mIntent = new Intent();
		}
		mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mIntent.putExtra(IntentParameter.NAME, name);
		mIntent.putExtra(IntentParameter.TYPE, type);
		mIntent.putExtra(IntentParameter.PLAYURI, playURI);
		mIntent.putExtra(IntentParameter.CURRENTURIMETADATA, currentURIMetaData);
		mIntent.putExtra(IntentParameter.SOURCE, source);
		switch (type) {
		case MediaType.VIDEO:
			startVideoActivity(mIntent);
			break;
		case MediaType.AUDIO:
			startAudioActivity(mIntent);
			break;
		case MediaType.IMAGE:
			startImageActivity(mIntent);
			break;
		}
	}

	/**
	 * 启动图片浏览Activity
	 * 
	 * @param intent
	 */
	protected abstract void startImageActivity(Intent intent);

	/**
	 * 启动音乐播放器Activity
	 * 
	 * @param intent
	 */
	protected abstract void startAudioActivity(Intent intent);

	/**
	 * 启动视频播放器Activity
	 * 
	 * @param intent
	 */
	protected abstract void startVideoActivity(Intent intent);

}
