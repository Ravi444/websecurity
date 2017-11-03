package websitesecurity.core.helpmechoose;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.jcr.PathNotFoundException;

import websitesecurity.core.models.DataModel;

import com.adobe.cq.sightly.WCMUse;

/**
 * We are using this class for help me choose component.
 *
 */
public class HMCPanelData extends WCMUse {
	private DataModel dm;
	private List<DataModel> data;
	private String[] title;
	
	List<DataModel> dataList = new ArrayList<DataModel>();

	/**
	 * This class execution starts from here.
	 * 
	 * @throws Exception
	 */
	@Override
	public void activate() throws PathNotFoundException {
		title = getProperties().get("icondata", String[].class);
		data = getValue();
	}

	/**
	 * This is the getter method for data variable.
	 * 
	 * @return it will return list object of DataModel type
	 */
	public List<DataModel> getData() {
		return data;
	}

	/**
	 * In this method is used to split the data by using split operation.
	 * 
	 * @return it will return List Data of type DataModel.
	 */
	private List<DataModel> getValue() {

		for (int i = 0; i < title.length; i++) {
			dm = new DataModel();
			getJSONData(dm, title[i].split("\\|"));
			dataList.add(dm);
		}
		return dataList;
	}

	/**
	 * This method is used to split options based on separator value.
	 * 
	 * @param it will accept model object of type DataModel.
	 * @param it will accept data object of type String Array.
	 * @return it will return model object of DataModel type.
	 */
	public DataModel getJSONData(DataModel model, String[] data) {
		for (int i = 0; i < data.length; i++) {
			if (i == 0) {
				model.setP_icon(data[i]);
			} else if (i == 1) {
				model.setP_title(data[i]);
			} else if (i == 2) {
				model.setP_desc(data[i]);
			} else if (i == 3) {
				model.setP_linkto(data[i]);
			} else if (i == 4) {
				model.setP_scenario(data[i]);
			} else if (i == 5) {
				List<String> myList = new ArrayList<String>(Arrays.asList(data[i].split(",")));
				model.setP_featlist(myList);
			} else if (i == 6) {
				model.setP_value(data[i]);
			}
		}
		return model;
	}
}