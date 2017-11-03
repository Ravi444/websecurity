package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;

import websitesecurity.core.models.PathBean;

import com.adobe.cq.sightly.WCMUse;
import com.adobe.granite.xss.XSSAPI;

/**
 * 
 * We are using this class in multiple component to retrieve title and path
 * properties and form it as a link.
 *
 */
public class TitleList extends WCMUse {
	private String[] title;
	private String[] path;
	private List<PathBean> listData;

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	/**
	 * Here we are retrieving title and path from respective object.
	 */
	@Override
	public void activate() {
		title = getProperties().get("title", String[].class);
		path = getProperties().get("path", String[].class);
		listData = getValues();
	}

	/**
	 * This is use to create List object of type PathBean with help of title and path details.
	 * @return it is returning List object of PathBean type.
	 */
	private List<PathBean> getValues() {
		List<PathBean> beanList = new ArrayList<PathBean>();
		PathBean pathBean = null;
		if (title != null && path != null && title.length == path.length) {
			for (int i = 0; i < title.length; i++) {
				pathBean = new PathBean(null, null, null);
				pathBean.setTitle(title[i]);
				ResourceResolver resourceResolver = getResourceResolver();
				XSSAPI xssAPI = resourceResolver.adaptTo(XSSAPI.class);
				pathBean.setPath(PathUtility.pathCheck(xssAPI.getValidHref(path[i])));
				pathBean.setTarget(PathUtility.pathTarget(path[i]));
				beanList.add(pathBean);
			}
		}
		return beanList;
	}

	public List<PathBean> getListData() {
		return listData;
	}
}