package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import websitesecurity.core.models.RelatedItemsBean;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.commons.RangeIterator;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.foundation.Image;

/**
 * We are using this class for GSA Search.
 */
public class RelatedItems extends WCMUse {
	public ValueMap compProperties = null;
	public PageManager pm = null;
	public ResourceResolver resourceResolver = null;
	
	/* private Logger logger = LoggerFactory.getLogger(RelatedItems.class); */

	public static final String TAGS_SEARCH_ROOT_PROPERTY_NAME = "tagsSearchRoot";
	public static final String TAGMODE_PROPERTY_NAME = "tagmode";
	public static final String MANUAL_PROPERTY_NAME = "manual";
	public static final String TAGS_PROPERTY_NAME = "tags";
	public static final String TAGS_MATCH_PROPERTY_NAME = "tagsMatch";
	public static final String ORDER_BY_PROPERTY_NAME = "orderBy";
	public static final String LIMIT_PROPERTY_NAME = "limit";
	public static final String LISTFROM_PROPERTY_NAME = "listFrom";
	final static public String PROPERTY_PAGE_PATH = "pagePath";
	final static public String EMPTY_STRING = "";
	final static public String PROPERTY_IMAGE_PATH = "imgPath";
	final static public String PROPERTY_TITLE_OVERRIDE = "titleOverride";
	final static public String PROPERTY_HEADER = "header";
	final static public String PROPERTY_DESC = "description";
	final static public String PROPERTY_CTA_TEXT = "ctaText";
	final static public String PROPERTY_CTA_URL = "ctaUrl";
	final static public String PROPERTY_HIDE_IMAGE = "hideimage";
	final static public String PAGE_PROPERTY_IMAGE_PATH = "relatedItemsImage";
	final static public String PAGE_IMAGE_SUFFIX = ".img.png";
	final static public String PAGE_IMAGE_CONTENT = "image";
	final static public String PAGE_PROPERTY_REFERENCETITLE = "relatedcardtitle";
	final static public String PAGE_PROPERTY_DESC = "relatedcarddescription";
	final static public String PAGE_PROPERTY_TITLE = "pageTitle";
	final static public String PAGE_PROPERTY_JCR_TITLE = "jcr:title";
	final static public String PAGE_PROPERTY_JCR_DESC = "jcr:description";
	final static public String PROPERTY_LINK_LIST_ITEMS_1 = "visualOptions1";
	final static public String PROPERTY_LINK_LIST_ITEMS_2 = "visualOptions2";
	final static public String PROPERTY_LINK_LIST_ITEMS_3 = "visualOptions3";
	final static private String PROPERTY_LINK_LIST_ITEMS_4 = "visualOptions4";

	public static final String HTML = ".html";

	public Iterator<Page> pageIterator = null;

	public PageFilter pageFilter = new PageFilter(false, true);
	public SlingHttpServletRequest slgReq;
	private String suffix;

	private List<RelatedItemsBean> itemsList1;
	private List<RelatedItemsBean> itemsList2;
	private List<RelatedItemsBean> itemsList3;
	private List<RelatedItemsBean> itemsList4;

	@Override
	public void activate() throws ValueFormatException, PathNotFoundException, JSONException, RepositoryException  {
		compProperties = getProperties();
		pm = getPageManager();
		resourceResolver = getResourceResolver();
		slgReq = getRequest();
		suffix = (String) slgReq.getAttribute("index");

		itemsList1 = new ArrayList<RelatedItemsBean>();
		String[] linkListItemsProperty1 = getProperties().get(
				PROPERTY_LINK_LIST_ITEMS_1, String[].class);
		if (linkListItemsProperty1 != null && linkListItemsProperty1.length > 0) {
			processLinkListProperty(linkListItemsProperty1, itemsList1);
		}

		itemsList2 = new ArrayList<RelatedItemsBean>();
		String[] linkListItemsProperty2 = getProperties().get(
				PROPERTY_LINK_LIST_ITEMS_2, String[].class);
		if (linkListItemsProperty2 != null && linkListItemsProperty2.length > 0) {
			processLinkListProperty(linkListItemsProperty2, itemsList2);
		}

		itemsList3 = new ArrayList<RelatedItemsBean>();
		String[] linkListItemsProperty3 = getProperties().get(
				PROPERTY_LINK_LIST_ITEMS_3, String[].class);
		if (linkListItemsProperty3 != null && linkListItemsProperty3.length > 0) {
			processLinkListProperty(linkListItemsProperty3, itemsList3);
		}

		itemsList4 = new ArrayList<RelatedItemsBean>();
		String[] linkListItemsProperty4 = getProperties().get(
				PROPERTY_LINK_LIST_ITEMS_4, String[].class);
		if (linkListItemsProperty4 != null && linkListItemsProperty4.length > 0) {
			processLinkListProperty(linkListItemsProperty4, itemsList4);
		}

	}

	private void processLinkListProperty(String[] linkListItemsProperty,
			List<RelatedItemsBean> passedList) throws JSONException,
			ValueFormatException, PathNotFoundException, RepositoryException {
		for (String linkListItem : linkListItemsProperty) {
			RelatedItemsBean linkListBean = getLinkListBeanFromProperty(linkListItem);
			passedList.add(linkListBean);
		}
	}

	/**
	 * @param linkListItem
	 * @return
	 * @throws JSONException
	 * @throws RepositoryException
	 * @throws PathNotFoundException
	 * @throws ValueFormatException
	 */
	private RelatedItemsBean getLinkListBeanFromProperty(String linkListItem)
			throws JSONException, ValueFormatException, PathNotFoundException,
			RepositoryException {

		JSONObject linkListJSON = new JSONObject(linkListItem);
		Page selectedPage = null;

		String pagePath = linkListJSON.getString(PROPERTY_PAGE_PATH);
		if (!pagePath.isEmpty()) {
			StringBuilder path = new StringBuilder(pagePath);
			if (path.indexOf(HTML) != -1) {
				path.delete(path.indexOf(HTML), path.length());
			}
			selectedPage = getPageManager().getPage(path.toString());
		}

		String imgPath = EMPTY_STRING;
		String header = EMPTY_STRING;
		String description = EMPTY_STRING;
		String titleOverride = EMPTY_STRING;
		Boolean hideImage = Boolean.FALSE;
		if (linkListJSON.has(PROPERTY_HIDE_IMAGE)
				&& linkListJSON.getString(PROPERTY_HIDE_IMAGE).contains("true")) {
			hideImage = Boolean.TRUE;
		}

		if (!hideImage.booleanValue()) {
			if (linkListJSON.has(PROPERTY_IMAGE_PATH)) {
				imgPath = linkListJSON.getString(PROPERTY_IMAGE_PATH);
			}
			if (imgPath.isEmpty() && selectedPage != null) {
				imgPath = selectedPage.getProperties().get(
						PAGE_PROPERTY_IMAGE_PATH, EMPTY_STRING);
				if (imgPath.isEmpty()) {
					Resource imageRes = selectedPage
							.getContentResource(PAGE_IMAGE_CONTENT);
					if (imageRes != null) {
						Image image = new Image(imageRes);
						imgPath = (String) selectedPage.getPath()
								+ PAGE_IMAGE_SUFFIX + image.getSuffix();
					}
				}
			}
		}
		if (linkListJSON.has(PROPERTY_TITLE_OVERRIDE)) {
			titleOverride = linkListJSON.getString(PROPERTY_TITLE_OVERRIDE);
		}
		if (titleOverride.isEmpty() && selectedPage != null) {
			titleOverride = selectedPage.getProperties().get(
					PAGE_PROPERTY_REFERENCETITLE, EMPTY_STRING);
			// if(titleOverride.isEmpty())titleOverride =
			// selectedPage.getProperties().get(PAGE_PROPERTY_TITLE,
			// EMPTY_STRING);
			if (titleOverride.isEmpty())
				titleOverride = selectedPage.getProperties().get(
						PAGE_PROPERTY_JCR_TITLE, EMPTY_STRING);
		}

		String ctaText = linkListJSON.getString(PROPERTY_CTA_TEXT);
		String ctaUrl = EMPTY_STRING;
		if (linkListJSON.has(PROPERTY_CTA_URL)) {
			ctaUrl = linkListJSON.getString(PROPERTY_CTA_URL);
		}
		if (ctaUrl.isEmpty() && selectedPage != null) {
			ctaUrl = pagePath + HTML; // Fix : CAP-11763
		}

		RelatedItemsBean linkListBean = new RelatedItemsBean();
		linkListBean.setImagePath(imgPath);
		linkListBean.setPagePath(pagePath);
		linkListBean.setCtaText(ctaText);
		linkListBean.setCtaUrl(ctaUrl);
		linkListBean.setDescription(description);
		linkListBean.setHeader(header);
		linkListBean.setTitleOverride(titleOverride);
		linkListBean.setHideImage(hideImage);

		return linkListBean;
	}

	/**
	 * @param linkListItem
	 * @return
	 * @throws JSONException
	 * @throws RepositoryException
	 * @throws PathNotFoundException
	 * @throws ValueFormatException
	 */
	private RelatedItemsBean getLinkListBeanFromPage(Page selectedPage) {

		String imgPath = EMPTY_STRING;
		String description = EMPTY_STRING;
		String titleOverride = EMPTY_STRING;
		String pagePath = EMPTY_STRING;

		if (selectedPage != null) {
			// pagepath
			pagePath = (String) selectedPage.getPath() + HTML;// Fix : CAP-11763

			imgPath = selectedPage.getProperties().get(
					PAGE_PROPERTY_IMAGE_PATH, EMPTY_STRING);
			if (imgPath.isEmpty()) {
				Resource imageRes = selectedPage
						.getContentResource(PAGE_IMAGE_CONTENT);
				if (imageRes != null) {
					Image image = new Image(imageRes);
					imgPath = (String) selectedPage.getPath()
							+ PAGE_IMAGE_SUFFIX + image.getSuffix();
				}
			}
			titleOverride = selectedPage.getProperties().get(
					PAGE_PROPERTY_REFERENCETITLE, EMPTY_STRING);
			// if(titleOverride.isEmpty())titleOverride =
			// selectedPage.getProperties().get(PAGE_PROPERTY_TITLE,
			// EMPTY_STRING);
			if (titleOverride.isEmpty())
				titleOverride = selectedPage.getProperties().get(
						PAGE_PROPERTY_JCR_TITLE, EMPTY_STRING);
			description = selectedPage.getProperties().get(PAGE_PROPERTY_DESC,
					EMPTY_STRING);
			if (description.isEmpty())
				description = selectedPage.getProperties().get(
						PAGE_PROPERTY_JCR_DESC, EMPTY_STRING);
		}

		RelatedItemsBean linkListBean = new RelatedItemsBean();
		linkListBean.setImagePath(imgPath);
		linkListBean.setPagePath(pagePath);
		linkListBean.setCtaText(EMPTY_STRING);
		linkListBean.setCtaUrl(pagePath);
		linkListBean.setDescription(description);
		linkListBean.setHeader(EMPTY_STRING);
		linkListBean.setTitleOverride(titleOverride);

		return linkListBean;
	}

	public boolean isTags() {
		boolean isTag = false;
		String suffix = (String) slgReq.getAttribute("index");
		if (suffix == null)
			suffix = "";

		if (compProperties.get(LISTFROM_PROPERTY_NAME + suffix) != null
				&& compProperties.get(LISTFROM_PROPERTY_NAME + suffix).equals(
						TAGMODE_PROPERTY_NAME + suffix)) {
			isTag = true;
		} else if (compProperties.get(LISTFROM_PROPERTY_NAME + suffix) != null
				&& compProperties.get(LISTFROM_PROPERTY_NAME + suffix).equals(
						MANUAL_PROPERTY_NAME + suffix)) {
			isTag = false;
		}
		return isTag;
	}

	public List<RelatedItemsBean> getPagesFromTags() {

		String suffix = (String) slgReq.getAttribute("index");
		if (suffix == null)
			suffix = "";
		// default to current page
		String parentPath = compProperties.get(TAGS_SEARCH_ROOT_PROPERTY_NAME
				+ suffix, "");
		String[] tags = compProperties.get(TAGS_PROPERTY_NAME + suffix,
				new String[0]);
		boolean matchAny = compProperties.get(
				TAGS_MATCH_PROPERTY_NAME + suffix, "any").equals("any");
		String orderBy = compProperties.get(ORDER_BY_PROPERTY_NAME + suffix,
				null);
		int limit = compProperties.get(LIMIT_PROPERTY_NAME + suffix, 100);
		List<Page> pagesTemp = new ArrayList<Page>();

		Page startPage = pm.getContainingPage(parentPath);
		if (startPage != null && tags.length > 0) {
			TagManager tagManager = resourceResolver.adaptTo(TagManager.class);
			RangeIterator<Resource> results = tagManager.find(
					startPage.getPath(), tags, matchAny);
			LinkedHashMap<String, Page> pages = new LinkedHashMap<String, Page>();
			while (results.hasNext()) {
				Resource r = results.next();
				Page page = pm.getContainingPage(r);
				if (page != null
						&& (pageFilter == null || pageFilter.includes(page))) {
					pages.put(page.getPath(), page);
				}
			}

			pageIterator = pages.values().iterator();

			// build list of pages and resources from page iterator
			while (pageIterator.hasNext()) {
				Page page = pageIterator.next();
				pagesTemp.add(page);
			}

			if (orderBy != null) {
				Collections.sort(pagesTemp, new PageComparator<Page>(orderBy));
			}
			// apply limit
			if (pagesTemp.size() > limit) {
				pagesTemp = pagesTemp.subList(0, limit);
			}

		}

		List<RelatedItemsBean> itemsList = new ArrayList<RelatedItemsBean>();
		for (Page selectedPage : pagesTemp) {
			itemsList.add(getLinkListBeanFromPage(selectedPage));
		}

		return itemsList;

	}

	/**
	 * Compares pages with eachother by property.
	 */
	public class PageComparator<P extends Page> implements Comparator<P> {

		private String property;

		private boolean isDateProperty;

		/**
		 * Creates a <code>PageComparator</code> instance using the specified
		 * property for comparison.
		 * 
		 * @param prop
		 *            The name of the property
		 */
		public PageComparator(String prop) {
			property = prop;
			isDateProperty = isDate(prop);
		}

		/**
		 * {@inheritDoc}
		 */
		public int compare(P p1, P p2) {
			int comp = getKey(p1).compareTo(getKey(p2));
			return (comp != 0 && isDateProperty) ? -comp : comp;
		}

		private String getKey(P p) {
			return p.getProperties().get(property, "");
		}

		private boolean isDate(String prop) {
			return prop.matches("jcr:created")
					|| prop.matches("cq:lastModified")
					|| prop.matches("cq:lastPublished");
		}
	}

	public String getSuffix() {
		return suffix;
	}

	public List<RelatedItemsBean> getItemsList1() {
		return itemsList1;
	}

	public List<RelatedItemsBean> getItemsList2() {
		return itemsList2;
	}

	public List<RelatedItemsBean> getItemsList3() {
		return itemsList3;
	}

	public List<RelatedItemsBean> getItemsList4() {
		return itemsList4;
	}

}
