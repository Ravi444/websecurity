package websitesecurity.core.models;

public class SSLCertificateTopRowBean {

	
	String titletext;
	String imageUrl;
	String subtitletext;
	String subtitlepath;
	String target;
	
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public SSLCertificateTopRowBean (String titletext, String imageUrl, String subtitletext, String subtitlepath, String target){
		
		this.titletext = titletext;
		this.imageUrl = imageUrl;
		this.subtitletext = subtitletext;
		this.subtitlepath = subtitlepath;
		this.target = target;
		
	}
	
	public SSLCertificateTopRowBean()
	{
		super();
	}

	public String getTitletext() {
		return titletext;
	}

	public void setTitletext(String titletext) {
		this.titletext = titletext;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSubtitletext() {
		return subtitletext;
	}

	public void setSubtitletext(String subtitletext) {
		this.subtitletext = subtitletext;
	}

	public String getSubtitlepath() {
		return subtitlepath;
	}

	public void setSubtitlepath(String subtitlepath) {
		this.subtitlepath = subtitlepath;
	}
	
	
	
}
