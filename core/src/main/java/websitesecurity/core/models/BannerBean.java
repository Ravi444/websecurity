package websitesecurity.core.models;

/**
 * We are using this bean class in herobanner_securitytopics component. 
 *
 */
public class BannerBean {
	private String title;
	private String icon;
	private String alternatetext;
	private String imagetitle;
	private String path;
	private String target;
	
	public BannerBean(String title, String icon, String alternatettext, String imagetitle, String path, String target) {
		this.title = title;
		this.icon = icon;
		this.alternatetext = alternatettext;
		this.imagetitle = imagetitle;
		this.path = path;
		this.target = target;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getAlternatetext() {
		return alternatetext;
	}

	public void setAlternatetext(String alternatetext) {
		this.alternatetext = alternatetext;
	}

	public String getImagetitle() {
		return imagetitle;
	}

	public void setImagetitle(String imagetitle) {
		this.imagetitle = imagetitle;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}