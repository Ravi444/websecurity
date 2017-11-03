"use strict";
use(function () {
    var target = "_blank";
    var finalpath = "";
    var path = properties.get("headerlogopath");
    if(path) {
        path = path;
    } else {
		 path = "#";
    }
    var result1 = path.includes("/content/");
    var result2 = path.includes("symantec.com");
    var result3 = path.endsWith(".pdf");
    var result4 = path.includes(".html?");

    if(path=="#" || result2){
		finalpath = path;
        target = "_parent";
    }
    else if(result1 && result3){
		finalpath = path;
        target = "_blank";
    }
    else if(result1 && result4){
		finalpath = path;
        target = "_parent";
    }
    else if(result1){
		finalpath = path+".html";
        target = "_parent";
    }
    else{
        finalpath = path;
    }

    return {
        path:finalpath,
        target:target
    };
});