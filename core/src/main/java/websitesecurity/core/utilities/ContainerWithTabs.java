package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import websitesecurity.core.models.ContainerWithTabBean;

import com.adobe.cq.sightly.WCMUse;

/**
 * @author Aakriti
 * We are using this for container with tabs component.
 */
public class ContainerWithTabs extends WCMUse {
	private String[] links;
	private List<ContainerWithTabBean> tabsdata;

	/**
	 * this is the getter method for tabsdata.
	 * @return it will return list object of ContainerWithTabBean type.
	 */
	public List<ContainerWithTabBean> getTabsdata() {
		return tabsdata;
	}

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() {
		try {
			tabsdata = getValues();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * this method is used to fetch links data from component.
	 * @return it will return list object of ContainerWithTabBean type.
	 * @throws JSONException
	 */
	private List<ContainerWithTabBean> getValues() throws JSONException {
		List<ContainerWithTabBean> beanList = new ArrayList<ContainerWithTabBean>();
		ContainerWithTabBean containerWithTabBean = null;
		JSONObject jsonobject;
		links = getProperties().get("links", String[].class);
		if (links != null) {
			for (int i = 0; i < links.length; i++) {
			    jsonobject = new JSONObject(links[i]);
				containerWithTabBean = new ContainerWithTabBean(null, null);
				containerWithTabBean.setLink(jsonobject.getString("text"));
				containerWithTabBean.setPath(jsonobject.getString("url"));
				beanList.add(containerWithTabBean);
			}
		}
		return beanList;
	}
}