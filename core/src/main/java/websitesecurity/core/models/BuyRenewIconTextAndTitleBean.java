package websitesecurity.core.models;

/**
 * We are using this bean class in BuyRenewIconTextAndTitle 
 *
 */
public class BuyRenewIconTextAndTitleBean {
	private String image;
	private String title;
	private String description;
	private String linkText;
	private String linkPath;
	private String altText;
	private String imgTitle;
	private String target;

	public BuyRenewIconTextAndTitleBean(String image, String title,
			String description, String linkText, String linkPath,
			String altText, String imgTitle, String target) {
		super();
		this.image = image;
		this.title = title;
		this.description = description;
		this.linkText = linkText;
		this.linkPath = linkPath;
		this.altText = altText;
		this.imgTitle = imgTitle;
		this.target = target;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLinkText() {
		return linkText;
	}

	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

	public String getLinkPath() {
		return linkPath;
	}

	public void setLinkPath(String linkPath) {
		this.linkPath = linkPath;
	}

	public String getAltText() {
		return altText;
	}

	public void setAltText(String altText) {
		this.altText = altText;
	}

	public String getImgTitle() {
		return imgTitle;
	}

	public void setImgTitle(String imgTitle) {
		this.imgTitle = imgTitle;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
}