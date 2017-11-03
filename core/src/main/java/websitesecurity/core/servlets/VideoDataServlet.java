package websitesecurity.core.servlets;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
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
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.replication.PathNotFoundException;

/**
 * We are using this servlet to retrieve the video data of each component dragged inside use case filter present on the page.
 */
@SlingServlet(paths = "/bin/websitesecurity/videodata", methods = "GET", metatype = false)
public class VideoDataServlet extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 2598426539166789515L;
	private final static Logger LOG = LoggerFactory.getLogger(VideoDataServlet.class);
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
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServerException, IOException {
		String nodeurl = request.getParameter("nodeurl");
		Node node;
		JSONObject compdetails = null;
		try{

			rr = ResourceResolverObject();
			social = rr.getResource(nodeurl);
			 node = social.adaptTo(Node.class);
					compdetails = new JSONObject();
					
					if(node.hasProperty("closeicon")){
	  					Property closeicon = node.getProperty("closeicon");
	  					String closeicon1 = closeicon.getValue().toString();
	  					compdetails.put("closeicon", closeicon1);
    					}
    					else{
    						compdetails.put("closeicon", "");
    					}
					if(node.hasProperty("hoverclose")){
	  					Property hoverclose = node.getProperty("hoverclose");
	  					String hoverclose1 = hoverclose.getValue().toString();
	  					compdetails.put("hoverclose", hoverclose1);
    					}
    					else{
    						compdetails.put("hoverclose", "");
    					}
					if(node.hasProperty("vendor")){
	  					Property vendor = node.getProperty("vendor");
	  					String vendor1 = vendor.getValue().toString();
	  					compdetails.put("vendor", vendor1);
    					}
    					else{
    					compdetails.put("vendor", "");
    					}
					if(node.hasProperty("videotitle")){
	  					Property videotitle = node.getProperty("videotitle");
	  					String videotitle1 = videotitle.getValue().toString();
	  					compdetails.put("videotitle", videotitle1);
    					}
    					else{
    						compdetails.put("videotitle", "");
    					}
					
					if(node.hasProperty("videoID")){
	  					Property videoID = node.getProperty("videoID");
	  					String videoID1 = videoID.getValue().toString();
	  					compdetails.put("videoid", videoID1);
    					}
    					else{
    					compdetails.put("videoid", "");
    					}
					if(node.hasProperty("playerID")){
	  					Property playerID = node.getProperty("playerID");
	  					String playerID1 = playerID.getValue().toString();
	  					compdetails.put("playerid", playerID1);
    					}
    					else{
    					compdetails.put("playerid", "");
    					}
					if(node.hasProperty("playerKey")){
	  					Property playerKey = node.getProperty("playerKey");
	  					String playerKey1 = playerKey.getValue().toString();
	  					compdetails.put("playerkey", playerKey1);
    					}
    					else{
    					compdetails.put("playerkey", "");
    					}
					if(node.hasProperty("youtubeembedCode")){
	  					Property youtubeembedCode = node.getProperty("youtubeembedCode");
	  					String youtubeembedCode1 = youtubeembedCode.getValue().toString();
	  					compdetails.put("youtubeembedcode", youtubeembedCode1);
    					}
    					else{
    						compdetails.put("youtubeembedcode", "");
    					}
					if(node.hasProperty("ustudioembedCode")){
	  					Property ustudioembedCode = node.getProperty("ustudioembedCode");
	  					String ustudioembedCode1 = ustudioembedCode.getValue().toString();
	  					compdetails.put("ustudioembedcode", ustudioembedCode1);
    					}
    					else{
    					compdetails.put("ustudioembedcode", "");
    					}
 				
				}
			catch (ValueFormatException e) {
				e.printStackTrace();
			} catch (RepositoryException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		response.getWriter().write("" + compdetails.toString());
	}
}