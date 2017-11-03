package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.wcm.api.Page;

/**
 * @author Ravi_Ranjan
 * We are using this class to retrive Breadcrumb data.
 */
public class BreadcrumbUse extends WCMUse {

	private List<Page> navList = new ArrayList<Page>();

	/* (non-Javadoc)
	 * @see com.adobe.cq.sightly.WCMUse#activate()
	 */
	@Override
	public void activate() {
		setBreadCrumbItems();
	}

	/**
	 * This method will fetch the level of the current page 
	 */
	private void setBreadCrumbItems() {
		long level = 3L;
		long endLevel = 0L;
		int currentLevel = getCurrentPage().getDepth();

		while (level < currentLevel - endLevel) {
			Page trailPage = getCurrentPage().getAbsoluteParent((int) level);
			if (trailPage == null) {
				break;
			}
			this.navList.add(trailPage);
			level++;
		}

	}

	/**
	 * this is the getter method for listData.
	 * @return it will return list object of page type.
	 */
	public List<Page> getNavList() {
		return this.navList;
	}
}