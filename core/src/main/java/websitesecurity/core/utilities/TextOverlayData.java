package websitesecurity.core.utilities;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.replication.PathNotFoundException;

/**
 * Using this class to retrieve TextOverlay Data.
 */
public class TextOverlayData extends WCMUse {
	private String textOverlayTitle = "";
	private String textOverlayDescription = "";
	private String closeIcon = "";
	private String hoverClose = "";

	public String getTextOverlayTitle() {
		return textOverlayTitle;
	}

	public void setTextOverlayTitle(String textOverlayTitle) {
		this.textOverlayTitle = textOverlayTitle;
	}

	public String getTextOverlayDescription() {
		return textOverlayDescription;
	}

	public void setTextOverlayDescription(String textOverlayDescription) {
		this.textOverlayDescription = textOverlayDescription;
	}

	public String getCloseIcon() {
		return closeIcon;
	}

	public void setCloseIcon(String closeIcon) {
		this.closeIcon = closeIcon;
	}

	public String getHoverClose() {
		return hoverClose;
	}

	public void setHoverClose(String hoverClose) {
		this.hoverClose = hoverClose;
	}

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() throws ValueFormatException, RepositoryException  {
		try {
			String compurl = get("overlayurl", String.class);
			if (compurl != null) {
				ResourceResolver resourceResolver = getResourceResolver();
				Resource res = resourceResolver.getResource(compurl);
				Node ntFileNode2 = res.adaptTo(Node.class);
				textOverlayTitle = OverlayData(ntFileNode2,"textoverlaytitle");
				textOverlayDescription = OverlayData(ntFileNode2,"overlaytext");
				closeIcon = OverlayData(ntFileNode2,"closeicon");
				hoverClose = OverlayData(ntFileNode2,"hoverclose");
			}
		} catch (PathNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to get the textoverlaydata from provided node.
	 * @param ntFileNode2, propvalue
	 * @return textoverlaydata
	 * @throws RepositoryException 
	 * @throws javax.jcr.PathNotFoundException 
	 * @throws ValueFormatException 
	 */
	private String OverlayData(Node ntFileNode2, String propvalue) throws ValueFormatException, javax.jcr.PathNotFoundException, RepositoryException, PathNotFoundException {
		String value = null;
		value = ntFileNode2.getProperty(propvalue).getString();
		return value;
	}

	
}