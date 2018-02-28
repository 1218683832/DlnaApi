package com.author.lipin.dlna.dmr.service;

import android.content.Intent;
import android.os.IBinder;

public class LiRenderPlayerService extends AbstractDistributeService {

	protected static final String TAG = LiRenderPlayerService.class
			.getSimpleName();

	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	protected void startImageActivity(Intent intent) {
		// TODO Auto-generated method stub
		// 在这里实现你的逻辑处理
	}

	@Override
	protected void startAudioActivity(Intent intent) {
		// TODO Auto-generated method stub
		// 在这里实现你的逻辑处理
	}

	@Override
	protected void startVideoActivity(Intent intent) {
		// TODO Auto-generated method stub
		// 在这里实现你的逻辑处理
	}
}
