package com.author.lipin.dlna.dmr.action;

import org.fourthline.cling.model.ModelUtil;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;
import org.fourthline.cling.support.avtransport.lastchange.AVTransportLastChangeParser;
import org.fourthline.cling.support.avtransport.lastchange.AVTransportVariable;
import org.fourthline.cling.support.lastchange.LastChange;
import org.fourthline.cling.support.model.MediaInfo;
import org.fourthline.cling.support.model.PositionInfo;
import org.fourthline.cling.support.model.StorageMedium;
import org.fourthline.cling.support.model.TransportAction;
import org.fourthline.cling.support.model.TransportInfo;
import org.fourthline.cling.support.model.TransportState;
import org.fourthline.cling.support.renderingcontrol.lastchange.RenderingControlLastChangeParser;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

public abstract class AbstractMediaPlayerControl implements IMediaPlayerControl {

	protected static final String TAG = AbstractMediaPlayerControl.class
			.getSimpleName();

	protected final UnsignedIntegerFourBytes instanceId;

	protected final LastChange avTransportLastChange = new LastChange(
			new AVTransportLastChangeParser());

	protected final LastChange renderingControlLastChange = new LastChange(
			new RenderingControlLastChangeParser());

	// We'll synchronize read/writes to these fields
	protected volatile TransportInfo currentTransportInfo = new TransportInfo();

	protected PositionInfo currentPositionInfo = new PositionInfo();

	protected MediaInfo currentMediaInfo = new MediaInfo();

	/**
	 * 存储音量数值,为静音后恢复音量使用
	 */
	protected double storedVolume;

	protected Context context;

	public AbstractMediaPlayerControl(UnsignedIntegerFourBytes instanceId,
			Context context) {
		super();
		this.instanceId = instanceId;
		this.context = context;
	}

	public final void transportStateChanged(TransportState newState) {
		currentTransportInfo = new TransportInfo(newState);

		this.avTransportLastChange.setEventedValue(getInstanceId(),
				new AVTransportVariable.TransportState(newState),
				new AVTransportVariable.CurrentTransportActions(
						getCurrentTransportActions()));
	}

	// 因为我们没有一个自动状态机,我们需要计算出可能的转换
	public final TransportAction[] getCurrentTransportActions() {
		TransportState state = currentTransportInfo.getCurrentTransportState();
		TransportAction[] actions;
		switch (state) {
		case STOPPED:
			actions = new TransportAction[] { TransportAction.Stop };
			break;
		case PLAYING:
			actions = new TransportAction[] { TransportAction.Stop,
					TransportAction.Pause, TransportAction.Seek };
			break;
		case PAUSED_PLAYBACK:
			actions = new TransportAction[] { TransportAction.Stop,
					TransportAction.Pause, TransportAction.Seek,
					TransportAction.Play };
		case PLAY_ERROR:
			actions = new TransportAction[] { TransportAction.PlayError };
		case NO_MEDIA_PRESENT:
			actions = new TransportAction[] { TransportAction.Stop };
			break;
		default:
			actions = null;
		}
		return actions;
	}

	public final void setVolume(double volume) {
		this.storedVolume = getVolume();
		Intent intent = new Intent();
		intent.putExtra(Action.KeyName.VOLUME, volume);
		sendBroadcastAction(intent, Action.Value.SET_VOLUME);
	}

	public final void setMute(boolean desiredMute) {
		if (desiredMute && getVolume() > 0) {// 静音
			setVolume(0);
		} else if (!desiredMute && getVolume() == 0) {// 恢复音量
			setVolume(this.storedVolume);
		}
	}

	public final double getVolume() {
		AudioManager audioManager = (AudioManager) this.context
				.getSystemService(Service.AUDIO_SERVICE);
		double v = (double) audioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC)
				/ audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		return v;
	}

	public final void play() {
		sendBroadcastAction(null, Action.Value.PLAY);
	}

	public final void pause() {
		sendBroadcastAction(null, Action.Value.PAUSE);
	}

	public final void stop() {
		sendBroadcastAction(null, Action.Value.STOP);
	}

	public final void seekTo(int position) {
		Intent intent = new Intent();
		intent.putExtra(Action.KeyName.POSITION, position);
		sendBroadcastAction(intent, Action.Value.SEEK);
	}

	public final void sendBroadcastAction(Intent intent, String action) {
		if (null == intent) {
			intent = new Intent();
		}
		intent.setAction(Action.Value.DMR);
		intent.putExtra(Action.KeyName.EXECUTACTIONMSG, action);
		this.context.sendBroadcast(intent);
	}

	public UnsignedIntegerFourBytes getInstanceId() {
		return instanceId;
	}

	public LastChange getAvTransportLastChange() {
		return avTransportLastChange;
	}

	public LastChange getRenderingControlLastChange() {
		return renderingControlLastChange;
	}

	public TransportInfo getCurrentTransportInfo() {
		return currentTransportInfo;
	}

	public PositionInfo getCurrentPositionInfo() {
		return currentPositionInfo;
	}

	public MediaInfo getCurrentMediaInfo() {
		return currentMediaInfo;
	}

	public class GstMediaListener implements MediaListener {

		public void pause() {
			transportStateChanged(TransportState.PAUSED_PLAYBACK);
		}

		public void start() {
			transportStateChanged(TransportState.PLAYING);
		}

		public void stop() {
			transportStateChanged(TransportState.STOPPED);
		}

		public void endOfMedia() {
			transportStateChanged(TransportState.NO_MEDIA_PRESENT);
		}

		public void positionChanged(int position) {
			synchronized (AbstractMediaPlayerControl.this) {
				currentPositionInfo = new PositionInfo(1,
						currentMediaInfo.getMediaDuration(),
						currentMediaInfo.getCurrentURI(),
						ModelUtil.toTimeString(position / 1000),
						ModelUtil.toTimeString(position / 1000));
			}
		}

		public void durationChanged(int duration) {
			synchronized (AbstractMediaPlayerControl.this) {
				String newValue = ModelUtil.toTimeString(duration / 1000);
				currentMediaInfo = new MediaInfo(
						currentMediaInfo.getCurrentURI(), "",
						new UnsignedIntegerFourBytes(1), newValue,
						StorageMedium.NETWORK);

				avTransportLastChange.setEventedValue(getInstanceId(),
						new AVTransportVariable.CurrentTrackDuration(newValue),
						new AVTransportVariable.CurrentMediaDuration(newValue));
			}
		}

		public void playError() {
			transportStateChanged(TransportState.PLAY_ERROR);
		}
	}
}
