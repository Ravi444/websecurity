package websitesecurity.core.models;

/**
 * We are using this NewsBean class for News class.
 *
 */
public class NewsBean {
	private String date;
	private String text;
	private String path;
	private String target;
	private String description;

	public NewsBean(String date, String text, String path, String target,
			String description) {
		super();
		this.date = date;
		this.text = text;
		this.path = path;
		this.target = target;
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}