package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import websitesecurity.core.models.ConnectBean;

import com.adobe.cq.sightly.WCMUse;

/**
 * @author Arunmozhi_Ns
 * We are using this class for community component.
 * 
 */
public class ConnectCommunity extends WCMUse {

	private String[] community;
	private List<ConnectBean> listData;

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() throws JSONException {
		community = getProperties().get("community", String[].class);
		if (community != null) {
			listData = getValues();
		}
	}

	/**
	 * This method is used to get data from Community Component. 
	 * @return it will return list object of ConnectBean type.
	 * @throws it will throws JSONException.
	 */
	private List<ConnectBean> getValues() throws JSONException {
		List<ConnectBean> beanList = new ArrayList<ConnectBean>();
		ConnectBean connectBean = null;
		JSONObject jsonobject;
		int max = 0;
		if (community.length <= 3) {
			max = community.length;
		} else {
			max = 3;
		}
		
		for (int i = 0; i < max; i++) {
		    jsonobject = new JSONObject(community[i]);
			String text = jsonobject.getString("text");
			String url = jsonobject.getString("url");
			String imgurl = jsonobject.getString("imgURL");
			connectBean = new ConnectBean(null, null, null, null);
			connectBean.setLinktitle(text);
			connectBean.setLinkpath(PathUtility.pathCheck(url));
			connectBean.setIconpath(imgurl);
			connectBean.setTarget(PathUtility.pathTarget(url));
			beanList.add(connectBean);
		}
		return beanList;
	}

	/**
	 * this is the getter method for listData.
	 * 
	 * @return it will return list object of ConnectBean type.
	 */
	public List<ConnectBean> getListData() {
		return listData;
	}
}