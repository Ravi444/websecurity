package websitesecurity.core.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import com.adobe.cq.sightly.WCMUse;
import websitesecurity.core.models.DropdownBean;

/**
 * Used for Product Search Filter Component.
 *
 * @author Carl.Huang
 *
 */
public class ProductSearchFilter extends WCMUse {

	private List<DropdownBean> categories = new ArrayList<DropdownBean>();
	private List<DropdownBean> products = new ArrayList<DropdownBean>();

	@Override
	public void activate() throws JSONException  {
		generateCategoriesDropdowns();
		generateProductsDropdowns();
	}

	private void generateProductsDropdowns() throws JSONException {
		String[] productsData = getProperties().get("products", String[].class);
		JSONObject json;
		JSONArray tagsArr;
		if (productsData == null)
			return;
		for (String categoryData : productsData) {
			 json = new JSONObject(categoryData);
			String name = json.getString("name");
			if (name == null)
				continue;
			 tagsArr = json.getJSONArray("tags");
			if (tagsArr.length() == 0)
				continue;
			DropdownBean dropdownBean = new DropdownBean(name, null, tagsArr);
			products.add(dropdownBean);
		}
	}

	private void generateCategoriesDropdowns() throws JSONException {
		String[] categoryDatas = getProperties().get("categories",
				String[].class);
		JSONObject json;
		String name;
		JSONArray tagsArr;
		if (categoryDatas == null)
			return;
		for (String categoryData : categoryDatas) {
		    json = new JSONObject(categoryData);
		    name = json.getString("name");
			if (name == null)
				continue;
			 tagsArr = json.getJSONArray("tags");
			if (tagsArr.length() == 0)
				continue;
			DropdownBean dropdownBean = new DropdownBean(name, null, tagsArr);
			categories.add(dropdownBean);
		}
	}

	public String getAllCategoryName() {
		return getProperties().get("allCategoryName", "All Categories");
	}

	public List<DropdownBean> getCategories() {
		return categories;
	}

	public String getProductsHomeName() {
		return getProperties().get("productHome", "Products Home");
	}

	public List<DropdownBean> getProducts() {
		return products;
	}
}
