

use(function () {
	var apiurl = properties.get("url", "");
    var country = properties.get("country", "");
    var brand = properties.get("brand", "");
    var international = properties.get("isInternational", "");
    var buttonurl = properties.get("path1", "");
    var producttype = properties.get("producttype", "");
    var locale = properties.get("locale", "");
    var alertData = properties.get("failuresectiontitle", "");
    var failureData = properties.get("failuretitle", "");
    var timeout = properties.get("timeout", "");
    //var amount = properties.get("amount", "");
  
    return {
        url : apiurl,
        coun: country,
        brnd: brand,
        inter: international,
        btnurl: buttonurl,
        type: producttype,
        local: locale,
        alert: alertData,
        failure: failureData,
        time: timeout,
       // amnt: amount,

    };

});