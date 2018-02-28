package com.author.lipin.dlna.dmp.broadcast;

import com.author.lipin.dlna.dmr.action.Action;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public abstract class ActionPlayerBroadcastReceiver extends BroadcastReceiver {

	private String executAction;

	protected Intent mIntent;

	@Override
	public void onReceive(Context context, Intent intent) {
		this.mIntent = intent;
		if (null == this.mIntent) {
			return;
		}
		executAction = this.mIntent
				.getStringExtra(Action.KeyName.EXECUTACTIONMSG);
		if (null == executAction) {
			return;
		}
		switch (executAction) {
		case Action.Value.PLAY:// 播放
			playAction();
			break;
		case Action.Value.PAUSE:// 暂停
			pauseAction();
			break;
		case Action.Value.SEEK:// 定位
			seekAction();
			break;
		case Action.Value.SET_VOLUME:// 调节音量
			setVolumeActon();
			break;
		case Action.Value.STOP:// 停止播放
			stopAction();
			break;
		}
	}

	/**
	 * 停止播放
	 */
	protected abstract void stopAction();

	/**
	 * 调节音量
	 */
	protected abstract void setVolumeActon();

	/**
	 * 定位
	 */
	protected abstract void seekAction();

	/**
	 * 暂停
	 */
	protected abstract void pauseAction();

	/**
	 * 播放
	 */
	protected abstract void playAction();

}
