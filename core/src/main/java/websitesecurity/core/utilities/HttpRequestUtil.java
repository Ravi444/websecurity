package websitesecurity.core.utilities;

import java.io.Closeable;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A helper class to proceed HTTP Request via get or post.
 *
 */
public class HttpRequestUtil {

	private static final Logger LOG = LoggerFactory
			.getLogger(HttpRequestUtil.class);

	/**
	 * Sends HTTP request via get.
	 *
	 * @param strUrl
	 *            request URL
	 * @return result
	 * @throws Exception
	 */
	public static String get(String gsaUrl) throws ClientProtocolException  {
		String gsaResponse = StringUtils.EMPTY;
		CloseableHttpResponse getResponse = null;
		CloseableHttpClient httpClient = null;
		HttpGet httpGet = null;

		try {
			httpGet = new HttpGet(gsaUrl);
			httpGet.addHeader("content-type", "text/xml");
			httpClient = HttpClients.createDefault();
			getResponse = httpClient.execute(httpGet);
			gsaResponse = EntityUtils
					.toString(getResponse.getEntity(), "UTF-8");

		} catch (ClientProtocolException e) {
			LOG.error(ExceptionUtils.getStackTrace(e));
		} catch (IllegalStateException e) {
			LOG.error(ExceptionUtils.getStackTrace(e));
		} catch (IOException e) {
			LOG.error(ExceptionUtils.getStackTrace(e));
		} finally {
			httpGet.releaseConnection();
			close(getResponse);
			close(httpClient);
		}
		return gsaResponse;

	}

	/**
	 * Sends HTTP request via post.
	 *
	 * @param strUrl
	 *            Request URL
	 * @return result
	 * @throws IOException
	 */
	public static String post(String strUrl) throws IOException {
		String httpResponse = StringUtils.EMPTY;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse postResponse = null;
		HttpPost httpPost = null;

		try {
			httpPost = new HttpPost(strUrl);
			httpClient = HttpClients.createDefault();
			postResponse = httpClient.execute(httpPost);
			httpResponse = EntityUtils.toString(postResponse.getEntity(),
					"UTF-8");
			StatusLine statusLine = postResponse.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				LOG.info("HTTP Method failed: " + statusLine);
			}

		} catch (IOException e) {
			LOG.error("Error executing post request", e);
		} finally {
			httpPost.releaseConnection();
			close(postResponse);
			close(httpClient);
		}
		return httpResponse;
	}

	/**
	 * @param closeable
	 */
	private static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				LOG.error("Error closing resource", e);
			}
		}
	}
}