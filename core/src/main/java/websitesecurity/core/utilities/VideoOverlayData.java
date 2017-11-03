package websitesecurity.core.utilities;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.replication.PathNotFoundException;

/**
 * Using this class to retrieve VideoOverlay Data.
 */
public class VideoOverlayData extends WCMUse {
	private String playerID = "";
	private String playerKey = "";
	private String videoID = "";
	private String embedCode = "";
	private String vendor = "";
	private String closeIcon = "";
	private String hoverClose = "";
	private String videoTitle = "";

	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}

	public String getPlayerKey() {
		return playerKey;
	}

	public void setPlayerKey(String playerKey) {
		this.playerKey = playerKey;
	}

	public String getVideoID() {
		return videoID;
	}

	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}

	public String getEmbedCode() {
		return embedCode;
	}

	public void setEmbedCode(String embedCode) {
		this.embedCode = embedCode;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
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

	public String getVideoTitle() {
		return videoTitle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() throws PathNotFoundException, ValueFormatException, RepositoryException {
		try {
			String compurl = get("overlayurl", String.class);
			if (compurl != null) {
				ResourceResolver resourceResolver = getResourceResolver();
				Resource res = resourceResolver.getResource(compurl);
				Node ntFileNode2 = res.adaptTo(Node.class);
				playerID = videoOverlayData(ntFileNode2, "playerID");
				playerKey = videoOverlayData(ntFileNode2, "playerKey");
				videoID = videoOverlayData(ntFileNode2, "videoID");
				embedCode = videoOverlayData(ntFileNode2, "embedCode");
				vendor = videoOverlayData(ntFileNode2, "vendor");
				closeIcon = videoOverlayData(ntFileNode2, "closeicon");
				hoverClose = videoOverlayData(ntFileNode2, "hoverclose");
				videoTitle = videoOverlayData(ntFileNode2, "videotitle");
			}
		} catch (PathNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is use to get the videoOverlayData from the given node.
	 * 
	 * @param ntFileNode2 , videoData
	 * @return data
	 * @throws RepositoryException 
	 * @throws  
	 * @throws ValueFormatException 
	 */
	private String videoOverlayData(Node ntFileNode2, String videoData) throws ValueFormatException, RepositoryException, PathNotFoundException {
		String data = null;
		data = ntFileNode2.getProperty(videoData).getString();
		return data;
	}
}