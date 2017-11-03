package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import websitesecurity.core.models.CampaignTextFieldsBean;
import com.adobe.cq.sightly.WCMUse;

public class CampaignTextFields extends WCMUse {


	private String[] campaignTextFields;
	private List<CampaignTextFieldsBean> listData;
	

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() throws JSONException {
		campaignTextFields = getProperties().get("campaignTextFields", String[].class);
		if(campaignTextFields != null){
		   listData = getValues();
		}
	}

	private List<CampaignTextFieldsBean> getValues() throws JSONException {
		List<CampaignTextFieldsBean> beanList = new ArrayList<CampaignTextFieldsBean>();
		CampaignTextFieldsBean campaignTextFieldsBean;
		campaignTextFieldsBean = null;
		String placeholder=null;
		String name=null;
		String id=null;
		
		   for (int i = 0; i < campaignTextFields.length; i++) {
			JSONObject jsonobject = new JSONObject(campaignTextFields[i]);
			placeholder = jsonobject.getString("text");
			name = jsonobject.getString("text1");
			id = jsonobject.getString("url");
			campaignTextFieldsBean = new CampaignTextFieldsBean(null, null, null, null);
			campaignTextFieldsBean.setPlaceholder(placeholder);
			campaignTextFieldsBean.setName(name);
			campaignTextFieldsBean.setId(id);
			
			beanList.add(campaignTextFieldsBean);
		}
		return beanList;
	}

	/**
	 * this is the getter method for listData.
	 * @return it will return list object of DataBean type.
	 */
	public List<CampaignTextFieldsBean> getListData() {
		return listData;
	}
}
