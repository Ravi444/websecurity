package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import websitesecurity.core.models.ChooseSealBean;

import com.adobe.cq.sightly.WCMUse;

/**
 * @author Arunmozhi_Ns
 * We are using this class for Seal component.
 */
public class ChooseSeal extends WCMUse {
	private String[] value;
	private String[] option;
	private String[] urlvalue;
	private String[] scripttext;
	private List<ChooseSealBean> listData;
	
	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() {
		value = getProperties().get("value" , String[].class);
		option = getProperties().get("option" , String[].class);
		urlvalue = getProperties().get("urlvalue" , String[].class);
		scripttext = getProperties().get("scripttext" , String[].class);
		listData = getValues();
	}

	/**
	 * This method is used to get data from Seal Component.
	 * @return it will return list object of PathBean type.  
	 */
	private List<ChooseSealBean> getValues() {
		List<ChooseSealBean> beanList = new ArrayList<ChooseSealBean>();
		ChooseSealBean chooseSealBean = null;
		for (int i = 0; i < value.length; i++) {
			chooseSealBean = new ChooseSealBean(null, null, null, null);
			chooseSealBean.setOption(option[i]);
			chooseSealBean.setValue(value[i]);
			chooseSealBean.setUrlvalue(urlvalue[i]);
			chooseSealBean.setScripttext(scripttext[i]);
			beanList.add(chooseSealBean);
		}	
		return beanList;
	}

	/**
	 * this is the getter method for listData.
	 * @return it will return listData object of type PathBean. 
	 */
	public List<ChooseSealBean> getListData() {
		return listData;
	}
	
}