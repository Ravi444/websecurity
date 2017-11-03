package websitesecurity.core.models;

/**
 * We are using this bean in header class.
 */
public class HeaderBean {
	private String title;
	private String path;
	private String bread;
	private String divid;
	private String target;
	private String target1;

	public HeaderBean(String title, String path, String bread, String divid,
			String target, String target1) {
		super();
		this.title = title;
		this.path = path;
		this.bread = bread;
		this.divid = divid;
		this.target = target;
		this.target1 = target1;
	}

	public String getBread() {
		return bread;
	}

	public void setBread(String bread) {
		this.bread = bread;
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

	public String getDivid() {
		return divid;
	}

	public void setDivid(String divid) {
		this.divid = divid;
	}

	public String getTarget1() {
		return target1;
	}

	public void setTarget1(String target1) {
		this.target1 = target1;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}