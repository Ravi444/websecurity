package websitesecurity.core.models;

/**
 * We are using this bean in Sitemap class.
 */
public class SitemapBean {

	private String title;
	private String buttontitle;
	private String buttonpath;
	private String target;

	public SitemapBean(String title, String buttontitle, String buttonpath,
			String target) {
		super();
		this.title = title;
		this.buttontitle = buttontitle;
		this.buttonpath = buttonpath;
		this.target = target;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getButtontitle() {
		return buttontitle;
	}

	public void setButtontitle(String buttontitle) {
		this.buttontitle = buttontitle;
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