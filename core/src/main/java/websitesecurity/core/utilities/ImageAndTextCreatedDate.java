package websitesecurity.core.utilities;

import java.util.HashMap;
import java.util.Map;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.replication.PathNotFoundException;

/**
 * @author Aakriti
 * We are using this class for ImageAndText component.
 */
public class ImageAndTextCreatedDate extends WCMUse {
	private String finalDate;
	private String tempDate;
	private String[] dateTemp;
	Map<String, String> tempMap = new HashMap<String, String>();

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	/**
	 * This class execution start from here.
	 * @throws Exception
	 */
	@Override
	public void activate() throws PathNotFoundException {
		tempDate = getProperties().get("jcr:created").toString();
		dateTemp = tempDate.split(",");
		String[] temp;
		for (int i = 0; i < dateTemp.length; i++) {
			temp = dateTemp[i].split("=");
			tempMap.put(temp[0], temp[1]);
		}
		finalDate = tempMap.get("DAY_OF_MONTH") + "" + tempMap.get("MONTH") + "" + tempMap.get("YEAR") + "" + tempMap.get("MINUTE") + "" + tempMap.get("SECOND") + "" + tempMap.get("MILLISECOND");
	}

	/**
	 * this is the getter method for finalDate.
	 * @return it will return list object of String type.
	 */
	public String getFinalDate() {
		return finalDate;
	}
}