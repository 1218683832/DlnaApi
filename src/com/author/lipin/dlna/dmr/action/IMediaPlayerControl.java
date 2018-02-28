package com.author.lipin.dlna.dmr.action;

import java.net.URI;

import org.fourthline.cling.support.model.TransportAction;
import org.fourthline.cling.support.model.TransportState;

import android.content.Intent;

public interface IMediaPlayerControl {

	public void setURI(URI uri, String type, String name,
			String currentURIMetaData, String source);

	public void setVolume(double volume);

	public void setMute(boolean desiredMute);

	public TransportAction[] getCurrentTransportActions();

	public void transportStateChanged(TransportState newState);

	public double getVolume();

	public void play();

	public void pause();

	public void stop();

	public void seekTo(int position);

	public void sendBroadcastAction(Intent intent, String action);
}
