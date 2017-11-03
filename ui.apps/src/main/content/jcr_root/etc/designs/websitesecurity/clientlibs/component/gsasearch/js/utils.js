var utils = {

	htmlEncode : function(value) {
		return !value ? value : String(value).replace(/&/g, "&amp;").replace(
				/>/g, "&gt;").replace(/</g, "&lt;").replace(/"/g, "&quot;");
	}

}