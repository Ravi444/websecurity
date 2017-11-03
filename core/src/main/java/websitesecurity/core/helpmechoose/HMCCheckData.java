package websitesecurity.core.helpmechoose;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.jcr.PathNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import websitesecurity.core.models.CheckModel;

import com.adobe.cq.sightly.WCMUse;

/**
 * @author Aakriti
 * we are using this for help me choose component.
 */
public class HMCCheckData extends WCMUse {
	private final Logger log = LoggerFactory.getLogger(HMCCheckData.class);

	private CheckModel om;
	private List<CheckModel> text;
	private String[] option;
	List<CheckModel> dataList1 = new ArrayList<CheckModel>();

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	/**
	 * This class execution start from here.
	 * @throws Exception
	 */
	@Override
	public void activate() throws PathNotFoundException {
		option = getProperties().get("titledata1", String[].class);
		text = getOption();
		
	}

	
	/**
	 * This is the getter method for text variable.
	 * @return it will return List object of checkModel type.
	 */
	public List<CheckModel> getText() {
		return text;
	}

	/**
	 * This method will access the data from the main method and split it.
	 * @return it will dataList1 object of CheckModel type.
	 */
	private List<CheckModel> getOption() {
		for (int i = 0; i < option.length; i++) {
			om = new CheckModel();
			getJSONData1(om, option[i].split("\\|"));
			dataList1.add(om);
		}
		return dataList1;
	}

	/**
	 * This method is used to get the JSON data.
	 * @param it will accept om2 of type CheckModel.
	 * @param it will accept data1 of type String[].
	 * @return it will return object of type CheckModel.
	 */
	private CheckModel getJSONData1(CheckModel om2, String[] data1) {
		List<String> myList = new ArrayList<String>();
		List<String> myList1 = new ArrayList<String>();
		List<String> myList2 = new ArrayList<String>();
		for (int i = 0; i < data1.length; i++) {
			
			if (i == 0) {
				om2.setP_title2(data1[i]);
			} else if (i == 1) {
			 myList = new ArrayList<String>(Arrays.asList(data1[i].split(",")));
			 om2.setP_optionlist1(myList);
			}
			else if (i == 2) {
				myList1 = new ArrayList<String>(Arrays.asList(data1[i].split(",")));
				om2.setP_optionlist2(myList1);
			}
			else if (i == 3) {
				 myList2 = new ArrayList<String>(Arrays.asList(data1[i].split(",")));
				 om2.setP_optionlist3(myList2);
			}
		}
		
		return om2;
	}
}