package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import websitesecurity.core.models.SSLCertificateBottomRowBean;
import com.adobe.cq.sightly.WCMUse;
import com.adobe.granite.xss.XSSAPI;

public class SSLCertificatesBottomRow extends WCMUse {


	private String[] sslcertificateBottomRow;
	private List<SSLCertificateBottomRowBean> listData;
	

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() throws JSONException {
		sslcertificateBottomRow = getProperties().get("sslcertificatebottomrow", String[].class);
		if(sslcertificateBottomRow != null){
		   listData = getValues();
		}
	}

	private List<SSLCertificateBottomRowBean> getValues() throws JSONException {
		List<SSLCertificateBottomRowBean> beanList = new ArrayList<SSLCertificateBottomRowBean>();
		SSLCertificateBottomRowBean sslcertificatesBottomRowBean;
		
		sslcertificatesBottomRowBean = null;
		String fromText = "";
		String price= "";
		String perYear= "";
		String buy= "";
		String buypath= "";
		String contactsales = "";
		
		   for (int i = 0; i < sslcertificateBottomRow.length; i++) {
			JSONObject jsonobject = new JSONObject(sslcertificateBottomRow[i]);
			
			if(jsonobject.has("fromtexttext"))
			fromText = jsonobject.getString("fromtexttext");
			
			if(jsonobject.has("pricetext"))
			price = jsonobject.getString("pricetext");
			
			if(jsonobject.has("peryeartext"))
			perYear = jsonobject.getString("peryeartext");
			
			if(jsonobject.has("buytext"))
			buy = jsonobject.getString("buytext");
			
			if(jsonobject.has("buypath"))
			buypath = jsonobject.getString("buypath");
			
			if(jsonobject.has("contactsales"))
			contactsales = jsonobject.getString("contactsales");
			
			
			
			sslcertificatesBottomRowBean = new SSLCertificateBottomRowBean();
			
			sslcertificatesBottomRowBean.setFromText(fromText);
			sslcertificatesBottomRowBean.setPrice(price);
			sslcertificatesBottomRowBean.setPerYear(perYear);
			sslcertificatesBottomRowBean.setBuy(buy);
			sslcertificatesBottomRowBean.setContactsales(contactsales);
			ResourceResolver resourceResolver = getResourceResolver();
			XSSAPI xssAPI = resourceResolver.adaptTo(XSSAPI.class);
			sslcertificatesBottomRowBean.setBuypath(PathUtility.pathCheck(xssAPI.getValidHref(buypath)));
			sslcertificatesBottomRowBean.setTarget(PathUtility.pathTarget(buypath));
			
			beanList.add(sslcertificatesBottomRowBean);
		}
		return beanList;
	}

	/**
	 * this is the getter method for listData.
	 * @return it will return list object of DataBean type.
	 */
	public List<SSLCertificateBottomRowBean> getListData() {
		return listData;
	}
}
