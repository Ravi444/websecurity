package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import websitesecurity.core.models.SSLCertificatesIconsBean;

import com.adobe.cq.sightly.WCMUse;

/**
 * @author Avinash_Perumalla
 * We are using this class for Campaign component.
 * 
 */
public class SSLCertificatesIcon extends WCMUse {
	private static Logger LOG = LoggerFactory.getLogger(SSLCertificatesIcon.class);

	private String[] lefttext;
	private String[] imagepath;
	private String[] icon1;
	private String[] icon2;
	private String[] icon3;
	private String[] icon4;
	private String[] icon5;
	private String[] icon6;
	private String[] boxprice;
	private String[] boxprice1;
	private String[] boxprice2;
	private String[] boxprice3;
	private String[] boxprice4;
	private String[] boxprice5;
	private String[] boxselection;
	private String[] boxselection1;
	private String[] boxselection2;
	private String[] boxselection3;
	private String[] boxselection4;
	private String[] boxselection5;

	private List<SSLCertificatesIconsBean> listData;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() {
		listData = getValues();
	}

	/**
	 * This method is used to get data.
	 * @return it will return list object of CampaignBean type.
	 */
	private List<SSLCertificatesIconsBean> getValues() {
		List<SSLCertificatesIconsBean> beanList = new ArrayList<SSLCertificatesIconsBean>();
		SSLCertificatesIconsBean sslCertificatesIconsBean = null;
			
		lefttext = getProperties().get("lefttext", String[].class);
		imagepath = getProperties().get("imagepath", String[].class);
		icon1 = getProperties().get("icon1", String[].class);
		icon2 = getProperties().get("icon2", String[].class);
		icon3 = getProperties().get("icon3", String[].class);
		icon4 = getProperties().get("icon4", String[].class);
		icon5 = getProperties().get("icon5", String[].class);
		icon6 = getProperties().get("icon6", String[].class);
		
		boxprice = getProperties().get("boxprice", String[].class);
		boxprice1 = getProperties().get("boxprice1", String[].class);
		boxprice2 = getProperties().get("boxprice2", String[].class);
		boxprice3 = getProperties().get("boxprice3", String[].class);
		boxprice4 = getProperties().get("boxprice4", String[].class);
		boxprice5 = getProperties().get("boxprice5", String[].class);
		
		boxselection = getProperties().get("boxselection", String[].class);
		boxselection1 = getProperties().get("boxselection1", String[].class);
		boxselection2 = getProperties().get("boxselection2", String[].class);
		boxselection3 = getProperties().get("boxselection3", String[].class);
		boxselection4 = getProperties().get("boxselection4", String[].class);
		boxselection5 = getProperties().get("boxselection5", String[].class);

		for (int i = 0; i < lefttext.length; i++) {
			sslCertificatesIconsBean = new SSLCertificatesIconsBean();
			sslCertificatesIconsBean.setLefttext(lefttext[i]);
			sslCertificatesIconsBean.setImagepath(imagepath[i]);
			sslCertificatesIconsBean.setIcon1(icon1[i]);
			sslCertificatesIconsBean.setIcon2(icon2[i]);
			sslCertificatesIconsBean.setIcon3(icon3[i]);
			sslCertificatesIconsBean.setIcon4(icon4[i]);
			sslCertificatesIconsBean.setIcon5(icon5[i]);
			sslCertificatesIconsBean.setIcon6(icon6[i]);
			
			sslCertificatesIconsBean.setBoxSelection(boxselection[i]);
			sslCertificatesIconsBean.setBoxSelection1(boxselection1[i]);
			sslCertificatesIconsBean.setBoxSelection2(boxselection2[i]);
			sslCertificatesIconsBean.setBoxSelection3(boxselection3[i]);
			sslCertificatesIconsBean.setBoxSelection4(boxselection4[i]);
			sslCertificatesIconsBean.setBoxSelection5(boxselection5[i]);
			
			sslCertificatesIconsBean.setBoxPrice(boxprice[i]);
			sslCertificatesIconsBean.setBoxPrice1(boxprice1[i]);
			sslCertificatesIconsBean.setBoxPrice2(boxprice2[i]);
			sslCertificatesIconsBean.setBoxPrice3(boxprice3[i]);
			sslCertificatesIconsBean.setBoxPrice4(boxprice4[i]);
			sslCertificatesIconsBean.setBoxPrice5(boxprice5[i]);
			beanList.add(sslCertificatesIconsBean);
		}
		return beanList;
	}

	/**
	 * this is the getter method for listData.
	 * @return it will return list object of CampaignBean type.
	 */
	public List<SSLCertificatesIconsBean> getListData() {
		return listData;
	}

}