package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;

import websitesecurity.core.models.SitemapBean;

import com.adobe.cq.sightly.WCMUse;
import com.adobe.granite.xss.XSSAPI;

/**
 * We are using this class for Sitemap component.
 */
public class Sitemap extends WCMUse {
	private String[] title;
	private String[] buttontitle;
	private String[] buttonpath;
	private List<SitemapBean> listData;

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() {
		listData = getValues();
	}

	/**
	 * This method is used to get data from Sitemap component
	 * @return it will return list object of SitemapBean type.
	 */
	private List<SitemapBean> getValues() {
		List<SitemapBean> beanList = new ArrayList<SitemapBean>();
		SitemapBean sitemapBean = null;
		title = getProperties().get("title", String[].class);
		buttontitle = getProperties().get("buttontitle", String[].class);
		buttonpath = getProperties().get("buttonpath", String[].class);
		for (int i = 0; i < title.length; i++) {
			sitemapBean = new SitemapBean(null, null, null, null);
			sitemapBean.setTitle(title[i]);
			sitemapBean.setButtontitle(buttontitle[i]);
			ResourceResolver resourceResolver = getResourceResolver();
			XSSAPI xssAPI = resourceResolver.adaptTo(XSSAPI.class);
			String updatedPath2 = PathUtility.pathCheck(xssAPI.getValidHref(buttonpath[i]));
			sitemapBean.setButtonpath(updatedPath2);
			String target = PathUtility.pathTarget(buttonpath[i]);
			sitemapBean.setTarget(target);
			beanList.add(sitemapBean);
		}
		return beanList;
	}

	/**
	 * this is the getter method for listData.
	 * @return it will return list object of SitemapBean type.
	 */
	public List<SitemapBean> getListData() {
		return listData;
	}
}