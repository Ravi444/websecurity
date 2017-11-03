package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.replication.PathNotFoundException;

/**
 * We are using this class for ThreadsBanner component.
 *
 */
public class ThreatsBannerBullets extends WCMUse{

	private String[] multifield;
	private List<String> listData;
	
	
	/**
	 * This class will start execution from this method.
	 * @throws Exception
	 */
	@Override
	public void activate() throws PathNotFoundException{
		listData = getValues();
	}
	
	/**
	 * This method is used to retrieve data and it will restrict it to 3.
	 * @return
	 */
	private List<String> getValues(){
		multifield = getProperties().get("multifield", String[].class);
		List<String> beanList = new ArrayList<String>();
		for(int i=0; i<3; i++){
			beanList.add(multifield[i]);
		}
		return beanList;
	}

	/**
	 * This is the getter method of ListData variable. 
	 * @return it will return list object of type string
	 */
	public List<String> getListData() {
		return listData;
	}

}