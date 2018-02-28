package com.author.lipin.dlna.dmr.support.avtransport;

import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;
import org.fourthline.cling.support.avtransport.AVTransportException;

import com.author.lipin.dlna.dmr.action.AbstractMediaPlayerControl;

public class LiAVTransportService extends AbstractLiAVTransportService {

	protected static final String TAG = LiAVTransportService.class
			.getSimpleName();

	public LiAVTransportService(AbstractMediaPlayerControl playerControl) {
		super();
		this.playerControl = playerControl;
	}

	@Override
	public void setAVTransportURI(UnsignedIntegerFourBytes instanceId,
			String currentURI, String currentURIMetaData)
			throws AVTransportException {
		// TODO Auto-generated method stub
		// 在这里实现你的逻辑处理

		getInstance(instanceId).setURI(this.uri, type, currentURIMetaData,
				currentURIMetaData, source);
		return;
	}
}
