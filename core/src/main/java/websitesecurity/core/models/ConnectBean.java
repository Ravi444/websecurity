package websitesecurity.core.models;

/**
 * We are using this bean for community component for connect variation.
 */
public class ConnectBean {

	private String linktitle;
	private String linkpath;
	private String iconpath;
	private String target;

	public ConnectBean(String linktitle, String linkpath, String iconpath,
			String target) {
		super();
		this.linktitle = linktitle;
		this.linkpath = linkpath;
		this.iconpath = iconpath;
		this.target = target;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getLinktitle() {
		return linktitle;
	}

	public void setLinktitle(String linktitle) {
		this.linktitle = linktitle;
	}

	public String getLinkpath() {
		return linkpath;
	}

	public void setLinkpath(String linkpath) {
		this.linkpath = linkpath;
	}

	public String getIconpath() {
		return iconpath;
	}

	public void setIconpath(String iconpath) {
		this.iconpath = iconpath;
	}
}
