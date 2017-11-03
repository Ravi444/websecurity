package websitesecurity.core.models;

/**
 * We are using this bean for ImageTextTitle component.
 * 
 */
public class ImageTextTitleBean {
	public String getAlternateText() {
		return alternateText;
	}




	public void setAlternateText(String alternateText) {
		this.alternateText = alternateText;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public String getDescriptionColor() {
		return descriptionColor;
	}




	public void setDescriptionColor(String descriptionColor) {
		this.descriptionColor = descriptionColor;
	}




	public String getImageReference() {
		return imageReference;
	}




	public void setImageReference(String imageReference) {
		this.imageReference = imageReference;
	}




	public String getImgTitle() {
		return imgTitle;
	}




	public void setImgTitle(String imgTitle) {
		this.imgTitle = imgTitle;
	}




	public String getLinkKey() {
		return linkKey;
	}




	public void setLinkKey(String linkKey) {
		this.linkKey = linkKey;
	}




	public String getLinkValue() {
		return linkValue;
	}




	public void setLinkValue(String linkValue) {
		this.linkValue = linkValue;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getTitleColor() {
		return titleColor;
	}




	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}




	public String getListFrom() {
		return listFrom;
	}




	public void setListFrom(String listFrom) {
		this.listFrom = listFrom;
	}




	public String getSnapShot() {
		return snapShot;
	}




	public void setSnapShot(String snapShot) {
		this.snapShot = snapShot;
	}




	public String getIcon() {
		return icon;
	}




	public void setIcon(String icon) {
		this.icon = icon;
	}




	public String getCloseIcon() {
		return closeIcon;
	}




	public void setCloseIcon(String closeIcon) {
		this.closeIcon = closeIcon;
	}




	public String getHoverClose() {
		return hoverClose;
	}




	public void setHoverClose(String hoverClose) {
		this.hoverClose = hoverClose;
	}




	public String getVendor() {
		return vendor;
	}




	public void setVendor(String vendor) {
		this.vendor = vendor;
	}




	public String getVideoTitle() {
		return videoTitle;
	}




	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}




	public String getVideoSubtitle() {
		return videoSubtitle;
	}




	public void setVideoSubtitle(String videoSubtitle) {
		this.videoSubtitle = videoSubtitle;
	}




	public String getVideoDuration() {
		return videoDuration;
	}




	public void setVideoDuration(String videoDuration) {
		this.videoDuration = videoDuration;
	}




	public String getVideoId() {
		return videoId;
	}




	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}




	public String getPlayerId() {
		return playerId;
	}




	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}




	public String getPlayerKey() {
		return playerKey;
	}




	public void setPlayerKey(String playerKey) {
		this.playerKey = playerKey;
	}




	public String getYoutubeEmbedCode() {
		return youtubeEmbedCode;
	}




	public void setYoutubeEmbedCode(String youtubeEmbedCode) {
		this.youtubeEmbedCode = youtubeEmbedCode;
	}




	public String getUstudioEmbedCode() {
		return ustudioEmbedCode;
	}




	public void setUstudioEmbedCode(String ustudioEmbedCode) {
		this.ustudioEmbedCode = ustudioEmbedCode;
	}




	public String getCreatedDate() {
		return createdDate;
	}




	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}


	public String getTarget() {
		return target;
	}




	public void setTarget(String target) {
		this.target = target;
	}






	private String alternateText;
	private String description;
	private String descriptionColor;
	private String imageReference;
	private String imgTitle;
	private String linkKey;
	private String linkValue;
	private String target;
	private String title;
	private String titleColor;
	private String listFrom;
	private String snapShot;
	private String icon;
	private String closeIcon;
	private String hoverClose;
	private String vendor;
	private String videoTitle;
	private String videoSubtitle;
	private String videoDuration;
	private String videoId;
	private String playerId;
	private String playerKey;
	private String youtubeEmbedCode;
	private String ustudioEmbedCode;
	private String createdDate;
	
	

	
	public ImageTextTitleBean(String alternateText, String description, String descriptionColor,
			String imageReference, String imgTitle, String linkKey, String linkValue, String target,
			String title ,String titleColor,String listFrom,String 
			snapShot,String icon,String closeIcon,String hoverClose,
			String vendor,String videoTitle,String videoSubtitle,String 
			videoDuration,String videoId,String playerId,String playerKey
			,String youtubeEmbedCode,String ustudioEmbedCode,String createdDate) {
			super();
			this.alternateText = alternateText;
			this.description = description;
			this.descriptionColor = descriptionColor;
			this.imageReference = imageReference;
			this.imgTitle = imgTitle;
			this.linkKey = linkKey;
			this.linkValue = linkValue;
			this.target = target;
			this.title = title;
			this.titleColor = titleColor;
			this.listFrom=listFrom;
			this.snapShot=snapShot;
			this.icon=icon;
			this.closeIcon=closeIcon;
			this.hoverClose=hoverClose;
			this.vendor=vendor;
			this.videoTitle=videoTitle;
			this.videoSubtitle=videoSubtitle;
			this.videoDuration=videoDuration;
			this.videoId=videoId;
			this.playerId=playerId;
			this.playerKey=playerKey;
			this.youtubeEmbedCode=youtubeEmbedCode;
			this.ustudioEmbedCode=ustudioEmbedCode;
			this.createdDate=createdDate;
			}
	
}
