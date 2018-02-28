package com.author.lipin.dlna.dms.support.contentdirectory;

import org.fourthline.cling.support.contentdirectory.AbstractContentDirectoryService;
import org.fourthline.cling.support.contentdirectory.ContentDirectoryErrorCode;
import org.fourthline.cling.support.contentdirectory.ContentDirectoryException;
import org.fourthline.cling.support.contentdirectory.DIDLParser;
import org.fourthline.cling.support.model.BrowseFlag;
import org.fourthline.cling.support.model.BrowseResult;
import org.fourthline.cling.support.model.DIDLContent;
import org.fourthline.cling.support.model.SortCriterion;
import org.fourthline.cling.support.model.container.Container;
import org.fourthline.cling.support.model.item.Item;

import com.author.lipin.dlna.bean.ContentNode;
import com.author.lipin.dlna.bean.ContentTree;

/**
 * 
 * @author lipin
 * 
 * @version 1.0
 * 
 */
public class LiContentDirectoryService extends AbstractContentDirectoryService {

	@Override
	public BrowseResult browse(String objectID, BrowseFlag browseFlag,
			String filter, long firstResult, long maxResults,
			SortCriterion[] orderby) throws ContentDirectoryException {
		// You have to create the DIDL content dynamically!
		try {
			DIDLContent didl = new DIDLContent();
			ContentNode contentNode = ContentTree.getNode(objectID);
			if (contentNode == null)
				return new BrowseResult("", 0, 0);
			if (contentNode.isItem()) {
				didl.addItem(contentNode.getItem());
				return new BrowseResult(new DIDLParser().generate(didl), 1, 1);
			} else {
				if (browseFlag == BrowseFlag.METADATA) {
					didl.addContainer(contentNode.getContainer());
					return new BrowseResult
							(new DIDLParser().generate(didl), 1, 1);
				} else {
					for (Container container : contentNode.getContainer()
							.getContainers()) {
						didl.addContainer(container);
					}
					for (Item item : contentNode.getContainer().getItems()) {
						didl.addItem(item);
					}
					return new BrowseResult(new DIDLParser().generate(didl),
							contentNode.getContainer().getChildCount(),
							contentNode.getContainer().getChildCount());
				}
			}
		} catch (Exception ex) {
			throw new ContentDirectoryException(
					ContentDirectoryErrorCode.CANNOT_PROCESS, ex.toString());
		}
	}

	@Override
	public BrowseResult search(String containerId, String searchCriteria,
			String filter, long firstResult, long maxResults,
			SortCriterion[] orderBy) throws ContentDirectoryException {
		// You can override this method to implement searching!
		return super.search(containerId, searchCriteria, filter, firstResult,
				maxResults, orderBy);
	}
}
