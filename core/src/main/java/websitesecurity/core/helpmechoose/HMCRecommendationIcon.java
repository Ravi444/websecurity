package websitesecurity.core.helpmechoose;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import websitesecurity.core.models.RecommendationModel;

import com.adobe.cq.sightly.WCMUse;

public class HMCRecommendationIcon extends WCMUse {

	private final static Logger LOG = LoggerFactory.getLogger(HMCRecommendationData.class);

	private RecommendationModel dm;
	private RecommendationModel dm1;
	private List<RecommendationModel> data;
	private List<RecommendationModel> data1;
	private String[] sslicondetail;
	private String[] csicondetail;
	private final Logger log = LoggerFactory.getLogger(HMCRecommendationIcon.class);
	List<RecommendationModel> dataList = new ArrayList<RecommendationModel>();
	List<RecommendationModel> csdataList = new ArrayList<RecommendationModel>();

	@Override
	public void activate() throws Exception {
	
		sslicondetail = getProperties().get("sslicondetail", String[].class);
		csicondetail = getProperties().get("csicondetail", String[].class);
		data = getSSLValue();
		data1 = getCSValue();
	

	}

	public List<RecommendationModel> getData() {
		return data;
	}

	public List<RecommendationModel> getData1() {
		return data1;
	}

	private List<RecommendationModel> getSSLValue() {
		for (int i = 0; i < sslicondetail.length; i++) {
			dm = new RecommendationModel();
			getJSONData(dm, sslicondetail[i].split("\\|"));
			dataList.add(dm);

		}
		return dataList;
	}

	private List<RecommendationModel> getCSValue() {
		for (int i = 0; i < csicondetail.length; i++) {
			dm1 = new RecommendationModel();
			getJSONData1(dm1, csicondetail[i].split("\\|"));
			csdataList.add(dm1);

		}
		return csdataList;
	}

	public RecommendationModel getJSONData1(RecommendationModel model1, String[] data1) {
		for (int i = 0; i < data1.length; i++) {
			if (i == 0) {
				model1.setTitle(data1[i]);
			} else if (i == 1) {
				model1.setOnicon(data1[i]);

			} else if (i == 2) {
				model1.setOfficon(data1[i]);

			}else if (i == 3) {
				model1.setHeading1(data1[i]);

			}
			else if (i == 4) {
				model1.setDescription1(data1[i]);

			}
			else if (i == 5) {
				model1.setHeading2(data1[i]);

			}
			else if (i == 6) {
				model1.setDescription2(data1[i]);

			}
			else if (i == 7) {
				model1.setHeading3(data1[i]);

			}else if (i == 8) {
				model1.setDescription3(data1[i]);

			}else if (i == 9) {
				model1.setHeading4(data1[i]);

			}else if (i == 10) {
				model1.setDescription4(data1[i]);

			}else if (i == 11) {
				model1.setHeading5(data1[i]);

			}else if (i == 12) {
				model1.setDescription5(data1[i]);

			}

		}
		return model1;
	}

	public RecommendationModel getJSONData(RecommendationModel model, String[] data) {
		for (int i = 0; i < data.length; i++) {
			if (i == 0) {
				model.setTitle(data[i]);
			} else if (i == 1) {
				model.setOnicon(data[i]);

			} else if (i == 2) {
				model.setOfficon(data[i]);

			}else if (i == 3) {
				model.setHeading1(data[i]);

			}
			else if (i == 4) {
				model.setDescription1(data[i]);

			}
			else if (i == 5) {
				model.setHeading2(data[i]);

			}
			else if (i == 6) {
				model.setDescription2(data[i]);

			}
			else if (i == 7) {
				model.setHeading3(data[i]);

			}else if (i == 8) {
				model.setDescription3(data[i]);

			}else if (i == 9) {
				model.setHeading4(data[i]);

			}else if (i == 10) {
				model.setDescription4(data[i]);

			}else if (i == 11) {
				model.setHeading5(data[i]);

			}else if (i == 12) {
				model.setDescription5(data[i]);

			}

		}
		return model;
	}

	public Logger getLog() {
		return log;
	}

}
