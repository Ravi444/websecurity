package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.Session;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import websitesecurity.core.models.ImageTextTitleBean;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
//QUeryBuilder APIs
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;

/**
 * 
 * We are using this class for ImageTextTitle in carousel.
 * It will retrieve data from ImageText and Title component.
 * 
 */


/**
 * @author rishabh-g
 *
 */
@Component(immediate = true)
@Service
public class CarouselImageTextTitleData implements CarouselCountService {

	private final static Logger LOG = LoggerFactory.getLogger(CarouselImageTextTitleData.class);
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
      private QueryBuilder builder ; 
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

	/* (non-Javadoc)
	 * @see websitesecurity.core.utilities.CarouselCountService#imageTextAndTextCount(java.lang.String)
	 * This method will take current url as string and retrieve all ImageTextandTitle component details in ImageTextTitleBean format.
	 */
	@Override
	public List<ImageTextTitleBean> imageTextAndTextCount(String currenturl) {
		 List<ImageTextTitleBean> imageTextTitleList = new ArrayList<ImageTextTitleBean>();
		 ImageTextTitleBean imageTextTitleBean = null;
		 Node node;
		try {
			
			rr = ResourceResolverObject();
			 session = rr.adaptTo(Session.class);
             // create query description as hash map 
             Map<String, String> map = new HashMap<String, String>();        
             map.put("path", currenturl + "/jcr:content/par1/carouselwithimagetex");
     		map.put("type", "nt:unstructured");
     		map.put("nodename", "imagetextandtitle*");
     		map.put("orderby", "@jcr:created");
			map.put("p.limit", "-1");

                                
           Query query = builder.createQuery(PredicateGroup.create(map), session);
           SearchResult result = query.getResult();
           Iterator<Node> nt = result.getNodes();
        	     while (nt.hasNext()) {
       			 node = (Node) nt.next();
    			try {
    				    imageTextTitleBean = new ImageTextTitleBean(null, null, null, null, null,
    				    null, null,null,null,null,null
    				    ,null,null,null,null,null,null,
    				    null,null,null,null,null,null,null,null);
    				    
    				    if(node.hasProperty("alternatetext")){
    				    Property alternatetext = node.getProperty("alternatetext");
	  					String alternatetext1 = alternatetext.getValue().toString();
	  					imageTextTitleBean.setAlternateText(alternatetext1);
    				    }
    				    else{
    				    imageTextTitleBean.setAlternateText(null);
    				    }
    				    if(node.hasProperty("description")){
	  					Property description = node.getProperty("description");
	  					String description1 = description.getValue().toString();
	  					imageTextTitleBean.setDescription(description1);
    				    }
    				    else{
    				    imageTextTitleBean.setDescription(null);
    				    }
    				    if(node.hasProperty("descriptioncolor")){
	  					Property descriptioncolor = node.getProperty("descriptioncolor");
	  					String descriptioncolor1 = descriptioncolor.getValue().toString();
	  					imageTextTitleBean.setDescriptionColor(descriptioncolor1);
    				    }
    				    else{
    	  				imageTextTitleBean.setDescriptionColor(null);
    				    }
    				    if(node.hasProperty("imageReference")){
	  					Property imageReference = node.getProperty("imageReference");
	  					String imageReference1 = imageReference.getValue().toString();
	  					imageTextTitleBean.setImageReference(imageReference1);
    				    }
    				    else{
    	  				imageTextTitleBean.setImageReference(null);
    				    }
    				    if(node.hasProperty("imgtitle")){
	  					Property imgtitle = node.getProperty("imgtitle");
	  					String imgtitle1 = imgtitle.getValue().toString();
	  					imageTextTitleBean.setImgTitle(imgtitle1);
    				    }
    				    else{
    				    imageTextTitleBean.setImgTitle(null);	
    				    }
    				    if(node.hasProperty("linkkey")){
	  					Property linkkey = node.getProperty("linkkey");
	  					String linkkey1 = linkkey.getValue().toString();
	  					imageTextTitleBean.setLinkKey(linkkey1);
    				    }
    				    else{
    				    imageTextTitleBean.setLinkKey(null);
    				    }
    				    if(node.hasProperty("linkvalue")){
	  					Property linkvalue = node.getProperty("linkvalue");
	  					String linkvalue1 = linkvalue.getValue().toString();
	  					imageTextTitleBean.setLinkValue( PathUtility.pathCheckAdvanced(linkvalue1));
	  					imageTextTitleBean.setTarget(PathUtility.pathTarget(linkvalue1));
    				    }
    				    else{
    				    	imageTextTitleBean.setLinkValue(null);
    				    	imageTextTitleBean.setTarget(null);
    				    }
    				    if(node.hasProperty("title1")){
	  					Property title = node.getProperty("title1");
	  					String title1 = title.getValue().toString();
	  					imageTextTitleBean.setTitle(title1);
    				    }
    				    else{
    				    imageTextTitleBean.setTitle(null);
    				    }
    					if(node.hasProperty("titlecolor")){
	  					Property titlecolor = node.getProperty("titlecolor");
	  					String titlecolor1 = titlecolor.getValue().toString();
	  					imageTextTitleBean.setTitleColor(titlecolor1);
    					}
    					else{
    					imageTextTitleBean.setTitleColor(null);
    					}
    					if(node.hasProperty("listFrom")){
    	  					Property listFrom = node.getProperty("listFrom");
    	  					String listFrom1 = listFrom.getValue().toString();
    	  					imageTextTitleBean.setListFrom(listFrom1);
        					}
        					else{
        					imageTextTitleBean.setListFrom("image");
        					}
    					if(node.hasProperty("snapshot")){
    	  					Property snapshot = node.getProperty("snapshot");
    	  					String snapshot1 = snapshot.getValue().toString();
    	  					imageTextTitleBean.setSnapShot(snapshot1);
        					}
        					else{
        					imageTextTitleBean.setSnapShot(null);
        					}
    					if(node.hasProperty("icon")){
    	  					Property icon = node.getProperty("icon");
    	  					String icon1 = icon.getValue().toString();
    	  					imageTextTitleBean.setIcon(icon1);
        					}
        					else{
        					imageTextTitleBean.setIcon(null);
        					}
    					if(node.hasProperty("closeicon")){
    	  					Property closeicon = node.getProperty("closeicon");
    	  					String closeicon1 = closeicon.getValue().toString();
    	  					imageTextTitleBean.setCloseIcon(closeicon1);
        					}
        					else{
        					imageTextTitleBean.setCloseIcon(null);
        					}
    					if(node.hasProperty("hoverclose")){
    	  					Property hoverclose = node.getProperty("hoverclose");
    	  					String hoverclose1 = hoverclose.getValue().toString();
    	  					imageTextTitleBean.setHoverClose(hoverclose1);
        					}
        					else{
        					imageTextTitleBean.setHoverClose(null);
        					}
    					if(node.hasProperty("vendor")){
    	  					Property vendor = node.getProperty("vendor");
    	  					String vendor1 = vendor.getValue().toString();
    	  					imageTextTitleBean.setVendor(vendor1);
        					}
        					else{
        					imageTextTitleBean.setVendor(null);
        					}
    					if(node.hasProperty("videotitle")){
    	  					Property videotitle = node.getProperty("videotitle");
    	  					String videotitle1 = videotitle.getValue().toString();
    	  					imageTextTitleBean.setVideoTitle(videotitle1);
        					}
        					else{
        					imageTextTitleBean.setVideoTitle(null);
        					}
    					if(node.hasProperty("videosubtitle")){
    	  					Property videosubtitle = node.getProperty("videosubtitle");
    	  					String videosubtitle1 = videosubtitle.getValue().toString();
    	  					imageTextTitleBean.setVideoSubtitle(videosubtitle1);
        					}
        					else{
        					imageTextTitleBean.setVideoSubtitle(null);
        					}
    					if(node.hasProperty("videoduration")){
    	  					Property videoduration = node.getProperty("videoduration");
    	  					String videoduration1 = videoduration.getValue().toString();
    	  					imageTextTitleBean.setVideoDuration(videoduration1);
        					}
        					else{
        					imageTextTitleBean.setVideoDuration(null);
        					}
    					if(node.hasProperty("videoID")){
    	  					Property videoID = node.getProperty("videoID");
    	  					String videoID1 = videoID.getValue().toString();
    	  					imageTextTitleBean.setVideoId(videoID1);
        					}
        					else{
        					imageTextTitleBean.setVideoId(null);
        					}
    					if(node.hasProperty("playerID")){
    	  					Property playerID = node.getProperty("playerID");
    	  					String playerID1 = playerID.getValue().toString();
    	  					imageTextTitleBean.setPlayerId(playerID1);
        					}
        					else{
        					imageTextTitleBean.setPlayerId(null);
        					}
    					if(node.hasProperty("playerKey")){
    	  					Property playerKey = node.getProperty("playerKey");
    	  					String playerKey1 = playerKey.getValue().toString();
    	  					imageTextTitleBean.setPlayerKey(playerKey1);
        					}
        					else{
        					imageTextTitleBean.setPlayerKey(null);
        					}
    					if(node.hasProperty("youtubeembedCode")){
    	  					Property youtubeembedCode = node.getProperty("youtubeembedCode");
    	  					String youtubeembedCode1 = youtubeembedCode.getValue().toString();
    	  					imageTextTitleBean.setYoutubeEmbedCode(youtubeembedCode1);
        					}
        					else{
        					imageTextTitleBean.setYoutubeEmbedCode(null);
        					}
    					if(node.hasProperty("ustudioembedCode")){
    	  					Property ustudioembedCode = node.getProperty("ustudioembedCode");
    	  					String ustudioembedCode1 = ustudioembedCode.getValue().toString();
    	  					imageTextTitleBean.setUstudioEmbedCode(ustudioembedCode1);
        					}
        					else{
        					imageTextTitleBean.setUstudioEmbedCode(null);
        					}
    					
    					
 						    String tempDate;
 					    Property createddate = node.getProperty("jcr:created");    
 						tempDate=createddate.getValue().toString(); 
 						tempDate = tempDate.replaceAll("[^a-zA-Z0-9]", "");
 						tempDate.replaceAll("\\s+", "");
 				        imageTextTitleBean.setCreatedDate(tempDate);
	  					imageTextTitleList.add(imageTextTitleBean);
    			}

    			catch (Exception e) {
    			LOG.error(e.getMessage());
    			}
       		}
                
        
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		finally
		{
			session.logout();
		}
		return imageTextTitleList;
	}
}