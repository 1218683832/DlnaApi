package com.author.lipin.dlna.dmr.action;

public interface MediaListener {
	
	void pause();

    void start();

    void stop();
    
    void playError();

    void endOfMedia();

    void positionChanged(int position);

    void durationChanged(int duration);
}
