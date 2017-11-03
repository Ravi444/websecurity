package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import websitesecurity.core.models.CampaignBean;

import com.adobe.cq.sightly.WCMUse;

/**
 * @author Avinash_Perumalla
 * We are using this class for Campaign component.
 * 
 */
public class Campaign extends WCMUse {
	private String[] title;
	private String[] titlecolor;
	private String[] description;
	private String[] descriptioncolor;

	private List<CampaignBean> listData;

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
	 * This method is used to get data.
	 * @return it will return list object of CampaignBean type.
	 */
	private List<CampaignBean> getValues() {
		List<CampaignBean> beanList = new ArrayList<CampaignBean>();
		CampaignBean campaignBean = null;

		title = getProperties().get("title1", String[].class);
		titlecolor = getProperties().get("titlecolor1", String[].class);
		description = getProperties().get("desc", String[].class);
		descriptioncolor = getProperties().get("desccolor1", String[].class);

		for (int i = 0; i < title.length; i++) {
			campaignBean = new CampaignBean(null, null, null, null);
			campaignBean.setTitle(title[i]);
			campaignBean.setTitlecolor(titlecolor[i]);
			campaignBean.setDescription(description[i]);
			campaignBean.setDescriptioncolor(descriptioncolor[i]);
			beanList.add(campaignBean);
		}
		return beanList;
	}

	/**
	 * this is the getter method for listData.
	 * @return it will return list object of CampaignBean type.
	 */
	public List<CampaignBean> getListData() {
		return listData;
	}

}