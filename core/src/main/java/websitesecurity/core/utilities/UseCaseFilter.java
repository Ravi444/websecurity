package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import websitesecurity.core.models.DataModel;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.replication.PathNotFoundException;

/**
 * We are using this class in usecase filter component
 *
 */
public class UseCaseFilter extends WCMUse {
	private DataModel dm;
	private List<DataModel> data;
	private String FirstSelected;
	private String[] title;

	List<DataModel> dataList = new ArrayList<DataModel>();

	@Override
	public void activate() throws PathNotFoundException {
		title = getProperties().get("filterdata", String[].class);
		data = getValue();
		FirstSelected = FirstSelectedValue();
	}

	public List<DataModel> getData() {
		return data;
	}

	public String getFirstSelected() {
		return FirstSelected;
	}

	private String FirstSelectedValue() {
		String aa = title[0].toString();
		String data[] = aa.split("\\|");
		String categoryparent = data[0];
		return categoryparent;
	}

	private List<DataModel> getValue() {
		for (int i = 0; i < title.length; i++) {
			dm = new DataModel();
			getJSONData(dm, title[i].split("\\|"));
			dataList.add(dm);
		}
		return dataList;
	}

	public DataModel getJSONData(DataModel model, String[] data) {
		for (int i = 0; i < data.length; i++) {
			if (i == 0) {
				model.setP_title(data[i]);
			} else if (i == 1) {
				List<String> myList = new ArrayList<String>(Arrays.asList(data[i].split(",")));
				model.setP_featlist(myList);
			}
		}
		return model;
	}

}