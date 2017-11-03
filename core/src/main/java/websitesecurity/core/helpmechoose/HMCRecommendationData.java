package websitesecurity.core.helpmechoose;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFormatException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import websitesecurity.core.models.HMCRecommendationBean;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
//QUeryBuilder APIs
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;

/**
 * 
 * We are using this class for Certificate in Recommendation.
 * It will retrieve data from Certificate component.
 * 
 */

/**
 * @author ravi ranjan
 *
 */
@Component(immediate = true)
@Service
public class HMCRecommendationData implements HMCRecommendationService {
	
	 

	private final static Logger LOG = LoggerFactory.getLogger(HMCRecommendationData.class);
	private Map<Integer, Integer> map1;
	private Map<Integer, Integer> map2;
	private Map<Integer, Integer> map3;
	/**
	 * Gets the log.
	 *
	 * @return the log
	 */
	public static Logger getLog() {
		return LOG;
	}

	@Reference
	private ResourceResolverFactory resourceFactory;
	ResourceResolver rr = null;
	private Session session;
	@Reference
	private QueryBuilder builder;
	
	Node node;

	/*
	 * (non-Javadoc)
	 * 
	 * @see websitesecurity.core.utilities.ReadService#ResourceResolverObject()
	 * returns the resource resolver object
	 */
	@Override
	public ResourceResolver ResourceResolverObject() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// Mention the subServiceName you had used in the User Mapping
		paramMap.put(ResourceResolverFactory.SUBSERVICE, "readService");
		try {
			rr = resourceFactory.getServiceResourceResolver(paramMap);
		} catch (LoginException e) {
			e.printStackTrace();
		}
		return rr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * websitesecurity.core.utilities.CarouselCountService#imageTextAndTextCount
	 * (java.lang.String) This method will take current url as string and
	 * retrieve all ImageTextandTitle component details in ImageTextTitleBean
	 * format.
	 */
	@Override
	public List<HMCRecommendationBean> certificateCount(String currenturl,String types, String product, String cws) {

		
	
	List<String> myList = new ArrayList<String>(Arrays.asList(product.split(":")));

	int incrementor = Integer.parseInt("1");
	   int i;
		
		
		List<HMCRecommendationBean> recommendatonList = new ArrayList<HMCRecommendationBean>();
		HMCRecommendationBean recommendationBean ;
		try {
			rr = ResourceResolverObject();
			session = rr.adaptTo(Session.class);
			
			// create query description as hash map

			Map<String, String> map = new HashMap<String, String>();
			map.put("path", currenturl + HMCRecommendationConfig.propertyPath);
			map.put("nodename", HMCRecommendationConfig.propertyNodeName);
			if (types.equalsIgnoreCase(HMCRecommendationConfig.propertyCS) && cws.equalsIgnoreCase(HMCRecommendationConfig.propertyCWSFalse)) {
				
				map.put("group.1_property", HMCRecommendationConfig.propertyListFrom);
				map.put("group.1_property.value", HMCRecommendationConfig.propertyCodesigning);
				for(i=0;i<myList.size();i++)
				{	
					
					int val=i+incrementor;

					map.put("group."+val+"_property", HMCRecommendationConfig.propertyCSProduct);
					map.put("group."+val+"_property.value", myList.get(i));
					map.put("group.p.or", HMCRecommendationConfig.propertyCWSTrue);
				}
				
			} else if (types.equalsIgnoreCase(HMCRecommendationConfig.propertyCS) && cws.equalsIgnoreCase(HMCRecommendationConfig.propertyCWSTrue)) {
			
				map.put("group.1_property", HMCRecommendationConfig.propertyListFrom);
				map.put("group.1_property.value", HMCRecommendationConfig.propertyCodesigning);
				for( i=0;i<myList.size();i++)
				{
					int val=i+incrementor;
				
					map.put("group."+val+"_property", HMCRecommendationConfig.propertyCSProduct);
					map.put("group."+val+"_property.value", myList.get(i));
					map.put("group.p.or", HMCRecommendationConfig.propertyCWSTrue);
				}
				map.put("group.21_property", HMCRecommendationConfig.propertyListFrom);
				map.put("group.21_property.value", HMCRecommendationConfig.propertyCWSCertificate);
			
			} else if (types.equalsIgnoreCase(HMCRecommendationConfig.propertySSL) && cws.equalsIgnoreCase(HMCRecommendationConfig.propertyCWSFalse)) {
		
				map.put("group.1_property", HMCRecommendationConfig.propertyListFrom);
				map.put("group.1_property.value", HMCRecommendationConfig.propertySSL);
				for(i=0;i<myList.size();i++)
				{
					int val=i+incrementor;
				  
					map.put("group."+val+"_property", HMCRecommendationConfig.propertySSLProduct);
					map.put("group."+val+"_property.value", myList.get(i));
					map.put("group.p.or", HMCRecommendationConfig.propertyCWSTrue);
				}
				
			} else if (types.equalsIgnoreCase(HMCRecommendationConfig.propertySSL) && cws.equalsIgnoreCase(HMCRecommendationConfig.propertyCWSTrue)) {
			
				map.put("group.1_property", HMCRecommendationConfig.propertyListFrom);
				map.put("group.1_property.value", HMCRecommendationConfig.propertySSL);
				for( i=0;i<myList.size();i++)
				{
					int val=i+incrementor;
					
					map.put("group."+val+"_property", HMCRecommendationConfig.propertySSLProduct);
					map.put("group."+val+"_property.value", myList.get(i));
					map.put("group.p.or", HMCRecommendationConfig.propertyCWSTrue);
				}
				map.put("group.21_property", HMCRecommendationConfig.propertyListFrom);
				map.put("group.21_property.value", HMCRecommendationConfig.propertyCWSCertificate);

			} else {
				
				map.put("group.1_property", HMCRecommendationConfig.propertyListFrom);
				map.put("group.1_property.value", HMCRecommendationConfig.propertyCWSCertificate);

			}
			
			Query query = builder.createQuery(PredicateGroup.create(map),session);
		
			SearchResult result = query.getResult();
			
			Iterator<Node> nt = result.getNodes();
			
			while (nt.hasNext()) {
				recommendationBean = new HMCRecommendationBean();
				 node = (Node) nt.next();
			
				try {

					if (types.equalsIgnoreCase(HMCRecommendationConfig.propertySSL) && cws.equalsIgnoreCase(HMCRecommendationConfig.propertyCWSFalse) ) {
						
							
							
							String listFrom = getPropertyValue(HMCRecommendationConfig.propertyListFrom, node);
							recommendationBean.setType(listFrom);
							
							String marginRequired = getPropertyValue(HMCRecommendationConfig.propertyMargin, node);
							recommendationBean.setMargin(marginRequired);
							
							String type = getPropertyValue(HMCRecommendationConfig.propertySSLProduct, node);
							recommendationBean.setProductType(type);
							
							String sslcompletebackground = getPropertyValue(HMCRecommendationConfig.propertySslCompleteBackground, node);
							recommendationBean.setSslbackgroundcolor(sslcompletebackground);
							
							String sslcertificatetitle = getPropertyValue(HMCRecommendationConfig.propertySSLCertificateTitle, node);
							recommendationBean.setSsltitle(sslcertificatetitle);
							
							String sslcertificatetitlecolor = getPropertyValue(HMCRecommendationConfig.propertySSLCertificateTitleColor, node);
							recommendationBean.setSsltitlecolor(sslcertificatetitlecolor);
							
							String sslcertificatesubtitle = getPropertyValue(HMCRecommendationConfig.propertySSLCertificateSubtitle, node);
							recommendationBean.setSslsubtitle(sslcertificatesubtitle);
							
							String sslcertificatesubtitlecolor = getPropertyValue(HMCRecommendationConfig.propertySSLCertificateSubtitleColor, node);
							recommendationBean.setSslsubtitlecolor(sslcertificatesubtitlecolor);
							
							String ssldescription = getPropertyValue(HMCRecommendationConfig.propertySSLDescription, node);
							recommendationBean.setSsldescripton(ssldescription);
							
							String sslrightsectiontitle = getPropertyValue(HMCRecommendationConfig.propertySSLRightSectionTitle, node);
							recommendationBean.setSslrightsectiontitle(sslrightsectiontitle);
							
							String sslrighttitlecolor = getPropertyValue(HMCRecommendationConfig.propertySSLRightSectionTitleColor, node);
							recommendationBean.setSslrightsectiontitlecolor(sslrighttitlecolor);
							
							String Validitytextlabel = getPropertyValue(HMCRecommendationConfig.propertySSLValidityTextLabel, node);
							recommendationBean.setSslvaliditytext(Validitytextlabel);
							
							String validityCount = getPropertyValue(HMCRecommendationConfig.propertySSLValidity, node);
	 						int ValidityValue = Integer.parseInt(validityCount);
							recommendationBean.setSslvalidity(ValidityValue);
							map1 = getValidityCount(ValidityValue);
							recommendationBean.setValidityMap1(map1);
							
							String servertextlabel = getPropertyValue(HMCRecommendationConfig.propertySSLServerTextLabel, node);
							recommendationBean.setServertextlabel(servertextlabel);
							
							String server = getPropertyValue(HMCRecommendationConfig.propertySSLServer, node);
							recommendationBean.setServer(server);
							
							String serveramount = getPropertyValue(HMCRecommendationConfig.propertySSLServerAmount, node);
							recommendationBean.setServeramount(serveramount);
							
							String serverrate = getPropertyValue(HMCRecommendationConfig.propertySSLServerRate, node);
							recommendationBean.setServerrate(serverrate);
							
							String santextlabel = getPropertyValue(HMCRecommendationConfig.propertySSLSanTextLabel, node);
							recommendationBean.setSantextlabel(santextlabel);
							
							String sanCount = getPropertyValue(HMCRecommendationConfig.propertySSLSan, node);
	 						int sanValue = Integer.parseInt(sanCount);
							recommendationBean.setSan(sanValue);
							map2 = getValidityCount(sanValue);
							recommendationBean.setValidityMap2(map2);
							
							String sslsanamount = getPropertyValue(HMCRecommendationConfig.propertySSLSanAmount, node);
							recommendationBean.setSslsanamount(sslsanamount);
							
							String sslsanrate = getPropertyValue(HMCRecommendationConfig.propertySSLSanRate, node);
							recommendationBean.setSslsanrate(sslsanrate);
							
							String sslbackground = getPropertyValue(HMCRecommendationConfig.propertySSLBackground, node);
							recommendationBean.setSslbackground(sslbackground);
							
							String ssltotal = getPropertyValue(HMCRecommendationConfig.propertySSLTotal, node);
							recommendationBean.setSsltotal(ssltotal);
							
							String sslamount = getPropertyValue(HMCRecommendationConfig.propertySSLAmount, node);
							recommendationBean.setSslamount(sslamount);
							
							String ssldiscounttext = getPropertyValue(HMCRecommendationConfig.propertySSLDiscountText, node);
							recommendationBean.setSsldiscounttext(ssldiscounttext);
							
							String ssldiscountamount = getPropertyValue(HMCRecommendationConfig.propertySSLDiscountAmount, node);
							recommendationBean.setSsldiscountamount(ssldiscountamount);
							
							String ssllocale = getPropertyValue(HMCRecommendationConfig.propertySSLLocale, node);
							recommendationBean.setSsllocale(ssllocale);
							
							String sslbutton = getPropertyValue(HMCRecommendationConfig.propertySSLButton, node);
							recommendationBean.setSslbutton(sslbutton);
							
							String sslpath = getPropertyValue(HMCRecommendationConfig.propertySSLPath, node);
							recommendationBean.setSslpath(sslpath);
							
							String sslPriceurl = getPropertyValue(HMCRecommendationConfig.propertySSLUrl, node);
							recommendationBean.setSslPriceurl(sslPriceurl);
							
							String sslBrand = getPropertyValue(HMCRecommendationConfig.propertySSLBrand, node);
							recommendationBean.setSslBrand(sslBrand);
							
							String sslCountry = getPropertyValue(HMCRecommendationConfig.propertySSLCountry, node);
							recommendationBean.setSslCountry(sslCountry);
							
							String sslIsInternational = getPropertyValue(HMCRecommendationConfig.propertySSLIsInternational, node);
							recommendationBean.setSslIsInternational(sslIsInternational);
							
							String sslAlertText = getPropertyValue(HMCRecommendationConfig.propertySSLFailureSectionTitle, node);
							recommendationBean.setSslFailureText(sslAlertText);
							
							String sslFailureText = getPropertyValue(HMCRecommendationConfig.propertySSLFailureTitle, node);
							recommendationBean.setSslAlertText(sslFailureText);
							
							String sslTimeOut = getPropertyValue(HMCRecommendationConfig.propertySSLTimeout, node);
							recommendationBean.setSslTimeOut(sslTimeOut);
							
							String sslproductname = getPropertyValue(HMCRecommendationConfig.propertySSLProductname, node);
							recommendationBean.setSslproductname(sslproductname);

					} else if (types.equalsIgnoreCase(HMCRecommendationConfig.propertySSL) && cws.equalsIgnoreCase(HMCRecommendationConfig.propertyCWSTrue)){
						
						String listFrom = getPropertyValue(HMCRecommendationConfig.propertyListFrom, node);
						recommendationBean.setType(listFrom);
						
						if(listFrom.equalsIgnoreCase(HMCRecommendationConfig.propertySSL)){
						
							
							String marginRequired = getPropertyValue(HMCRecommendationConfig.propertyMargin, node);
							recommendationBean.setMargin(marginRequired);
							
							String type = getPropertyValue(HMCRecommendationConfig.propertySSLProduct, node);
							recommendationBean.setProductType(type);
							
							String sslcompletebackground = getPropertyValue(HMCRecommendationConfig.propertySslCompleteBackground, node);
							recommendationBean.setSslbackgroundcolor(sslcompletebackground);
							
							String sslcertificatetitle = getPropertyValue(HMCRecommendationConfig.propertySSLCertificateTitle, node);
							recommendationBean.setSsltitle(sslcertificatetitle);
							
							String sslcertificatetitlecolor = getPropertyValue(HMCRecommendationConfig.propertySSLCertificateTitleColor, node);
							recommendationBean.setSsltitlecolor(sslcertificatetitlecolor);
							
							String sslcertificatesubtitle = getPropertyValue(HMCRecommendationConfig.propertySSLCertificateSubtitle, node);
							recommendationBean.setSslsubtitle(sslcertificatesubtitle);
							
							String sslcertificatesubtitlecolor = getPropertyValue(HMCRecommendationConfig.propertySSLCertificateSubtitleColor, node);
							recommendationBean.setSslsubtitlecolor(sslcertificatesubtitlecolor);
							
							String ssldescription = getPropertyValue(HMCRecommendationConfig.propertySSLDescription, node);
							recommendationBean.setSsldescripton(ssldescription);
							
							String sslrightsectiontitle = getPropertyValue(HMCRecommendationConfig.propertySSLRightSectionTitle, node);
							recommendationBean.setSslrightsectiontitle(sslrightsectiontitle);
							
							String sslrighttitlecolor = getPropertyValue(HMCRecommendationConfig.propertySSLRightSectionTitleColor, node);
							recommendationBean.setSslrightsectiontitlecolor(sslrighttitlecolor);
							
							String Validitytextlabel = getPropertyValue(HMCRecommendationConfig.propertySSLValidityTextLabel, node);
							recommendationBean.setSslvaliditytext(Validitytextlabel);
							
							String validityCount = getPropertyValue(HMCRecommendationConfig.propertySSLValidity, node);
	 						int ValidityValue = Integer.parseInt(validityCount);
							recommendationBean.setSslvalidity(ValidityValue);
							map1 = getValidityCount(ValidityValue);
							recommendationBean.setValidityMap1(map1);
							
							String servertextlabel = getPropertyValue(HMCRecommendationConfig.propertySSLServerTextLabel, node);
							recommendationBean.setServertextlabel(servertextlabel);
							
							String server = getPropertyValue(HMCRecommendationConfig.propertySSLServer, node);
							recommendationBean.setServer(server);
							
							String serveramount = getPropertyValue(HMCRecommendationConfig.propertySSLServerAmount, node);
							recommendationBean.setServeramount(serveramount);
							
							String serverrate = getPropertyValue(HMCRecommendationConfig.propertySSLServerRate, node);
							recommendationBean.setServerrate(serverrate);
							
							String santextlabel = getPropertyValue(HMCRecommendationConfig.propertySSLSanTextLabel, node);
							recommendationBean.setSantextlabel(santextlabel);
							
							String sanCount = getPropertyValue(HMCRecommendationConfig.propertySSLSan, node);
	 						int sanValue = Integer.parseInt(sanCount);
							recommendationBean.setSan(sanValue);
							map2 = getValidityCount(sanValue);
							recommendationBean.setValidityMap2(map2);
							
							String sslsanamount = getPropertyValue(HMCRecommendationConfig.propertySSLSanAmount, node);
							recommendationBean.setSslsanamount(sslsanamount);
							
							String sslsanrate = getPropertyValue(HMCRecommendationConfig.propertySSLSanRate, node);
							recommendationBean.setSslsanrate(sslsanrate);
							
							String sslbackground = getPropertyValue(HMCRecommendationConfig.propertySSLBackground, node);
							recommendationBean.setSslbackground(sslbackground);
							
							String ssltotal = getPropertyValue(HMCRecommendationConfig.propertySSLTotal, node);
							recommendationBean.setSsltotal(ssltotal);
							
							String sslamount = getPropertyValue(HMCRecommendationConfig.propertySSLAmount, node);
							recommendationBean.setSslamount(sslamount);
							
							String ssldiscounttext = getPropertyValue(HMCRecommendationConfig.propertySSLDiscountText, node);
							recommendationBean.setSsldiscounttext(ssldiscounttext);
							
							String ssldiscountamount = getPropertyValue(HMCRecommendationConfig.propertySSLDiscountAmount, node);
							recommendationBean.setSsldiscountamount(ssldiscountamount);
							
							String ssllocale = getPropertyValue(HMCRecommendationConfig.propertySSLLocale, node);
							recommendationBean.setSsllocale(ssllocale);
							
							String sslbutton = getPropertyValue(HMCRecommendationConfig.propertySSLButton, node);
							recommendationBean.setSslbutton(sslbutton);
							
							String sslpath = getPropertyValue(HMCRecommendationConfig.propertySSLPath, node);
							recommendationBean.setSslpath(sslpath);
							
							String sslPriceurl = getPropertyValue(HMCRecommendationConfig.propertySSLUrl, node);
							recommendationBean.setSslPriceurl(sslPriceurl);
							
							String sslBrand = getPropertyValue(HMCRecommendationConfig.propertySSLBrand, node);
							recommendationBean.setSslBrand(sslBrand);
							
							String sslCountry = getPropertyValue(HMCRecommendationConfig.propertySSLCountry, node);
							recommendationBean.setSslCountry(sslCountry);
							
							String sslIsInternational = getPropertyValue(HMCRecommendationConfig.propertySSLIsInternational, node);
							recommendationBean.setSslIsInternational(sslIsInternational);
							
							String sslAlertText = getPropertyValue(HMCRecommendationConfig.propertySSLFailureSectionTitle, node);
							recommendationBean.setSslFailureText(sslAlertText);
							
							String sslFailureText = getPropertyValue(HMCRecommendationConfig.propertySSLFailureTitle, node);
							recommendationBean.setSslAlertText(sslFailureText);
							
							String sslTimeOut = getPropertyValue(HMCRecommendationConfig.propertySSLTimeout, node);
							recommendationBean.setSslTimeOut(sslTimeOut);
							
							String sslproductname = getPropertyValue(HMCRecommendationConfig.propertySSLProductname, node);
							recommendationBean.setSslproductname(sslproductname);
						}
						else
						{
							
						//cws start
							String marginRequired = getPropertyValue(HMCRecommendationConfig.propertyMargin, node);
							recommendationBean.setMargin(marginRequired);
							
							String listFrom1 = getPropertyValue(HMCRecommendationConfig.propertyCWSListFrom1, node);
							recommendationBean.setListfrom(listFrom1);
							
							String cwsbackgroundcolor = getPropertyValue(HMCRecommendationConfig.propertyCWSBackgroundColor, node);
							recommendationBean.setCwsbackgroundcolor(cwsbackgroundcolor);
							
							String cwstitle = getPropertyValue(HMCRecommendationConfig.propertyCWSTitle, node);
							recommendationBean.setCwstitle(cwstitle);
							
							String cwstitlecolor = getPropertyValue(HMCRecommendationConfig.propertyCWSTitleColor, node);
							recommendationBean.setCwstitlecolor(cwstitlecolor);
							
							String cwssubtitle = getPropertyValue(HMCRecommendationConfig.propertyCWSSubtitle, node);
							recommendationBean.setCwssubtitle(cwssubtitle);
							
							String cwssubtitlecolor = getPropertyValue(HMCRecommendationConfig.propertyCWSSubtitleColor, node);
							recommendationBean.setCwssubtitlecolor(cwssubtitlecolor);
							
							String cwsdescription = getPropertyValue(HMCRecommendationConfig.propertyCWSDescription, node);
							recommendationBean.setCwsdescripton	(cwsdescription);
							
							String cwslinktitle = getPropertyValue(HMCRecommendationConfig.propertyCWSLinkTitle, node);
							recommendationBean.setCwslinktitle(cwslinktitle);
							
							String cwslinkvalue = getPropertyValue(HMCRecommendationConfig.propertyCWSLinkValue, node);
							recommendationBean.setCwslinkvalue(cwslinkvalue);
							
							String cwsdesctitle = getPropertyValue(HMCRecommendationConfig.propertyCWSDescriptionTitle, node);
							recommendationBean.setCwsdesctitle(cwsdesctitle);
							
							String cwsleftbottomdesc = getPropertyValue(HMCRecommendationConfig.propertyCWSLeftBottomDesc, node);
							recommendationBean.setCwsleftbottomdesc(cwsleftbottomdesc);
							
							String cwsrightbottomdesc = getPropertyValue(HMCRecommendationConfig.propertyCWSRightBottomDesc, node);
							recommendationBean.setCwsrightbottomdesc(cwsrightbottomdesc);
							
							String cwsbottomlink = getPropertyValue(HMCRecommendationConfig.propertyCWSBottomLink, node);
							recommendationBean.setCwsbottomlink(cwsbottomlink);
							
							String cwsbottompath = getPropertyValue(HMCRecommendationConfig.propertyCWSBottomPath, node);
							recommendationBean.setCwsbottompath(cwsbottompath);
							
							String cwsimage = getPropertyValue(HMCRecommendationConfig.propertyCWSImage, node);
							recommendationBean.setCwsimage(cwsimage);
							
							String cwsimgtitle = getPropertyValue(HMCRecommendationConfig.propertyCWSImgTitle, node);
							recommendationBean.setCwsimgtitle(cwsimgtitle	);
							
							String cwsalternatetext = getPropertyValue(HMCRecommendationConfig.propertyCWSAlternateText, node);
							recommendationBean.setCwsalternatetext(cwsalternatetext);
							
							String cwssnapshot = getPropertyValue(HMCRecommendationConfig.propertyCWSSnapshot, node);
							recommendationBean.setSnapShot(cwssnapshot);
							
							String cwsvideoicon = getPropertyValue(HMCRecommendationConfig.propertyCWSVideoIcon, node);
							recommendationBean.setIcon(cwsvideoicon);
							
							String closeicon = getPropertyValue(HMCRecommendationConfig.propertyCWSCloseIcon, node);
							recommendationBean.setCloseIcon(closeicon);
							
							String hoverclose = getPropertyValue(HMCRecommendationConfig.propertyCWSHoverClose, node);
							recommendationBean.setHoverClose(hoverclose);
							
							String vendor = getPropertyValue(HMCRecommendationConfig.propertyCWSVendor, node);
							recommendationBean.setVendor(vendor);
							
							String videotitle = getPropertyValue(HMCRecommendationConfig.propertyCWSVideoTitle, node);
							recommendationBean.setVideoTitle(videotitle);
							
							String videosubtitle = getPropertyValue(HMCRecommendationConfig.propertyCWSVideoSubtitle, node);
							recommendationBean.setVideoSubtitle(videosubtitle);
							
							String videoduration = getPropertyValue(HMCRecommendationConfig.propertyCWSVideoDuration, node);
							recommendationBean.setVideoDuration(videoduration);
							
							String videoID = getPropertyValue(HMCRecommendationConfig.propertyCWSVideoID, node);
							recommendationBean.setVideoId(videoID);
							
							String playerID = getPropertyValue(HMCRecommendationConfig.propertyCWSPlayerID, node);
							recommendationBean.setPlayerId(playerID);
							
							String playerKey = getPropertyValue(HMCRecommendationConfig.propertyCWSPlayerKey, node);
							recommendationBean.setPlayerKey(playerKey);
							
							String youtubeembedCode = getPropertyValue(HMCRecommendationConfig.propertyCWSYoutubeEmbedCode, node);
							recommendationBean.setYoutubeEmbedCode(youtubeembedCode);
							
							String ustudioembedCode = getPropertyValue(HMCRecommendationConfig.propertyCWSUstudioEmbedCode, node);
							recommendationBean.setUstudioEmbedCode(ustudioembedCode);
						    
	 					    Property createddate = node.getProperty(HMCRecommendationConfig.propertyCWSCreated);    
	 						String tempDate=createddate.getValue().toString(); 
	 						tempDate = tempDate.replaceAll("[^a-zA-Z0-9]", "");
	 						tempDate.replaceAll("\\s+", "");
	 						recommendationBean.setCreatedDate(tempDate);
 						
						}
						
					}

					else if (types.equalsIgnoreCase(HMCRecommendationConfig.propertyCS) && cws.equalsIgnoreCase(HMCRecommendationConfig.propertyCWSFalse)) {
						
							
							String marginRequired = getPropertyValue(HMCRecommendationConfig.propertyMargin, node);
							recommendationBean.setMargin(marginRequired);
						
							String listFrom = getPropertyValue(HMCRecommendationConfig.propertyListFrom, node);
							recommendationBean.setType(listFrom);
							
							String cscompletebackground = getPropertyValue(HMCRecommendationConfig.propertyCSCompleteBackground, node);
							recommendationBean.setCsbackgroundcolor(cscompletebackground);
							
							String cstitle = getPropertyValue(HMCRecommendationConfig.propertyCSTitle, node);
							recommendationBean.setCstitle(cstitle);
							
							String cstitlecolor = getPropertyValue(HMCRecommendationConfig.propertyCSTitleColor, node);
							recommendationBean.setCstitlecolor(cstitlecolor);
							
							String cssubtitle = getPropertyValue(HMCRecommendationConfig.propertyCSSubtitle, node);
							recommendationBean.setCssubtitle(cssubtitle);
							
							String cssubtitlecolor = getPropertyValue(HMCRecommendationConfig.propertyCSSubtitleColor, node);
							recommendationBean.setCssubtitlecolor(cssubtitlecolor);
							
							String csdescription = getPropertyValue(HMCRecommendationConfig.propertyCSDescription, node);
							recommendationBean.setCsdescripton(csdescription);
							
							String csrightsectiontitle = getPropertyValue(HMCRecommendationConfig.propertyCSRightSectionTitle, node);
							recommendationBean.setCsrightsectiontitle(csrightsectiontitle);
							
							String csrightsectiontitlecolor = getPropertyValue(HMCRecommendationConfig.propertyCSRightSectionTitleColor, node);
							recommendationBean.setCsrightsectiontitlecolor(csrightsectiontitlecolor);
							
							String csValiditytextlabel = getPropertyValue(HMCRecommendationConfig.propertyCSValidityTextLabel, node);
							recommendationBean.setCsvaliditytext(csValiditytextlabel);
							
							String csvalidityCount = getPropertyValue(HMCRecommendationConfig.propertyCSValidity, node);
	 						int csValidityValue = Integer.parseInt(csvalidityCount);
							recommendationBean.setCsvalidity(csValidityValue);
							map3 = getValidityCount(csValidityValue);
							recommendationBean.setCsvalidityMap(map3);
							
							String csbackground = getPropertyValue(HMCRecommendationConfig.propertyCSBackground, node);
							recommendationBean.setCsbackground(csbackground);
							
							String cstotal = getPropertyValue(HMCRecommendationConfig.propertyCSTotal, node);
							recommendationBean.setCstotal(cstotal);
							
							String csdefault = getPropertyValue(HMCRecommendationConfig.propertyCSDefault, node);
							recommendationBean.setCsamount(csdefault);
							
							String csdiscounttext = getPropertyValue(HMCRecommendationConfig.propertyCSDiscountText, node);
							recommendationBean.setCsdiscounttext(csdiscounttext);
							
							String csdiscountamount = getPropertyValue(HMCRecommendationConfig.propertyCSDiscountAmount, node);
							recommendationBean.setCsdiscountamount(csdiscountamount);
							
							String cslocale = getPropertyValue(HMCRecommendationConfig.propertyCSLocale, node);
							recommendationBean.setCslocale(cslocale);
							
							String csbutton = getPropertyValue(HMCRecommendationConfig.propertyCSButton, node);
							recommendationBean.setCsbutton(csbutton);
							
							String cspath = getPropertyValue(HMCRecommendationConfig.propertyCSPath, node);
							recommendationBean.setCspath(cspath);

					} else if(types.equalsIgnoreCase(HMCRecommendationConfig.propertyCS) && cws.equalsIgnoreCase(HMCRecommendationConfig.propertyCWSTrue)){
						
						String listFrom = getPropertyValue(HMCRecommendationConfig.propertyListFrom, node);
						recommendationBean.setType(listFrom);
						
						if(listFrom.equalsIgnoreCase(HMCRecommendationConfig.propertyCodesigning)){
						
							
							String marginRequired = getPropertyValue(HMCRecommendationConfig.propertyMargin, node);
							recommendationBean.setMargin(marginRequired);
						
							String cscompletebackground = getPropertyValue(HMCRecommendationConfig.propertyCSCompleteBackground, node);
							recommendationBean.setCsbackgroundcolor(cscompletebackground);
							
							String cstitle = getPropertyValue(HMCRecommendationConfig.propertyCSTitle, node);
							recommendationBean.setCstitle(cstitle);
							
							String cstitlecolor = getPropertyValue(HMCRecommendationConfig.propertyCSTitleColor, node);
							recommendationBean.setCstitlecolor(cstitlecolor);
							
							String cssubtitle = getPropertyValue(HMCRecommendationConfig.propertyCSSubtitle, node);
							recommendationBean.setCssubtitle(cssubtitle);
							
							String cssubtitlecolor = getPropertyValue(HMCRecommendationConfig.propertyCSSubtitleColor, node);
							recommendationBean.setCssubtitlecolor(cssubtitlecolor);
							
							String csdescription = getPropertyValue(HMCRecommendationConfig.propertyCSDescription, node);
							recommendationBean.setCsdescripton(csdescription);
							
							String csrightsectiontitle = getPropertyValue(HMCRecommendationConfig.propertyCSRightSectionTitle, node);
							recommendationBean.setCsrightsectiontitle(csrightsectiontitle);
							
							String csrightsectiontitlecolor = getPropertyValue(HMCRecommendationConfig.propertyCSRightSectionTitleColor, node);
							recommendationBean.setCsrightsectiontitlecolor(csrightsectiontitlecolor);
							
							String csValiditytextlabel = getPropertyValue(HMCRecommendationConfig.propertyCSValidityTextLabel, node);
							recommendationBean.setCsvaliditytext(csValiditytextlabel);
							
							String csvalidityCount = getPropertyValue(HMCRecommendationConfig.propertyCSValidity, node);
	 						int csValidityValue = Integer.parseInt(csvalidityCount);
							recommendationBean.setCsvalidity(csValidityValue);
							map3 = getValidityCount(csValidityValue);
							recommendationBean.setCsvalidityMap(map3);
							
							String csbackground = getPropertyValue(HMCRecommendationConfig.propertyCSBackground, node);
							recommendationBean.setCsbackground(csbackground);
							
							String cstotal = getPropertyValue(HMCRecommendationConfig.propertyCSTotal, node);
							recommendationBean.setCstotal(cstotal);
							
							String csdefault = getPropertyValue(HMCRecommendationConfig.propertyCSDefault, node);
							recommendationBean.setCsamount(csdefault);
							
							String csdiscounttext = getPropertyValue(HMCRecommendationConfig.propertyCSDiscountText, node);
							recommendationBean.setCsdiscounttext(csdiscounttext);
							
							String csdiscountamount = getPropertyValue(HMCRecommendationConfig.propertyCSDiscountAmount, node);
							recommendationBean.setCsdiscountamount(csdiscountamount);
							
							String cslocale = getPropertyValue(HMCRecommendationConfig.propertyCSLocale, node);
							recommendationBean.setCslocale(cslocale);
							
							String csbutton = getPropertyValue(HMCRecommendationConfig.propertyCSButton, node);
							recommendationBean.setCsbutton(csbutton);
							
							String cspath = getPropertyValue(HMCRecommendationConfig.propertyCSPath, node);
							recommendationBean.setCspath(cspath);
							

						}
						else
						{
						//cws start
							
							String marginRequired = getPropertyValue(HMCRecommendationConfig.propertyMargin, node);
							recommendationBean.setMargin(marginRequired);
							
							String listFrom1 = getPropertyValue(HMCRecommendationConfig.propertyCWSListFrom1, node);
							recommendationBean.setListfrom(listFrom1);
							
							String cwsbackgroundcolor = getPropertyValue(HMCRecommendationConfig.propertyCWSBackgroundColor, node);
							recommendationBean.setCwsbackgroundcolor(cwsbackgroundcolor);
							
							String cwstitle = getPropertyValue(HMCRecommendationConfig.propertyCWSTitle, node);
							recommendationBean.setCwstitle(cwstitle);
							
							String cwstitlecolor = getPropertyValue(HMCRecommendationConfig.propertyCWSTitleColor, node);
							recommendationBean.setCwstitlecolor(cwstitlecolor);
							
							String cwssubtitle = getPropertyValue(HMCRecommendationConfig.propertyCWSSubtitle, node);
							recommendationBean.setCwssubtitle(cwssubtitle);
							
							String cwssubtitlecolor = getPropertyValue(HMCRecommendationConfig.propertyCWSSubtitleColor, node);
							recommendationBean.setCwssubtitlecolor(cwssubtitlecolor);
							
							String cwsdescription = getPropertyValue(HMCRecommendationConfig.propertyCWSDescription, node);
							recommendationBean.setCwsdescripton	(cwsdescription);
							
							String cwslinktitle = getPropertyValue(HMCRecommendationConfig.propertyCWSLinkTitle, node);
							recommendationBean.setCwslinktitle(cwslinktitle);
							
							String cwslinkvalue = getPropertyValue(HMCRecommendationConfig.propertyCWSLinkValue, node);
							recommendationBean.setCwslinkvalue(cwslinkvalue);
							
							String cwsdesctitle = getPropertyValue(HMCRecommendationConfig.propertyCWSDescriptionTitle, node);
							recommendationBean.setCwsdesctitle(cwsdesctitle);
							
							String cwsleftbottomdesc = getPropertyValue(HMCRecommendationConfig.propertyCWSLeftBottomDesc, node);
							recommendationBean.setCwsleftbottomdesc(cwsleftbottomdesc);
							
							String cwsrightbottomdesc = getPropertyValue(HMCRecommendationConfig.propertyCWSRightBottomDesc, node);
							recommendationBean.setCwsrightbottomdesc(cwsrightbottomdesc);
							
							String cwsbottomlink = getPropertyValue(HMCRecommendationConfig.propertyCWSBottomLink, node);
							recommendationBean.setCwsbottomlink(cwsbottomlink);
							
							String cwsbottompath = getPropertyValue(HMCRecommendationConfig.propertyCWSBottomPath, node);
							recommendationBean.setCwsbottompath(cwsbottompath);
							
							String cwsimage = getPropertyValue(HMCRecommendationConfig.propertyCWSImage, node);
							recommendationBean.setCwsimage(cwsimage);
							
							String cwsimgtitle = getPropertyValue(HMCRecommendationConfig.propertyCWSImgTitle, node);
							recommendationBean.setCwsimgtitle(cwsimgtitle	);
							
							String cwsalternatetext = getPropertyValue(HMCRecommendationConfig.propertyCWSAlternateText, node);
							recommendationBean.setCwsalternatetext(cwsalternatetext);
							
							String cwssnapshot = getPropertyValue(HMCRecommendationConfig.propertyCWSSnapshot, node);
							recommendationBean.setSnapShot(cwssnapshot);
							
							String cwsvideoicon = getPropertyValue(HMCRecommendationConfig.propertyCWSVideoIcon, node);
							recommendationBean.setIcon(cwsvideoicon);
							
							String closeicon = getPropertyValue(HMCRecommendationConfig.propertyCWSCloseIcon, node);
							recommendationBean.setCloseIcon(closeicon);
							
							String hoverclose = getPropertyValue(HMCRecommendationConfig.propertyCWSHoverClose, node);
							recommendationBean.setHoverClose(hoverclose);
							
							String vendor = getPropertyValue(HMCRecommendationConfig.propertyCWSVendor, node);
							recommendationBean.setVendor(vendor);
							
							String videotitle = getPropertyValue(HMCRecommendationConfig.propertyCWSVideoTitle, node);
							recommendationBean.setVideoTitle(videotitle);
							
							String videosubtitle = getPropertyValue(HMCRecommendationConfig.propertyCWSVideoSubtitle, node);
							recommendationBean.setVideoSubtitle(videosubtitle);
							
							String videoduration = getPropertyValue(HMCRecommendationConfig.propertyCWSVideoDuration, node);
							recommendationBean.setVideoDuration(videoduration);
							
							String videoID = getPropertyValue(HMCRecommendationConfig.propertyCWSVideoID, node);
							recommendationBean.setVideoId(videoID);
							
							String playerID = getPropertyValue(HMCRecommendationConfig.propertyCWSPlayerID, node);
							recommendationBean.setPlayerId(playerID);
							
							String playerKey = getPropertyValue(HMCRecommendationConfig.propertyCWSPlayerKey, node);
							recommendationBean.setPlayerKey(playerKey);
							
							String youtubeembedCode = getPropertyValue(HMCRecommendationConfig.propertyCWSYoutubeEmbedCode, node);
							recommendationBean.setYoutubeEmbedCode(youtubeembedCode);
							
							String ustudioembedCode = getPropertyValue(HMCRecommendationConfig.propertyCWSUstudioEmbedCode, node);
							recommendationBean.setUstudioEmbedCode(ustudioembedCode);
						    
	 					    Property createddate = node.getProperty(HMCRecommendationConfig.propertyCWSCreated);    
	 						String tempDate=createddate.getValue().toString(); 
	 						tempDate = tempDate.replaceAll("[^a-zA-Z0-9]", "");
	 						tempDate.replaceAll("\\s+", "");
	 						recommendationBean.setCreatedDate(tempDate);
						}	
					}
					else
					{
					
						String marginRequired = getPropertyValue(HMCRecommendationConfig.propertyMargin, node);
						
						recommendationBean.setMargin(marginRequired);
						
						String listFrom = getPropertyValue(HMCRecommendationConfig.propertyListFrom, node);
						recommendationBean.setType(listFrom);
						
						String listFrom1 = getPropertyValue(HMCRecommendationConfig.propertyCWSListFrom1, node);
						recommendationBean.setListfrom(listFrom1);
						
						String cwsbackgroundcolor = getPropertyValue(HMCRecommendationConfig.propertyCWSBackgroundColor, node);
						recommendationBean.setCwsbackgroundcolor(cwsbackgroundcolor);
						
						String cwstitle = getPropertyValue(HMCRecommendationConfig.propertyCWSTitle, node);
						recommendationBean.setCwstitle(cwstitle);
						
						String cwstitlecolor = getPropertyValue(HMCRecommendationConfig.propertyCWSTitleColor, node);
						recommendationBean.setCwstitlecolor(cwstitlecolor);
						
						String cwssubtitle = getPropertyValue(HMCRecommendationConfig.propertyCWSSubtitle, node);
						recommendationBean.setCwssubtitle(cwssubtitle);
						
						String cwssubtitlecolor = getPropertyValue(HMCRecommendationConfig.propertyCWSSubtitleColor, node);
						recommendationBean.setCwssubtitlecolor(cwssubtitlecolor);
						
						String cwsdescription = getPropertyValue(HMCRecommendationConfig.propertyCWSDescription, node);
						recommendationBean.setCwsdescripton	(cwsdescription);
						
						String cwslinktitle = getPropertyValue(HMCRecommendationConfig.propertyCWSLinkTitle, node);
						recommendationBean.setCwslinktitle(cwslinktitle);
						
						String cwslinkvalue = getPropertyValue(HMCRecommendationConfig.propertyCWSLinkValue, node);
						recommendationBean.setCwslinkvalue(cwslinkvalue);
						
						String cwsdesctitle = getPropertyValue(HMCRecommendationConfig.propertyCWSDescriptionTitle, node);
						recommendationBean.setCwsdesctitle(cwsdesctitle);
						
						
						String cwsleftbottomdesc = getPropertyValue(HMCRecommendationConfig.propertyCWSLeftBottomDesc, node);
						recommendationBean.setCwsleftbottomdesc(cwsleftbottomdesc);
						
						String cwsrightbottomdesc = getPropertyValue(HMCRecommendationConfig.propertyCWSRightBottomDesc, node);
						recommendationBean.setCwsrightbottomdesc(cwsrightbottomdesc);
						
						String cwsbottomlink = getPropertyValue(HMCRecommendationConfig.propertyCWSBottomLink, node);
						recommendationBean.setCwsbottomlink(cwsbottomlink);
						
						String cwsbottompath = getPropertyValue(HMCRecommendationConfig.propertyCWSBottomPath, node);
						recommendationBean.setCwsbottompath(cwsbottompath);
						
						String cwsimage = getPropertyValue(HMCRecommendationConfig.propertyCWSImage, node);
						recommendationBean.setCwsimage(cwsimage);
						
						String cwsimgtitle = getPropertyValue(HMCRecommendationConfig.propertyCWSImgTitle, node);
						recommendationBean.setCwsimgtitle(cwsimgtitle	);
						
						String cwsalternatetext = getPropertyValue(HMCRecommendationConfig.propertyCWSAlternateText, node);
						recommendationBean.setCwsalternatetext(cwsalternatetext);
						
						String cwssnapshot = getPropertyValue(HMCRecommendationConfig.propertyCWSSnapshot, node);
						recommendationBean.setSnapShot(cwssnapshot);
						
						String cwsvideoicon = getPropertyValue(HMCRecommendationConfig.propertyCWSVideoIcon, node);
						recommendationBean.setIcon(cwsvideoicon);
						
						String closeicon = getPropertyValue(HMCRecommendationConfig.propertyCWSCloseIcon, node);
						recommendationBean.setCloseIcon(closeicon);
						
						String hoverclose = getPropertyValue(HMCRecommendationConfig.propertyCWSHoverClose, node);
						recommendationBean.setHoverClose(hoverclose);
						
						String vendor = getPropertyValue(HMCRecommendationConfig.propertyCWSVendor, node);
						recommendationBean.setVendor(vendor);
						
						String videotitle = getPropertyValue(HMCRecommendationConfig.propertyCWSVideoTitle, node);
						recommendationBean.setVideoTitle(videotitle);
						
						String videosubtitle = getPropertyValue(HMCRecommendationConfig.propertyCWSVideoSubtitle, node);
						recommendationBean.setVideoSubtitle(videosubtitle);
						
						String videoduration = getPropertyValue(HMCRecommendationConfig.propertyCWSVideoDuration, node);
						recommendationBean.setVideoDuration(videoduration);
						
						String videoID = getPropertyValue(HMCRecommendationConfig.propertyCWSVideoID, node);
						recommendationBean.setVideoId(videoID);
						
						String playerID = getPropertyValue(HMCRecommendationConfig.propertyCWSPlayerID, node);
						recommendationBean.setPlayerId(playerID);
						
						String playerKey = getPropertyValue(HMCRecommendationConfig.propertyCWSPlayerKey, node);
						recommendationBean.setPlayerKey(playerKey);
						
						String youtubeembedCode = getPropertyValue(HMCRecommendationConfig.propertyCWSYoutubeEmbedCode, node);
						recommendationBean.setYoutubeEmbedCode(youtubeembedCode);
						
						String ustudioembedCode = getPropertyValue(HMCRecommendationConfig.propertyCWSUstudioEmbedCode, node);
						recommendationBean.setUstudioEmbedCode(ustudioembedCode);
					    
 					    Property createddate = node.getProperty(HMCRecommendationConfig.propertyCWSCreated);    
 						String tempDate=createddate.getValue().toString(); 
 						tempDate = tempDate.replaceAll("[^a-zA-Z0-9]", "");
 						tempDate.replaceAll("\\s+", "");
 						recommendationBean.setCreatedDate(tempDate);
						
					}
					
					recommendatonList.add(recommendationBean);
					
				} catch (Exception e) {

					LOG.error(e.getMessage());

				}

			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		} finally {

			session.logout();

		}
		LOG.info("recommendatonList"+recommendatonList);
		return recommendatonList;
		
	}

	public String getPropertyValue(String propertyName,Node node) {
		String propValue=null;
		try {
			if (node.hasProperty(propertyName)) {
				Property property = node.getProperty(propertyName);
				 propValue = property.getValue().toString();
			} else {
				propValue = null;
			}
		} catch (PathNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValueFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return propValue;
	}
	
	private Map<Integer, Integer> getValidityCount(int ValidityPeriod) {
		Map<Integer, Integer> map1 = new LinkedHashMap<Integer, Integer>();
		if (ValidityPeriod > 0) {
			for (int i = 1; i <= ValidityPeriod; i++) {
				map1.put(i, i);
			}
		} else {
			LOG.debug("Check your Input, it might be less than or Equal to One.");
		}
		return map1;
	}
	
	public Map<Integer, Integer> getMap1() {
		return map1;
	}
}
