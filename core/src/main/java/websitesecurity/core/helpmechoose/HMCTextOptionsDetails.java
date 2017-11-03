package websitesecurity.core.helpmechoose;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.PathNotFoundException;

import websitesecurity.core.models.TextOptionsDetailsBean;

import com.adobe.cq.sightly.WCMUse;

/**
 * We are using this class for help me choose component.
 *
 */
public class HMCTextOptionsDetails extends WCMUse{

	private String[] ssltitle;
	private String[] sslpath;
	private int numberofitems;
	private List<TextOptionsDetailsBean> listData;
	
	/**
	 * This class execution will start from this method.
	 * @throws Exception
	 */
	@Override
	public void activate() throws PathNotFoundException {
		listData = getValues();
	}

	/**
	 * This method is used to retrieve the data and create List Data of type TextOptionsDetailsBean.
	 * @return it will return List object of type TextOptionsDetailsBean.
	 */
	private List<TextOptionsDetailsBean> getValues() {
		ssltitle = getProperties().get("ssltitle", String[].class);
		sslpath = getProperties().get("sslpath", String[].class);
		numberofitems = getProperties().get("numberofitems", Integer.class);
		List<TextOptionsDetailsBean> beanList = new ArrayList<TextOptionsDetailsBean>();
		TextOptionsDetailsBean pathBean = null;
		for (int i = 0; i <numberofitems ; i++) {
			pathBean = new TextOptionsDetailsBean(null, null);
			pathBean.setTitle(ssltitle[i]);
			pathBean.setSubtitle(sslpath[i]);
			beanList.add(pathBean);
		}
		return beanList;
	}
	
	/**
	 * This is the getter method for ListData variable.
	 * @return it will return List object of type TextOptionsDatailsBean.
	 */
	public List<TextOptionsDetailsBean> getListData() {
		return listData;
	}

}