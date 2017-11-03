package websitesecurity.core.models;

/**
 * We are using this TextOptionsDetailsBean class for Help me choose component.
 *
 */
public class TextOptionsDetailsBean {

	private String title;
	private String subtitle;


	public TextOptionsDetailsBean(String title, String subtitle) {
		super();
		this.title = title;
		this.subtitle = subtitle;
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	

}