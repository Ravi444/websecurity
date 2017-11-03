package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;

import websitesecurity.core.models.DataBean;

import com.adobe.cq.sightly.WCMUse;
import com.adobe.granite.xss.XSSAPI;

/**
 * @author Ravi_Ranjan
 * we are using this class for Carousal component.
 */
public class Carousal extends WCMUse {
	private String[] image;
	private String[] background_color;
	private String[] desc;
	private String[] subtitle;
	private String[] link;
	private String[] path;
	private List<DataBean> listData;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() {
		listData = getValues();
	}

	/**
	 * This method is used to get data from Carousal component
	 * 
	 * @return it will return list object of DataBean type.
	 */
	private List<DataBean> getValues() {
		List<DataBean> beanList = new ArrayList<DataBean>();
		DataBean dataBean = null;
		image = getProperties().get("image", String[].class);
		background_color = getProperties().get("color", String[].class);
		desc = getProperties().get("desc", String[].class);
		link = getProperties().get("link", String[].class);
		path = getProperties().get("path", String[].class);
		subtitle = getProperties().get("subtitle", String[].class);
		for (int i = 0; i < desc.length; i++) {
			dataBean = new DataBean(null, null, null, null, null, null, null);
			dataBean.setImage(image[i]);
			dataBean.setBackground_color(background_color[i]);
			dataBean.setDescription(desc[i]);
			dataBean.setSubtitle(subtitle[i]);
			dataBean.setLink(link[i]);
			ResourceResolver resourceResolver = getResourceResolver();
			XSSAPI xssAPI = resourceResolver.adaptTo(XSSAPI.class);
			dataBean.setPath(PathUtility.pathCheck(xssAPI.getValidHref(path[i])));
			dataBean.setTarget(PathUtility.pathTarget(path[i]));
			beanList.add(dataBean);
		}
		return beanList;
	}

	/**
	 * this is the getter method for listData.
	 * 
	 * @return it will return list object of DataBean type.
	 */
	public List<DataBean> getListData() {
		return listData;
	}
}