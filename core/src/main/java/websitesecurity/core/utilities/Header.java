package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;

import websitesecurity.core.models.HeaderBean;

import com.adobe.cq.sightly.WCMUse;
import com.adobe.granite.xss.XSSAPI;

/**
 * @author Vasudev_Munagala
 * We are using this for Header component.
 */
public class Header extends WCMUse {
	private String[] title;
	private String[] path;
	private String[] bread;
	private String[] div;
	private List<HeaderBean> listData;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() {
		try {
			title = getProperties().get("title", String[].class);
		} catch (Exception e) {
			title = null;
		}

		try {
			path = getProperties().get("path", String[].class);
		} catch (Exception e) {
			path = null;
		}

		try {
			bread = getProperties().get("bread", String[].class);
		} catch (Exception e) {
			bread = null;
		}

		try {
			div = getProperties().get("div", String[].class);
		} catch (Exception e) {
			div = null;
		}
		
		if(title!=null){
		listData = getValues();
		}
	}

	/**
	 * this method is used to fetch data from header component.
	 * 
	 * @return it will return list object of HeaderBean type.
	 */
	private List<HeaderBean> getValues() {
		List<HeaderBean> beanList = new ArrayList<HeaderBean>();
		HeaderBean headerBean = null;

		

		for (int i = 0; i < title.length; i++) {
			headerBean = new HeaderBean(null, null, null, null, null, null);
			headerBean.setTitle(title[i]);
			ResourceResolver resourceResolver = getResourceResolver();
			XSSAPI xssAPI = resourceResolver.adaptTo(XSSAPI.class);
			String updatedPath = PathUtility.pathCheckAdvanced(xssAPI.getValidHref(path[i]));
			String breadPath = PathUtility.pathCheckAdvanced(xssAPI.getValidHref(bread[i]));
			headerBean.setPath(updatedPath);
			headerBean.setBread(breadPath);
			headerBean.setDivid(div[i]);
			String target = PathUtility.pathTarget(path[i]);
			headerBean.setTarget(target);
			String target1 = PathUtility.pathTarget(bread[i]);
			headerBean.setTarget1(target1);
			beanList.add(headerBean);
		}
		return beanList;
	}

	/**
	 * this is the getter method for listData.
	 * 
	 * @return it will return list object of HeaderBean.
	 */
	public List<HeaderBean> getListData() {
		return listData;
	}
}