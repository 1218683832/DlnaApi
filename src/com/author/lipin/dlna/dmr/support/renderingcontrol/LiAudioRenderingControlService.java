package com.author.lipin.dlna.dmr.support.renderingcontrol;

import org.fourthline.cling.binding.annotations.UpnpAction;
import org.fourthline.cling.binding.annotations.UpnpInputArgument;
import org.fourthline.cling.binding.annotations.UpnpOutputArgument;
import org.fourthline.cling.model.types.ErrorCode;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;
import org.fourthline.cling.model.types.UnsignedIntegerTwoBytes;
import org.fourthline.cling.support.model.Channel;
import org.fourthline.cling.support.renderingcontrol.AbstractAudioRenderingControl;
import org.fourthline.cling.support.renderingcontrol.RenderingControlErrorCode;
import org.fourthline.cling.support.renderingcontrol.RenderingControlException;

import com.author.lipin.dlna.dmr.action.AbstractMediaPlayerControl;

public class LiAudioRenderingControlService extends
		AbstractAudioRenderingControl {

	protected AbstractMediaPlayerControl playerControl;
	
	public LiAudioRenderingControlService(AbstractMediaPlayerControl players) {
		this.playerControl = players;
	}

	@Override
	public final UnsignedIntegerFourBytes[] getCurrentInstanceIds() {
		return new UnsignedIntegerFourBytes[0];
	}

	protected AbstractMediaPlayerControl getInstance(
			UnsignedIntegerFourBytes instanceId)
			throws RenderingControlException {
		AbstractMediaPlayerControl player = getPlayerControl();
		if (player == null) {
			throw new RenderingControlException(
					RenderingControlErrorCode.INVALID_INSTANCE_ID);
		}
		return player;
	}

	protected AbstractMediaPlayerControl getPlayerControl() {
		return playerControl;
	}

	@Override
	@UpnpAction(out = @UpnpOutputArgument(name = "CurrentMute", stateVariable = "Mute"))
	public final boolean getMute(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId,
			@UpnpInputArgument(name = "Channel") String channelName)
			throws RenderingControlException {
		checkChannel(channelName);
		return getInstance(instanceId).getVolume() == 0;
	}

	@Override
	@UpnpAction
	public final void setMute(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId,
			@UpnpInputArgument(name = "Channel") String channelName,
			@UpnpInputArgument(name = "DesiredMute", stateVariable = "Mute") boolean desiredMute)
			throws RenderingControlException {
		checkChannel(channelName);
		getInstance(instanceId).setMute(desiredMute);
	}

	@Override
	@UpnpAction(out = @UpnpOutputArgument(name = "CurrentVolume", stateVariable = "Volume"))
	public final UnsignedIntegerTwoBytes getVolume(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId,
			@UpnpInputArgument(name = "Channel") String channelName)
			throws RenderingControlException {
		checkChannel(channelName);
		Math.round(getInstance(instanceId).getVolume() * 100);
		int vol = (int) Math.round(getInstance(instanceId).getVolume() * 100);
		return new UnsignedIntegerTwoBytes(vol);
	}

	@Override
	@UpnpAction
	public final void setVolume(
			@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId,
			@UpnpInputArgument(name = "Channel") String channelName,
			@UpnpInputArgument(name = "DesiredVolume", stateVariable = "Volume") UnsignedIntegerTwoBytes desiredVolume)
			throws RenderingControlException {
		checkChannel(channelName);
		double vol = desiredVolume.getValue() / 100d;
		getInstance(instanceId).setVolume(vol);

	}

	@Override
	protected final Channel[] getCurrentChannels() {
		// TODO Auto-generated method stub
		return null;
	}

	private void checkChannel(String channelName)
			throws RenderingControlException {
		if (!getChannel(channelName).equals(Channel.Master)) {
			throw new RenderingControlException(
					ErrorCode.ARGUMENT_VALUE_INVALID,
					"Unsupported audio channel: " + channelName);
		}
	}

}
