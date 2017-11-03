package websitesecurity.core.utilities;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.replication.PathNotFoundException;
import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;

/**
 * We are using PageHelper class for GSA Search.
 */
public class PageHelper extends WCMUse {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PageHelper.class);

	/** The current page. */
	private transient Page currentPage;
	/** The settings service. */
	// private SlingSettingsService settingsService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	Tag[] tagsArray;

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() throws PathNotFoundException {
		ReadService service = getSlingScriptHelper().getService(
				ReadService.class);
		tagsArray = service.tagsList(getCurrentPage().getPath());
		currentPage = getCurrentPage();
	}

	public String getProductTags() {
		return getTags("Products");
	}

	public String getSegmentTags() {
		return getTags("Segment");
	}

	public String getResourceTags() {
		return getTags("Resources");
	}

	public String getCategoryTags() {
		return getTags("Category");
	}

	public String getSubCategoryTags() {
		return getTags("Subcategory");
	}

	public String getIndustryTags() {
		return getTags("Industry");
	}

	public String getTrendTags() {
		return getTags("Trend");
	}

	public String getServiceTags() {
		return getTags("Services-Subcategory");
	}

	public String getPartnerResourceTags() {
		return getTags("Partner-Resource");
	}

	/**
	 * @param parent
	 * @return
	 */
	private String getTags(String parent) {
		StringBuilder tags = new StringBuilder();
		// Tag[] tagsArray = currentPage.getTags();
		if (tagsArray != null && tagsArray.length > 0) {
			for (int i = 0; i < tagsArray.length; i++) {
				if (tagsArray[i] != null
						&& StringUtils.startsWith(tagsArray[i].getLocalTagID(),
								parent)) {
					tags.append(tagsArray[i].getTitle());
					tags.append(",");
				}
			}
		}
		return StringEscapeUtils.escapeXml(StringUtils.removeEnd(
				tags.toString(), ","));
	}
}