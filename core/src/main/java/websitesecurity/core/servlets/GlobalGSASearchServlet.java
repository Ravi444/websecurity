package websitesecurity.core.servlets;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.http.client.ClientProtocolException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.settings.SlingSettingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.xss.XSSAPI;
import com.day.cq.replication.PathNotFoundException;

import websitesecurity.core.utilities.HttpRequestUtil;
import websitesecurity.core.utilities.GSASearchConfigFactoryService;

/**
 * We are using this class for GSA Search.
 */
@SlingServlet(paths = { "/bin/websitesecurity/globalsearch" })
@Properties({
		@Property(name = "service.pid", value = "com.websitesecurity.core.impl.servlets.GlobalSearchServlet", propertyPrivate = false),
		@Property(name = "service.description", value = "Website Security GSA Global Search", propertyPrivate = false),
		@Property(name = "service.vendor", value = "Symantec", propertyPrivate = false) })
public class GlobalGSASearchServlet extends SlingAllMethodsServlet {

	/** The Constant logger. */
	private final static Logger LOG = LoggerFactory.getLogger(GlobalGSASearchServlet.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8795673847499208743L;

	/** The gsa search config factory service. */
	@Reference
	GSASearchConfigFactoryService gsaSearchConfigFactoryService;

	/** The m sling settings service. */
	@Reference
	SlingSettingsService mSlingSettingsService;

	/** The xss api. */
	@Reference
	XSSAPI xssAPI;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.sling.api.servlets.SlingSafeMethodsServlet#doGet(org.apache
	 * .sling.api.SlingHttpServletRequest,
	 * org.apache.sling.api.SlingHttpServletResponse)
	 */
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws IOException,
			ServletException {
		if (LOG.isDebugEnabled()) {
			LOG.debug("doGet(SlingHttpServletRequest, SlingHttpServletResponse) - start"); //$NON-NLS-1$
		}

		doPost(request, response);

		if (LOG.isDebugEnabled()) {
			LOG.debug("doGet(SlingHttpServletRequest, SlingHttpServletResponse) - end"); //$NON-NLS-1$
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.sling.api.servlets.SlingAllMethodsServlet#doPost(org.apache
	 * .sling.api.SlingHttpServletRequest,
	 * org.apache.sling.api.SlingHttpServletResponse)
	 */
	@Override
	protected void doPost(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
		LOG.debug("in doPost");

		try {
			request.setCharacterEncoding("UTF-8");
			String gsaHostPartner = getParemeterValue(request, "isPartner");
			LOG.debug("GSA isPartner LogInfo" + gsaHostPartner);
			String gsaHostValue;
			String siteValue;
			String clientValue;
			String clientOverride = getParemeterValue(request, "clientOverride");
			LOG.debug("GSA Clienbt override Vamsi" + clientOverride);
			String siteOverride = getParemeterValue(request, "siteOverride");
			LOG.debug("GSA Site Override Vamsi" + siteOverride);

			if ("Y".equals(gsaHostPartner)) {
				gsaHostValue = gsaSearchConfigFactoryService
						.getPropertyValue("PARTNER_GSA_HOST");
			} else {
				gsaHostValue = gsaSearchConfigFactoryService
						.getPropertyValue("GSA_HOST");
			}

			if (StringUtils.isNotEmpty(clientOverride)) {
				clientValue = clientOverride;
			} else {
				clientValue = gsaSearchConfigFactoryService
						.getPropertyValue("CLIENT");
			}
			if (StringUtils.isNotEmpty(siteOverride)) {
				siteValue = siteOverride;
			} else {
				siteValue = gsaSearchConfigFactoryService
						.getPropertyValue("SITE");
			}

			String searchURL = "https://"
					+ gsaHostValue
					+ "/search?q="
					+ getParemeterValue(request, "q")
					+ "&site="
					+ siteValue
					+ "&client="
					+ clientValue
					+ "&num="
					+ gsaSearchConfigFactoryService
							.getPropertyValue("MAX_RESULTS") + "&sort="
					+ getParemeterValue(request, "sort") + "&oe=UTF-8&ie=UTF-8"
					+ "&ulang=" + getParemeterValue(request, "ulang")
					+ "&filter=0";
			LOG.info("GSA Search Url 1234: " + searchURL);
			LOG.debug("GSA Search Url: " + searchURL);
			/*
			 * if
			 * (gsaSearchConfigFactoryService.getPropertyValue("GSA_HOST").contains
			 * ("dev01")) { searchURL += "&filter=0"; }
			 */
			LOG.debug(searchURL);

			response.setContentType("text/html; charset=UTF-8");
			String gsaResponse = get(searchURL);
			response.getWriter().write(gsaResponse);

		} catch (IOException e) {
			LOG.error(
					"doPost(SlingHttpServletRequest, SlingHttpServletResponse)", e); //$NON-NLS-1$

			LOG.error(ExceptionUtils.getStackTrace(e));
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("doPost(SlingHttpServletRequest, SlingHttpServletResponse) - end"); //$NON-NLS-1$
		}
	}

	/**
	 * Gets the.
	 *
	 * @param url
	 *            the url
	 * @return the GSA Response
	 * @throws ClientProtocolException 
	 * @throws Exception
	 *             the exception
	 */
	public String get(String url) throws ClientProtocolException {
		if (LOG.isDebugEnabled()) {
			LOG.debug("get(String) - start"); //$NON-NLS-1$
		}

		String gsaResponse = HttpRequestUtil.get(url);
		if (LOG.isDebugEnabled()) {
			LOG.debug("get(String) - end"); //$NON-NLS-1$
		}
		return gsaResponse;
	}

	/**
	 * Gets the paremeter value.
	 *
	 * @param request
	 *            the request
	 * @param parameterName
	 *            the parameter name
	 * @return the paremeter value
	 */
	private String getParemeterValue(SlingHttpServletRequest request,
			String parameterName) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("getParemeterValue(SlingHttpServletRequest, String) - start"); //$NON-NLS-1$
		}
		String parameterValue = StringUtils.EMPTY;

		parameterValue = StringUtils.defaultString(request
				.getParameter(parameterName));
		parameterValue = xssAPI.filterHTML(parameterValue);

		LOG.debug(parameterName + ": " + parameterValue);

		if (LOG.isDebugEnabled()) {
			LOG.debug("getParemeterValue(SlingHttpServletRequest, String) - end"); //$NON-NLS-1$
		}
		return parameterValue;
	}

}
