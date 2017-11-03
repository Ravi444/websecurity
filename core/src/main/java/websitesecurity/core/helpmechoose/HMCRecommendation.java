package websitesecurity.core.helpmechoose;

import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import websitesecurity.core.models.HMCRecommendationBean;

import com.adobe.cq.sightly.WCMUse;

public class HMCRecommendation extends WCMUse {

	private final static Logger log = LoggerFactory
			.getLogger(HMCRecommendation.class);

	private List<HMCRecommendationBean> CertificateData;
	/**
	 * This is getter method for CertificateData Variable
	 * 
	 * @return it will return List object of HMCRecommendationBean type.
	 */
	private SlingHttpServletRequest httpRequest;
	private SlingHttpServletResponse httpResponse;
	

	public List<HMCRecommendationBean> getCertificateData() {
		return CertificateData;
	}

	/**
	 * This is the setter method for CertificateData Variable
	 * 
	 * @param It
	 *            will access CertificateData as input.
	 */
	public void setCertificateData(List<HMCRecommendationBean> certificateData) {
		CertificateData = certificateData;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 * 
	 * 
	 */

	@Override
	public void activate() throws Exception {

		String type = null;
		String cws = null;
		String products = null;
		String typeValue;
		String cwsValue;
		String productsValue;
		
		httpRequest = get("slingreq", SlingHttpServletRequest.class);
		
		httpResponse = get("slingres", SlingHttpServletResponse.class);
		httpResponse.setHeader("Dispatcher", "no-cache");
		
		Cookie cookie = httpRequest.getCookie("url_params");
		
		if (cookie != null) {
			String[] cookieValue = cookie.getValue().split("&");
			
			try {
				typeValue = cookieValue[0];
			} catch (Exception e) {
				typeValue = "null";
			}
			try {
				cwsValue = cookieValue[4];
			} catch (Exception e) {
				cwsValue = "null";
			}
			try {
				productsValue = cookieValue[5];
			} catch (Exception e) {
				productsValue = "null";
			}

			String[] typeData = typeValue.split("=");
			try {
				type = typeData[1];
			
			} catch (Exception e) {
				type = "null";
			}

			String[] cwsData = cwsValue.split("=");
			try {
				cws = cwsData[1];
				
			} catch (Exception e) {
				cws = "null";
			}

			String[] productsData = productsValue.split("=");
			try {
				products = productsData[1];
				
				if(products.contains("%2C")){
					products = products.replace("%2C", ":");
				}
				
				if(products.contains("%26")){
					products = products.replace("%26", "&");
				}
				
				
			} catch (Exception e) {
				products = "null";
			}



		} else {

			this.httpRequest = get("slingreq", SlingHttpServletRequest.class);

			type = this.httpRequest.getParameter("type");

			products = this.httpRequest.getParameter("products");

			cws = this.httpRequest.getParameter("cws");
		}
		
		HMCRecommendationService service = getSlingScriptHelper().getService(
				HMCRecommendationService.class);
		
		CertificateData = service.certificateCount(getCurrentPage().getPath(),
				type, products, cws);
		
		cookie.setValue(null);
		cookie.setMaxAge(0);
	}

}