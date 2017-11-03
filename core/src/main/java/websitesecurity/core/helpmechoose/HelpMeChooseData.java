package websitesecurity.core.helpmechoose;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.jcr.ValueFormatException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import websitesecurity.core.models.DataModel;
import websitesecurity.core.models.CheckModel;

import com.adobe.cq.sightly.WCMUse;

public class HelpMeChooseData extends WCMUse{

private final Logger log = LoggerFactory.getLogger(HelpMeChooseData.class);
private String bannerHeading;
private Map<String, String> bannerValues;
private Map<String, String> analyticsValue;
private Map<String, String> analyticsData;


private DataModel dm;
private CheckModel om;
private List<DataModel> paneldata;
List<DataModel> dataList = new ArrayList<DataModel>();
private Map<String, String> panelValues;
private String cssliderHeading;
private Map<String, String> csOptionText;
private Map<String, String> csOptionValue;
private Map<String, String> csNavigationvalue;
private Map<String, String> formValue;
private List<String> questionone;
private List<String> questiontwo;
private Map<String, String> formSliderData;
private Map<String, String> formNavigationvalue;
private Map<String, String> formSelectionData;
private List<CheckModel> formOptionData;
List<CheckModel> dataList1 = new ArrayList<CheckModel>();
private Map<String, String> sslNavigationvalue;
private String sslSliderHeading;
private String domaintabHeading;
private Map<String, String> sslOptionText;
private Map<String, String> sslOptionData;
private Map<String, String> domainData;

private Resource analytics;
private Resource banner;
private Resource panel;
private Resource csslider;
private Resource csoptiontext;
private Resource csnavigation;
private Resource formdata;
private Resource formslider;
private Resource formNavigation;
private Resource formselect;
private Resource sslnavigation;
private Resource sslSlider;
private Resource domaintabs;
private Resource ssloption;
private Resource domainselection;

@Override
public void activate() throws ValueFormatException, RepositoryException {
String pagePaths = getCurrentPage().getPath();

ResourceResolver resourceResolver = getResourceResolver();

analytics = resourceResolver.getResource(pagePaths + "/jcr:content/par1/hmcanalytics");
banner = resourceResolver.getResource(pagePaths + "/jcr:content/par1/hmcbanner");
panel = resourceResolver.getResource(pagePaths + "/jcr:content/par1/hmcicontextwithbutto");
csslider = resourceResolver.getResource(pagePaths + "/jcr:content/par2/hmcslider");
csoptiontext = resourceResolver.getResource(pagePaths + "/jcr:content/par2/hmctextwithoption");
csnavigation = resourceResolver.getResource(pagePaths + "/jcr:content/par2/hmcnavigation");
formdata = resourceResolver.getResource(pagePaths + "/jcr:content/par4/hmcformdata");
formslider = resourceResolver.getResource(pagePaths + "/jcr:content/par4/hmcslider");
formNavigation = resourceResolver.getResource(pagePaths + "/jcr:content/par4/hmcnavigation");
formselect = resourceResolver.getResource(pagePaths + "/jcr:content/par4/hmcselectiondata");
sslnavigation = resourceResolver.getResource(pagePaths + "/jcr:content/par3/hmcnavigation");
sslSlider = resourceResolver.getResource(pagePaths + "/jcr:content/par3/hmcslider");
domaintabs = resourceResolver.getResource(pagePaths + "/jcr:content/par3/hmcdomaintabs");
ssloption = resourceResolver.getResource(pagePaths + "/jcr:content/par3/hmctextwithoption");
domainselection = resourceResolver.getResource(pagePaths + "/jcr:content/par3/hmcbasicdomain");
try {
	
	analyticsData = getTrackerData();
	analyticsValue = getAnalytics();
	bannerValues = getBanner();
	bannerHeading = getHeading();
	paneldata = getValue();
	panelValues = getPanValue();
	cssliderHeading = getCsSlider();
	csOptionText = getText();
	csOptionValue = getOptionData();
	csNavigationvalue = getCsnavigationData();
	formValue = getForm();
	questionone = getQuesOne();
	questiontwo = getQuesTwo();
	formSliderData = getFormSliderValue();
	formNavigationvalue = getformNavigationData();
	formSelectionData = getFormData();
	formOptionData = getFormOption();
	sslNavigationvalue= getSslnavigationData();
	sslSliderHeading = getSslSlider();
	domaintabHeading = getTabHeading();
	sslOptionText = getSSlOption();
	sslOptionData = getSslData();
	domainData = getDomainValue();

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
}

private Map<String, String> getTrackerData() {

String tracker = null;
String debugger = null;
Map<String, String> m1 = new HashMap<String, String>();
Node ntFileNode = analytics.adaptTo(Node.class);
try {
	tracker = ntFileNode.getProperty("tracker").getString();
	debugger = ntFileNode.getProperty("debugger").getString();
} catch (RepositoryException e) {
log.error("Retrieved validation " + e);
tracker = null;
debugger = null;
} finally{
m1.put("tracker", tracker);
m1.put("debugger", debugger);

}
return m1;

}

public Map<String, String> getAnalyticsData() {
	return analyticsData;
}

private Map<String, String> getAnalytics() throws ValueFormatException, PathNotFoundException, RepositoryException {

	Value[] analyticsTitle;
	Value[] analyticsPath;
	Map<String, String> analyticsvalues = new LinkedHashMap<String, String>();
	Node ntFileNode = analytics.adaptTo(Node.class);
	analyticsTitle = ntFileNode.getProperty("title").getValues();
	analyticsPath = ntFileNode.getProperty("path").getValues();
	for (int i = 0; i < analyticsTitle.length; i++) {
	analyticsvalues.put(analyticsPath[i].getString(), analyticsTitle[i].getString());
}

return analyticsvalues;

}

public Map<String, String> getAnalyticsValue() {
	return analyticsValue;
}



private String getHeading() throws ValueFormatException, PathNotFoundException, RepositoryException {
	String heading = null;
	Node ntFileNode = banner.adaptTo(Node.class);
	heading = ntFileNode.getProperty("heading").getString();
	return heading;
}

public String getBannerHeading() {
	return bannerHeading;
}

private Map<String, String> getBanner() throws ValueFormatException, PathNotFoundException, RepositoryException {
	Value[] stepsTitle;
	Map<String, String> bannervalues = new LinkedHashMap<String, String>();
	Node ntFileNode = banner.adaptTo(Node.class);
	stepsTitle = ntFileNode.getProperty("title").getValues();
	for (int i = 0; i < stepsTitle.length; i++) {
	bannervalues.put("title"+i, stepsTitle[i].getString());
}

return bannervalues;
}

public Map<String, String> getBannerValues() {
	return bannerValues;
}

private List<DataModel> getValue() throws ValueFormatException, PathNotFoundException, RepositoryException {
	Value[] icondata;
	String singlevalue;
	Node ntFileNode = panel.adaptTo(Node.class);
	icondata = ntFileNode.getProperty("icondata").getValues();
	
	for (int i = 0; i < icondata.length; i++) {
	singlevalue = icondata[i].toString();
	String[] values = singlevalue.split("\\|");
	dm = new DataModel();
	getJSONData(dm, values);
	dataList.add(dm);
}

return dataList;
}

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
	}else if (i == 6) {
		model.setP_value(data[i]);
	}
	}
	return model;
	}

public List<DataModel> getPaneldata() {
	return paneldata;
}


private Map<String, String> getPanValue() throws ValueFormatException, PathNotFoundException, RepositoryException {
	String heading = null;
	String description = null;
	Map<String, String> m1 = new HashMap<String, String>();
	Node ntFileNode = panel.adaptTo(Node.class);
	try {
	heading = ntFileNode.getProperty("panelheading").getString();
	description = ntFileNode.getProperty("paneldesc").getString();
	} catch (PathNotFoundException e) {
		heading = null;
		description = null;
	}finally{
		
		m1.put("heading", heading);
		m1.put("description", description);
	}

	return m1;
}

public Map<String, String> getPanelValues() {
return panelValues;
}

private String getCsSlider() throws ValueFormatException, PathNotFoundException, RepositoryException {
String csheading = null;
Node ntFileNode = csslider.adaptTo(Node.class);
csheading = ntFileNode.getProperty("codeheading").getString();
return csheading;
}


public String getCssliderHeading() {
return cssliderHeading;
}

private Map<String, String> getText() throws ValueFormatException, PathNotFoundException, RepositoryException {
String title = null;
String subtitle = null;
String validation = null;
Map<String, String> m1 = new HashMap<String, String>();
Node ntFileNode = csoptiontext.adaptTo(Node.class);
try {
title = ntFileNode.getProperty("codetitle").getString();
subtitle = ntFileNode.getProperty("subtitle").getString();
validation = ntFileNode.getProperty("cssecondvalidation").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved validation " + e);
title = null;
subtitle = null;
validation = null;
} finally{


m1.put("title", title);
m1.put("subtitle", subtitle);
m1.put("validation", validation);

}
return m1;
}

public Map<String, String> getCsOptionText() {
return csOptionText;
}

private Map<String, String> getOptionData() throws ValueFormatException, PathNotFoundException, RepositoryException {
Value[] optiondata;
String singlevalue;
String text=null;
String tooltext=null;
Map<String, String> map = new LinkedHashMap<String, String>();

Node ntFileNode = csoptiontext.adaptTo(Node.class);
optiondata = ntFileNode.getProperty("optiondata").getValues();
for (int i = 0; i < optiondata.length; i++) {
singlevalue = optiondata[i].toString();
String[] values = singlevalue.split("\\|");
try{
	 text = values[0];
	 tooltext = values[1];
	}catch(ArrayIndexOutOfBoundsException e){
		e.getStackTrace();
	}
map.put(text, tooltext);
}
return map;
}


public Map<String, String> getCsOptionValue() {
return csOptionValue;
}

private Map<String, String> getCsnavigationData() throws ValueFormatException, PathNotFoundException, RepositoryException {
String backtext = null;
String backpath = null;
String forwardtext = null;
String forwardpath = null;
Map<String, String> m1 = new HashMap<String, String>();
Node ntFileNode = csnavigation.adaptTo(Node.class);
try {
backtext = ntFileNode.getProperty("secbacklink").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved backtext " + e);
backtext = null;
}

try {
backpath = ntFileNode.getProperty("secbacklinkpath").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved backpath " + e);
backpath = null;
}
try {
forwardtext = ntFileNode.getProperty("secforward").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved forwardtext " + e);
forwardtext = null;
}
try {
forwardpath = ntFileNode.getProperty("secforwardbuttonvalue").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved forwardpath " + e);
forwardpath = null;
}
m1.put("backtext", backtext);
m1.put("backpath", backpath);
m1.put("forwardtext", forwardtext);
m1.put("forwardpath", forwardpath);
return m1;
}

public Map<String, String> getCsNavigationvalue() {
return csNavigationvalue;
}

private Map<String, String> getForm() throws ValueFormatException, PathNotFoundException, RepositoryException {
String heading = null;
String title1 = null;
String validation1 = null;
String title2 = null;
String validation2 = null;
Map<String, String> m1 = new HashMap<String, String>();
Node ntFileNode = formdata.adaptTo(Node.class);
try {
heading = ntFileNode.getProperty("title1").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved heading " + e);
heading = null;
}

try {
title1 = ntFileNode.getProperty("optiontitle").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved title1 " + e);
title1 = null;
}
try {
validation1 = ntFileNode.getProperty("validation1").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved validation1 " + e);
validation1 = null;
}
try {
title2 = ntFileNode.getProperty("optiontitle1").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved title2 " + e);
title2 = null;
}
try {
validation2 = ntFileNode.getProperty("validation2").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved validation2 " + e);
validation2 = null;
}
m1.put("heading", heading);
m1.put("title1", title1);
m1.put("validation1", validation1);
m1.put("title2", title2);
m1.put("validation2", validation2);
return m1;
}

public Map<String, String> getFormValue() {
return formValue;
}

private List<String> getQuesOne() throws ValueFormatException, PathNotFoundException, RepositoryException {
Value[] optiondata;
String singlevalue;
List<String> dataList = new ArrayList<String>();
Node ntFileNode = formdata.adaptTo(Node.class);
optiondata = ntFileNode.getProperty("radiooption").getValues();
for (int i = 0; i < optiondata.length; i++) {
singlevalue = optiondata[i].toString();
dataList.add(singlevalue);            

}
return dataList;
}


public List<String>  getQuestionone() {
return questionone;
}

private List<String> getQuesTwo() throws ValueFormatException, PathNotFoundException, RepositoryException {
Value[] optiondata;
String singlevalue;
List<String> dataList = new ArrayList<String>();
Node ntFileNode = formdata.adaptTo(Node.class);
optiondata = ntFileNode.getProperty("radiooption1").getValues();
for (int i = 0; i < optiondata.length; i++) {
singlevalue = optiondata[i].toString();
dataList.add(singlevalue);            

}
return dataList;
}


public List<String>  getQuestiontwo() {
return questiontwo;
}

private Map<String, String> getFormSliderValue() throws ValueFormatException, PathNotFoundException, RepositoryException {
String ssltitle = null;
String cstitle = null;
Map<String, String> m1 = new HashMap<String, String>();
Node ntFileNode = formslider.adaptTo(Node.class);
try {
ssltitle = ntFileNode.getProperty("formheading").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved ssltitle " + e);
ssltitle = null;
}

try {
cstitle = ntFileNode.getProperty("formheadingcs").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved cstitle " + e);
cstitle = null;
}

m1.put("ssltitle", ssltitle);
m1.put("cstitle", cstitle);

return m1;
}

public Map<String, String> getFormSliderData() {
return formSliderData;
}

private Map<String, String> getformNavigationData() throws ValueFormatException, PathNotFoundException, RepositoryException {
String backtext = null;
String backpath = null;
String forwardtext = null;
String forwardpath = null;
String redirectpath = null;
Map<String, String> m1 = new HashMap<String, String>();
Node ntFileNode = formNavigation.adaptTo(Node.class);
try {
backtext = ntFileNode.getProperty("thirdbacklink").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved backtext " + e);
backtext = null;
}

try {
backpath = ntFileNode.getProperty("thirdbacklinkpath").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved backpath " + e);
backpath = null;
}
try {
forwardtext = ntFileNode.getProperty("thirdforward").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved forwardtext " + e);
forwardtext = null;
}
try {
forwardpath = ntFileNode.getProperty("thirdforwardbuttonvalue").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved forwardpath " + e);
forwardpath = null;
}
try {
redirectpath = ntFileNode.getProperty("recommendationurl").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved forwardpath " + e);
redirectpath = null;
}
m1.put("backtext", backtext);
m1.put("backpath", backpath);
m1.put("forwardtext", forwardtext);
m1.put("forwardpath", forwardpath);
m1.put("redirectpath", redirectpath);
return m1;
}
public Map<String, String> getFormNavigationvalue() {
return formNavigationvalue;
}

private Map<String, String> getFormData() throws ValueFormatException, PathNotFoundException, RepositoryException {
String title = null;
String subtitle = null;
String application = null;
String applicationcount = null;
Map<String, String> m1 = new HashMap<String, String>();
Node ntFileNode = formselect.adaptTo(Node.class);
try {
title = ntFileNode.getProperty("title2").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved title " + e);
title = null;
}

try {
subtitle = ntFileNode.getProperty("subtitle2").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved subtitle " + e);
subtitle = null;
}
try {
application = ntFileNode.getProperty("application").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved application " + e);
application = null;
}
try {
applicationcount = ntFileNode.getProperty("applicationcount").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved applicationcount " + e);
application = null;
}

m1.put("title", title);
m1.put("subtitle", subtitle);
m1.put("application", application);
m1.put("applicationcount", applicationcount);

return m1;
}


public Map<String, String> getFormSelectionData() {
return formSelectionData;
}

private List<CheckModel> getFormOption() throws ValueFormatException, PathNotFoundException, RepositoryException {
Value[] optiondata;
String singlevalue;
Node ntFileNode = formselect.adaptTo(Node.class);
optiondata = ntFileNode.getProperty("titledata1").getValues();

for (int i = 0; i < optiondata.length; i++) {
singlevalue = optiondata[i].toString();
String[] values = singlevalue.split("\\|");
om = new CheckModel();
getJSONDatal1(om, values);
dataList1.add(om);
}



return dataList1;
}

public CheckModel getJSONDatal1(CheckModel om2, String[] data1) {
	List<String> myList = new ArrayList<String>();
	List<String> myList1 = new ArrayList<String>();
	List<String> myList2 = new ArrayList<String>();
for (int i = 0; i < data1.length; i++) {
	if (i == 0) {
		om2.setP_title2(data1[i]);
	} else if (i == 1) {
		 myList = new ArrayList<String>(Arrays.asList(data1[i].split(",")));
	}
	else if (i == 2) {
		 myList1 = new ArrayList<String>(Arrays.asList(data1[i].split(",")));
	}
	else if (i == 3) {
		 myList2 = new ArrayList<String>(Arrays.asList(data1[i].split(",")));
	}
}

List<List<String>> finalList= new ArrayList<List<String>>();
finalList.add(myList);
finalList.add(myList1);
finalList.add(myList2);
List<String> value=null;
List<List<String>> finalData= new ArrayList<List<String>>();
int i=0;
for(String bb:myList){

value= new ArrayList<String>();
for(List<String> aaa: finalList)
{      
	value.add(aaa.get(i));
}
finalData.add(value);
i=i+1;
}
om2.setP_finaldata(finalData);


return om2;
}

public List<CheckModel> getFormOptionData() {
return formOptionData;
}

private Map<String, String> getSslnavigationData() throws ValueFormatException, PathNotFoundException, RepositoryException {
String backtext = null;
String backpath = null;
String forwardtext = null;
String forwardpath = null;
Map<String, String> m1 = new HashMap<String, String>();
Node ntFileNode = sslnavigation.adaptTo(Node.class);
try {
backtext = ntFileNode.getProperty("secbacklink").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved backtext " + e);
backtext = null;
}

try {
backpath = ntFileNode.getProperty("secbacklinkpath").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved backpath " + e);
backpath = null;
}
try {
forwardtext = ntFileNode.getProperty("secforward").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved forwardtext " + e);
forwardtext = null;
}
try {
forwardpath = ntFileNode.getProperty("secforwardbuttonvalue").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved forwardpath " + e);
forwardpath = null;
}
m1.put("backtext", backtext);
m1.put("backpath", backpath);
m1.put("forwardtext", forwardtext);
m1.put("forwardpath", forwardpath);
return m1;
}

public Map<String, String> getSslNavigationvalue() {
return sslNavigationvalue;
}

private String getSslSlider() throws ValueFormatException, PathNotFoundException, RepositoryException {
String sslheading = null;
Node ntFileNode = sslSlider.adaptTo(Node.class);
sslheading = ntFileNode.getProperty("sslheading").getString();
return sslheading;
}

public String getSslSliderHeading() {
return sslSliderHeading;
}

private String getTabHeading() throws ValueFormatException, PathNotFoundException, RepositoryException {
String tabheading = null;
Node ntFileNode = domaintabs.adaptTo(Node.class);
tabheading = ntFileNode.getProperty("heading").getString();
return tabheading;
}

public String getDomaintabHeading() {
return domaintabHeading;
}

private Map<String, String> getSSlOption() throws ValueFormatException, PathNotFoundException, RepositoryException {
String title = null;
String subtitle = null;
String validation = null;
Map<String, String> m1 = new HashMap<String, String>();
Node ntFileNode = ssloption.adaptTo(Node.class);
try {
title = ntFileNode.getProperty("head").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved title " + e);
title = null;
}

try {
subtitle = ntFileNode.getProperty("subtitle1").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved subtitle " + e);
subtitle = null;
}
try {
validation = ntFileNode.getProperty("validation").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved validation " + e);
validation = null;
}
m1.put("title", title);
m1.put("subtitle", subtitle);
m1.put("validation", validation);
return m1;
}

public Map<String, String> getSslOptionText() {
return sslOptionText;
}

private Map<String, String> getSslData() throws ValueFormatException, PathNotFoundException, RepositoryException {
Value[] optionTitle;
Value[] optionSubTitle;
Map<String, String> titlevalues = new LinkedHashMap<String, String>();
Node ntFileNode = ssloption.adaptTo(Node.class);
optionTitle = ntFileNode.getProperty("ssltitle").getValues();
optionSubTitle = ntFileNode.getProperty("sslpath").getValues();
for (int i = 0; i < optionTitle.length; i++) {
titlevalues.put(optionTitle[i].getString(), optionSubTitle[i].getString());
}

return titlevalues;
}

public Map<String, String> getSslOptionData() {
return sslOptionData;
}

private Map<String, String> getDomainValue() throws ValueFormatException, PathNotFoundException, RepositoryException {

String basicdomain = null;
String basicdomaintooltip = null;
String basicdomainrequired = null;
String basicsubdomain = null;
String basicsubdomaintooltip = null;
String basicselectionheading = null;
String selectionsubdomaintext = null;
String basicfailuretitle = null;
String basicfailuresubtitle = null;
String basicfailurebutton = null;
String basicfailurepath = null;
String advanceheading = null;
String textarea = null;
String advanceheadingtooltip = null;
String advancebutton = null;
String advancebuttonpath = null;
String advancesuccesstitle = null;
String advancesuccesssubtitle = null;
String advanselectioncebutton = null;
String advanselectioncebuttonpath = null;
String advancedomaintext = null;
String advancesubdomaintext = null;
String advancefailuretitle = null;
String advancefailuresubtitle = null;
String advancefailureheading = null;
String advancefailurelink = null;
String advancefailurelinkpath = null;
String advanceerrormsg1 = null;
String advanceerrormsg2 = null;
Map<String, String> m1 = new HashMap<String, String>();

Node ntFileNode = domainselection.adaptTo(Node.class);

try {
basicdomain = ntFileNode.getProperty("basicdomain").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved basicdomain " + e);
basicdomain = null;
}
try {
basicdomaintooltip = ntFileNode.getProperty("basicdomaintooltip").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved basicdomaintooltip " + e);
basicdomaintooltip = null;
}
try {
basicdomainrequired = ntFileNode.getProperty("basicdomainrequired").getString();
} catch (PathNotFoundException e) {
log.error("Retrieved basicdomainrequired " + e);
basicdomainrequired = null;
}
try {
basicsubdomain = ntFileNode.getProperty("basicsubdomain").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved basicsubdomain " + e);
basicsubdomain = null;
}
try {
basicsubdomaintooltip = ntFileNode.getProperty("basicsubdomaintooltip").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved basicsubdomaintooltip " + e);
basicsubdomaintooltip = null;
}
try {
basicselectionheading = ntFileNode.getProperty("basicselectionheading").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved basicselectionheading " + e);
basicselectionheading = null;
}
try {
selectionsubdomaintext = ntFileNode.getProperty("selectionsubdomaintext").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved selectionsubdomaintext " + e);
selectionsubdomaintext = null;
}
try {
basicfailuretitle = ntFileNode.getProperty("basicfailuretitle").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved basicfailuretitle " + e);
basicfailuretitle = null;
}
try {
basicfailuresubtitle = ntFileNode.getProperty("basicfailuresubtitle").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved basicfailuresubtitle " + e);
basicfailuresubtitle = null;
}
try {
basicfailurebutton = ntFileNode.getProperty("basicfailurebutton").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved basicfailurebutton " + e);
basicfailurebutton = null;
}
try {
basicfailurepath = ntFileNode.getProperty("basicfailurepath").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved basicfailurepath " + e);
basicfailurepath = null;
}
try {
advanceheading = ntFileNode.getProperty("advanceheading").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved advanceheading " + e);
advanceheading = null;
}
try {
textarea = ntFileNode.getProperty("textarea").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved textarea " + e);
textarea = null;
}
try {
advanceheadingtooltip = ntFileNode.getProperty("advanceheadingtooltip").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved advanceheadingtooltip " + e);
advanceheadingtooltip = null;
}
try {
advancebutton = ntFileNode.getProperty("advancebutton").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved advancebutton " + e);
advancebutton = null;
}
try {
advancebuttonpath = ntFileNode.getProperty("advancebuttonpath").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved advancebuttonpath " + e);
advancebuttonpath = null;
}
try {
advancesuccesstitle = ntFileNode.getProperty("advancesuccesstitle").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved advancesuccesstitle " + e);
advancesuccesstitle = null;
}
try {
advancesuccesssubtitle = ntFileNode.getProperty("advancesuccesssubtitle").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved advancesuccesssubtitle " + e);
advancesuccesssubtitle = null;
}
try {
advanselectioncebutton = ntFileNode.getProperty("advanselectioncebutton").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved basicdomain " + e);
advanselectioncebutton = null;
}
try {
advanselectioncebuttonpath = ntFileNode.getProperty("advanselectioncebuttonpath").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved advanselectioncebuttonpath " + e);
advanselectioncebuttonpath = null;
}
try {
advancedomaintext = ntFileNode.getProperty("advancedomaintext").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved advancedomaintext " + e);
advancedomaintext = null;
}
try {
advancesubdomaintext = ntFileNode.getProperty("advancesubdomaintext").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved advancesubdomaintext " + e);
advancesubdomaintext = null;
}
try {
advancefailuretitle = ntFileNode.getProperty("advancefailuretitle").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved advancefailuretitle " + e);
advancefailuretitle = null;
}
try {
advancefailuresubtitle = ntFileNode.getProperty("advancefailuresubtitle").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved advancefailuresubtitle " + e);
advancefailuresubtitle = null;
}
try {
advancefailureheading = ntFileNode.getProperty("advancefailureheading").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved advancefailureheading " + e);
advancefailureheading = null;
}
try {
advancefailurelink = ntFileNode.getProperty("advancefailurelink").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved advancefailurelink " + e);
advancefailurelink = null;
}
try {
advancefailurelinkpath = ntFileNode.getProperty("advancefailurelinkpath").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved advancefailurelinkpath " + e);
advancefailurelinkpath = null;
}
try {
advanceerrormsg1 = ntFileNode.getProperty("advanceerrormsg1").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved advanceerrormsg1 " + e);
advanceerrormsg1 = null;
}
try {
advanceerrormsg2 = ntFileNode.getProperty("advanceerrormsg2").getString();

} catch (PathNotFoundException e) {
log.error("Retrieved advanceerrormsg2 " + e);
advanceerrormsg2 = null;
}

	m1.put("basicdomain", basicdomain);
	m1.put("basicdomaintooltip", basicdomaintooltip);
	m1.put("basicdomainrequired", basicdomainrequired);
	m1.put("basicsubdomain", basicsubdomain);
	m1.put("basicsubdomaintooltip", basicsubdomaintooltip);
	m1.put("basicselectionheading", basicselectionheading);
	m1.put("selectionsubdomaintext", selectionsubdomaintext);
	m1.put("basicfailuretitle", basicfailuretitle);
	m1.put("basicfailuresubtitle", basicfailuresubtitle);
	m1.put("basicfailurebutton", basicfailurebutton);
	m1.put("basicfailurepath", basicfailurepath);
	m1.put("advanceheading", advanceheading);
	m1.put("textarea", textarea);
	m1.put("advanceheadingtooltip", advanceheadingtooltip);
	m1.put("advancebutton", advancebutton);
	m1.put("advancebuttonpath", advancebuttonpath);
	m1.put("advancesuccesstitle", advancesuccesstitle);
	m1.put("advancesuccesssubtitle", advancesuccesssubtitle);
	m1.put("advanselectioncebutton", advanselectioncebutton);
	m1.put("advanselectioncebuttonpath", advanselectioncebuttonpath);
	m1.put("advancedomaintext", advancedomaintext);
	m1.put("advancesubdomaintext", advancesubdomaintext);
	m1.put("advancefailuretitle", advancefailuretitle);
	m1.put("advancefailuresubtitle", advancefailuresubtitle);
	m1.put("advancefailureheading", advancefailureheading);
	m1.put("advancefailurelink", advancefailurelink);
	m1.put("advancefailurelinkpath", advancefailurelinkpath);
	m1.put("advanceerrormsg1", advanceerrormsg1);
	m1.put("advanceerrormsg2", advanceerrormsg2);

return m1;
}


public Map<String, String> getDomainData() {
	return domainData;
}



}
