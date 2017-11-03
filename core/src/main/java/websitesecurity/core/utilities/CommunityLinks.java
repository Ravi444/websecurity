package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;

import websitesecurity.core.models.CommunityBean;

import com.adobe.cq.sightly.WCMUse;
import com.adobe.granite.xss.XSSAPI;

/**
 * @author Arunmozhi_Ns
 * we are using this for Community links component.
 */
public class CommunityLinks extends WCMUse {
	
	private String[] desc;
	private String[] link;
	private String[] path;
	private String[] linkcolor;
	private List<CommunityBean> listData;
	

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() {
		listData = getValues();
	}

	/**
	 * This method is used to get data from community links component
	 * @return it will return list object of CommunityBean type.
	 */
	private List<CommunityBean> getValues() {
		List<CommunityBean> beanList = new ArrayList<CommunityBean>();
		CommunityBean communityBean = null;
		desc = getProperties().get("desc", String[].class);
		link = getProperties().get("link", String[].class);
		path = getProperties().get("path", String[].class);
		linkcolor = getProperties().get("linkcolor", String[].class);
		for (int i = 0; i < desc.length; i++) {
			communityBean = new CommunityBean(null, null, null, null, null);
			communityBean.setDescription(desc[i]);
			communityBean.setLinktext(link[i]);
			communityBean.setLinkcolor(linkcolor[i]);
			ResourceResolver resourceResolver = getResourceResolver();
			XSSAPI xssAPI = resourceResolver.adaptTo(XSSAPI.class);
			communityBean.setLinkvalue(PathUtility.pathCheck(xssAPI.getValidHref(path[i])));
			communityBean.setTarget(PathUtility.pathTarget(path[i]));
			beanList.add(communityBean);
		}
		return beanList;
	}

	/**
	 * this is the getter method for listData.
	 * @return it will return list object of CommunityBean type.
	 */
	public List<CommunityBean> getListData() {
		return listData;
	}
}