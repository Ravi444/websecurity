package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;

import websitesecurity.core.models.NavBean;

import com.adobe.cq.sightly.WCMUse;
import com.adobe.granite.xss.XSSAPI;

/**
 * We are using this class for Navlist component in header(Buy/Renew menu).
 */
public class Navlist extends WCMUse {
	private String[] title;
	private String[] path;
	private String[] buttonpath;
	private List<NavBean> listData;

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() {
		listData = getValues();
	}

	/**
	 * This method is used to get data from Navlist component
	 * @return it will return list object of NavBean type.
	 */
	private List<NavBean> getValues() {
		List<NavBean> beanList = new ArrayList<NavBean>();
		NavBean navBean = null;
		title = getProperties().get("title", String[].class);
		path = getProperties().get("path", String[].class);
		buttonpath = getProperties().get("buttontitle", String[].class);
		for (int i = 0; i < title.length; i++) {
			navBean = new NavBean(null, null, null, null);
			navBean.setTitle(title[i]);
			ResourceResolver resourceResolver = getResourceResolver();
			XSSAPI xssAPI = resourceResolver.adaptTo(XSSAPI.class);
			String updatedPath = PathUtility.pathCheckAdvanced(xssAPI.getValidHref(path[i]));
			navBean.setPath(updatedPath);
			navBean.setButtonpath(buttonpath[i]);
			String target = PathUtility.pathTarget(path[i]);
			navBean.setTarget(target);
			beanList.add(navBean);
		}
		return beanList;
	}

	/**
	 * this is the getter method for listData.
	 * @return it will return list object of NavBean type.
	 */
	public List<NavBean> getListData() {
		return listData;
	}
}