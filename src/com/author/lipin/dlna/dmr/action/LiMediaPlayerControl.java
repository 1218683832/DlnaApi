package com.author.lipin.dlna.dmr.action;

import java.net.URI;

import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;
import org.fourthline.cling.support.avtransport.lastchange.AVTransportVariable;
import org.fourthline.cling.support.model.MediaInfo;
import org.fourthline.cling.support.model.PositionInfo;
import org.fourthline.cling.support.model.TransportState;

import com.author.lipin.dlna.constant.IntentParameter;
import com.author.lipin.dlna.constant.MediaType;
import com.author.lipin.dlna.dmr.service.LiRenderPlayerService;

import android.content.Context;
import android.content.Intent;

public class LiMediaPlayerControl extends AbstractMediaPlayerControl {

	protected static final String TAG = LiMediaPlayerControl.class
			.getSimpleName();

	public LiMediaPlayerControl(UnsignedIntegerFourBytes instanceId,
			Context context) {
		super(instanceId, context);
	}

	@Override
	public void setURI(URI uri, String type, String name,
			String currentURIMetaData, String source) {
		// TODO Auto-generated method stub
		// 在这里实现你的逻辑处理

		currentMediaInfo = new MediaInfo(uri.toString(), currentURIMetaData);
		currentPositionInfo = new PositionInfo(1, "", uri.toString());

		this.avTransportLastChange.setEventedValue(getInstanceId(),
				new AVTransportVariable.AVTransportURI(uri),
				new AVTransportVariable.CurrentTrackURI(uri));

		transportStateChanged(TransportState.STOPPED);
		if (type.equals(MediaType.VIDEO)) {
			// TODO Auto-generated method stub
			// 在这里实现你的逻辑处理
			// 比如VideoPlayerActivity.setMediaListener(new GstMediaListener());
		} else if (type.equals(MediaType.AUDIO)) {
			// TODO Auto-generated method stub
			// 在这里实现你的逻辑处理
			// 比如AudioPlayerActivity.setMediaListener(new GstMediaListener());
		}

		Intent intent = new Intent();
		intent.setClass(context, LiRenderPlayerService.class);
		intent.putExtra(IntentParameter.TYPE, type);
		intent.putExtra(IntentParameter.NAME, name);
		intent.putExtra(IntentParameter.PLAYURI, uri.toString());
		intent.putExtra(IntentParameter.CURRENTURIMETADATA, currentURIMetaData);
		intent.putExtra(IntentParameter.SOURCE, source);
		this.context.startService(intent);
	}
}
