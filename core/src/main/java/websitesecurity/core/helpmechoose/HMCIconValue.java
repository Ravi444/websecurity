package websitesecurity.core.helpmechoose;

import javax.servlet.http.Cookie;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;

public class HMCIconValue extends WCMUse {

	private final static Logger log = LoggerFactory
			.getLogger(HMCIconValue.class);

	private String IconData;
	private SlingHttpServletRequest httpRequest;
	private SlingHttpServletResponse httpResponse;
	/**
	 * This is getter method for IconData Variable
	 * 
	 * @return it will return String of IconData type.
	 */
	
	public String getIconData() {
		return IconData;
	}
	
	/**
	 * This is the setter method for IconData Variable
	 * 
	 * @param It will access IconData as input.
	 *            
	 */
	public void setIconData(String iconData) {
		IconData = iconData;
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
			String typeValue;
			
			
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
				

				String[] typeData = typeValue.split("=");
				try {
					type = typeData[1];
				
				} catch (Exception e) {
					type = "null";
				}
				
			} else {

				this.httpRequest = get("slingreq", SlingHttpServletRequest.class);

				type = this.httpRequest.getParameter("type");

			}
		
		IconData = type;
	
		
		
	}


	

}