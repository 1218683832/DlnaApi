package com.author.lipin.dlna.dms.actioncallback;

import android.app.Activity;
import android.widget.ArrayAdapter;

import org.fourthline.cling.model.action.ActionException;
import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.model.types.ErrorCode;
import org.fourthline.cling.support.contentdirectory.callback.Browse;
import org.fourthline.cling.support.model.BrowseFlag;
import org.fourthline.cling.support.model.DIDLContent;
import org.fourthline.cling.support.model.SortCriterion;
import org.fourthline.cling.support.model.container.Container;
import org.fourthline.cling.support.model.item.Item;

import com.author.lipin.dlna.bean.ContentItem;

public class ContentBrowseActionCallback extends Browse {

	private Service<?, ?> service;

	@SuppressWarnings("unused")
	private Container container;

	private ArrayAdapter<ContentItem> listAdapter;

	private Activity activity;

	public ContentBrowseActionCallback(Activity activity,
			Service<?, ?> service, Container container,
			ArrayAdapter<ContentItem> listadapter) {
		super(service, container.getId(), BrowseFlag.DIRECT_CHILDREN, "*", 0L,
				null,
				new SortCriterion[] { new SortCriterion(true, "dc:title") });
		this.activity = activity;
		this.service = service;
		this.container = container;
		this.listAdapter = listadapter;
	}

	@SuppressWarnings("rawtypes")
	public void received(final ActionInvocation actionInvocation,
			final DIDLContent didl) {
		this.activity.runOnUiThread(new Runnable() {

			@SuppressWarnings("unused")
			@Override
			public void run() {
				try {
					listAdapter.clear();
					// Containers first
					for (Container childContainer : didl.getContainers()) {
						listAdapter
								.add(new ContentItem(childContainer, service));
					}
					// Now items
					for (Item childItem : didl.getItems()) {
					}
				} catch (Exception ex) {
					actionInvocation.setFailure(new ActionException(
							ErrorCode.ACTION_FAILED,
							"Can't create list childs: " + ex, ex));
					failure(actionInvocation, null);
				}
			}
		});
	}

	public void updateStatus(final Status status) {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void failure(ActionInvocation invocation, UpnpResponse operation,
			final String defaultMsg) {
	}
}