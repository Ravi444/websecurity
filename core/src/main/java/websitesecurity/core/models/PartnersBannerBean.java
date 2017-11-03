package websitesecurity.core.models;

/**
 * We are using this PartnersBannerBean class for PartnersBanner class.
 *
 */
public class PartnersBannerBean {

	private String title;
	private String titlecolor;
	private String description;

	public PartnersBannerBean(String title, String titlecolor,
			String description) {
		super();
		this.title = title;
		this.titlecolor = titlecolor;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitlecolor() {
		return titlecolor;
	}

	public void setTitlecolor(String titlecolor) {
		this.titlecolor = titlecolor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}