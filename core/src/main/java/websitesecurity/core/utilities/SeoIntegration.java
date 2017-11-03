package websitesecurity.core.utilities;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.jcr.AccessDeniedException;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.UnsupportedRepositoryOperationException;
import javax.jcr.Value;
import javax.jcr.ValueFormatException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.wcm.api.Page;

/**
 * We are using this class for SEOIntegration.
 */
public class SeoIntegration extends WCMUse {

	private final Logger log = LoggerFactory.getLogger(SeoIntegration.class);
	private String logoValues;
	private Map<String, String> videoValue;
	private Map<String, String> socialValues;
	private String country;
	private String language;
	private String pagePath;
	private Page pathvalue;
	private String basepath;
	private String contenttitle;
	private String contentpath;
	private String langpath;
	private String brandname;

	private Resource social;
	private Resource lang;
	private Resource cntry;
	private Resource vedio;
	private Resource logo;
	private Resource homepath;
	private Resource hometitle;
	private Resource brand;

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() throws AccessDeniedException,
			UnsupportedRepositoryOperationException, RepositoryException {
		String pagePaths = getCurrentPage().getPath();
		String[] parts = pagePaths.split("/");
		String part1 = parts[1];
		String part2 = parts[2];
		String part3 = parts[3];
		String part4 = parts[4];
		contentpath = "/" + part1 + "/" + part2 + "/" + part3 + "/" + part4;
		langpath = "/" + part1 + "/" + part2 + "/" + part3;
		ResourceResolver resourceResolver = getResourceResolver();
		social = resourceResolver.getResource(contentpath
				+ "/jcr:content/footer/par2/socialmedia");
		cntry = resourceResolver.getResource(contentpath + "/jcr:content");
		lang = resourceResolver.getResource(langpath + "/jcr:content");
		vedio = resourceResolver.getResource(contentpath
				+ "/homepage/jcr:content/par1/imageandtext");
		logo = resourceResolver.getResource(contentpath
				+ "/jcr:content/headermain/headerpar1/headercomponent");
		homepath = resourceResolver.getResource(contentpath
				+ "/jcr:content/headermain/headerpar1/headercomponent");
		hometitle = resourceResolver.getResource(contentpath
				+ "/homepage/jcr:content");
		brand = resourceResolver.getResource(pagePaths
				+ "/jcr:content/par1/menunavigation/menuparsys2/stats");

		try {
			pagePath = getCurrentPage().getPath();
			logoValues = getAltValue();
			videoValue = getVidValue();
			pathvalue = getPagePath();
			country = gethrefLangValues();
			socialValues = getSocial();
			basepath = getHomepath();
			contenttitle = getBaseTitle();
			language = getLang();
			brandname = getBrand();
		} catch (PathNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValueFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method is used to get the product brand for ssl details page
	 * @return it will return brand name.
	 * @throws ValueFormatException
	 * @throws PathNotFoundException
	 * @throws RepositoryException
	 */
	private String getBrand() throws ValueFormatException,
			PathNotFoundException, RepositoryException, NullPointerException {
		String value1 = null;
		Node ntFileNode = brand.adaptTo(Node.class);
		value1 = getPropertyValue("brand", ntFileNode);
	
		return value1;
	}

	/**
	 * This method is used to get the social icon details.
	 * @return it will give map object of String type.
	 * @throws PathNotFoundException
	 * @throws RepositoryException
	 */
	private Map<String, String> getSocial() throws PathNotFoundException, RepositoryException, NullPointerException {
		Value[] socialpaths;
		Value[] socialtitles;
		String path;
		String title;

		Map<String, String> m3 = new LinkedHashMap<String, String>();
		Node ntFileNode = social.adaptTo(Node.class);
		socialpaths = ntFileNode.getProperty("iconlink").getValues();
		for (int i = 0; i < socialpaths.length; i++) {
			path = socialpaths[i].getString();
			socialtitles = ntFileNode.getProperty("iconclass").getValues();
			title = socialtitles[i].getString();
			m3.put(socialtitles[i].getString(), socialpaths[i].getString());
		}
		return m3;
	}

	/**
	 * This method is used to get the language of web page.
	 * @return it will pass String of language.
	 * @throws ValueFormatException
	 * @throws PathNotFoundException
	 * @throws RepositoryException
	 */
	private String getLang() throws ValueFormatException,
			PathNotFoundException, RepositoryException, NullPointerException {
		String value2 = null;
		Node ntFileNode = lang.adaptTo(Node.class);
		value2 = getPropertyValue("jcr:title", ntFileNode);
		return value2;
	}

	/**
	 * This method is used to get the country of the web page.
	 * @return it will give country value.
	 * @throws PathNotFoundException
	 * @throws RepositoryException
	 */
	private String gethrefLangValues() throws PathNotFoundException,
			RepositoryException, NullPointerException {
		String value1 = null;
		Node ntFileNode = cntry.adaptTo(Node.class);
		value1 = getPropertyValue("jcr:title", ntFileNode);
		return value1;
	}

	/**
	 * This method is used to get the vedio values in homepage.
	 * @return it will give map object of String type. 
	 * @throws PathNotFoundException
	 * @throws RepositoryException
	 */
	private Map<String, String> getVidValue() throws PathNotFoundException,
			RepositoryException, NullPointerException {
		String thumbnail = "";
		String title = "";
		String creadted = "";
		String description = "";
		String embedCode = "";
		String vendor = "";
		String duration = "";

		Map<String, String> m1 = new HashMap<String, String>();
		Node ntFileNode = vedio.adaptTo(Node.class);
		thumbnail = getPropertyValue("snapshot", ntFileNode);
		title = getPropertyValue("videotitle", ntFileNode);
		creadted = getPropertyValue("jcr:lastModified", ntFileNode);
		description = getPropertyValue("videosubtitle", ntFileNode);
		embedCode = getPropertyValue("embedCode", ntFileNode);
		vendor = getPropertyValue("vendor", ntFileNode);
		duration = getPropertyValue("videoduration", ntFileNode);
		

		m1.put("thumbnail", thumbnail);
		m1.put("title", title);
		m1.put("creadted", creadted);
		m1.put("description", description);
		m1.put("embedCode", embedCode);
		m1.put("vendor", vendor);
		m1.put("duration", duration);
		return m1;
	}

	/**
	 * This method is used to get the logopath of website logo.
	 * @return it will pass String value of logopath.
	 * @throws PathNotFoundException
	 * @throws RepositoryException
	 */
	private String getAltValue() throws PathNotFoundException,
			RepositoryException, NullPointerException {
		String value = null;
		Node ntFileNode = logo.adaptTo(Node.class);
		value = getPropertyValue("imageReference", ntFileNode);
		return value;
	}

	/**
	 * This method is used to get the homepage of website .
	 * @return it will pass String value of homepage path.
	 */
	private String getHomepath() throws PathNotFoundException,
	RepositoryException, NullPointerException {
		String value3 = null;
		try {
			Node ntFileNode = homepath.adaptTo(Node.class);
			value3 = getPropertyValue("headerlogopath", ntFileNode);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return value3;
	}

	/**
	 * This method is used to get the title of website .
	 * @return it will pass String value of homepage title.
	 */
	private String getBaseTitle() throws PathNotFoundException,
	RepositoryException, NullPointerException {
		String value2 = null;
		try {
			Node ntFileNode = hometitle.adaptTo(Node.class);
			value2 = getPropertyValue("jcr:title", ntFileNode);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return value2;
	}

	public Page getPagePath() {
		return this.getPageManager().getPage(pagePath);
	}

	public Page getPathvalue() {
		return pathvalue;
	}

	public Map<String, String> getSocialValues() {
		return socialValues;
	}

	public String getCountry() {
		return country;
	}

	public String getLanguage() {
		return language;
	}

	public Map<String, String> getVideoValue() {
		return videoValue;
	}

	public String getLogoValues() {
		return logoValues;
	}

	public String getBasepath() {
		return basepath;
	}

	public String getContenttitle() {
		return contenttitle;
	}

	public String getBrandname() {
		return brandname;
	}
	
	
	public String getPropertyValue(String propertyName,Node node) {
		String propValue=null;
		try {
			if (node.hasProperty(propertyName)) {
				Property property = node.getProperty(propertyName);
				 propValue = property.getValue().toString();
			} else {
				propValue = "";
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
		catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return propValue;
	}
}