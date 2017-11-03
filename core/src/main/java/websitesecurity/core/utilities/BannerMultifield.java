package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.PathNotFoundException;

import org.apache.sling.commons.json.JSONException;

import websitesecurity.core.models.BannerBean;

import com.adobe.cq.sightly.WCMUse;

/**
 * @author Arunmozhi_NS
 * We are using class for herobanner_SecurityTopics component. 
 *
 */
public class BannerMultifield extends WCMUse {

	private String[] title;
	private String[] iconpath;
	private String[] alternatetext;
	private String[] imagetitle;
	private String[] path;
	private List<BannerBean> listData;

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() throws PathNotFoundException {
		title = getProperties().get("title", String[].class);
		iconpath = getProperties().get("iconpath", String[].class);
		alternatetext = getProperties().get("alternatetext", String[].class);
		imagetitle = getProperties().get("imgtitle", String[].class);
		path = getProperties().get("path", String[].class);
		listData = getValues();
	}

	/**
	 * We are using this method to retrieve data from component and create object of type BannerBean type.  
	 * @return It will return List object of BannerBean type.
	 * @throws JSONException
	 */
	private List<BannerBean> getValues() {
		List<BannerBean> beanList = new ArrayList<BannerBean>();
		BannerBean bannerBean = null;
		for (int i = 0; i < title.length; i++) {
			bannerBean = new BannerBean(null, null, null, null, null, null);
			bannerBean.setTitle(title[i]);
			bannerBean.setIcon(iconpath[i]);
			bannerBean.setAlternatetext(alternatetext[i]);
			bannerBean.setImagetitle(imagetitle[i]);
			bannerBean.setPath(PathUtility.pathCheck(path[i]));
			bannerBean.setTarget(PathUtility.pathTarget(path[i]));
			beanList.add(bannerBean);
		}
		return beanList;
	}

	/**
	 * this is the getter method for listData.
	 * @return it will return list object of BannerBean type.
	 */
	public List<BannerBean> getListData() {
		return listData;
	}
}