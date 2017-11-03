package websitesecurity.core.utilities;

import java.util.LinkedHashMap;
import java.util.Map;

import com.adobe.cq.sightly.WCMUse;

/**
 * @author Vasudev_Munagala
 * We are using this class to retrieve icons in the footer component.
 */
public class FooterBottomIcons extends WCMUse {
	private String[] iconclass;
	private String[] iconlink;
	private Map<String, String> map;

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() {
		iconclass = getProperties().get("iconclass", String[].class);
		iconlink = getProperties().get("iconlink", String[].class);
		map = getValues();
	}

	/**
	 * This method is used to create a map by using iconclass and iconlink.
	 * @return it will return map object of string type.
	 */
	private Map<String, String> getValues() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (iconclass != null && iconlink != null) {
			for (int i = 0; i < iconclass.length; i++) {
				map.put(iconclass[i], iconlink[i]);
			}
		}
		return map;
	}

	/**
	 * this is the getter method for map.
	 * @return it will return map object of string type.
	 */
	public Map<String, String> getMap() {
		return map;
	}
}