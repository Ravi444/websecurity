package websitesecurity.core.utilities;

import javax.jcr.PathNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;

public class HMCIconRecommendationPath extends WCMUse {
	
	
	private final Logger log = LoggerFactory.getLogger(HMCIconRecommendationPath.class);
	String myPage;

	@Override
	public void activate() throws PathNotFoundException {
		myPage = getRequest().getAttribute("test").toString();
	}

	public String getMyPage() {
		return myPage;
	}
	
}