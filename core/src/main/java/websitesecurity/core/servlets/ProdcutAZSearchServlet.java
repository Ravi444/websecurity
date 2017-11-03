package websitesecurity.core.servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.QueryManager;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.servlets.post.JSONResponse.JSONResponseException;
import org.apache.sling.settings.SlingSettingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import websitesecurity.core.utilities.Search;

/**
 * We are using this Servlet for GSA Search.
 */
@SlingServlet(paths = { "/bin/websitesecurity/prodcutazsearch" })
public class ProdcutAZSearchServlet extends SlingAllMethodsServlet {

	private static final long serialVersionUID = -4924283668996701424L;
	private static Logger LOG = LoggerFactory
			.getLogger(ProdcutAZSearchServlet.class);
	@Reference
	private Search search;

	ResourceResolver resourceResolver;
	/*
	 * @Reference private SlingSettingsService mSlingSettingsService;
	 */

	@Reference
	private SlingSettingsService sling;
	public static final String jsonFileName = "/content/dam/websitesecurity/gsaautosuggest/allProducts.json";

	/**
	 * @param s
	 * @return
	 */
	private String[] parseTag(String s) {
		if (s == null || s.trim().length() == 0) {
			return null;
		}
		if (s.endsWith("|")) {
			s = s.substring(0, s.length() - 1);
		}
		return s.split("\\|");
	}

	/* (non-Javadoc)
	 * @see org.apache.sling.api.servlets.SlingAllMethodsServlet#doPost(org.apache.sling.api.SlingHttpServletRequest, org.apache.sling.api.SlingHttpServletResponse)
	 */
	@Override
	protected void doPost(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
		String type = request.getParameter("type");
		String[] proTags = parseTag(request.getParameter("proTags"));
		String[] cateTags = parseTag(request.getParameter("categories"));
		String searchPath = request.getParameter("searchPath");
		String productTag = request.getParameter("productTag");
		String keyword = request.getParameter("keyword");
		if (keyword != null) {
			keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
		}
		JSONArray result = null;
		try {
			resourceResolver = request.getResourceResolver();
			QueryManager manager = getQueryManager(resourceResolver);
			if ("suggest".equals(type)) {
				result = search.getTitleSuggestion(resourceResolver, manager,
						productTag, searchPath);
			} else if ("filter".equals(type)) {
				result = search.getFliterResult(resourceResolver, manager,
						cateTags, proTags, productTag, searchPath);
			} else if ("search".equals(type)) {
				result = search.getSearchResult(resourceResolver, manager,
						keyword, productTag, searchPath);
			} else if ("tagSuggestion".equals(type)) {
				result = search.getSuggestion(resourceResolver, manager,
						searchPath);
			}

			// Set<String> runModes =
			// sling.getService(SlingSettingsService.class).getRunModes();
			Set<String> runmodes = sling.getRunModes();

			if (runmodes.contains("author")) {
				LOG.info("IT is in Author");
				String jsonArrayInString = null;
				InputStream fin = null;
				if (result != null) {
					jsonArrayInString = result.toString();
					fin = new ByteArrayInputStream(jsonArrayInString.getBytes());
					writeToDam(fin, jsonFileName);
				}
			} else {
				LOG.info("IT is in publisher");
			}

		} catch (JSONException e) {
			LOG.error("Exception of search filter component. [type=" + type
					+ "]");
			LOG.error("{}", e);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (result == null) {
				result = new JSONArray();
			}
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result.toString());
			response.getWriter().flush();
		}
	}

	/**
	 * @param isd
	 * @param fileName
	 * @return
	 */
	private String writeToDam(InputStream isd, String fileName) {
		try {
			// Use AssetManager to place the file into the AEM DAM
			com.day.cq.dam.api.AssetManager assetMgr = resourceResolver
					.adaptTo(com.day.cq.dam.api.AssetManager.class);
			String newFile = fileName;
			assetMgr.createAsset(newFile, isd, "application/json", true);
		} catch (JSONResponseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.sling.api.servlets.SlingSafeMethodsServlet#doGet(org.apache.sling.api.SlingHttpServletRequest, org.apache.sling.api.SlingHttpServletResponse)
	 */
	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
		doPost(request, response);
	}

	/**
	 * @param resourceResolver
	 * @return
	 * @throws RepositoryException 
	 * @throws Exception
	 */
	private QueryManager getQueryManager(ResourceResolver resourceResolver)
			throws RepositoryException {
		Session session = resourceResolver.adaptTo(Session.class);
		return session.getWorkspace().getQueryManager();
	}

}
