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
 * @author 
 * We are using this class for ImageandText component.
 */
public class ImageAndTextRightLinks extends WCMUse {
	private String[] rightlinks;
	private List<PathBean> listData;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() throws JSONException {
		rightlinks = getProperties().get("rightlinks", String[].class);
		if (rightlinks != null) {
			listData = getValues();
		}
	}

	/**
	 * this method is used to create link based on the data from rightlinks.
	 * 
	 * @return it will give list object of PathBean type.
	 * @throws JSONException
	 */
	private List<PathBean> getValues() throws JSONException {
		List<PathBean> beanList = new ArrayList<PathBean>();
		PathBean pathBean = null;
		JSONObject jsonobject ;
		for (int i = 0; i < rightlinks.length; i++) {
		    jsonobject = new JSONObject(rightlinks[i]);
			String text = jsonobject.getString("text");
			String url = jsonobject.getString("url");
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
	 * 
	 * @return it will return list object of Pathbean type.
	 */
	public List<PathBean> getListData() {
		return listData;
	}
}