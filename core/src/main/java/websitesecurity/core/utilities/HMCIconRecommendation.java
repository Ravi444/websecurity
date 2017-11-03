package websitesecurity.core.utilities;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;

public class HMCIconRecommendation extends WCMUse {
	private Resource resource;
	Value[] title;
	String[] titleString;
	String[] splitValues1;
	String[] splitValues2;
	String[] splitValues3;
	Value[] path;
	private final Logger log = LoggerFactory
			.getLogger(HMCIconRecommendation.class);

	@Override
	public void activate() throws PathNotFoundException, RepositoryException{
		ResourceResolver resourceResolver = getResourceResolver();
		Session session = resourceResolver.adaptTo(Session.class);
		resource = resourceResolver.getResource("/content/websitesecurity_phase2/en/us/Recommendation/jcr:content/par1/recommendationicon");
		log.info("Resource :" + resource);
		Node ntFileNode = resource.adaptTo(Node.class);
		title = ntFileNode.getProperty("sslicondetail").getValues();
		String string1 = title[0].toString();
		splitValues1 = string1.split("\\|");
		String string2 = title[1].toString();
		splitValues2 = string2.split("\\|");
		String string3 = title[2].toString();
		splitValues3 = string3.split("\\|");
		Node titleNode = session.getNode("/content/websitesecurity_phase2/en/us/Recommendation/jcr:content/par1/recommendationicon");
		for (int i = 0; i < splitValues1.length; i++) {
			titleNode.setProperty("first" + (i + 1), splitValues1[i]);
		}
		for (int i = 0; i < splitValues2.length; i++) {
			titleNode.setProperty("second" + (i + 1), splitValues2[i]);
		}
		for (int i = 0; i < splitValues3.length; i++) {
			titleNode.setProperty("third" + (i + 1), splitValues3[i]);
		}
		session.save();
	}
}