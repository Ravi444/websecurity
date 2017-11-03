package websitesecurity.core.models;

import java.util.List;

/**
 * We are using this bean for Help me choose component.
 */
public class CheckModel {

	private String p_title2;
	private List<String> p_optionlist1;
	private List<String> p_optionlist2;
	private List<String> p_optionlist3;
	private List<List<String>> p_finaldata;

	public CheckModel(String p_title2, List<String> p_optionlist1, List<String> p_optionlist2,List<String> p_optionlist3,List<List<String>> p_finaldata) {
		super();
		this.p_title2 = p_title2;
		this.setP_optionlist1(p_optionlist1);
		this.setP_optionlist2(p_optionlist2);
		this.setP_optionlist3(p_optionlist3);
		this.setP_finaldata(p_finaldata);
	}

	public CheckModel() {
		super();
	}

	public String getP_title2() {
		return p_title2;
	}

	public void setP_title2(String p_title2) {
		this.p_title2 = p_title2;
	}
	
	public List<String> getP_optionlist1() {
		return p_optionlist1;
	}

	public void setP_optionlist1(List<String> p_optionlist1) {
		this.p_optionlist1 = p_optionlist1;
	}

	public List<String> getP_optionlist2() {
		return p_optionlist2;
	}

	public void setP_optionlist2(List<String> p_optionlist2) {
		this.p_optionlist2 = p_optionlist2;
	}

	public List<String> getP_optionlist3() {
		return p_optionlist3;
	}

	public void setP_optionlist3(List<String> p_optionlist3) {
		this.p_optionlist3 = p_optionlist3;
	}
	
	public List<List<String>> getP_finaldata() {
		return p_finaldata;
	}

	public void setP_finaldata(List<List<String>> p_finaldata) {
		this.p_finaldata = p_finaldata;
	}

	
	@Override
	public String toString() {
		return "{p_title2:" + p_title2 + ", p_optionlist1:" + p_optionlist1 + ", p_optionlist2:" + p_optionlist2 + ", p_optionlist3:" + p_optionlist3 + "}";
	}

	
	

	
}