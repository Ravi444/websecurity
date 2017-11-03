package websitesecurity.core.utilities;

import org.apache.sling.api.resource.ResourceResolver;

import com.adobe.cq.sightly.WCMUse;
import com.adobe.granite.xss.XSSAPI;

/**
 * We are using this class to check whether Call Back link in Contact us
 * component is internal or external.
 */
public class SingleLinkChecker extends WCMUse {

	private String link;
	private String path;
	private String text;
	private String textPath;
	private String target;

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	/**
	 * In this method we checking the link path and assigning the target based on the link path.
	 */
	@Override
	public void activate() {
		link = getProperties().get("contactlinktext", String.class);
		path = getProperties().get("contactlinkpath", String.class);
		text = link;
		ResourceResolver resourceResolver = getResourceResolver();
		XSSAPI xssAPI = resourceResolver.adaptTo(XSSAPI.class);
		textPath = PathUtility.pathCheck(xssAPI.getValidHref(path));
		target = PathUtility.pathTarget(path);
	}

	public String getText() {
		return text;
	}

	public String getTextPath() {
		return textPath;
	}

	public String getTarget() {
		return target;
	}

}