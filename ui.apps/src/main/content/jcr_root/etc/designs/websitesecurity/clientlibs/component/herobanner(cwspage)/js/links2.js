"use strict";
use(function () {
    var target = "_blank";
    var finalpath = "";
    var text = properties.get("image2link");
    var path = properties.get("linkvalue2");
    if(path) {
        path = path;
    } else {
		 path = "#";
    }
    var result1 = path.includes("/content/");
    var result2 = path.includes("symantec.com");
    var result3 = path.endsWith(".pdf");
    if(path=="#" || result2){
        text = text;
		finalpath = path;
        target = "_parent";
    }
    else if(result1 && result3){
        text = text;
		finalpath = path;
        target = "_blank";
    }
    else if(result1){
        text = text;
		finalpath = path+".html";
        target = "_parent";
    }
    else{
        finalpath = path;
    }

    return {
        text:text,
        path:finalpath,
        target:target
    };
});