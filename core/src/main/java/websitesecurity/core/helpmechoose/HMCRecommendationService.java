package websitesecurity.core.helpmechoose;

import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;

import websitesecurity.core.models.HMCRecommendationBean;

public interface HMCRecommendationService {
public List<HMCRecommendationBean> certificateCount(String url,String type, String products,String cws);
public ResourceResolver ResourceResolverObject();
}