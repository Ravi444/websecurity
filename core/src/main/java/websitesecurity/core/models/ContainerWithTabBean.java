package websitesecurity.core.models;

/**
 * 
 * We are using this bean class in ContainerWithTabs class.
 * 
 */
public class ContainerWithTabBean {
	
	private String link;
	private String path;
    
    public ContainerWithTabBean(String link, String path) {
    	super();
    	this.link = link;
    	this.path = path;
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
}