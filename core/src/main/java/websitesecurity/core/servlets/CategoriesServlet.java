package websitesecurity.core.servlets;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.ServerException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import com.day.cq.replication.PathNotFoundException;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;

/**
 * We are using this servlet to retrieve the categories in use case filter component.
 * 
 */
@SlingServlet(paths = "/bin/websitesecurity/filtercategories", methods = "GET", metatype = false)
public class CategoriesServlet extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 2598426539166789515L;

	@Reference
	private ResourceResolverFactory resolverFactory;

	@SuppressWarnings("rawtypes")
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServerException, IOException {
		Enumeration values = request.getHeaders("referer");
		String url = null;
		if (values != null) {
			while (values.hasMoreElements()) {
				url = (String) values.nextElement();
			}
		}
		
		ResourceResolver resourceResolver = request.getResourceResolver();
		String resourcePath = null;
		try {
			resourcePath = new URI(url).getPath();
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		Resource res = resourceResolver.resolve(resourcePath);

		Map<String, String> map = new HashMap<String, String>();
		JSONArray jsonArray = new JSONArray();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		map.put("path", res.getPath() + "/jcr:content");
		map.put("type", "nt:unstructured");
		map.put("nodename", "pagination_filter");
		PredicateGroup predicateGroup = PredicateGroup.create(map);
		QueryBuilder builder = request.getResourceResolver().adaptTo(QueryBuilder.class);
		Query query = builder.createQuery(predicateGroup, request.getResourceResolver().adaptTo(Session.class));
		SearchResult result = query.getResult();
		Iterator<Node> nt = result.getNodes();

		while (nt.hasNext()) {
			Node node = (Node) nt.next();
			JSONObject jsonObject;
			try {
				Value[] category = node.getProperty("category").getValues();
				for (int i = 0; i < category.length; i++) {
					jsonObject = new JSONObject();
					jsonObject.put("text", category[i]);
					jsonObject.put("value", category[i]);
					jsonArray.put(jsonObject);
				}
			} catch (RepositoryException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		response.getWriter().write(jsonArray.toString());
	}
}