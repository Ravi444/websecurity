package websitesecurity.core.utilities;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Locale;

import javax.jcr.PathNotFoundException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.adobe.granite.xss.XSSAPI;

import websitesecurity.core.utilities.GSASearchConfigFactoryService;

// TODO: Auto-generated Javadoc
/**
 * The Class GsaSearch.
 */
public class GsaSearch extends WCMUse {
	/** The Constant LOG. */
	public static final Logger LOG = LoggerFactory.getLogger(GsaSearch.class);

	/**
	 * Gets the log.
	 *
	 * @return the log
	 */
	public static Logger getLog() {
		return LOG;
	}

	/** The page country. */
	private String pageCountry;

	/** The page language. */
	private String pageLanguage;

	/** The search query. */
	private String searchQuery = "";

	/** The xss api. */
	private XSSAPI xssAPI;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() throws PathNotFoundException {
		searchQuery = getProperties().get("searchText", String.class);
		// Assign a default value in case the search query is empty or null
		if (searchQuery == "" || searchQuery == null) {
			searchQuery = "Security";
		}
		SlingHttpServletRequest servletRequest = getRequest();
		String requestParameter = getSearchQuery(servletRequest);
		if (StringUtils.isNotEmpty(requestParameter)) {
			searchQuery = requestParameter;
		}

		final Locale pageLocale = getCurrentPage().getLanguage(false);

		pageLanguage = pageLocale.getLanguage();
		if (StringUtils.isEmpty(pageLanguage)) {
			pageLanguage = "en";
		}
		pageCountry = pageLocale.getCountry();
		if (StringUtils.isEmpty(pageCountry) && pageLanguage.equals("en")) {
			pageCountry = "US";
		}

	}

	/**
	 * Gets the page language.
	 *
	 * @return the page language
	 */
	public String getPageLanguage() {
		return pageLanguage;
	}

	/**
	 * Gets the page locale.
	 *
	 * @return the page locale
	 */
	public String getPageLocale() {
		return pageLanguage + "_" + pageCountry;
	}

	/**
	 * Gets the results per page.
	 *
	 * @return the results per page
	 */
	public String getResultsPerPage() {
		GSASearchConfigFactoryService gsaSearchConfigFactoryService = getSlingScriptHelper()
				.getService(GSASearchConfigFactoryService.class);
		return gsaSearchConfigFactoryService
				.getPropertyValue(GSASearchConfigFactoryService.RESULTS_PER_PAGE);

	}

	/**
	 * Gets the search query.
	 *
	 * @return the search query
	 */
	public String getSearchQuery() {
		return searchQuery;
	}

	/**
	 * Gets the search query.
	 *
	 * @param request
	 *            the request
	 * @return the search query
	 */
	private String getSearchQuery(SlingHttpServletRequest request) {
		String parameterValue = StringUtils.EMPTY;
		try {

			xssAPI = getSlingScriptHelper().getService(XSSAPI.class);
			LinkedHashMap<String, String> queryMap = queryToMap(URLDecoder
					.decode(StringUtils.defaultString(request.getQueryString())
							.replace("+", "%20"), "UTF-8"));

			if (queryMap.containsKey("q")) {
				parameterValue = queryMap.get("q");
				parameterValue = xssAPI.encodeForHTMLAttr(parameterValue);
			}
		} catch (UnsupportedEncodingException e) {
			LOG.error(ExceptionUtils.getStackTrace(e));
		}

		return parameterValue;
	}

	/**
	 * Query to map.
	 *
	 * @param query
	 *            the query
	 * @return the linked hash map
	 */
	public LinkedHashMap<String, String> queryToMap(String query) {
		LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
		int index = query.lastIndexOf("&gsaSearchJson=");
		if (index != -1) {
			String q = query.substring(0, index);
			String gsaSearchJson = query.substring(index + 1);
			pair(q, result);
			pair(gsaSearchJson, result);
		} else {
			pair(query, result);
		}
		return result;
	}

	/**
	 * split "="
	 * 
	 * @param query
	 * @param result
	 */
	public void pair(String query, LinkedHashMap<String, String> result) {
		String pair[] = query.split("=");
		if (pair.length > 1)
			result.put(pair[0], pair[1]);
		else
			result.put(pair[0], "");
	}
}
