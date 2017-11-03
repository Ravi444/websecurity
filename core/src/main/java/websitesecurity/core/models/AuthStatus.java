package websitesecurity.core.models;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;

import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shashank_garg on 4/20/16.
 */
public class AuthStatus {

	private Authorizable auth;

	private enum ProfilePath {
		PARTNER("./profile/isPartner"), EMAIL("./profile/email"), FIRST_NAME(
				"./profile/givenName"), LAST_NAME("./profile/familyName"), UID(
				"./profile/uid"), USER_TYPE("./profile/userType"), TIME_ZONE(
				"./profile/timeZone"), CITY("./profile/city"), ADDRESS(
				"./profile/address "), LOGGEDIN("./profile/isLoggedIn"), WAS_REDIRECTED(
				"./profile/wasRedirected");

		String path;

		ProfilePath(String path) {
			this.path = path;
		}

		String getPath() {
			return this.path;
		}
	}

	private static Logger LOG = LoggerFactory.getLogger(AuthStatus.class);

	// Initialized with the ResourseResolver of the calling component class
	public AuthStatus(ResourceResolver rr) {
		Session session = rr.adaptTo(Session.class);
		UserManager userManager = rr.adaptTo(UserManager.class);

		auth = null;

		try {
			auth = userManager.getAuthorizable(session.getUserID());

		} catch (RepositoryException e) {
			LOG.error("Not able to get the user");
			e.printStackTrace();
		}

	}

	/* To check if somebody is logged in or not */

	public boolean isLoggedIn() {
		LOG.debug("isLoggedIn = {}", getProperty(ProfilePath.LOGGEDIN.getPath()));
		LOG.debug("UID = {}", getProperty(ProfilePath.UID.getPath()));
		return getProperty(ProfilePath.UID.getPath()) != null;
	}

	/* Get isPartner */
	public boolean isPartner() {
		return "Y".equalsIgnoreCase(getProperty(ProfilePath.PARTNER.getPath()));
	}

	public String getEmail() {
		return getProperty(ProfilePath.EMAIL.getPath());
	}

	public String getFirstName() {
		return getProperty(ProfilePath.FIRST_NAME.getPath());
	}

	public String getLastName() {
		return getProperty(ProfilePath.LAST_NAME.getPath());
	}

	public String getUID() {
		return getProperty(ProfilePath.UID.getPath());
	}

	public String getUserType() {
		return getProperty(ProfilePath.USER_TYPE.getPath());
	}

	public String getTimeZone() {
		return getProperty(ProfilePath.TIME_ZONE.getPath());
	}

	public String getCity() {
		return getProperty(ProfilePath.CITY.getPath());
	}

	public String getAddress() {
		return getProperty(ProfilePath.ADDRESS.getPath());
	}

	public Boolean wasRedirected() {
		return Boolean.valueOf(getProperty(ProfilePath.WAS_REDIRECTED.getPath()));
	}

	public String getProperty(String profileProperty) {
		if (auth == null) {
			return "user does not exist";
		}

		Value[] values;

		try {
			values = auth.getProperty(profileProperty);
			if (values != null && values.length > 0) {
				Value value = values[0];
				return value.getString();
			}
		} catch (RepositoryException e) {
			LOG.error("Could not find the property {}", profileProperty);
			e.printStackTrace();
		}
		return null;
	}
}