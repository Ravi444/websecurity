package websitesecurity.core.servlets;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.ServerException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.jcr.ValueFormatException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * We are using this servlet to retrieve the video data of each component
 * dragged inside use case filter present on the page.
 */
@SlingServlet(paths = "/bin/websitesecurity/hmcproductdata", methods = "GET", metatype = false)
public class HmcProductdata extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 2598426539166789515L;
	private final static Logger LOG = LoggerFactory
			.getLogger(HmcProductdata.class);

	/**
	 * Gets the log.
	 *
	 * @return the log
	 */
	public static Logger getLog() {
		return LOG;
	}

	@Reference
	private ResourceResolverFactory resourceFactory;
	ResourceResolver rr = null;
	private Resource social;
	JSONObject compname;
	String grid = "";
	
	

	public ResourceResolver ResourceResolverObject() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// Mention the subServiceName you had used in the User Mapping
		paramMap.put(ResourceResolverFactory.SUBSERVICE, "readService");
		try {
			rr = resourceFactory.getServiceResourceResolver(paramMap);
		} catch (LoginException e) {
			e.printStackTrace();
		}
		return rr;
	}

	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServerException,
			IOException {

		Enumeration<?> values1 = request.getHeaders("referer");
		String url = null;
		JSONArray jsonArray = new JSONArray();
		String resourcePath = null;
		String singlevalue;
		Value[] optiondata;
		String value;
		Node node;
		JSONObject options;

		if (values1 != null) {
			while (values1.hasMoreElements()) {
				url = (String) values1.nextElement();
			}
		}

		rr = request.getResourceResolver();

		try {
			resourcePath = new URI(url).getPath();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}

		Resource res = rr.resolve(resourcePath);
		String resPath = res.getPath();
		String path = resPath.substring(0, resPath.lastIndexOf("/"));

		try {

			social = rr.getResource(path
					+ "/jcr:content/par2/hmctextwithoption");
			node = social.adaptTo(Node.class);
			optiondata = node.getProperty("optiondata").getValues();

			for (int i = 0; i < optiondata.length; i++) {
				options = new JSONObject();
				singlevalue = optiondata[i].toString();
				String[] values = singlevalue.split("\\|");
				String text = values[0];
				value = text.replaceAll("[^a-zA-Z0-9\\s]", "");	

				value = value.replaceAll(" ", "_").toLowerCase();
				options.put("text", text);
				options.put("value", value);
				
				jsonArray.put(options);

			}
		} catch (JSONException e) {

			LOG.error(e.getMessage());
		} catch (ValueFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PathNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().write(jsonArray.toString());
	}
}
