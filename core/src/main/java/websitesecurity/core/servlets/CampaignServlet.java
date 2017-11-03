package websitesecurity.core.servlets;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.jcr.ValueFormatException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;

/**
 * We are using this servlet to retrieve the overlays present in the page.
 */
@SlingServlet(paths = "/bin/websitesecurity/campaign", methods = "GET", metatype = false)
public class CampaignServlet extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 2598426539166789515L;
	private static Logger log = LoggerFactory.getLogger(CampaignServlet.class);

	@Reference
	private ResourceResolverFactory resolverFactory;

	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServerException,
			IOException {
		Node node;
		JSONObject jsonObject = null;
		JSONObject jsonObject1 = null;
		String jobTitles;
		String industries;
		String servers;
		String people;
		String responsible;
		String primaryInterest;
		String shoppingCart;
		String timeFrame;
		String quote;
		String projectPlan;
		String profileInfo;
		String jobLevel;
		String explicitConsent;
		String urlPath;
		Value[] iconData = null;
		List<String> iconDataValues= new ArrayList<String>();
		Property arrayValue = null;

		urlPath=request.getParameter("urlparam");
		ResourceResolver resourceResolver = request.getResourceResolver();
		Resource res = resourceResolver.resolve(urlPath);
		Map<String, String> map = new HashMap<String, String>();

		JSONArray jobsTitleArray = new JSONArray();
		JSONArray industryArray = new JSONArray();
		JSONArray serverArray = new JSONArray();
		JSONArray peopleArray = new JSONArray();
		JSONArray responsibleArray = new JSONArray();
		JSONArray primaryinterestArray = new JSONArray();
		JSONArray shoppingcartArray = new JSONArray();
		JSONArray timeframeArray = new JSONArray();
		JSONArray quoteArray = new JSONArray();
		JSONArray projectplannedArray = new JSONArray();
		JSONArray profileinfoArray = new JSONArray();
		JSONArray joblevelArray = new JSONArray();
		JSONArray explicitconsentArray = new JSONArray();
		JSONArray countryStateArray = new JSONArray();
		JSONArray statesArray=null;
		// JSONObject finalArray = new JSONObject();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		map.put("path", res.getPath() + "/jcr:content");
		map.put("type", "nt:unstructured");
		map.put("nodename", "campaign");

		// Quering JCR using com.day.cq.search.QueryBuilder API
		PredicateGroup predicateGroup = PredicateGroup.create(map);
		QueryBuilder builder = request.getResourceResolver().adaptTo(QueryBuilder.class);
		Query query = builder.createQuery(predicateGroup, request.getResourceResolver().adaptTo(Session.class));
		SearchResult result = query.getResult();
		Iterator<Node> nt = result.getNodes();

		while (nt.hasNext()) {
			node = (Node) nt.next();
			jobTitles = null;
			industries = null;
			servers = null;
			people = null;
			responsible = null;
			primaryInterest = null;
			shoppingCart = null;
			timeFrame = null;
			quote = null;
			projectPlan = null;
			profileInfo = null;
			jobLevel = null;
			explicitConsent = null;
			jsonObject = new JSONObject();

			try {

				if (node.hasProperty("jobtitlevalues")) {
				Property jobtitlevalues = node.getProperty("jobtitlevalues");
				jobTitles = jobtitlevalues.getValue().toString();
				}

				if (node.hasProperty("industryvalues")) {
				Property industryvalues = node.getProperty("industryvalues");
				industries = industryvalues.getValue().toString();
				}
				
				if (node.hasProperty("servervalues")) {
				Property servervalues = node.getProperty("servervalues");
				servers = servervalues.getValue().toString();
				}
				
				if (node.hasProperty("peoplevalues")) {
				Property peoplevalues = node.getProperty("peoplevalues");
				people = peoplevalues.getValue().toString();
				}
				if (node.hasProperty("responsiblevalues")) {
				Property responsiblevalues = node
						.getProperty("responsiblevalues");
				responsible = responsiblevalues.getValue().toString();
				}
				
				
				if (node.hasProperty("primaryvalues")) {
				Property primaryinterestvalues = node
						.getProperty("primaryvalues");
				primaryInterest = primaryinterestvalues.getValue().toString();
				}

				if (node.hasProperty("shoppingcartvalues")) {
				Property shoppingcartvalues = node
						.getProperty("shoppingcartvalues");
				shoppingCart = shoppingcartvalues.getValue().toString();
				}

				if (node.hasProperty("timeframevalues")) {
				Property timeframevalues = node.getProperty("timeframevalues");
				timeFrame = timeframevalues.getValue().toString();
				}

				if (node.hasProperty("quotevalues")) {
				Property quotevalues = node.getProperty("quotevalues");
				quote = quotevalues.getValue().toString();
				}

				if (node.hasProperty("projectplannedvalues")) {
				Property projectplanvalues = node
						.getProperty("projectplannedvalues");
				projectPlan = projectplanvalues.getValue().toString();
				}

				if (node.hasProperty("profileinfovalues")) {
				Property profileinfovalues = node
						.getProperty("profileinfovalues");
				profileInfo = profileinfovalues.getValue().toString();
				}

				if (node.hasProperty("joblevelvalues")) {
				Property joblevelvalues = node.getProperty("joblevelvalues");
				jobLevel = joblevelvalues.getValue().toString();
				}
				
				if (node.hasProperty("explicitconsentvalues")) {
				Property explicitConsentValues = node
						.getProperty("explicitconsentvalues");
				explicitConsent = explicitConsentValues.getValue().toString();
				}
				if (node.hasProperty("icondata")) {
					arrayValue = node.getProperty("icondata");
					
					}

			}

			catch (javax.jcr.PathNotFoundException e) {
				e.printStackTrace();
			} catch (ValueFormatException e) {
			
				e.printStackTrace();
			} catch (RepositoryException e) {
			
				e.printStackTrace();
			}

			try{
				
				if (jobTitles != null) {
					String[] jobtitle = jobTitles.split(",");

					for (int i = 0; i < jobtitle.length; i++) {
						jsonObject = new JSONObject();
						jsonObject.put("value", jobtitle[i]);
						jsonObject.put("name", jobtitle[i]);
						jobsTitleArray.put(jsonObject);
					}

				}

				if (industries != null) {
					String[] industry = industries.split(",");

					for (int i = 0; i < industry.length; i++) {
						jsonObject = new JSONObject();
						jsonObject.put("value", industry[i]);
						jsonObject.put("name", industry[i]);
						industryArray.put(jsonObject);

					}

				}

				if (servers != null) {
					String[] server = servers.split(",");

					for (int i = 0; i < server.length; i++) {
						jsonObject = new JSONObject();
						jsonObject.put("value", server[i]);
						jsonObject.put("name", server[i]);
						serverArray.put(jsonObject);
					}

				}

			
				try{
				
					if (primaryInterest != null) {
						String[] primaryinterests = primaryInterest.split(",");
						String[] keyPrimaryInterest;
	
						for (int i = 0; i < primaryinterests.length; i++) {
							keyPrimaryInterest = primaryinterests[i].split(":");
							jsonObject = new JSONObject();
							jsonObject.put("value", keyPrimaryInterest[0]);
							jsonObject.put("name", keyPrimaryInterest[1]);
							primaryinterestArray.put(jsonObject);
	
							}
						}
					}
				
					catch(ArrayIndexOutOfBoundsException e){
						
					}

				if (people != null) {
					String[] peoples = people.split("\\|");

					for (int i = 0; i < peoples.length; i++) {
						jsonObject = new JSONObject();
						jsonObject.put("value", peoples[i]);
						jsonObject.put("name", peoples[i]);
						peopleArray.put(jsonObject);
					}
				}

				if (responsible != null) {
					String[] responsibles = responsible.split(",");

					for (int i = 0; i < responsibles.length; i++) {
						jsonObject = new JSONObject();
						jsonObject.put("value", responsibles[i]);
						jsonObject.put("name", responsibles[i]);
						responsibleArray.put(jsonObject);
					}
				}
				if (shoppingCart != null) {
					String[] shoppingcarts = shoppingCart.split(",");

					for (int i = 0; i < shoppingcarts.length; i++) {
						jsonObject = new JSONObject();
						jsonObject.put("value", shoppingcarts[i]);
						jsonObject.put("name", shoppingcarts[i]);
						shoppingcartArray.put(jsonObject);
					}
				}

				if (timeFrame != null) {
					String[] timeframes = timeFrame.split(",");

					for (int i = 0; i < timeframes.length; i++) {
						jsonObject = new JSONObject();
						jsonObject.put("value", timeframes[i]);
						jsonObject.put("name", timeframes[i]);
						timeframeArray.put(jsonObject);
					}
				}

				if (quote != null) {
					String[] quotes = quote.split(",");

					for (int i = 0; i < quotes.length; i++) {
						jsonObject = new JSONObject();
						jsonObject.put("value", quotes[i]);
						jsonObject.put("name", quotes[i]);
						quoteArray.put(jsonObject);
					}
				}

				if (projectPlan != null) {
					String[] projectplanned = projectPlan.split(",");

					for (int i = 0; i < projectplanned.length; i++) {
						jsonObject = new JSONObject();
						jsonObject.put("value", projectplanned[i]);
						jsonObject.put("name", projectplanned[i]);
						projectplannedArray.put(jsonObject);
					}
				}

				if (profileInfo != null) {
					String[] profileinfos = profileInfo.split(",");

					for (int i = 0; i < profileinfos.length; i++) {
						jsonObject = new JSONObject();
						jsonObject.put("value", profileinfos[i]);
						jsonObject.put("name", profileinfos[i]);
						profileinfoArray.put(jsonObject);
					}

				}

				if (jobLevel != null) {
					String[] joblevels = jobLevel.split(",");

					for (int i = 0; i < joblevels.length; i++) {
						jsonObject = new JSONObject();
						jsonObject.put("value", joblevels[i]);
						jsonObject.put("name", joblevels[i]);
						joblevelArray.put(jsonObject);
					}

				}

				if (explicitConsent != null) {
					String[] explicitconsents = explicitConsent.split(",");

					for (int i = 0; i < explicitconsents.length; i++) {
						jsonObject = new JSONObject();
						jsonObject.put("value", explicitconsents[i]);
						jsonObject.put("name", explicitconsents[i]);
						explicitconsentArray.put(jsonObject);
					}

				}
			
				try{
					      String[] countryStateData;
					          if(arrayValue.isMultiple()) // This condition checks for properties whose type is String[](String array)  
				               {  
					    	    iconData = arrayValue.getValues();
					    	    for(Value v : iconData)
					    	    {
					    	    iconDataValues.add(v.getString());
					    	    }
					    	    Collections.sort(iconDataValues);
								for (String v : iconDataValues) {
								jsonObject = new JSONObject();
							   countryStateData=v.split("\\|");
							   jsonObject.put("country", countryStateData[0]);
							   jsonObject.put("staterequired", countryStateData[1]);
							   jsonObject.put("statelabel", countryStateData[2]);
							   String[] statelist=countryStateData[3].split(",");
							   Arrays.sort(statelist);
							   statesArray = new JSONArray();
							   for (String stateval : statelist) {
								   jsonObject1 = new JSONObject();
								   String[] stateSplit=stateval.split(":");
								   jsonObject1.put("value", stateSplit[0]);
								   jsonObject1.put("name", stateSplit[1]);
								   statesArray.put(jsonObject1);
							   }
							   jsonObject.put("statevalue", statesArray);
	                           countryStateArray.put(jsonObject);
	                           
	                           
							   
							} 
						
					}
						
						else{
							String singleArrayValue=arrayValue.getValue().toString();
							countryStateData=singleArrayValue.split("\\|");	
							 jsonObject.put("country", countryStateData[0]);
							   jsonObject.put("staterequired", countryStateData[1]);
							   jsonObject.put("statelabel", countryStateData[2]);
							   String[] statelist=countryStateData[3].split(",");
							   Arrays.sort(statelist);
							   statesArray = new JSONArray();
							   for (String stateval : statelist) {
								   jsonObject1 = new JSONObject();
								   String[] stateSplit=stateval.split(":");
								   jsonObject1.put("value", stateSplit[0]);
								   jsonObject1.put("name", stateSplit[1]);
								   statesArray.put(jsonObject1);
							   }
							   jsonObject.put("statevalue", statesArray);
	                           countryStateArray.put(jsonObject);
						}
						
						
					}
				
					catch(ArrayIndexOutOfBoundsException e){
						
					} catch (ValueFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (RepositoryException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			catch(JSONException e){
				
			}
				
		}
			try {
				
				jsonObject=new JSONObject();
				
				jsonObject.put("jobtitles", jobsTitleArray);

				jsonObject.put("industries", industryArray);

				jsonObject.put("servers", serverArray);
			
				jsonObject.put("peoples", peopleArray);

				jsonObject.put("responsible", responsibleArray);

				jsonObject.put("primaryinterest", primaryinterestArray);

				jsonObject.put("shoppingcart", shoppingcartArray);

				jsonObject.put("timeframes", timeframeArray);

				jsonObject.put("quotes", quoteArray);

				jsonObject.put("projectplanned", projectplannedArray);

				jsonObject.put("profileinfo", profileinfoArray);

				jsonObject.put("joblevels", joblevelArray);

				jsonObject.put("explicitconsents", explicitconsentArray);
				
				jsonObject.put("countryStateMappingList", countryStateArray);
			
			} catch (JSONException e) {
				e.printStackTrace();
			}

			response.getWriter().write(jsonObject.toString());
		
	}
}