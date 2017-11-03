"use strict";
use(function () {
    var target = "_blank";
    var text = "";
    var path = "";
    try {
		text = properties.get("linktitle");
	} catch (err) {
		text = "";
	}
    try {
		path = properties.get("linkvalue");
	} catch (err) {
		path = "";
	}
    var result1 = path.includes("/content/");
    var result2 = path.includes("symantec.com");
    if(path=="#" || result2){
        text = text;
		path = path;
        target = "_parent";
    }
    if(result1){
        text = text;
		path = path+".html";
        target = "_parent";
    }
    return {
        text:text,
        path:path,
        target:target
    };
});