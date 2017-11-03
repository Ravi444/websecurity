package websitesecurity.core.servlets;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.ServerException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

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
 * We are using this servlet to retrieve the overlays present in the page.
 */
@SlingServlet(paths = "/bin/websitesecurity/overlayoptions", methods = "GET", metatype = false)
public class OverlayServlet extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 2598426539166789515L;

	@Reference
	private ResourceResolverFactory resolverFactory;

	@SuppressWarnings("rawtypes")
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServerException, IOException {
		Node node;
		ResourceResolver resourceResolver = request.getResourceResolver();
		String resourcePath = null;
		Enumeration values = request.getHeaders("referer");
		String url = null;
		if (values != null) {
			while (values.hasMoreElements()) {
				url = (String) values.nextElement();
			}
		}
		try {
			resourcePath = new URI(url).getPath();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		
		Resource res = resourceResolver.resolve(resourcePath);

		Map<String, String> map = new HashMap<String, String>();
		JSONArray jsonArray = new JSONArray();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		map.put("path", res.getPath() + "/jcr:content");
		map.put("type", "nt:unstructured");
		map.put("nodename", "overlay*");
		
		// Quering JCR using com.day.cq.search.QueryBuilder API
		PredicateGroup predicateGroup = PredicateGroup.create(map);
		QueryBuilder builder = request.getResourceResolver().adaptTo(QueryBuilder.class);
		Query query = builder.createQuery(predicateGroup, request.getResourceResolver().adaptTo(Session.class));
		SearchResult result = query.getResult();
		Iterator<Node> nt = result.getNodes();

		while (nt.hasNext()) {
		    node = (Node) nt.next();
			JSONObject jsonObject = null;
			try {
				jsonObject = new JSONObject();
				Property vendor = node.getProperty("vendor");
				String vendorselected = vendor.getValue().toString();
				Property texttitle = node.getProperty("textoverlaytitle");
				String texttitleselected = texttitle.getValue().toString();
				Property videotitle = node.getProperty("videotitle");
				String videotitleselected = videotitle.getValue().toString();
				jsonObject.put("text", "Overlay With (" + vendorselected + ") Selected & Having Text Overlay Title As (" + texttitleselected + ") & Video Overlay Title As (" + videotitleselected + ")");
				jsonObject.put("value", node.getPath());
			}
			catch (RepositoryException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jsonArray.put(jsonObject);
		}
		response.getWriter().write(jsonArray.toString());
	}
}