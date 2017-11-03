package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.json.JSONException;

import websitesecurity.core.models.NewsBean;

import com.adobe.cq.sightly.WCMUse;
import com.adobe.granite.xss.XSSAPI;

/**
 * We are using this class to retrieve data from custom multifield of news
 * component.
 */
public class News extends WCMUse {
	private String[] date;
	private String[] text;
	private String[] path;
	private String[] description;
	private int numberOfItems;
	private List<NewsBean> listData;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() throws JSONException {
		date = getProperties().get("date", String[].class);
		text = getProperties().get("text", String[].class);
		path = getProperties().get("path", String[].class);
		description = getProperties().get("description", String[].class);
		numberOfItems = getProperties().get("numberofitems", Integer.class);
		if (date != null || text != null || path != null || description != null) {
			listData = getValues();
		}
	}

	/**
	 * In this method we are creating object of type NewsBean by using data,
	 * text, path, description and numberofitems from the component.
	 * 
	 * @return it will return List object of NewsBean type.
	 * @throws JSONException
	 */
	private List<NewsBean> getValues() throws JSONException {
		List<NewsBean> beanList = new ArrayList<NewsBean>();
		NewsBean newsBean = null;
		for (int i = 0; i < numberOfItems; i++) {
			newsBean = new NewsBean(null, null, null, null, null);
			newsBean.setDate(date[i]);
			newsBean.setText(text[i]);
			ResourceResolver resourceResolver = getResourceResolver();
			XSSAPI xssAPI = resourceResolver.adaptTo(XSSAPI.class);
			newsBean.setPath(PathUtility.pathCheck(xssAPI.getValidHref(path[i])));
			newsBean.setTarget(PathUtility.pathTarget(path[i]));
			newsBean.setDescription(description[i]);
			beanList.add(newsBean);
		}
		return beanList;
	}

	/**
	 * this is the getter method for listData.
	 * 
	 * @return it will return List object of NewsBean type.
	 */
	public List<NewsBean> getListData() {
		return listData;
	}
}