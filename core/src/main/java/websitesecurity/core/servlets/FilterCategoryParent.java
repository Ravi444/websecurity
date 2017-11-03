package websitesecurity.core.servlets;

import java.io.IOException;
import java.rmi.ServerException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

/**
 * We are using this servlet to retrieve the Parent categories in the use case
 * filter component.
 */
@SlingServlet(paths = "/bin/websitesecurity/filtercategoryparent", methods = "GET", metatype = false)
public class FilterCategoryParent extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 2598426539166789515L;

	@Reference
	private ResourceResolverFactory resolverFactory;
	JSONObject compname;
	JSONObject parent = new JSONObject();

	String grid = "";

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServerException, IOException {
		String categoryparent = request.getParameter("parentselected");
		JSONArray jsonArray = new JSONArray();
		JSONObject abc;
		try {
			for (int i = 0; i < 2; i++) {
			    abc = new JSONObject();
				if (i == 0) {
					abc.put("text", "(None)");
					abc.put("value", "(None)");
				} else if (i == 1) {
					abc.put("text", categoryparent);
					abc.put("value", categoryparent);
				}
				jsonArray.put(abc);
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		response.getWriter().write(jsonArray.toString());
	}
}
