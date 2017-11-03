package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import websitesecurity.core.models.SSLCertificateTopRowBean;
import com.adobe.cq.sightly.WCMUse;
import com.adobe.granite.xss.XSSAPI;

public class SSLCertificateTopRow extends WCMUse {


	private String[] sslcertificateTopRow;
	private List<SSLCertificateTopRowBean> listData;
	

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() throws JSONException {
		sslcertificateTopRow = getProperties().get("sslcertificatetoprow", String[].class);
		if(sslcertificateTopRow != null){
		   listData = getValues();
		}
	}

	private List<SSLCertificateTopRowBean> getValues() throws JSONException {
		List<SSLCertificateTopRowBean> beanList = new ArrayList<SSLCertificateTopRowBean>();
		SSLCertificateTopRowBean sslcertificatesTopRowBean;
		
		sslcertificatesTopRowBean = null;
		
		
		
		   for (int i = 0; i < sslcertificateTopRow.length; i++) {
			JSONObject jsonobject = new JSONObject(sslcertificateTopRow[i]);
			String titletext;
			titletext = jsonobject.getString("titletext");
			String imageurl;
			imageurl = jsonobject.getString("imgURL");
			String subtitletext;
			subtitletext = jsonobject.getString("subtitletext");
			String subtitlepath;
			subtitlepath = jsonobject.getString("url");
			
			
			
			sslcertificatesTopRowBean = new SSLCertificateTopRowBean();
			
			sslcertificatesTopRowBean.setTitletext(titletext);
			sslcertificatesTopRowBean.setImageUrl(imageurl);
			sslcertificatesTopRowBean.setSubtitletext(subtitletext);
			ResourceResolver resourceResolver = getResourceResolver();
			XSSAPI xssAPI = resourceResolver.adaptTo(XSSAPI.class);
			sslcertificatesTopRowBean.setSubtitlepath(PathUtility.pathCheck(xssAPI.getValidHref(subtitlepath)));
			sslcertificatesTopRowBean.setTarget(PathUtility.pathTarget(subtitlepath));
			beanList.add(sslcertificatesTopRowBean);
		}
		return beanList;
	}

	/**
	 * this is the getter method for listData.
	 * @return it will return list object of DataBean type.
	 */
	public List<SSLCertificateTopRowBean> getListData() {
		return listData;
	}
}
