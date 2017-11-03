package websitesecurity.core.utilities;

import java.util.HashMap;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * We are using this class for GSA search. It is referred by Search class.
 */
public class AcronymsMapping {

	private static AcronymsMapping acro = new AcronymsMapping();
	private HashMap<String, String> acronymsMap = new HashMap<String, String>();
	private String MAP_PATH = "/etc/designs/symantec/jcr:content/productDetail/datalayer";
	private static Logger LOG = LoggerFactory.getLogger(AcronymsMapping.class);

	private AcronymsMapping() {
	}

	// Singleton Constructor
	public static AcronymsMapping getUniqueMap() {
		return acro;
	}

	/**
	 * @param rr
	 * @return
	 * @throws LoginException
	 * @throws JSONException
	 */
	public HashMap<String, String> getAcronymsMap(ResourceResolver rr)
			throws LoginException, JSONException {
		populateMap(rr);
		return this.acronymsMap;
	}

	/**
	 * @param rr
	 * @throws LoginException
	 * @throws JSONException
	 */
	private void populateMap(ResourceResolver rr) throws LoginException,
			JSONException {
		String[] data = getAcroDataFromJCR(rr);
		if (data != null) {
			for (String one : data) {
				JSONObject acro = new JSONObject(one);
				acronymsMap.put(acro.getString("title").toLowerCase(),
						acro.getString("abbr"));
			}
		}
	}

	/**
	 * @param rr
	 * @return
	 * @throws LoginException
	 * @throws JSONException
	 */
	private String[] getAcroDataFromJCR(ResourceResolver rr) throws LoginException, JSONException {
		String[] properties = null;
			ValueMap dlComp = rr.resolve(MAP_PATH).getValueMap();
			properties = dlComp.get("acro", String[].class);
			LOG.error("Resource not present at the given path");
		return properties;
	}
	
}