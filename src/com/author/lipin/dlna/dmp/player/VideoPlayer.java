package com.author.lipin.dlna.dmp.player;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.Surface;
import android.view.SurfaceHolder;

public class VideoPlayer implements IMediaPlayer {

	protected MediaPlayer mediaPlayer;

	PlayerState currentPlayerState = PlayerState.IDLE;

	protected boolean isPlaying = false;

	public VideoPlayer(Context context) {
		super();
		this.mediaPlayer = new MediaPlayer();
		this.currentPlayerState = PlayerState.IDLE;
	}

	@Override
	public void setPlaySource(String path) throws Throwable {
		if (null != this.mediaPlayer) {
			this.mediaPlayer.reset();
			this.isPlaying = false;
			this.mediaPlayer.setDataSource(path);
			this.mediaPlayer.prepare();
		}
	}

	@Override
	public void setPlaySourceAsync(final String path) throws Throwable {
		if (null != mediaPlayer) {
			this.mediaPlayer.reset();
			this.isPlaying = false;
			this.mediaPlayer.setDataSource(path);
			this.mediaPlayer.prepareAsync();
			this.currentPlayerState = PlayerState.PREPARING;
		}
	}

	@Override
	public void exitPlayer() {
		this.stopPlay();
		if (null != this.mediaPlayer) {
			this.mediaPlayer.release();
			this.currentPlayerState = PlayerState.IDLE;
		}
	}

	@Override
	public void startPlay() {
		if (null != this.mediaPlayer
				&& (this.currentPlayerState.equals(PlayerState.PREPARED)
						|| this.currentPlayerState.equals(PlayerState.PAUSED) || this.currentPlayerState
							.equals(PlayerState.COMPLETED))) {
			this.mediaPlayer.start();
			this.isPlaying = true;
			this.currentPlayerState = PlayerState.PLAYING;
		}
	}

	@Override
	public void pausePlay() {
		if (null != this.mediaPlayer
				&& this.currentPlayerState.equals(PlayerState.PLAYING)) {
			this.mediaPlayer.pause();
			this.isPlaying = false;
			this.currentPlayerState = PlayerState.PAUSED;
		}
	}

	@Override
	public int getDuration() {
		int duration = 0;
		if (null != this.mediaPlayer) {
			duration = (int) this.mediaPlayer.getDuration();
		}
		return duration;
	}

	@Override
	public int getCurrentPosition() {
		int currentPosition = 0;
		if (null != this.mediaPlayer) {
			currentPosition = (int) this.mediaPlayer.getCurrentPosition();
		}
		return currentPosition;
	}

	@Override
	public void seekTo(int msec) {
		if (null != this.mediaPlayer
				&& (this.currentPlayerState.equals(PlayerState.PREPARED))
				|| this.currentPlayerState.equals(PlayerState.PLAYING)
				|| this.currentPlayerState.equals(PlayerState.PAUSED)
				|| this.currentPlayerState.equals(PlayerState.COMPLETED)) {
			this.mediaPlayer.seekTo(msec);
		}
	}

	@Override
	public boolean isPlaying() {
		return this.isPlaying;
	}

	@Override
	public void stopPlay() {
		if (null != this.mediaPlayer
				&& (this.currentPlayerState.equals(PlayerState.PREPARED) || this.currentPlayerState
						.equals(PlayerState.PLAYING))
				|| this.currentPlayerState.equals(PlayerState.PAUSED)
				|| this.currentPlayerState.equals(PlayerState.COMPLETED)) {
			this.mediaPlayer.stop();
			this.isPlaying = false;
			this.currentPlayerState = PlayerState.STOP;
		}
	}

	@Override
	public void setDisplay(SurfaceHolder holder) {
		if (null != this.mediaPlayer) {
			this.mediaPlayer.setDisplay(holder);
		}
	}

	public MediaPlayer getPlayer() {
		return this.mediaPlayer;
	}

	@Override
	public int getVideoWidth() {
		int videoWidth = 0;
		if (null != this.mediaPlayer) {
			videoWidth = this.mediaPlayer.getVideoWidth();
		}
		return videoWidth;
	}

	@Override
	public int getVideoHeight() {
		int videoHeight = 0;
		if (null != this.mediaPlayer) {
			videoHeight = this.mediaPlayer.getVideoHeight();
		}
		return videoHeight;
	}

	@Override
	public void setSurface(Surface surface) {
		if (null != this.mediaPlayer) {
			this.mediaPlayer.setSurface(surface);
		}
	}

	@Override
	public void setAudioStreamType(int streamtype) {
		if (null != this.mediaPlayer) {
			this.mediaPlayer.setAudioStreamType(streamtype);
		}
	}

	public PlayerState getPlayerState() {
		return this.currentPlayerState;
	}

	public void setPlayerState(PlayerState state) {
		this.currentPlayerState = state;
	}
}
