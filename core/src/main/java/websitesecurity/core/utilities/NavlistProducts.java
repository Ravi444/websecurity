package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;

import websitesecurity.core.models.NavlistProductsBean;

import com.adobe.cq.sightly.WCMUse;
import com.adobe.granite.xss.XSSAPI;

/**
 * We are using this class for Navlist component in header(Products menu).
 */
public class NavlistProducts extends WCMUse {
	private String[] title;
	private String[] path;
	private List<NavlistProductsBean> listData;

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() {
		title = getProperties().get("title", String[].class);
		path = getProperties().get("path", String[].class);
		listData = getValues();
	}

	/**
	 * This method is used to create links for the navlist in products menu.
	 * @return it will return list object of NavlistProductsBean type.
	 */
	private List<NavlistProductsBean> getValues() {
		List<NavlistProductsBean> beanList = new ArrayList<NavlistProductsBean>();
		NavlistProductsBean navlistProductsBean = null;
		for (int i = 0; i < title.length; i++) {
			navlistProductsBean = new NavlistProductsBean(null, null, null);
			navlistProductsBean.setTitle(title[i]);
			ResourceResolver resourceResolver = getResourceResolver();
			XSSAPI xssAPI = resourceResolver.adaptTo(XSSAPI.class);
			String updatedPath = PathUtility.pathCheckAdvanced(xssAPI.getValidHref(path[i]));
			navlistProductsBean.setPath(updatedPath);
			String target = PathUtility.pathTarget(path[i]);
			navlistProductsBean.setTarget(target);
			beanList.add(navlistProductsBean);
		}
		return beanList;
	}

	/**
	 * this is the getter method for listData.
	 * @return it will return list object of NavlistProductsBean type.
	 */
	public List<NavlistProductsBean> getListData() {
		return listData;
	}
}