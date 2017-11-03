package websitesecurity.core.models;
/**
 *
 *We are using this Bean class in repository component.
 *
 */
public class RepositoryBean {
  
	private String title;
	

	public RepositoryBean(String title){
		super();
		this.title = title;
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}	
      	
}