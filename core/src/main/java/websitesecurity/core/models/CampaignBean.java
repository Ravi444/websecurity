package websitesecurity.core.models;

/**
 * We are using this Bean for Campaign in Campaign class.
 *
 */
public class CampaignBean {

	private String title;
	private String titlecolor;
	private String description;
	private String descriptioncolor;

	public CampaignBean(String title, String titlecolor, String description,
			String descriptioncolor) {
		super();
		this.title = title;
		this.titlecolor = titlecolor;
		this.description = description;
		this.descriptioncolor = descriptioncolor;
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

	public String getDescriptioncolor() {
		return descriptioncolor;
	}

	public void setDescriptioncolor(String descriptioncolor) {
		this.descriptioncolor = descriptioncolor;
	}

}