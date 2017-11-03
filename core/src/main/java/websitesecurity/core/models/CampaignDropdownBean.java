package websitesecurity.core.models;

import java.util.List;

public class CampaignDropdownBean {

	private String placeholder;
	private String name;
	private String id;
	private List<String> options;

	public CampaignDropdownBean(String placeholder, String name, String id, List<String> options) {
		this.placeholder = placeholder;
		this.name = name;
		this.id = id;
		this.options = options;
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

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "{placeholder:" + placeholder + ", name:" + name + ", id:" + id
				+ ",  options:" + options + "}";
	}

}
