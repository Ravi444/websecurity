package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import websitesecurity.core.models.PartnersBannerBean;

import com.adobe.cq.sightly.WCMUse;

/**
 * We are using this class for Hero Banner Partners component.
 */
public class PartnersBanner extends WCMUse {
	private String[] title;
	private String[] titlecolor;
	private String[] description;
	private int numberOfItems;
	private List<PartnersBannerBean> listData;

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() {
		listData = getValues();
	}

	/**
	 * This method is used to get data from Partners Banner component
	 * @return it will return list object of PartnersBannerBean type.
	 */
	private List<PartnersBannerBean> getValues() {
		List<PartnersBannerBean> beanList = new ArrayList<PartnersBannerBean>();
		PartnersBannerBean partnersBannerBean = null;
		title = getProperties().get("title", String[].class);
		titlecolor = getProperties().get("titlecolor1", String[].class);
		description = getProperties().get("desc", String[].class);
		numberOfItems = getProperties().get("numberofitems", Integer.class);
		for (int i = 0; i < numberOfItems; i++) {
			partnersBannerBean = new PartnersBannerBean(null, null, null);
			partnersBannerBean.setTitle(title[i]);
			partnersBannerBean.setTitlecolor(titlecolor[i]);
			partnersBannerBean.setDescription(description[i]);
			beanList.add(partnersBannerBean);
		}
		return beanList;
	}

	/**
	 * this is the getter method for listData.
	 * @return it will return list object of PartnersBannerBean type.
	 */
	public List<PartnersBannerBean> getListData() {
		return listData;
	}	
}