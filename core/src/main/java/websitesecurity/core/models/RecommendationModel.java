package websitesecurity.core.models;

public class RecommendationModel {

	private String title;
	private String onicon;
	private String officon;
	
	private String heading1;
	private String heading2;
	private String heading3;
	private String heading4;
	private String heading5;
	
	private String description1;
	private String description2;
	private String description3;
	private String description4;
	private String description5;


	public RecommendationModel(String title, String onicon, String officon, String heading1, String heading2, String heading3, String heading4, String heading5, String description1, String description2, String description3, String description4, String description5) {
		super();
		this.title = title;
		this.onicon = onicon;
		this.officon = officon;
		this.heading1 = heading1;
		this.heading2 = heading2;
		this.heading3 = heading3;
		this.heading4 = heading4;
		this.heading5 = heading5;
		this.description1 = description1;
		this.description2 = description2;
		this.description3 = description3;
		this.description4 = description4;
		this.description5 = description5;
	}

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getOnicon() {
		return onicon;
	}

	public void setOnicon(String onicon) {
		this.onicon = onicon;
	}

	public String getOfficon() {
		return officon;
	}

	public void setOfficon(String officon) {
		this.officon = officon;
	}

	public String getHeading1() {
		return heading1;
	}



	public void setHeading1(String heading1) {
		this.heading1 = heading1;
	}



	public String getHeading2() {
		return heading2;
	}



	public void setHeading2(String heading2) {
		this.heading2 = heading2;
	}



	public String getHeading3() {
		return heading3;
	}



	public void setHeading3(String heading3) {
		this.heading3 = heading3;
	}



	public String getHeading4() {
		return heading4;
	}



	public void setHeading4(String heading4) {
		this.heading4 = heading4;
	}



	public String getHeading5() {
		return heading5;
	}



	public void setHeading5(String heading5) {
		this.heading5 = heading5;
	}



	public String getDescription1() {
		return description1;
	}



	public void setDescription1(String description1) {
		this.description1 = description1;
	}



	public String getDescription2() {
		return description2;
	}



	public void setDescription2(String description2) {
		this.description2 = description2;
	}



	public String getDescription3() {
		return description3;
	}



	public void setDescription3(String description3) {
		this.description3 = description3;
	}



	public String getDescription4() {
		return description4;
	}



	public void setDescription4(String description4) {
		this.description4 = description4;
	}



	public String getDescription5() {
		return description5;
	}



	public void setDescription5(String description5) {
		this.description5 = description5;
	}



	public RecommendationModel() {
		super();
	}

}