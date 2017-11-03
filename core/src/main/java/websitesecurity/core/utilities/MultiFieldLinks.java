package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import websitesecurity.core.models.PathBean;

import com.adobe.cq.sightly.WCMUse;
import com.adobe.granite.xss.XSSAPI;

/**
 * We are using this class to retrieve data from multifield, Where multifield
 * property name is ./links.
 */
public class MultiFieldLinks extends WCMUse {
	private String[] links;
	private List<PathBean> listData;

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() throws JSONException {
		links = getProperties().get("links", String[].class);
		if (links != null) {
			listData = getValues();
		}
	}

	/**
	 * In this method we are retrieving links text and links path from json object of links. 
	 * @return it will return List object of PathBean type.
	 * @throws JSONException
	 */
	private List<PathBean> getValues() throws JSONException {
		List<PathBean> beanList = new ArrayList<PathBean>();
		PathBean pathBean = null;
		JSONObject jsonobject;
		String text;
		String url;
		for (int i = 0; i < links.length; i++) {
			 jsonobject = new JSONObject(links[i]);
			 text = jsonobject.getString("text");
			 url = jsonobject.getString("url");
			pathBean = new PathBean(null, null, null);
			pathBean.setTitle(text);
			ResourceResolver resourceResolver = getResourceResolver();
			XSSAPI xssAPI = resourceResolver.adaptTo(XSSAPI.class);
			pathBean.setPath(PathUtility.pathCheck(xssAPI.getValidHref(url)));
			pathBean.setTarget(PathUtility.pathTarget(url));
			beanList.add(pathBean);
		}
		return beanList;
	}

	/**
	 * this is the getter method for listData.
	 * @return it will return List object of PathBean type.
	 */
	public List<PathBean> getListData() {
		return listData;
	}
}