package websitesecurity.core.utilities;

import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;

import websitesecurity.core.models.ImageTextTitleBean;

/**
 * @author Rishabh_Gupta
 * We are using this Interface for Analytics. 
 */
public interface CarouselCountService {
	public List<ImageTextTitleBean> imageTextAndTextCount(String url);
	public ResourceResolver ResourceResolverObject();
}