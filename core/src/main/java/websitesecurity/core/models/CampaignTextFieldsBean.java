package websitesecurity.core.models;

public class CampaignTextFieldsBean {

	private String placeholder;
	private String name;
	private String id;

	public CampaignTextFieldsBean(String placeholder, String name, String id, String error) {
		super();
		this.placeholder = placeholder;
		this.name = name;
		this.id = id;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}