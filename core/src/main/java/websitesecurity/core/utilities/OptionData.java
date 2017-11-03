package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import websitesecurity.core.models.OptionModel;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.replication.PathNotFoundException;

/**
 * This class is used to give options in help me choose component.
 */
public class OptionData extends WCMUse {
	private OptionModel om;
	private List<OptionModel> text;
	private String[] option;
	List<OptionModel> dataList1 = new ArrayList<OptionModel>();

	/**
	 * Execution of this class will start from this method.
	 * @throws Exception
	 */
	@Override
	public void activate() throws PathNotFoundException {
		option = getProperties().get("titledata", String[].class);
		text = getOption();
	}

	/**
	 * This is getter method for text variable.
	 * @return it will return list object of OptionModel type.
	 */
	public List<OptionModel> getText() {
		return text;
	}

	/**
	 * In this method we will apply required operation on the raw data.
	 * @return it will return list object of type OptionModel.
	 */
	private List<OptionModel> getOption() {
		for (int i = 0; i < option.length; i++) {
			om = new OptionModel();
			getJSONData1(om, option[i].split("\\|"));
			dataList1.add(om);
		}
		return dataList1;
	}

	/**
	 * This method is used to split options based on separator value.
	 * @param  it will accept om2 object of type OptionModel. 
	 * @param it will accept data1 object of type String Array.
	 * @return it will return object of ObjectModel type
	 */
	private OptionModel getJSONData1(OptionModel om2, String[] data1) {
		for (int i = 0; i < data1.length; i++) {
			if (i == 0) {
				om2.setP_title1(data1[i]);
			} else if (i == 1) {
				List<String> myList = new ArrayList<String>(Arrays.asList(data1[i].split(",")));
				om2.setP_featlist1(myList);
			}
		}
		return om2;
	}
}