package websitesecurity.core.models;

public class ChooseSealBean {
	private String value;
	private String option;
	private String urlvalue;
	private String scripttext;

	public ChooseSealBean(String value, String option, String urlvalue, String scripttext) {
		super();
		this.value = value;
		this.option = option;
		this.urlvalue = urlvalue;
		this.scripttext = scripttext;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getUrlvalue() {
		return urlvalue;
	}

	public void setUrlvalue(String urlvalue) {
		this.urlvalue = urlvalue;
	}

	public String getScripttext() {
		return scripttext;
	}

	public void setScripttext(String scripttext) {
		this.scripttext = scripttext;
	}
	
}