package websitesecurity.core.models;

/**
 * We are using this bean class for pricingAPI.
 *
 */
public class PricingDataBean {
	private String title;
	private String subtitle;
	private String path;
	private String value;
	private String details1;
	private String details2;
	private String details3;
	private String image1;
	private String image2;
	private String image3;

	public PricingDataBean(String title, String subtitle, String path,
			String value, String details1, String details2, String details3,
			String image1, String image2, String image3) {
		super();
		this.title = title;
		this.subtitle = subtitle;
		this.path = path;
		this.value = value;
		this.details1 = details1;
		this.details2 = details2;
		this.details3 = details3;
		this.setImage1(image1);
		this.setImage2(image2);
		this.setImage3(image3);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDetails1() {
		return details1;
	}

	public void setDetails1(String details1) {
		this.details1 = details1;
	}

	public String getDetails2() {
		return details2;
	}

	public void setDetails2(String details2) {
		this.details2 = details2;
	}

	public String getDetails3() {
		return details3;
	}

	public void setDetails3(String details3) {
		this.details3 = details3;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

}