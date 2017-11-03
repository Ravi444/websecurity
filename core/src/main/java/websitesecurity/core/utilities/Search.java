package websitesecurity.core.utilities;

import java.util.HashMap;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.query.InvalidQueryException;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;
import websitesecurity.core.utilities.URLMappingConfigFactoryService;

/**
 * We are using this class for GSA Search.
 */
@Component(immediate = true, metatype = false)
@Properties({
		@Property(name = Constants.SERVICE_DESCRIPTION, value = " Product A-Z Search"),
		@Property(name = Constants.SERVICE_VENDOR, value = "Symantec") })
@Service(value = Search.class)
public class Search {

	@Reference
	private URLMappingConfigFactoryService mapService;
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(Search.class);

	public JSONArray getSearchResult(ResourceResolver resourceResolver,
			QueryManager manager, String[] prodTags, String productTag,
			String searchPath) throws InvalidQueryException, RepositoryException, JSONException   {
		return doSearch(resourceResolver, manager, null, prodTags, productTag,
				searchPath);
	}

	public JSONArray getSearchResult(ResourceResolver resourceResolver,
			QueryManager manager, String keywords, String productTag,
			String searchPath) throws InvalidQueryException, RepositoryException, JSONException {
		return doSearch(resourceResolver, manager, keywords, productTag,
				searchPath);
	}

	/**
	 * Return the products filtered by product tags and category.
	 * 
	 * @param rr
	 * @param queryManager
	 * @param searchPath
	 * @param cateTags
	 * @param prodTags
	 * @return
	 * @throws Exception
	 */
	public JSONArray getFliterResult(ResourceResolver resourceResolver,
			QueryManager manager, String[] ctgTags, String[] prodTags,
			String productTag, String searchPath) throws InvalidQueryException, RepositoryException, JSONException   {
		return doSearch(resourceResolver, manager, ctgTags, prodTags,
				productTag, searchPath);
	}

	public JSONArray getSuggestion(ResourceResolver resourceResolver,
			QueryManager manager, String searchPath) throws InvalidQueryException, RepositoryException, LoginException, JSONException  {
		HashMap<String, String> map = AcronymsMapping.getUniqueMap()
				.getAcronymsMap(resourceResolver);
		JSONArray results = new JSONArray();
		Node node;
		String sql = "SELECT * FROM [nt:base] AS s WHERE ISDESCENDANTNODE(["
				+ searchPath + "])";
		Query query = manager.createQuery(sql, Query.JCR_JQOM);
		QueryResult queryResult = query.execute();

		NodeIterator ni = queryResult.getNodes();
		while (ni.hasNext()) {
			 node = (Node) ni.next();
			Tag tag = resourceResolver.getResource(node.getPath()).adaptTo(
					Tag.class);
			JSONObject j = new JSONObject();
			j.put("value", getAbbreviatedValue(tag.getTitle(), map));
			j.put("data", tag.getTagID());
			results.put(j);
		}
		return results;
	}

	public JSONArray getTitleSuggestion(ResourceResolver resourceResolver,
			QueryManager manager, String productTag, String searchPath) throws LoginException, JSONException, InvalidQueryException, RepositoryException
			 {
		HashMap<String, String> map = AcronymsMapping.getUniqueMap()
				.getAcronymsMap(resourceResolver);
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM [cq:PageContent] AS s WHERE ISDESCENDANTNODE([")
				.append(searchPath).append("]) AND s.[cq:tags] IS NOT NULL")
				.append(" AND s.[cq:tags]='").append(productTag).append("'");
		Query query = manager.createQuery(sql.toString(), Query.JCR_JQOM);
		QueryResult queryResult = query.execute();
		NodeIterator ni = queryResult.getNodes();
		JSONArray result = new JSONArray();
		Node prodNode;
		Page prodPage;
		JSONObject prodJsonObject;
		
		while (ni.hasNext()) {
			 prodNode = (Node) ni.next();
			 prodPage = resourceResolver.getResource(
			prodNode.getParent().getPath()).adaptTo(Page.class);
			 prodJsonObject = new JSONObject();
			prodJsonObject.put(
					"value",
					getAbbreviatedValue(
							prodPage.getProperties().get(
									"relatedcardtitle",
									prodPage.getProperties().get("jcr:title",
											"")), map));
			prodJsonObject.put(
					"data",
					prodPage.getProperties().get("relatedcardtitle",
							prodPage.getProperties().get("jcr:title", "")));
			result.put(prodJsonObject);
		}
		return result;

	}

	public JSONArray doSearch(ResourceResolver resourceResolver,
			QueryManager manager, String[] ctgTags, String[] prodTags,
			String productTag, String searchPath) throws InvalidQueryException, RepositoryException, JSONException  {

		StringBuffer sql = new StringBuffer(
				"SELECT * FROM [cq:PageContent] AS s WHERE ISDESCENDANTNODE([")
				.append(searchPath).append("]) AND s.[cq:tags] IS NOT NULL")
				.append(" AND s.[cq:tags]='").append(productTag).append("'");

		if (prodTags != null) {
			for (String tag : prodTags) {
				sql.append(" AND s.[cq:tags]='").append(tag).append("'");
			}
		}
		if (ctgTags != null) {
			for (String tag : ctgTags) {
				sql.append(" AND s.[cq:tags]='").append(tag).append("'");
			}
		}
		Query query = manager.createQuery(sql.toString(), Query.JCR_JQOM);
		QueryResult queryResult = query.execute();
		NodeIterator ni = queryResult.getNodes();
		JSONArray result = new JSONArray();
		Node prodNode;
		Page prodPage;
		JSONObject prodJsonObject;
		String desc;
		String path;
		JSONArray tagName;
		
		while (ni.hasNext()) {
		    prodNode = (Node) ni.next();
		    prodPage = resourceResolver.getResource(
			prodNode.getParent().getPath()).adaptTo(Page.class);
		    prodJsonObject = new JSONObject();
			prodJsonObject.put(
					"title",
					prodPage.getProperties().get("relatedcardtitle",
							prodPage.getProperties().get("jcr:title", "")));
			 desc = prodPage.getProperties().get(
					"relatedcarddescription", "");
			if (desc.isEmpty()) {
				desc = prodPage.getProperties().get("jcr:description", "");
			}
			prodJsonObject.put("desc", desc);
			prodJsonObject.put(
					"image",
					prodPage.getProperties("image").get("fileReference",
							String.class));
			 path = prodPage.getPath();
			prodJsonObject.put("url", mapService.mapPath(path));
		    tagName = new JSONArray();
			Tag[] allTag = prodPage.getTags();
			for (Tag tag : allTag) {
				tagName.put(tag.getTagID());
			}
			prodJsonObject.put("tags", tagName);
			result.put(prodJsonObject);
		}
		return result;
	}

	public JSONArray doSearch(ResourceResolver resourceResolver,
			QueryManager manager, String keyword, String productTag,
			String searchPath) throws InvalidQueryException, RepositoryException, JSONException  {

		StringBuffer sql = new StringBuffer(
				"SELECT * FROM [cq:PageContent] AS s WHERE ISDESCENDANTNODE([")
				.append(searchPath).append("]) AND s.[cq:tags] IS NOT NULL")
				.append(" AND s.[cq:tags]='").append(productTag).append("'");

		if (keyword != null) {
			sql.append(" AND (LOWER(s.[jcr:title]) like '%")
					.append(keyword.toLowerCase()).append("%'");
			sql.append(" OR LOWER(s.[relatedcardtitle]) like '%")
					.append(keyword.toLowerCase()).append("%')");
		}
		Query query = manager.createQuery(sql.toString(), Query.JCR_JQOM);
		QueryResult queryResult = query.execute();
		NodeIterator ni = queryResult.getNodes();
		JSONArray result = new JSONArray();
		Node prodNode;
		Page prodPage;
		JSONObject prodJsonObject;
		String desc;
		String path;
		
		while (ni.hasNext()) {
			 prodNode = (Node) ni.next();
			 prodPage = resourceResolver.getResource(
					prodNode.getParent().getPath()).adaptTo(Page.class);
			 prodJsonObject = new JSONObject();
			prodJsonObject.put(
					"title",
					prodPage.getProperties().get("relatedcardtitle",
							prodPage.getProperties().get("jcr:title", "")));
			 desc = prodPage.getProperties().get(
					"relatedcarddescription", "");
			if (desc.isEmpty()) {
				desc = prodPage.getProperties().get("jcr:description", "");
			}
			prodJsonObject.put("desc", desc);
			prodJsonObject.put(
					"image",
					prodPage.getProperties("image").get("fileReference",
							String.class));
			 path = prodPage.getPath();
			prodJsonObject.put("url", mapService.mapPath(path));
			JSONArray tagName = new JSONArray();
			Tag[] allTag = prodPage.getTags();
			for (Tag tag : allTag) {
				tagName.put(tag.getTagID());
			}
			prodJsonObject.put("tags", tagName);
			result.put(prodJsonObject);
		}
		return result;
	}

	public String getAbbreviatedValue(String value, HashMap<String, String> map) {
		if (map.containsKey(value.toLowerCase())) {
			value = value + " (" + map.get(value.toLowerCase()) + ")";
		}
		return value;
	}
}