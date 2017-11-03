package websitesecurity.core.models;

/**
 * We are using this bean in Navlist class.
 */
public class NavBean {

	private String title;
	private String path;
	private String buttonpath;
	private String target;

	public NavBean(String title, String path, String buttonpath, String target) {
		super();
		this.title = title;
		this.path = path;
		this.buttonpath = buttonpath;
		this.target = target;
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

	public String getButtonpath() {
		return buttonpath;
	}

	public void setButtonpath(String buttonpath) {
		this.buttonpath = buttonpath;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}