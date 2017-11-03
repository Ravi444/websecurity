package websitesecurity.core.utilities;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.NullArgumentException;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;

/**
 * @author Rishabh_Gupta
 * We are using this class for resource resolver object and Tags.
 */
@Component(immediate = true)
@Service
public class DatalayerTagsFetching implements ReadService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Reference
	private ResourceResolverFactory resourceFactory;
	
	Tag[] datalayerpageTags;
	JSONObject datalayer = new JSONObject();
	JSONObject datalayer1 = new JSONObject();
	ResourceResolver rr = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see websitesecurity.core.utilities.ReadService#ResourceResolverObject()
	 * returns the resource resolver object
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see websitesecurity.core.utilities.ReadService#tagsList(java.lang.String)
	 * returns the object of the tags array taking argument as the current page path
	 */
	@Override
	public Tag[] tagsList(String currenturl) {
		try {
			rr = ResourceResolverObject();
			Resource res = rr.getResource(currenturl);
			Page page = res.adaptTo(Page.class);
			datalayerpageTags = page.getTags();
		} catch (NullArgumentException e) {
			log.error(e.getMessage());
		}
		return datalayerpageTags;
	}
}