package websitesecurity.core.utilities;

/**
 * We are using this class for checking path and assigning target for the link.
 */
public class PathUtility {

	/**
	 * We are using this method to check whether the path is from internal or
	 * external.
	 * 
	 * @param it will take path as input
	 * @return it will give path.
	 */
	public static String pathCheck(String path) {
		String textPath;
		if (path.equals("#") || (path.startsWith("https://www.websecurity.symantec.com/")) || (path.startsWith("/content/") && path.endsWith(".pdf")) || (path.startsWith("/content/") && path.endsWith(".jpg")) || (path.startsWith("/content/") && path.endsWith(".png"))  || (path.startsWith("/content/") && path.contains(".html?"))) {
			textPath = path;
		} else {
			if (path.startsWith("/content/")) {
				textPath = path + ".html";
			} else {
				textPath = path;
			}
		}
		return textPath;
	}

	/**
	 * we are using this method to check the page path and assign the target.
	 * 
	 * @param path
	 * @return
	 */
	public static String pathTarget(String path) {
		String textTarget;
		if (path.equals("#") || (path.startsWith("/content/") && !path.endsWith(".pdf")) || (path.startsWith("https://www.websecurity.symantec.com/") && !path.endsWith(".pdf")) || (path.startsWith("https://websecurity.symantec.com/") && !path.endsWith(".pdf")) || (path.startsWith("www.websecurity.symantec.com/") && !path.endsWith(".pdf")) || (path.startsWith("websecurity.symantec.com/") && !path.endsWith(".pdf"))) {
			textTarget = "_parent";
		} else {
			if ((path.startsWith("/content/") && path.endsWith(".pdf")) || path.endsWith(".pdf")) {
				textTarget = "_blank";
			} else {
				textTarget = "_blank";
			}
		}
		return textTarget;
	}
	
	/**
	 * we are using this method to check the page path and append the .html.
	 * @param path
	 * @return
	 */
	public static String pathCheckAdvanced(String path) {
		String textPath;
		if (path.equals("#") || path.startsWith("https://www.websecurity.symantec.com/") || (path.startsWith("/content/") && path.endsWith(".pdf")) || (path.startsWith("/content/") && path.endsWith(".jpg")) || (path.startsWith("/content/") && path.endsWith(".png"))  || (path.startsWith("/content/") && path.contains(".html?"))) {
			textPath = path;
		} else {
			if ((path.startsWith("/content/")) && path.contains("?")) {
				String[] uri = path.split("\\?");
				textPath = uri[0] + ".html?"+uri[1];
			} else {
				if (path.startsWith("/content/")) {
					textPath = path + ".html";
				} else {
					textPath = path;
				}
			}
		}
		return textPath;
	}
}