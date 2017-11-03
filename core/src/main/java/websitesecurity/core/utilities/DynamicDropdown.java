package websitesecurity.core.utilities;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;

/**
 * @author Arunmozhi_Ns
 * We are using this class for Stats component which contain 3 dropdowns.
 */
public class DynamicDropdown extends WCMUse {
	private int validity;
	private int san;
	private Map<Integer, Integer> map1;
	private Map<Integer, Integer> map3;
	private final Logger log = LoggerFactory.getLogger(DynamicDropdown.class);

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() {
		validity = getProperties().get("validity", Integer.class);
		san = getProperties().get("san", Integer.class);
		map1 = getValidityCount();
		map3 = getSanCount();
	}

	/**
	 * This method is used to give validity in the validity dropdown. 
	 * @return it will return map object.
	 */
	private Map<Integer, Integer> getValidityCount() {
		Map<Integer, Integer> map1 = new LinkedHashMap<Integer, Integer>();
		if (validity > 0) {
			for (int i = 1; i <= validity; i++) {
				map1.put(i, i);
			}
		} else {
			log.debug("Check your Input, it might be less than or Equal to One.");
		}
		return map1;
	}

	/**
	 * This method is used to give san's count in the san dropdown. 
	 * @return it will return map object.
	 */
	private Map<Integer, Integer> getSanCount() {
		Map<Integer, Integer> map3 = new LinkedHashMap<Integer, Integer>();
		if (san > 0) {
			for (int i = 0; i <= san; i++) {
				map3.put(i, i);
			}
		} else {
			log.debug("Check your Input, it might be less than or Equal to one.");
		}
		return map3;
	}

	/**
	 * this is the getter method for map1.
	 * @return it will return map object.
	 */
	public Map<Integer, Integer> getMap1() {
		return map1;
	}

	/**
	 * this is the getter method for map3.
	 * @return it will return map object.
	 */
	public Map<Integer, Integer> getMap3() {
		return map3;
	}
}