package com.author.lipin.dlna.dmr.support.avtransport;

import java.net.URI;

import org.fourthline.cling.binding.annotations.UpnpAction;
import org.fourthline.cling.binding.annotations.UpnpInputArgument;
import org.fourthline.cling.binding.annotations.UpnpOutputArgument;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;
import org.fourthline.cling.support.avtransport.AVTransportErrorCode;
import org.fourthline.cling.support.avtransport.AVTransportException;
import org.fourthline.cling.support.avtransport.AbstractAVTransportService;
import org.fourthline.cling.support.model.DeviceCapabilities;
import org.fourthline.cling.support.model.MediaInfo;
import org.fourthline.cling.support.model.PlayMode;
import org.fourthline.cling.support.model.PositionInfo;
import org.fourthline.cling.support.model.SeekMode;
import org.fourthline.cling.support.model.StorageMedium;
import org.fourthline.cling.support.model.TransportAction;
import org.fourthline.cling.support.model.TransportInfo;
import org.fourthline.cling.support.model.TransportSettings;

import com.author.lipin.dlna.dmr.action.AbstractMediaPlayerControl;

public abstract class AbstractLiAVTransportService extends AbstractAVTransportService {

	protected static final String TAG = AbstractLiAVTransportService.class
			.getSimpleName();

protected AbstractMediaPlayerControl playerControl;
	
	protected UnsignedIntegerFourBytes instanceId;
	
	protected String currentURI;
	
	protected String currentURIMetaData;
	
	protected URI uri;

	protected String type;
	
	protected String source;

	public AbstractLiAVTransportService() {
		super();
	}
	
	@Override
	public final UnsignedIntegerFourBytes[] getCurrentInstanceIds() {
		return new UnsignedIntegerFourBytes[0];
	}

	protected final AbstractMediaPlayerControl getInstance(
			UnsignedIntegerFourBytes instanceId) throws AVTransportException {
		AbstractMediaPlayerControl player = getPlayerControl();
		if (player == null) {
			throw new AVTransportException(
					AVTransportErrorCode.INVALID_INSTANCE_ID);
		}
		return player;
	}

	protected final AbstractMediaPlayerControl getPlayerControl() {
		return playerControl;
	}

	@Override
	@UpnpAction
	public void setAVTransportURI(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId,
			@UpnpInputArgument(name = "CurrentURI", stateVariable = "AVTransportURI") String currentURI,
			@UpnpInputArgument(name = "CurrentURIMetaData", stateVariable = "AVTransportURIMetaData") String currentURIMetaData)
			throws AVTransportException {
		// TODO Auto-generated method stub

	}

	@Override
	@UpnpAction
	public final void setNextAVTransportURI(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId,
			@UpnpInputArgument(name = "NextURI", stateVariable = "AVTransportURI") String nextURI,
			@UpnpInputArgument(name = "NextURIMetaData", stateVariable = "AVTransportURIMetaData") String nextURIMetaData)
			throws AVTransportException {
		// TODO Auto-generated method stub
		// Not implemented
	}
	
	@Override
	@UpnpAction
	public final void record(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId)
			throws AVTransportException {
		// TODO Auto-generated method stub
		// Not implemented
	}
	
	@Override
	@UpnpAction
	public final void next(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId)
			throws AVTransportException {
		// TODO Auto-generated method stub
		// Not implemented
	}

	@Override
	@UpnpAction
	public final void previous(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId)
			throws AVTransportException {
		// TODO Auto-generated method stub
		// Not implemented
	}

	@Override
	@UpnpAction
	public final void setPlayMode(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId,
			@UpnpInputArgument(name = "NewPlayMode", stateVariable = "CurrentPlayMode") String newPlayMode)
			throws AVTransportException {
		// TODO Auto-generated method stub
		// Not implemented
	}

	@Override
	@UpnpAction
	public final void setRecordQualityMode(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId,
			@UpnpInputArgument(name = "NewRecordQualityMode", stateVariable = "CurrentRecordQualityMode") String newRecordQualityMode)
			throws AVTransportException {
		// TODO Auto-generated method stub
		// Not implemented
	}

	@Override
	@UpnpAction(out = {
			@UpnpOutputArgument(name = "NrTracks", stateVariable = "NumberOfTracks", getterName = "getNumberOfTracks"),
			@UpnpOutputArgument(name = "MediaDuration", stateVariable = "CurrentMediaDuration", getterName = "getMediaDuration"),
			@UpnpOutputArgument(name = "CurrentURI", stateVariable = "AVTransportURI", getterName = "getCurrentURI"),
			@UpnpOutputArgument(name = "CurrentURIMetaData", stateVariable = "AVTransportURIMetaData", getterName = "getCurrentURIMetaData"),
			@UpnpOutputArgument(name = "NextURI", stateVariable = "NextAVTransportURI", getterName = "getNextURI"),
			@UpnpOutputArgument(name = "NextURIMetaData", stateVariable = "NextAVTransportURIMetaData", getterName = "getNextURIMetaData"),
			@UpnpOutputArgument(name = "PlayMedium", stateVariable = "PlaybackStorageMedium", getterName = "getPlayMedium"),
			@UpnpOutputArgument(name = "RecordMedium", stateVariable = "RecordStorageMedium", getterName = "getRecordMedium"),
			@UpnpOutputArgument(name = "WriteStatus", stateVariable = "RecordMediumWriteStatus", getterName = "getWriteStatus") })
	public final MediaInfo getMediaInfo(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId)
			throws AVTransportException {
		MediaInfo mediaInfo = getInstance(instanceId).getCurrentMediaInfo();
		return mediaInfo;
	}

	@Override
	@UpnpAction(out = {
			@UpnpOutputArgument(name = "CurrentTransportState", stateVariable = "TransportState", getterName = "getCurrentTransportState"),
			@UpnpOutputArgument(name = "CurrentTransportStatus", stateVariable = "TransportStatus", getterName = "getCurrentTransportStatus"),
			@UpnpOutputArgument(name = "CurrentSpeed", stateVariable = "TransportPlaySpeed", getterName = "getCurrentSpeed") })
	public final TransportInfo getTransportInfo(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId)
			throws AVTransportException {
		TransportInfo transportInfo = getInstance(instanceId).getCurrentTransportInfo();
		return transportInfo;
	}

	@Override
	@UpnpAction(out = {
			@UpnpOutputArgument(name = "Track", stateVariable = "CurrentTrack", getterName = "getTrack"),
			@UpnpOutputArgument(name = "TrackDuration", stateVariable = "CurrentTrackDuration", getterName = "getTrackDuration"),
			@UpnpOutputArgument(name = "TrackMetaData", stateVariable = "CurrentTrackMetaData", getterName = "getTrackMetaData"),
			@UpnpOutputArgument(name = "TrackURI", stateVariable = "CurrentTrackURI", getterName = "getTrackURI"),
			@UpnpOutputArgument(name = "RelTime", stateVariable = "RelativeTimePosition", getterName = "getRelTime"),
			@UpnpOutputArgument(name = "AbsTime", stateVariable = "AbsoluteTimePosition", getterName = "getAbsTime"),
			@UpnpOutputArgument(name = "RelCount", stateVariable = "RelativeCounterPosition", getterName = "getRelCount"),
			@UpnpOutputArgument(name = "AbsCount", stateVariable = "AbsoluteCounterPosition", getterName = "getAbsCount") })
	public final PositionInfo getPositionInfo(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId)
			throws AVTransportException {
		PositionInfo positionInfo = getInstance(instanceId).getCurrentPositionInfo();
		return positionInfo;
	}

	@Override
	@UpnpAction(out = {
			@UpnpOutputArgument(name = "PlayMedia", stateVariable = "PossiblePlaybackStorageMedia", getterName = "getPlayMediaString"),
			@UpnpOutputArgument(name = "RecMedia", stateVariable = "PossibleRecordStorageMedia", getterName = "getRecMediaString"),
			@UpnpOutputArgument(name = "RecQualityModes", stateVariable = "PossibleRecordQualityModes", getterName = "getRecQualityModesString") })
	public final DeviceCapabilities getDeviceCapabilities(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId)
			throws AVTransportException {
		return new DeviceCapabilities(
				new StorageMedium[] { StorageMedium.NETWORK});
	}

	@Override
	@UpnpAction(out = {
			@UpnpOutputArgument(name = "PlayMode", stateVariable = "CurrentPlayMode", getterName = "getPlayMode"),
			@UpnpOutputArgument(name = "RecQualityMode", stateVariable = "CurrentRecordQualityMode", getterName = "getRecQualityMode") })
	public final TransportSettings getTransportSettings(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId)
			throws AVTransportException {
		return new TransportSettings(PlayMode.NORMAL);
	}

	@Override
	@UpnpAction
	public final void stop(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId)
			throws AVTransportException {
		getInstance(instanceId).stop();
	}

	@Override
	@UpnpAction
	public final void play(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId,
			@UpnpInputArgument(name = "Speed", stateVariable = "TransportPlaySpeed") String speed)
			throws AVTransportException {
		getInstance(instanceId).play();
	}

	@Override
	@UpnpAction
	public final void pause(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId)
			throws AVTransportException {
		getInstance(instanceId).pause();
	}


	@Override
	@UpnpAction
	public final void seek(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId,
			@UpnpInputArgument(name = "Unit", stateVariable = "A_ARG_TYPE_SeekMode") String unit,
			@UpnpInputArgument(name = "Target", stateVariable = "A_ARG_TYPE_SeekTarget") String target)
			throws AVTransportException {
		SeekMode seekMode;
		try {
			seekMode = SeekMode.valueOrExceptionOf(unit);
			if (!seekMode.equals(SeekMode.REL_TIME)) {
				throw new IllegalArgumentException();
			}
			int pos = (int) (getRealTime(target) * 1000);
			getInstance(instanceId).seekTo(pos);
		} catch (IllegalArgumentException ex) {
			throw new AVTransportException(
					AVTransportErrorCode.SEEKMODE_NOT_SUPPORTED,
					"Unsupported seek mode: " + unit);
		}
	}

	@Override
	@UpnpAction(out = @UpnpOutputArgument(name = "TransportAction[]", stateVariable = "CurrentTransportAction", getterName = ""))
	protected final TransportAction[] getCurrentTransportActions(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId)
			throws AVTransportException {
		return getInstance(instanceId).getCurrentTransportActions();
	}

	private static int getRealTime(String paramString) {
		int i = paramString.indexOf(":");
		int j = 0;
		if (i > 0) {
			String[] arrayOfString = paramString.split(":");
			j = Integer.parseInt(arrayOfString[2]) + 60
					* Integer.parseInt(arrayOfString[1]) + 3600
					* Integer.parseInt(arrayOfString[0]);
		}
		return j;
	}
}
