package websitesecurity.core.models;

public class SSLCertificateBottomRowBean {

	
	String fromText;
	String price;
	String perYear;
	String buy;
	String buypath;
	String target;
	String contactsales;
	
	
	public String getTarget() {
		return target;
	}


	public void setTarget(String target) {
		this.target = target;
	}


	public String getBuypath() {
		return buypath;
	}


	public void setBuypath(String buypath) {
		this.buypath = buypath;
	}


	public SSLCertificateBottomRowBean (String fromText, String price, String perYear, String buy,String buypath,String target, String contactsales) {
		
		this.fromText = fromText;
		this.price = price;
		this.perYear = perYear;
		this.buy = buy;
		this.buypath = buypath;
		this.target = target;
		this.contactsales = contactsales;
	}
	public SSLCertificateBottomRowBean()
	{
		super();
	}

	public String getFromText() {
		return fromText;
	}


	public void setFromText(String fromText) {
		this.fromText = fromText;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getPerYear() {
		return perYear;
	}


	public void setPerYear(String perYear) {
		this.perYear = perYear;
	}


	public String getBuy() {
		return buy;
	}


	public void setBuy(String buy) {
		this.buy = buy;
	}


	public String getContactsales() {
		return contactsales;
	}


	public void setContactsales(String contactsales) {
		this.contactsales = contactsales;
	}
	
	
}
