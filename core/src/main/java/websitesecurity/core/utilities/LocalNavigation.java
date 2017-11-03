package websitesecurity.core.utilities;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import com.adobe.cq.sightly.WCMUse;

/**
 * We are using this class for Local navigation component.
 */
public class LocalNavigation extends WCMUse {
	private String[] links;
	private Map<String, String> map;

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() throws JSONException {
		links = getProperties().get("links", String[].class);
		if (links != null) {
			map = getValues();
		}
	}

	/**
	 * By using this method we have set max limit as 5 for local navigation.
	 * In this method we will get the link text and link path from links.
	 * @return it will return map object of String type.
	 * @throws JSONException
	 */
	private Map<String, String> getValues() throws JSONException {
		int max = 0;
		JSONObject jsonobject;
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (links.length <= 5) {
			max = links.length;
		} else {
			max = 5;
		}
		
		for (int i = 0; i < max; i++) {
			 jsonobject = new JSONObject(links[i]);
			String text = jsonobject.getString("text");
			String url = jsonobject.getString("url");
			url = PathUtility.pathCheck(url);
			url = url.replaceAll("[^a-zA-Z0-9]", "");
			url.replaceAll("\\s+", "");
			map.put(text, url);
			
		}
		return map;
	}

	/**
	 * this is the getter method for map.
	 * @return it will return map object of String type.
	 */
	public Map<String, String> getMap() {
		return map;
	}
}