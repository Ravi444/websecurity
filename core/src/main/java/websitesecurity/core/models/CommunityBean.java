package websitesecurity.core.models;

/**
 * We are using this bean for community component.
 */
public class CommunityBean {

	private String linktext;
	private String linkvalue;
	private String description;
	private String linkcolor;
	private String target;

	public CommunityBean(String linktext, String linkvalue, String description,
			String linkcolor, String target) {
		super();
		this.linktext = linktext;
		this.linkvalue = linkvalue;
		this.description = description;
		this.linkcolor = linkcolor;
		this.target = target;
	}

	public String getLinkcolor() {
		return linkcolor;
	}

	public void setLinkcolor(String linkcolor) {
		this.linkcolor = linkcolor;
	}

	public String getLinktext() {
		return linktext;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public void setLinktext(String linktext) {
		this.linktext = linktext;
	}

	public String getLinkvalue() {
		return linkvalue;
	}

	public void setLinkvalue(String linkvalue) {
		this.linkvalue = linkvalue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
