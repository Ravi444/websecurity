package websitesecurity.core.servlets;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.Session;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import websitesecurity.core.utilities.PathUtility;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;

/**
 * We are using this servlet to retrieve the filter data present on the page.
 */
@SlingServlet(paths = "/bin/websitesecurity/usecasefilter", methods = "GET", metatype = false)
public class UseCaseFilterServlet extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 2598426539166789515L;
	private final static Logger LOG = LoggerFactory.getLogger(UseCaseFilterServlet.class);
	/**
	 * Gets the log.
	 *
	 * @return the log
	 */
	public static Logger getLog() {
		return LOG;
	}
	@Reference
	private ResourceResolverFactory resolverFactory;
	JSONObject compname;
	String grid = "";

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServerException, IOException {
		String filterkey = request.getParameter("filterkey");
		String division = request.getParameter("division");
		String type = request.getParameter("type");
		String url = request.getParameter("url");
		Map<String, String> map = new HashMap<String, String>();
		Node node;
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		if (division.equalsIgnoreCase("col-md-4 col-sm-6 col-xs-12 col-lg-4"))
			grid = "col-4-4-4";
		else if (division.equalsIgnoreCase("col-lg-3 col-md-3 col-sm-6 col-xs-12"))
			grid = "col-3-3-3-3";
		else
			grid = "col-2-2-2-2";

		if (filterkey.contains("All") || filterkey.equalsIgnoreCase("null") || filterkey.equalsIgnoreCase("undefined")) {
			map.put("path", url + "/jcr:content/par1/pagination_filter");
			map.put("type", "nt:unstructured");
			map.put("nodename", "imagetextandtitle*");
			map.put("orderby", "@jcr:created");
			map.put("p.limit", "-1");
		} else if (type.equalsIgnoreCase("parent")) {
			map.put("path", url + "/jcr:content/par1/pagination_filter");
			map.put("type", "nt:unstructured");
			map.put("nodename", "imagetextandtitle*");
			map.put("orderby", "@jcr:created");
			map.put("p.limit", "-1");
			map.put("group.p.or", "true");
			map.put("group.1_property", "dropvalue1");
			map.put("group.1_property.value", filterkey);
			map.put("group.2_property", "dropvalue11");
			map.put("group.2_property.value", filterkey);
		} else if (type.equalsIgnoreCase("child")) {
			map.put("path", url + "/jcr:content/par1/pagination_filter");
			map.put("type", "nt:unstructured");
			map.put("nodename", "imagetextandtitle*");
			map.put("orderby", "@jcr:created");
			map.put("p.limit", "-1");
			map.put("group.p.or", "true");
			map.put("group.1_property", "dropvalue2");
			map.put("group.1_property.value", filterkey);
			map.put("group.2_property", "dropvalue3");
			map.put("group.2_property.value", filterkey);
		}
		
		// Quering JCR using com.day.cq.search.QueryBuilder API
		PredicateGroup predicateGroup = PredicateGroup.create(map);
		QueryBuilder builder = request.getResourceResolver().adaptTo(QueryBuilder.class);
		Query query = builder.createQuery(predicateGroup, request.getResourceResolver().adaptTo(Session.class));
		SearchResult result = query.getResult();
		Iterator<Node> nt = result.getNodes();
		int k = 1;
		compname = new JSONObject();
		JSONObject compdetails;
		while (nt.hasNext()) {
			node = (Node) nt.next();
			try {
				if (node.getPath().contains(grid)) {
					compdetails = new JSONObject();
					compdetails.put("nodeurl", node.getPath());
					if (node.hasProperty("alternatetext")) {
						Property alternatetext = node.getProperty("alternatetext");
						String alternatetext1 = alternatetext.getValue().toString();
						compdetails.put("alternatetext", alternatetext1);
					} else
						compdetails.put("alternatetext", "");
					if (node.hasProperty("description")) {
						Property description = node.getProperty("description");
						String description1 = description.getValue().toString();
						compdetails.put("description", description1);
					} else
						compdetails.put("description", "");
					if (node.hasProperty("descriptioncolor")) {
						Property descriptioncolor = node.getProperty("descriptioncolor");
						String descriptioncolor1 = descriptioncolor.getValue().toString();
						compdetails.put("descriptioncolor", descriptioncolor1);
					} else
						compdetails.put("descriptioncolor", "");
					if (node.hasProperty("imageReference")) {
						Property imageReference = node.getProperty("imageReference");
						String imageReference1 = imageReference.getValue().toString();
						compdetails.put("imageReference", imageReference1);
					} else
						compdetails.put("imageReference", "");
					if (node.hasProperty("imgtitle")) {
						Property imgtitle = node.getProperty("imgtitle");
						String imgtitle1 = imgtitle.getValue().toString();
						compdetails.put("imgtitle", imgtitle1);
					} else
						compdetails.put("imgtitle", "");
					if (node.hasProperty("linkkey")) {
						Property linkkey = node.getProperty("linkkey");
						String linkkey1 = linkkey.getValue().toString();
						compdetails.put("linkkey", linkkey1);
					} else
						compdetails.put("linkkey", "");
					if (node.hasProperty("linkvalue")) {
						Property linkvalue = node.getProperty("linkvalue");
						String linkvalue1 = linkvalue.getValue().toString();
						compdetails.put("linkvalue", PathUtility.pathCheckAdvanced(linkvalue1));
						compdetails.put("linktarget", PathUtility.pathTarget(linkvalue1));
					} else
					{
						compdetails.put("linkvalue", "");
					    compdetails.put("linktarget","");
					}
					    
					if (node.hasProperty("title1")) {
						Property title = node.getProperty("title1");
						String title1 = title.getValue().toString();
						compdetails.put("title", title1);
					} else
						compdetails.put("title", "");
					if (node.hasProperty("titlecolor")) {
						Property titlecolor = node.getProperty("titlecolor");
						String titlecolor1 = titlecolor.getValue().toString();
						compdetails.put("titlecolor", titlecolor1);
					} else
						compdetails.put("titlecolor", "");
					
					if(node.hasProperty("listFrom")){
	  					Property listFrom = node.getProperty("listFrom");
	  					String listFrom1 = listFrom.getValue().toString();
						compdetails.put("listfrom", listFrom1);
    					}
    					else{
    					compdetails.put("listfrom", "");
    					}
					if(node.hasProperty("snapshot")){
	  					Property snapshot = node.getProperty("snapshot");
	  					String snapshot1 = snapshot.getValue().toString();
	  					compdetails.put("snapshot", snapshot1);
    					}
    					else{
    						compdetails.put("snapshot", "");
    					}
					if(node.hasProperty("icon")){
	  					Property icon = node.getProperty("icon");
	  					String icon1 = icon.getValue().toString();
	  					compdetails.put("icon", icon1);
    					}
    					else{
    						compdetails.put("icon", "");
    					}
				
					if(node.hasProperty("videosubtitle")){
	  					Property videosubtitle = node.getProperty("videosubtitle");
	  					String videosubtitle1 = videosubtitle.getValue().toString();
	  					compdetails.put("videosubtitle", videosubtitle1);
    					}
    					else{
    						compdetails.put("videosubtitle", "");
    					}
					if(node.hasProperty("videoduration")){
	  					Property videoduration = node.getProperty("videoduration");
	  					String videoduration1 = videoduration.getValue().toString();
	  					compdetails.put("videoduration", videoduration1);
    					}
    					else{
    					compdetails.put("videoduration", "");
    					}
					if(node.hasProperty("videoduration")){
	  					Property videoduration = node.getProperty("videoduration");
	  					String videoduration1 = videoduration.getValue().toString();
	  					compdetails.put("videoduration", videoduration1);
    					}
    					else{
    					compdetails.put("videoduration", "");
    					}
						String tempDate;
					    Property createddate = node.getProperty("jcr:created");    
						tempDate=createddate.getValue().toString(); 
						tempDate = tempDate.replaceAll("[^a-zA-Z0-9]", "");
						tempDate.replaceAll("\\s+", "");
						compdetails.put("tempdate", tempDate);
 					
					compname.put("" + k, compdetails);
				}
			} catch (Exception e) {
				LOG.error(e.getMessage());
			}
			k = k + 1;
		}
		response.getWriter().write("" + compname.toString());
	}
}