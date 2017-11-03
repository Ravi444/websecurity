package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import websitesecurity.core.models.CampaignDropdownBean;


//import websitesecurity.core.models.DataModel;





import com.adobe.cq.sightly.WCMUse;

public class CampignDropdown extends WCMUse {

	
	
	private CampaignDropdownBean campaignDropdownBean;
	private List<CampaignDropdownBean> dropdowndata;
	private String[] title;
	
	List<CampaignDropdownBean> dataList = new ArrayList<CampaignDropdownBean>();
	private static Logger LOG = LoggerFactory.getLogger(CampignDropdown.class);

	
	@Override
	public void activate() throws Exception {
		title = getProperties().get("campaignDropdowns", String[].class);
		if(title != null){
		dropdowndata = getValue();
		}
	}
	
	
	public List<CampaignDropdownBean> getData() {
		return dropdowndata;
	}
	
	
	private List<CampaignDropdownBean> getValue() {
		

		for (int i = 0; i < title.length; i++) {
			campaignDropdownBean = new CampaignDropdownBean(null,null,null,null);
			getJSONData(campaignDropdownBean, title[i].split("\\|"));
			dataList.add(campaignDropdownBean);
		}
		return dataList;
	}
	
	
	public CampaignDropdownBean getJSONData(CampaignDropdownBean model, String[] data) {
		for (int i = 0; i < data.length; i++) {
			if (i == 0) {
				model.setPlaceholder(data[i]);
			} else if (i == 1) {
				model.setName(data[i]);
			} else if (i == 2) {
				model.setId(data[i]);
			}else if (i == 3) {
				List<String> dropdownoptions = new ArrayList<String>(Arrays.asList(data[i].split(",")));
				model.setOptions(dropdownoptions);
			}
		}
		return model;
	}
	
}
