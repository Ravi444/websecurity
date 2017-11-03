package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;

import websitesecurity.core.models.BuyRenewIconTextAndTitleBean;

import com.adobe.cq.sightly.WCMUse;
import com.adobe.granite.xss.XSSAPI;

/**
 * @author Avinash_Perumalla
 * We are using this class for container icon text and title component.
 */
public class BuyRenewIconTextAndTitle extends WCMUse {
	private String[] image;
	private String[] title;
	private String[] description;
	private String[] linkText;
	private String[] linkPath;
	private String[] altText;
	private String[] imgTitle;
	private List<BuyRenewIconTextAndTitleBean> listData;


	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() {
		listData = getValues();
	}

	/**
	 * This method is used to get data from IconTextandTitle component
	 * @return it will return list object of BuyRenewIconTextAndTitleBean type  
	 */
	private List<BuyRenewIconTextAndTitleBean> getValues() {
		List<BuyRenewIconTextAndTitleBean> buyRenewIconTextAndTitleBeanList = new ArrayList<BuyRenewIconTextAndTitleBean>();
		BuyRenewIconTextAndTitleBean buyRenewIconTextAndTitleBean = null;
		image = getProperties().get("image", String[].class);
		title = getProperties().get("title", String[].class);
		description = getProperties().get("description", String[].class);
		linkText = getProperties().get("linkText", String[].class);
		linkPath = getProperties().get("linkPath", String[].class);
		altText = getProperties().get("altText", String[].class);
		imgTitle = getProperties().get("imgTitle", String[].class);

		for (int i = 0; i < title.length; i++) {
			buyRenewIconTextAndTitleBean = new BuyRenewIconTextAndTitleBean(null, null, null, null, null, null, null, null);
			buyRenewIconTextAndTitleBean.setImage(image[i]);
			buyRenewIconTextAndTitleBean.setTitle(title[i]);
			buyRenewIconTextAndTitleBean.setDescription(description[i]);
			buyRenewIconTextAndTitleBean.setLinkText(linkText[i]);
			ResourceResolver resourceResolver = getResourceResolver();
			XSSAPI xssAPI = resourceResolver.adaptTo(XSSAPI.class);
			buyRenewIconTextAndTitleBean.setLinkPath(PathUtility.pathCheck(xssAPI.getValidHref(linkPath[i])));
			buyRenewIconTextAndTitleBean.setTarget(PathUtility.pathTarget(linkPath[i]));
			buyRenewIconTextAndTitleBean.setAltText(altText[i]);
			buyRenewIconTextAndTitleBean.setImgTitle(imgTitle[i]);
			buyRenewIconTextAndTitleBeanList.add(buyRenewIconTextAndTitleBean);
		}
		return buyRenewIconTextAndTitleBeanList;
	}

	/**
	 * this is the getter method for listData.
	 * @return it will return list object of type BuyRenewIconTextAndTitleBean.
	 */
	public List<BuyRenewIconTextAndTitleBean> getListData() {
		return listData;
	}
}