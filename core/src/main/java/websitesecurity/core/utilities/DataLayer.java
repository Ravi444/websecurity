package websitesecurity.core.utilities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.servlet.http.Cookie;

import org.apache.commons.lang.NullArgumentException;
import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.settings.SlingSettingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import websitesecurity.core.models.AuthStatus;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;

/**
 * @author Rishabh_Gupta
 * We are using this class for Analytics.
 */
public class DataLayer extends WCMUse {

	private static String[] DATA_LAYER_FIELDS = { "pagetype", "resource",
			"product", "category", "subcategory", "industry", "trend",
			"services", "services-category", "services-subcategory", "custom",
			"default" };

	public HashMap<String, Set<String>> fieldMap;
	ResourceResolver rr;
	Resource res;
	Node ntFileNode;
	Value[] values;
	String tagParent;

	String tagChild;
	Tag[] pageTagsArray;
	private static Logger LOG = LoggerFactory.getLogger(DataLayer.class);

	AuthStatus auth;
	TagManager tm;

	public JSONObject datalayer;
	private SlingHttpServletRequest httpRequest;
	private SlingHttpServletResponse httpResponse;
	@Reference
	private URLMappingConfigFactoryService urlMappingConfigFactoryService;
	@Reference
	public Property prop;
	@Reference
	private ResourceResolverFactory resourceFactory;
	Cookie cookie;

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() throws PathNotFoundException {
		// rr = getResourceResolver();
		ReadService service = getSlingScriptHelper().getService(
				ReadService.class);
		pageTagsArray = service.tagsList(getCurrentPage().getPath());
		rr = service.ResourceResolverObject();
		tm = rr.adaptTo(TagManager.class);
		auth = new AuthStatus(getResourceResolver());
		urlMappingConfigFactoryService = getSlingScriptHelper().getService(
				URLMappingConfigFactoryService.class);
		    httpRequest = get("slingreq", SlingHttpServletRequest.class);
			httpResponse = get("slingres", SlingHttpServletResponse.class);
			httpResponse.setHeader("Dispatcher", "nocache");
			
	}

	/**
	 * It will fetch the tags related to datalayer.
	 * @return it will return JSONObject in string format.
	 * @throws JSONException
	 * @throws RepositoryException
	 */
	public String getDatalayer() throws JSONException, RepositoryException {
      
		
		datalayer = new JSONObject();
		prepareTagsMapFromPageProps();
		prepareTagsMapFromDialog();
		prepareNewDataLayer();
		addNewOmnitureData();
		addNonDialogFields();
		return datalayer.toString();
	}

	/**
	 *  prepares tags map from page props
	 */
	public void prepareTagsMapFromPageProps() {
		fieldMap = new HashMap<String, Set<String>>();
		for (Tag tag : pageTagsArray) {

			String parent = tag.getParent().getName().toLowerCase();

			if (fieldMap.get(parent) != null) {
				Set<String> newList = fieldMap.get(parent);
				newList.add(tag.getTagID());
				fieldMap.put(parent, newList);
			} else {
				Set<String> newList = new HashSet<String>();
				newList.add(tag.getTagID());
				fieldMap.put(parent, newList);
			}
		}
	}
	
	/**
	 *  prepares tags map from dialog
	 */
	public void prepareTagsMapFromDialog() {

		for (String key : DATA_LAYER_FIELDS) {

			String[] values = (String[]) getProperties().get(key,
					String[].class);

			if (!fieldMap.containsKey(key) && values == null) {
				fieldMap.put(key, null);
			}

			if (fieldMap.containsKey(key) && values != null) {
				Set<String> fromPageProps = fieldMap.get(key);
				for (String tag : values) {
					fromPageProps.add(tag);
				}
				fieldMap.put(key, fromPageProps);
			}

			if (!fieldMap.containsKey(key) && values != null) {
				Set<String> fromDialog = new HashSet<String>();
				for (String tag : values) {
					fromDialog.add(tag);
				}
				fieldMap.put(key, fromDialog);
			}

		}

	}

	/**
	 * This method will prepare new datalayer
	 * @throws JSONException
	 */
	public void prepareNewDataLayer() throws JSONException {
		Set<Tag> allTags = new HashSet<Tag>();

		for (String key : fieldMap.keySet()) {

			Set<String> setForKey = fieldMap.get(key);
			String[] values = null;
			if (setForKey != null) {
				values = setForKey.toArray(new String[setForKey.size()]);
			}

			if (setForKey == null) {
				if (!"custom".equalsIgnoreCase(key)
						&& !"default".equalsIgnoreCase(key))
					datalayer.put(key, "n/a");
				if ("default".equalsIgnoreCase(key))
					datalayer.put("keywords", "n/a");
			} else {
				if ("custom".equalsIgnoreCase(key)) {
					for (String one : setForKey) {
						JSONObject oneField = new JSONObject(one);
						String[] title = toStringArray((JSONArray) oneField
								.get("title"));
						addFieldToDataLayer(title[0],
								toStringArray((JSONArray) oneField.get("tags")));
					}
				} else if ("default".equalsIgnoreCase(key)) {
					addFieldToDataLayer("keywords", values);
				} else {
					addFieldToDataLayer(key, values);
				}
			}

			if (setForKey != null) {
				for (String tag : setForKey) {
					try {
						if (tm == null)
							tm = rr.adaptTo(TagManager.class);
						allTags.add(tm.resolve(tag));
					} catch (NullArgumentException e) {
						LOG.error(e.getMessage());
						LOG.debug("Error in adding tag {}", tag);
					}
				}
			}
		}

		try {

			if (tm == null)
				tm = rr.adaptTo(TagManager.class);
			Resource currPage = getCurrentPage().adaptTo(Resource.class);
			Resource jcrContent = currPage.getChild("jcr:content");
			tm.setTags(jcrContent, allTags.toArray(new Tag[allTags.size()]));
		} catch (NullArgumentException e) {
			LOG.error(e.getMessage());
			LOG.debug("Error in adding all tags to the Page properties");
		}

	}

	/**
	 * used to get the tag value from TagManager
	 * @param tagString
	 * @return
	 */
	private String getTagValue(String tagString) {
		Tag tag = null;
		try {
			if (tm == null)
				tm = rr.adaptTo(TagManager.class);
			tag = tm.resolve(tagString);
		} catch (NullArgumentException e) {
			e.printStackTrace();
		}
		if (tag != null)
			return tag.getTitle();
		else
			return "";
	}

	/**
	 * used to get the tag name from TagManager
	 * @param tagString
	 * @return
	 */
	private String getTagName(String tagString) {
		Tag tag = null;
		try {
			if (tm == null)
				tm = rr.adaptTo(TagManager.class);
			tag = tm.resolve(tagString);
		} catch (NullArgumentException e) {
			LOG.error(e.getMessage());
			LOG.debug("Error in resolving the tag for tagname : {}", tag);
		}
		if (tag != null)
			return tag.getName();
		else
			return "";
	}

	/**
	 * used to add the muliple values corresponding to key to datalayer
	 * @param key
	 * @param values
	 * @throws JSONException
	 */
	private void addFieldToDataLayer(String key, String[] values)
			throws JSONException {
		if (values.length == 0) {
			datalayer.put(key, "n/a");
		} else if (values.length == 1) {
			datalayer.put(key, getTagValue(values[0]));
		} else {
			JSONArray valuesArray = new JSONArray();
			for (String one : values) {
				valuesArray.put(getTagValue(one));
			}
			datalayer.put(key, valuesArray);
		}
	}

	/**
	 * This method is used to get String array from JSON Array
	 * @param jsonArray
	 * @return
	 * @throws JSONException
	 */
	private static String[] toStringArray(JSONArray jsonArray)
			throws JSONException {
		String[] stringArray = null;
		int length = jsonArray.length();
		if (jsonArray != null) {
			stringArray = new String[length];
			for (int i = 0; i < length; i++) {
				stringArray[i] = jsonArray.getString(i);
			}
		}
		return stringArray;
	}

	/**
	 * @throws JSONException
	 * used to add non dialog fields to datalayer json
	 */
	private void addNonDialogFields() throws JSONException {
		JSONObject om = datalayer.getJSONObject("om");
		datalayer.put("language", om.get("site_language"));
		datalayer.put("country", om.get("site_country"));
	}

	/**
	 * @param pathArray
	 * @param om
	 * @throws JSONException
	 * used to set page language and country to datalayer json
	 */
	private void setPageLanguageCountry(String[] pathArray, JSONObject om)
			throws JSONException {
		int length = pathArray.length;
		StringBuilder path = new StringBuilder();
		path.append("/content/websitesecurity/");
		for (int i = 0; i < 2 && i < length; i++) {
			LOG.debug("Path to map {}", pathArray[i] + i);
			path.append(pathArray[i]);
			path.append("/");
		}

		LOG.debug("Map Path with lgCtMap = {}", urlMappingConfigFactoryService
				.getDataLayerMapping(path.toString()));

		String mappedPath = urlMappingConfigFactoryService
				.getDataLayerMapping(path.toString());
		String[] mappedPathArray = mappedPath.split("/");
		if (mappedPathArray.length >= 3) {
			om.put("site_country", mappedPathArray[2]);
			om.put("site_language", mappedPathArray[1]);
		}
	}

	/**
	 * @throws JSONException
	 * to add new page properties tagged in to the datalayer json
	 */
	void addNewOmnitureData() throws JSONException {
		JSONObject om = new JSONObject();

		Page currPage = getCurrentPage();
		String pagePath = currPage.getPath();
		pagePath = pagePath.substring(28);
		LOG.debug("after substring:-" + pagePath);
		String[] urlParsed = pagePath.split("/");
		LOG.debug("splitted url length:-" + urlParsed);
		om.put("site_country", "us");
		om.put("site_language", "en");
		setPageLanguageCountry(urlParsed, om);
		SlingSettingsService rm = getSlingScriptHelper().getService(
				SlingSettingsService.class);
		Set<String> runModes = rm.getRunModes();
		om.put("account", "veritassymantecwebsitesecuritydev");
		for (String runMode : runModes) {
			if (runMode.contains("prod")) {
				om.put("account", "veritassymantecwebsitesecurity");
				break;
			}
		}

		om.put("content_format", "html");
		om.put("content_type", "page");
		om.put("content_title", getCurrentPage().getPageTitle());

		LOG.debug("isLoggedin = ", auth.isLoggedIn());
		if (!auth.isLoggedIn()) {
			om.put("signed_in", "signed out");
		} else {
			om.put("signed_in", "signed in");
		}

		String AEM_PAGE_NAME = getCurrentPage().getName();
		LOG.info("current page path:-"+AEM_PAGE_NAME);
		String SITE_SECTION = "";
		String SITE_SUB_SECTION = "";
		String SITE_SUB_SUB_SECTION = "";
		String PAGE_NAME = "";

		Set<String> site_section_set = fieldMap.get("site-section");
		if (site_section_set != null) {
			for (String one : site_section_set) {
				SITE_SECTION = getTagName(one);
				om.put("site_section", SITE_SECTION);
			}
		} else {
			SITE_SECTION = "na";
			om.put("site_section", "na");
		}

		Set<String> site_sub_section_set = fieldMap.get("site-sub-section");
		if (site_sub_section_set != null) {
			for (String one : site_sub_section_set) {
				SITE_SUB_SECTION = getTagName(one);
				om.put("site_sub_section", SITE_SUB_SECTION);
			}
		} else {
			SITE_SUB_SECTION = "na";
			om.put("site_sub_section", "na");
		}

		Set<String> site_sub_sub_section_set = fieldMap
				.get("site-sub-sub-section");
		if (site_sub_sub_section_set != null) {
			for (String one : site_sub_sub_section_set) {
				SITE_SUB_SUB_SECTION = getTagName(one);
				om.put("site_sub_sub_section", SITE_SUB_SUB_SECTION);
			}
		} else {
			SITE_SUB_SUB_SECTION = "na";
			om.put("site_sub_sub_section", "na");
		}
		Set<String> page_name_set = fieldMap.get("custom-name");
		if (page_name_set != null) {
			for (String one : page_name_set) {

				PAGE_NAME = getTagName(one);

				om.put("page_name", AEM_PAGE_NAME + ":" + PAGE_NAME);

			}
		} else {
			if(getCurrentPage().getName().toString().equalsIgnoreCase("recommendation")){
			om.put("page_name", "step 4 recommendation");
			cookie = httpRequest.getCookie("hmc_result");
			if(cookie.getValue()!= null)
			{
			 String hmcresult = cookie.getValue();
			 om.put("hmc_result", hmcresult);
			 cookie.setValue(null);
			 cookie.setMaxAge(0);
			}
			}
			else
			om.put("page_name", AEM_PAGE_NAME);	
		}
		
		datalayer.put("om", om);
	}

}