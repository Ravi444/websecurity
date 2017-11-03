package websitesecurity.core.models;

/**
 * We are using this bean class in different classes for link purpose.
 *
 */
public class PathBean {
	private String title;
	private String path;
	private String target;

	public PathBean(String title, String path, String target) {
		super();
		this.title = title;
		this.path = path;
		this.target = target;
	}

	public PathBean(String title, String path) {
		super();
		this.title = title;
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
}