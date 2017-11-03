package websitesecurity.core.utilities;

import org.apache.sling.api.resource.ResourceResolver;

import com.day.cq.tagging.Tag;

/**
 * We are using this Interface for Analytics.
 */
public interface ReadService {
	public Tag[] tagsList(String currenturl);
	public ResourceResolver ResourceResolverObject();
}