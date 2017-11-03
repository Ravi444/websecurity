package websitesecurity.core.servlets;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.jcr.ValueFormatException;
import javax.servlet.ServletException;

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

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;

/**
 * We are using this servlet to retrieve the child categories in the use case filter component.
 */
@SlingServlet(paths = "/bin/websitesecurity/filtercategorychilds", methods = "GET", metatype = false)
public class FilterCategoryChilds extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 2598426539166789515L;

	@Reference
	private ResourceResolverFactory resolverFactory;
	JSONObject compname;
	JSONObject parent = new JSONObject();

	String grid = "";

	@SuppressWarnings("rawtypes")
	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServerException, IOException {
		Node node;
		Value[] category;
		String l;
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
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		

		String categoryparent = request.getParameter("selectedval");
		Resource res = resourceResolver.resolve(resourcePath);
		Map<String, String> map = new HashMap<String, String>();
		JSONArray jsonArray = new JSONArray();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		map.put("path", res.getPath() + "/jcr:content");
		map.put("type", "nt:unstructured");
		map.put("nodename", "pagination_filter*");

		// Quering JCR using com.day.cq.search.QueryBuilder API
		PredicateGroup predicateGroup = PredicateGroup.create(map);
		QueryBuilder builder = request.getResourceResolver().adaptTo(QueryBuilder.class);
		Query query = builder.createQuery(predicateGroup, request.getResourceResolver().adaptTo(Session.class));
		SearchResult result = query.getResult();
		Iterator<Node> nt = result.getNodes();

		while (nt.hasNext()) {
			node = (Node) nt.next();
			try {
				category = node.getProperty("filterdata").getValues();
				List<String> childs1 = null;
				for (int i = 0; i < category.length; i++) {
					String categorychild;
					String aa = category[i].toString();
					if (aa.toLowerCase().contains(categoryparent.toLowerCase())) {
						String data[] = aa.split("\\|");
						if (data.length > 1) {
							childs1 = new ArrayList<String>();
							categorychild = data[1];
							String data1[] = categorychild.split(",");
							childs1.add("(None)");
							for (int j = 0; j < data1.length; j++) {
								childs1.add(data1[j]);
							}
						}
					}
				}
				

				for (int k = 0; k < childs1.size(); k++) {
					l = childs1.get(k).replaceAll("[^a-zA-Z0-9]", "");
					l = l.replaceAll("\\s+", "");
					JSONObject abc1 = new JSONObject();
					if (childs1.get(k).equalsIgnoreCase("(None)")) {
						abc1.put("text", childs1.get(k));
						abc1.put("value", " ");
						jsonArray.put(abc1);
					} else {
						abc1.put("text", childs1.get(k));
						abc1.put("value", k + categoryparent);
						jsonArray.put(abc1);
					}
				}
			} catch (PathNotFoundException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (ValueFormatException e) {
				e.printStackTrace();
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		}

		response.getWriter().write(jsonArray.toString());
	}
}