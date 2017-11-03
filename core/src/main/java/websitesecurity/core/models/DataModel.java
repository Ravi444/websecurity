package websitesecurity.core.models;

import java.util.List;

/**
 * We are using this Bean class for Helpmechoose Component.
 * 
 */
public class DataModel {

	private String p_title;
	private String p_icon;
	private String p_desc;
	private List<String> p_featlist;
	private String p_linkto;
	private String p_scenario;
	private String p_value;

	public DataModel(String p_title, String p_icon, String p_desc, List<String> p_featlist, String p_linkto, String p_scenario,String p_value) {
		super();
		this.p_title = p_title;
		this.p_icon = p_icon;
		this.p_desc = p_desc;
		this.p_featlist = p_featlist;
		this.p_linkto = p_linkto;
		this.p_scenario = p_scenario;
		this.p_value = p_value;
	}

	public String getP_value() {
		return p_value;
	}

	public void setP_value(String p_value) {
		this.p_value = p_value;
	}

	public DataModel(String p_title, List<String> p_featlist) {
		super();
		this.p_title = p_title;
		this.p_featlist = p_featlist;

	}

	public DataModel() {
		super();
	}

	public String getP_title() {
		return p_title;
	}

	public void setP_title(String p_title) {
		this.p_title = p_title;
	}

	public String getP_icon() {
		return p_icon;
	}

	public void setP_icon(String p_icon) {
		this.p_icon = p_icon;
	}

	public String getP_desc() {
		return p_desc;
	}

	public void setP_desc(String p_desc) {
		this.p_desc = p_desc;
	}

	public List<String> getP_featlist() {
		return p_featlist;
	}

	public void setP_featlist(List<String> p_featlist) {
		this.p_featlist = p_featlist;
	}

	public String getP_linkto() {
		return p_linkto;
	}

	public void setP_linkto(String p_linkto) {
		this.p_linkto = p_linkto;
	}

	public String getP_scenario() {
		return p_scenario;
	}

	public void setP_scenario(String p_scenario) {
		this.p_scenario = p_scenario;
	}

	@Override
	public String toString() {
		return "{p_title:" + p_title + ", p_icon:" + p_icon + ", p_desc:"
				+ p_desc + ", p_featlist:" + p_featlist + ", p_linkto:"
				+ p_linkto + ", p_scenario:" + p_scenario + ", p_value:" + p_value + "}";
	}
}
