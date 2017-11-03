package websitesecurity.core.utilities;

import java.util.List;

import javax.jcr.PathNotFoundException;

import websitesecurity.core.models.ImageTextTitleBean;

import com.adobe.cq.sightly.WCMUse;

/**
 * @author Rishabh_Gupta
 * We are using this class to retrieve currentPage path.
 *
 */
public class CarouselImageTextTitle extends WCMUse {

	private List<ImageTextTitleBean> imageTextTitleData;
	
	/**
	 * This is getter method for ImageTextTitleData Variable
	 * @return it will return List object of ImageTextTitleBean type. 
	 */
	public List<ImageTextTitleBean> getImageTextTitleData() {
		return imageTextTitleData;
	}

	/**
	 * This is the setter method for ImageTextTitleData Variable
	 * @param It will access imageTextTitleData as input.
	 */
	public void setImageTextTitleData(List<ImageTextTitleBean> imageTextTitleData) {
		this.imageTextTitleData = imageTextTitleData;
	}

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() throws PathNotFoundException {
		CarouselCountService service = getSlingScriptHelper().getService(CarouselCountService.class);
		imageTextTitleData = service.imageTextAndTextCount(getCurrentPage().getPath());
	}

}