package websitesecurity.core.utilities;

import java.util.Dictionary;



import org.apache.commons.lang.NullArgumentException;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.framework.Constants;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(policy = ConfigurationPolicy.REQUIRE, immediate = true, label = "Website Security GSA Global Search Configuration", description = "Website Security GSA Global Search Configuration", metatype = true)
@Properties({
		@Property(name = Constants.SERVICE_DESCRIPTION, value = "Website Security GSA Global Search Configuration"),
		@Property(name = Constants.SERVICE_VENDOR, value = "Symantec") })
@Service(value = GSASearchConfigFactoryService.class)
public class GSASearchConfigFactoryService {

	/**
	 * We are using this class in GSA Search.
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(GSASearchConfigFactoryService.class);

	@Property(label = "GSA_HOST", value = "", description = "GSA HOST")
	public static final String GSA_HOST = "GSA_HOST";

	@Property(label = "PARTNER_GSA_HOST", value = "", description = "PARTNER GSA HOST")
	public static final String PARTNER_GSA_HOST = "PARTNER_GSA_HOST";

	@Property(label = "CLIENT", value = "websec_en_US", description = "GSA Client")
	public static final String CLIENT = "CLIENT";

	@Property(label = "SITE", value = "websec_en_US", description = "GSA Site")
	public static final String SITE = "SITE";

	@Property(label = "MAX_RESULTS", value = "100", description = "Max number of results to return")
	public static final String MAX_RESULTS = "MAX_RESULTS";

	@Property(label = "RESULTS_PER_PAGE", value = "10", description = "Results per page")
	public static final String RESULTS_PER_PAGE = "RESULTS_PER_PAGE";

	private static Dictionary<?, ?> props = null;

	/**
	 * Returns parameter value as a String object.
	 *
	 * @param key - key value
	 * @return Value of param key.
	 */
	public String getPropertyValue(String key) {
		return String.valueOf(props.get(key));
	}

	/**
	 * Method called when Sling component is activated
	 *
	 * @param context - ComponentContext reference
	 */
	protected void activate(ComponentContext context) {
		try {
			props = context.getProperties();
		} catch (NullArgumentException e) {
			LOG.error("GSA: Error occured in activate method: ", e);
		}
	}

}
