package websitesecurity.core.utilities;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Set;

import org.apache.commons.lang.NullArgumentException;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.apache.sling.settings.SlingSettingsService;
import org.osgi.framework.Constants;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Using this class for GSA Search.
 */
@Component(immediate = true, label = "Symantec Dispatcher URL Mapping Configuration", description = "Symantec Dispatcher URL Mapping Configuration", metatype = true)
@Properties({
		@Property(name = Constants.SERVICE_DESCRIPTION, value = "Symantec Dispatcher URL Mapping Configuration"),
		@Property(name = Constants.SERVICE_VENDOR, value = "Symantec") })
@Service(value = URLMappingConfigFactoryService.class)
public class URLMappingConfigFactoryService {
	private static final Logger LOG = LoggerFactory
			.getLogger(URLMappingConfigFactoryService.class);
	private boolean isRmode = false;
	@Reference
	private SlingSettingsService mSlingSettingsService;

	@Property(label = "URL Pattern", value = { "default", "/es/mx/", "/pt/br/",
			"/fr/ca/", "/en/uk/", "/fr/fr/", "/de/de/", "/en/ca/", "/en/aa/",
			"/ja/jp/", "/zh/cn/", "/ko/kr/", "/en/au/", "/en/hk/", "/en/in/",
			"/en/sg/" }, description = "Dispatcher URL Pattern, 'default' or leave it empty means www.symantec.com")
	public static final String URL_PATTERN = "urlPattern";

	@Property(label = "AEM folder path", value = {
			"/content/symantec/english/en/",
			"/content/symantec/spanish/es_mx/",
			"/content/symantec/portuguese/pt_br/",
			"/content/symantec/french/fr_ca/",
			"/content/symantec/english/en_gb/",
			"/content/symantec/french/fr_fr/",
			"/content/symantec/german/de_de/",
			"/content/symantec/english/en_ca/",
			"/content/symantec/english/en_aa/",
			"/content/symantec/japanese/jp_ja/",
			"/content/symantec/simplifiedchinese/cn_zh/",
			"/content/symantec/korean/ko_kr/",
			"/content/symantec/english/en_au/",
			"/content/symantec/english/en_hk/",
			"/content/symantec/english/en_in/",
			"/content/symantec/english/en_sg/" }, description = "Please enter the Corresponding AEM folder path with the same order")
	public static final String FOLDER_PATH = "folderPath";

	private static Dictionary<?, ?> props = null;

	/**
	 * @param key
	 * @return
	 */
	public String[] getMultiPropertyValue(String key) {
		return PropertiesUtil.toStringArray(props.get(key));
	}

	// Holds the key-value pairs for Datalayer.
	private HashMap<String, String> lgCtMap = null;

	// Holds the key-value pairs key=/lg/ct and value=AEM folder path.
	private HashMap<String, String> lgCtToFolderPathMap = null;

	/**
	 * Method called when Sling component is activated
	 * 
	 * @param context
	 *            - ComponentContext reference
	 */
	protected void activate(ComponentContext context) {
		try {
			props = context.getProperties();
			Set<String> runModes = mSlingSettingsService.getRunModes();
			if (runModes.contains("publish")) {
				if (runModes.contains("uat") || runModes.contains("prod")) {
					isRmode = true;
				}
			}
			prepareURLMap();
			preparelgCtToFolderPathMap();
			LOG.info("Symantec Dispatcher URL Mapping Configuration activated ...");

		} catch (NullArgumentException e) {
			LOG.error(
					"Symantec Dispatcher URL Mapping: Error occured in activate method: ",
					e);
		}

	}

	/**
	 * @param path
	 * @return
	 */
	public String mapPath(String path) {
		if (getRunModes()) {
			String[] urlPattern = getMultiPropertyValue(URL_PATTERN);
			String[] folderPath = getMultiPropertyValue(FOLDER_PATH);
			if (urlPattern.length == folderPath.length) {
				for (int urlIndex = 0; urlIndex < urlPattern.length; urlIndex++) {
					if (urlPattern[urlIndex].equals("default")) {
						path = path.replace(folderPath[urlIndex], "/");
					} else {
						path = path.replace(folderPath[urlIndex],
								urlPattern[urlIndex]);
					}
				}
			} else {
				path = path.replace("/content/symantec/english/en/", "/");
			}
		} else {
			if (path.startsWith("/content/symantec"))
				path = path + ".html";
		}
		return path;
	}

	/**
	 * @param path
	 * @return
	 */
	public String mapPathWithOutRunMode(String path) {
		LOG.debug("The path : {}", path);
		String[] urlPattern = getMultiPropertyValue(URL_PATTERN);
		String[] folderPath = getMultiPropertyValue(FOLDER_PATH);
		if (urlPattern.length == folderPath.length) {
			for (int urlIndex = 0; urlIndex < urlPattern.length; urlIndex++) {
				LOG.debug("The path : {}", urlPattern[urlIndex] + " : "
						+ folderPath[urlIndex]);
				if (urlPattern[urlIndex].equals("default")) {
					path = path.replace(folderPath[urlIndex], "/");
				} else {
					LOG.debug("Before path : {}", path);
					path = path.replace(folderPath[urlIndex],
							urlPattern[urlIndex]);
					LOG.debug("After path :", path);
				}
			}
		} else {
			path = path.replace("/content/symantec/english/en/", "/");
		}
		return path;
	}

	/*
	 * Converts the language country folder structure to standard two chars
	 * country and language code, based on the values in the config.
	 */
	public String getDataLayerMapping(String path) {
		if (lgCtMap != null) {
			LOG.debug("lgctmap {}", lgCtMap);
			return lgCtMap.get(path) == null ? "/en/us" : lgCtMap.get(path);
		} else {
			return "/en/us/";
		}
	}

	// Prepare the Map for key-values paths, key = AEM Folder and value = URL
	// pattern
	void prepareURLMap() {
		lgCtMap = new HashMap<String, String>();
		LOG.debug("Preparing URL lg/ct Map");
		String[] urlPattern = getMultiPropertyValue(URL_PATTERN);
		String[] folderPath = getMultiPropertyValue(FOLDER_PATH);
		if (urlPattern.length == folderPath.length) {
			for (int urlIndex = 0; urlIndex < urlPattern.length; urlIndex++) {
				lgCtMap.put(folderPath[urlIndex], urlPattern[urlIndex]);
			}
		}

	}

	// Prepare the Map for key-values paths, key = URL pattern and value = AEM
	// Folder
	void preparelgCtToFolderPathMap() {
		lgCtToFolderPathMap = new HashMap<String, String>();
		LOG.debug("Preparing URL lgCtToFolderPathMap");
		String[] urlPattern = getMultiPropertyValue(URL_PATTERN);
		String[] folderPath = getMultiPropertyValue(FOLDER_PATH);
		if (urlPattern.length == folderPath.length) {
			for (int urlIndex = 0; urlIndex < urlPattern.length; urlIndex++) {
				lgCtToFolderPathMap.put(urlPattern[urlIndex],
						folderPath[urlIndex]);
			}
		}
	}

	public HashMap<String, String> getLgCtToFolderPathMap() {
		return lgCtToFolderPathMap;
	}

	private boolean getRunModes() {
		return isRmode;
	}
}