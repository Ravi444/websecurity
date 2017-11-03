package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import websitesecurity.core.models.PathBean;

import com.adobe.cq.sightly.WCMUse;

/**
 * @author Vasudev_Munagala
 * We are using this class for Icon Component in 5 variation.
 *
 */
public class IconsComponent extends WCMUse{
	private String[] imageTitle;
	private String[] imagePath;
	private List<PathBean> listData;

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() {
		listData = getValues();
	}

	/**
	 * This method is used to get data from Icon component for 5 variation
	 * @return it will return list object of PathBean type.
	 */
	private List<PathBean> getValues() {
		List<PathBean> beanList = new ArrayList<PathBean>();
		PathBean pathBean = null;
		imageTitle = getProperties().get("title", String[].class);
		imagePath = getProperties().get("path", String[].class);
		int max = 0;
		if (imageTitle.length <= 5) {
			max = imageTitle.length;
		} else {
			max = 5;
		}
		for (int i = 0; i < max; i++) {
			pathBean = new PathBean(null, null);
			pathBean.setTitle(imageTitle[i]);
			pathBean.setPath(imagePath[i]);
			beanList.add(pathBean);
		}
		return beanList;
	}

	/**
	 * this is the getter method for listData.
	 * @return it will return list object of PathBean type.
	 */
	public List<PathBean> getListData() {
		return listData;
	}
}