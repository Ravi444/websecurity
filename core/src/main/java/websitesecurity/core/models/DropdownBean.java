package websitesecurity.core.models;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;

/**
 * We are using this bean in ProductSearchFilter class.
 */
public class DropdownBean {
	private String name;
	private String description;
	private JSONArray tags;

	public DropdownBean(String name, String description, JSONArray tags) {
		super();
		this.name = name;
		this.description = description;
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public String getTags() throws JSONException {
		StringBuffer tags2String = new StringBuffer();
		for (int i = 0; i < tags.length(); i++) {
			tags2String.append(tags.get(i));
			tags2String.append('|');
		}
		return tags2String.toString();
	}
}
