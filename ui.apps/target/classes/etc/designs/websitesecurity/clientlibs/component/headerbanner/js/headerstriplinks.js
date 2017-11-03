"use strict";
use(function () {
    var target = "_blank";
    var text = properties.get("headerbannertitle");
    var path = properties.get("headerbannerpath");
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