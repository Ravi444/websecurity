package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import websitesecurity.core.models.RepositoryBean;

import com.adobe.cq.sightly.WCMUse;

/**
 * 
 * We are using this class in footer list component for repositoryLinks Variation.
 *
 */
public class RepositoryLinks extends WCMUse {
	
	private String[] repositorylinks;
	private List<RepositoryBean> listData;
	

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() throws JSONException {
		repositorylinks = getProperties().get("repositorylinks", String[].class);
		if(repositorylinks != null){
		   listData = getValues();
		}
	}

	private List<RepositoryBean> getValues() throws JSONException {
		List<RepositoryBean> beanList = new ArrayList<RepositoryBean>();
		RepositoryBean repositorybean = null;
		JSONObject jsonobject;
		String text;
		
		   for (int i = 0; i < repositorylinks.length ; i++) {
			 jsonobject = new JSONObject(repositorylinks[i]);
			 text = jsonobject.getString("text");
			repositorybean = new RepositoryBean(null);
			repositorybean.setTitle(text);
			beanList.add(repositorybean);
		}
		return beanList;
	}

	/**
	 * this is the getter method for listData.
	 * @return it will return list object of DataBean type.
	 */
	public List<RepositoryBean> getListData() {
		return listData;
	}
}