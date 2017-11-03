package websitesecurity.core.utilities;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.jcr.ValueFormatException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import com.adobe.cq.sightly.WCMUse;

public class HMCBanner extends WCMUse {
	private Resource resource;
	Value[] title;
	Value[] path;

	@Override
	public void activate() throws ValueFormatException, PathNotFoundException, RepositoryException {
		
		ResourceResolver resourceResolver = getResourceResolver();
		Session session = resourceResolver.adaptTo(Session.class);
		
		String page = getCurrentPage().getPath();
		resource = resourceResolver.getResource(page+"/jcr:content/par1/helpmechoosebanner");
		
		Node ntFileNode = resource.adaptTo(Node.class);
		
		title = ntFileNode.getProperty("title").getValues();
		Node titleNode = session.getNode(page+"/jcr:content/par1/helpmechoosebanner");
		for (int i = 0; i < title.length; i++) {
			titleNode.setProperty("title" + (i + 1), title[i]);
		}
		
		path = ntFileNode.getProperty("path").getValues();
		Node pathNode = session.getNode(page+"/jcr:content/par1/helpmechoosebanner");
		for (int i = 0; i < path.length; i++) {
			pathNode.setProperty("path" + (i + 1), path[i]);
		}
		
		session.save();
	}
}