package com.author.lipin.dlna.dmp.player;

import android.view.Surface;
import android.view.SurfaceHolder;

/**
 * 播放器接口
 * 
 * @author lipin
 * 
 */
public interface IMediaPlayer {
	
	public interface Error {
		/**
		 * Unspecified media player error.
		 * 未知错误
		 * 1. -2147483648 未知错误
		 * 例如log输出：
		 * MediaPlayer: error (1, -2147483648)
		 * error的第一个参数1表示未知错误
		 */
		public static final int MEDIA_ERROR_UNKNOWN = 1;
		
		/**
		 * -107 网络链接错误
		 * 例如log输出：
		 * MediaPlayer: error (1, -107)
		 */
		public static final int MEDIA_ERROR_NETWORK_LINK = -107;
	}
	
	public interface Info {
		/**
		 * MediaPlayer is temporarily pausing playback internally in order to buffer
		 * more data.
		 * 
		 * PlayerState.BUFFERING
		 */
		public static final int MEDIA_INFO_BUFFERING_START = 701;

		/**
		 * MediaPlayer is resuming playback after filling buffers.
		 * 
		 * PlayerState.PLAYING
		 */
		public static final int MEDIA_INFO_BUFFERING_END = 702;
		
		/**
		 * 媒体信息的网络带宽
		 * 第二个数值表示当前下载带宽
		 * 比如：1Mb/s
		 * 1Mb/s=1024*1024b/s=1024Kb/s=1024/8KB/s=128KB/s
		 */
		public static final int MEDIA_INFO_NETWORK_BANDWIDTH = 703;
		
		/**
		 * 媒体信息缓冲中
		 * 第二个数值表示当前缓冲比率
		 */
		public static final int MEDIA_INFO_BUFFERING_ING = 704;
		
		/**
		 * PlayerState.PLAYING
		 */
		public static final int MEDIA_INFO_3 = 3;
	}
	
	/**
	 * 设置播放源
	 * 
	 * @param path
	 * @throws Throwable
	 */
	void setPlaySource(String path) throws Throwable;

	/**
	 * 设置播放源
	 * 
	 * @param path
	 * @throws Throwable
	 */
	void setPlaySourceAsync(String path) throws Throwable;

	/**
	 * 退出播放器
	 * 释放播放器资源
	 */
	void exitPlayer();

	/**
	 * 开始播放
	 */
	void startPlay();

	/**
	 * 暂停播放
	 */
	void pausePlay();

	/**
	 * 停止播放
	 */
	void stopPlay();

	/**
	 * 获取播放时长
	 * 
	 * @return
	 */
	int getDuration();

	/**
	 * 获取当前播放时间
	 * 
	 * @return
	 */
	int getCurrentPosition();

	/**
	 * 播放定位
	 * 
	 * @param msec
	 */
	void seekTo(int msec);

	/**
	 * 是否正在播放
	 * 
	 * @return
	 */
	boolean isPlaying();

	void setDisplay(SurfaceHolder surfaceHolder);

	int getVideoWidth();

	int getVideoHeight();

	void setSurface(Surface surface);

	void setAudioStreamType(int streamtype);
}
