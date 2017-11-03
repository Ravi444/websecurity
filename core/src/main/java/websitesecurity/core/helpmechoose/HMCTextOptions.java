package websitesecurity.core.helpmechoose;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;

/**
 * We are using this component for help me choose component.
 *
 */
public class HMCTextOptions extends WCMUse {
	private final static Logger LOG = LoggerFactory.getLogger(HMCRecommendationData.class);

	private Map<String, String> data;
	private String[] optionData;

	/**
	 * This class execution starts from this method.
	 * @throws it will throws Exception
	 */
	@Override
	public void activate() throws Exception {
		optionData = getProperties().get("optiondata", String[].class);
		data = getValue();
	}

	/**
	 * This method is used to get the data and perform the split operation.
	 * @return it will return map object of type Map<String, String>.
	 */
	private Map<String, String> getValue() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		String[] value;
		String text=null;
		String tooltext=null;
		for (int i = 0; i < optionData.length; i++) {
		    value = optionData[i].split("\\|");
		    try{
			 text = value[0];
			 tooltext = value[1];
			}catch(ArrayIndexOutOfBoundsException e){
				e.getStackTrace();
			}
			map.put(text, tooltext);
		}
		return map;
	}

	
	/**
	 * This is getter method of data variable.
	 * @return it will return map object of type map<String String>.
	 */
	public Map<String, String> getData() {
		return data;
	}

}