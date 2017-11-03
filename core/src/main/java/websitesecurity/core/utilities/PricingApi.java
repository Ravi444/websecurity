package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;

import websitesecurity.core.models.PricingDataBean;

import com.adobe.cq.sightly.WCMUse;
import com.adobe.granite.xss.XSSAPI;

/**
 * We are using class in Pricing API Component.
 *
 */
public class PricingApi extends WCMUse {
	private String[] title;
	private String[] subtitle;
	private String[] path;
	private String[] value;
	private String[] details1;
	private String[] details2;
	private String[] details3;
	private String[] image1;
	private String[] image2;
	private String[] image3;
	private List<PricingDataBean> listData;

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() {
		listData = getValues();
	}

	/**
	 * In this method we will retrieve required data for the pricing API
	 * @return it will return List object of PricingDataBean type.
	 */
	private List<PricingDataBean> getValues() {
		List<PricingDataBean> beanList = new ArrayList<PricingDataBean>();
		PricingDataBean dataBean = null;
		title = getProperties().get("downsectiontitle", String[].class);
		subtitle = getProperties().get("bottomsubtitle", String[].class);
		path = getProperties().get("titlepath", String[].class);
		value = getProperties().get("productvalue", String[].class);
		details1 = getProperties().get("details1", String[].class);
		details2 = getProperties().get("details2", String[].class);
		details3 = getProperties().get("details3", String[].class);
		image1 = getProperties().get("propicons1", String[].class);
		image2 = getProperties().get("propicons2", String[].class);
		image3 = getProperties().get("propicons3", String[].class);

		for (int i = 0; i < title.length; i++) {
			dataBean = new PricingDataBean(null, null, null, null, null, null, null, null, null, null);
			dataBean.setTitle(title[i]);
			dataBean.setSubtitle(subtitle[i]);
			ResourceResolver resourceResolver = getResourceResolver();
			XSSAPI xssAPI = resourceResolver.adaptTo(XSSAPI.class);
			dataBean.setPath(PathUtility.pathCheck(xssAPI.getValidHref(path[i])));
			dataBean.setValue(value[i]);
			dataBean.setDetails1(details1[i]);
			dataBean.setDetails2(details2[i]);
			dataBean.setDetails3(details3[i]);
			dataBean.setImage1(image1[i]);
			dataBean.setImage2(image2[i]);
			dataBean.setImage3(image3[i]);
			beanList.add(dataBean);
		}
		return beanList;
	}

	/**
	 * this is the getter method for listData.
	 * @return it will return List object of PricingDataBean type.
	 */
	public List<PricingDataBean> getListData() {
		return listData;
	}
}