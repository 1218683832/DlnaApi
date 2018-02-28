package com.author.lipin.dlna.dmp.player;

public interface IMediaController {

	/**
	 * 播放器UI方面控制接口,如:显隐控制栏、更新播放进度、更新播放时间文本等 由承接播放器的Activity或Fragment实现
	 * 
	 * @author lipin
	 * 
	 */
	public interface UIControl {
		
		/**
		 * UI：隐藏控制栏
		 */
		public static final int UI_HIDE_CONTROL_VIEW = 1000;
		/**
		 * 默认指定延迟5秒后隐藏控制栏
		 */
		public static int DEFAULT_DELAYED_UI_HIDE_CONTROL_VIEW = 5 * 1000;
		/**
		 * UI：显示控制栏
		 */
		public static final int UI_SHOW_CONTROL_VIEW = 1001;

		/**
		 * UI：更新播放进度和播放时间文本
		 */
		public static final int UI_UPDATE_PLAYBACK_PROGRESS_AND_TIMETEXT = 1002;
		/**
		 * 默认指定延迟1秒后更新播放进度
		 */
		public static int DEFAULT_DELAYED_UI_UPDATE_PLAYBACK_PROGRESS = 2 * 500;

		/**
		 * UI：更新播放暂停按钮状态
		 */
		public static final int UI_UPDATE_PAUSE_OR_PLAY = 1003;
		
		/**
		 * UI：播放出错
		 */
		public static final int UI_MEDIA_PLAYER_ERROR = 1004;
		
		/**
		 * UI：播放出错
		 */
		public static final int UI_MEDIA_UNSUPORTED_FORMAT = 1005;

		/**
		 * 显示控制栏
		 */
		public void ui_ShowControl();

		/**
		 * 隐藏控制栏
		 */
		public void ui_HideControl();

		/**
		 * 更新播放进度
		 */
		public void ui_UpdateProgress();
		
		/**
		 * 重置播放进度
		 */
		public void ui_ResetProgress();

		/**
		 * 更新播放时间文本
		 */
		public void ui_UpdateTimeText();
		
		/**
		 * 重置播放时间文本
		 */
		public void ui_ResetTimeText();

		/**
		 * 更新播放暂停按钮状态
		 */
		public void ui_UpdatePauseOrPlay();

		/**
		 * 开始缓冲
		 */
		public void ui_StartBuffer();

		/**
		 * 结束缓冲
		 */
		public void ui_EndBuffer();

		/**
		 * 缓冲中
		 * 
		 * @param rate
		 *            下载比率
		 */
		public void ui_BufferingRate(int rate);

		/**
		 * 缓冲中
		 * 
		 * @param percent
		 *            缓冲百分比
		 */
		public void ui_BufferingPercent(int percent);

	}

	/**
	 * 播放器功能方面控制接口,如:播放、暂停、停止等; 由承接播放器的Activity或Fragment实现
	 * 
	 * @author lipin
	 * 
	 */
	public interface FunctionControl {

		/**
		 * Function：音量调节(+)
		 */
		public static final int VOLUME_UP = 2000;

		/**
		 * Function：音量调节(-)
		 */
		public static final int VOLUME_DOWN = 2001;

		/**
		 * 默认音量调节间隔：数值 1
		 */
		public static final int DEFAULT_VOLUME_INTERVAL = 1;

		/**
		 * 初始化播放器
		 */
		public void function_InitPlayer();

		/**
		 * 退出并销毁播放器
		 */
		public void function_DestroyPlayer();

		/**
		 * 开始播放
		 */
		public void function_StartPlay();

		/**
		 * 暂停播放
		 */
		public void function_PausePlay();

		/**
		 * 停止播放
		 */
		public void function_StopPlay();

		/**
		 * 播放定位
		 * 
		 * @param msec
		 */
		public void function_SeekTo(int msec);

		/**
		 * 是否正在播放
		 * 
		 * @return
		 */
		public boolean function_IsPlaying();

		/**
		 * 调节音量
		 * 
		 * @param upOrDown
		 *            (指定音量加减调节)
		 * @param interval
		 *            (指定音量调节间隔)
		 */
		public void function_AdjustVolume(int upOrDown, int interval);

		/**
		 * 调节音量
		 * 
		 * @param volume
		 *            (指定音量大小调节)
		 */
		public void function_AdjustVolume(int volume);

		/**
		 * 停止播放
		 */
		public void function_CompletePlay();
	}
}
