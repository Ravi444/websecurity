package websitesecurity.core.models;

/**
 * We are using this bean in GSA Search.
 */
public class RelatedItemsBean {

	private String imagePath;
	private String pagePath;
	private String ctaText;
	private String ctaUrl;
	private String tabTitle;
	private String header;
	private String description;
	private String titleOverride;
	private Boolean hideImage;

	public String getTitleOverride() {
		return titleOverride;
	}

	public void setTitleOverride(String titleOverride) {
		this.titleOverride = titleOverride;
	}

	public String getTabTitle() {
		return tabTitle;
	}

	public void setTabTitle(String tabTitle) {
		this.tabTitle = tabTitle;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	public String getCtaText() {
		return ctaText;
	}

	public void setCtaText(String ctaText) {
		this.ctaText = ctaText;
	}

	public String getCtaUrl() {
		return ctaUrl;
	}

	public void setCtaUrl(String ctaUrl) {
		this.ctaUrl = ctaUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public Boolean isHideImage() {
		return hideImage;
	}

	public void setHideImage(Boolean hideImage) {
		this.hideImage = hideImage;
	}
}