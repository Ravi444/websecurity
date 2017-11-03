package websitesecurity.core.models;

import java.util.List;

/**
 * We are using this bean for Help me choose component.
 */
public class OptionModel {

	private String p_title1;
	private List<String> p_featlist1;

	public OptionModel(String p_title1, List<String> p_featlist1) {
		super();
		this.p_title1 = p_title1;
		this.p_featlist1 = p_featlist1;
	}

	public OptionModel() {
		super();
	}

	public String getP_title1() {
		return p_title1;
	}

	public void setP_title1(String p_title1) {
		this.p_title1 = p_title1;
	}

	public List<String> getP_featlist1() {
		return p_featlist1;
	}

	public void setP_featlist1(List<String> p_featlist1) {
		this.p_featlist1 = p_featlist1;
	}

	@Override
	public String toString() {
		return "{p_title1:" + p_title1 + ", p_featlist1:" + p_featlist1 + "}";
	}
}