package com.author.lipin.dlna.bean;

import java.util.HashMap;

import org.fourthline.cling.support.model.WriteStatus;
import org.fourthline.cling.support.model.container.Container;

/**
 * 
 * @author lipin
 * 
 * @version 1.0
 * 
 */
public class ContentTree {

	public final static String ROOT_ID = "0";
	
	public final static String VIDEO_ID = "1";
	
	public final static String AUDIO_ID = "2";
	
	public final static String IMAGE_ID = "3";
	
	public final static String VIDEO_PREFIX = "video-item-";
	
	public final static String AUDIO_PREFIX = "audio-item-";
	
	public final static String IMAGE_PREFIX = "image-item-";
	
	private static HashMap<String, ContentNode> contentMap = new HashMap<String, ContentNode>();

	private static ContentNode rootNode = createRootNode();

	public ContentTree() {};

	protected static ContentNode createRootNode() {
		// create root rootContainer
		Container rootContainer = new Container();
		rootContainer.setId(ROOT_ID);
		rootContainer.setParentID("-1");
		rootContainer.setTitle("GNaP MediaServer root directory");
		rootContainer.setCreator("GNaP Media Server");
		rootContainer.setRestricted(true);
		rootContainer.setSearchable(true);
		rootContainer.setWriteStatus(WriteStatus.NOT_WRITABLE);
		rootContainer.setChildCount(0);
		ContentNode rootNode = new ContentNode(ROOT_ID, rootContainer);
		contentMap.put(ROOT_ID, rootNode);
		return rootNode;
	}
	
	public static ContentNode getRootNode() {
		return rootNode;
	}
	
	public static HashMap<String, ContentNode> getcontentMap() {
		return contentMap;
	}
	
	public static ContentNode getNode(String id) {
		if( contentMap.containsKey(id)) {
			return contentMap.get(id);
		}
		return null;
	}
	
	public static boolean hasNode(String id) {
		return contentMap.containsKey(id);
	}
	
	public static void addNode(String ID, ContentNode Node) {
		contentMap.put(ID, Node);
	}
}
