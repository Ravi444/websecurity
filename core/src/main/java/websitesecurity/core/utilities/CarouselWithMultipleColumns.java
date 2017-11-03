package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import websitesecurity.core.models.DataBean;

import com.adobe.cq.sightly.WCMUse;

/**
 * @author Aakriti
 * we are using this class for carouselwithimagetexttitle component.
 * 
 */
public class CarouselWithMultipleColumns extends WCMUse {
	private int count;
	private List<DataBean> listData;

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() {
		listData = getValues();
	}

	/**
	 * This method is used to get data from carouselwithimagetexttitle component
	 * 
	 * @return it will return list object of DataBean type.
	 */
	private List<DataBean> getValues() {
		List<DataBean> beanList = new ArrayList<DataBean>();
		DataBean dataBean = null;
		count = getProperties().get("slidescount", Integer.class);
		for (int i = 0; i < count; i++) {
			dataBean = new DataBean(null);
			dataBean.setCount("" + i);
			beanList.add(dataBean);
		}
		return beanList;
	}

	/**
	 * this is the getter method for listData.
	 * 
	 * @return it will return list object of DataBean type.
	 */
	public List<DataBean> getListData() {
		return listData;
	}
}