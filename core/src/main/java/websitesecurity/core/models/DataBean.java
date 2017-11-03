package websitesecurity.core.models;

/**
 * We are using this bean in Carousal class.
 */
public class DataBean {
	private String image;
	private String background_color;
	private String description;
	private String subtitle;
	private String link;
	private String path;
	private String target;
	private String title;
	private String count;
	private String titlecolor;

	public DataBean(String image, String background_color, String description, 
			String subtitle, String link, String path, String target) {
		super();
		this.image = image;
		this.background_color = background_color;
		this.description = description;
		this.subtitle = subtitle;
		this.link = link;
		this.path = path;
		this.target = target;
	}

	public DataBean(String count) {
		super();
		this.count = count;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBackground_color() {
		return background_color;
	}

	public void setBackground_color(String background_color) {
		this.background_color = background_color;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
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

}