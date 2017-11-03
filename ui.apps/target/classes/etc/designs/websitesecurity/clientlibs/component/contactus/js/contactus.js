//Contact us starts here

function chatImgloadfunc(obj) {
    var myHref = obj.parentNode.href;  //grabs the appropriate chat URL so the link will go to the right place.
    if (obj.width == "1") {
	    // chat is NOT avaiable. Show the unavailable message.
        $(obj).closest(".moxiechat").find(".chat-unavailable").show();
    }
    else{
	    // chat is AVAILABLE. Set the link to the proper queue and show it. Expecting openimg variable
	    $(obj).closest(".moxiechat").find(".chat-available a").attr("href", myHref);
        $(obj).closest(".moxiechat").find(".chat-available").show();
        
    }
}

function chatOpenInParent(url) {
	if (window && window.opener) {
		window.opener.location.href = url;
		window.close();
	}
	else if (window && window.parent) {
		window.parent.location = url;
	}
	else if (document) {
		document.location = url;
	}
}

$(document).ready(function() {

	// IMPORTANT: For production use, the  hostref value will need to change to
	//  symctrust.ehosts.net
	var hostref,
		questid,
		portid,
		defaultStyleId,
		openimg,
		closeimg,
		style = "style0";

	//		openimg and closeimg are arbitrary and can be hosted anywhere.
	//		The script is expecting closeimg to be 1px wide.
	// 		openimg needs to be wider.

    $( ".moxiechat" ).each(function(index) {
        chatObj = $(this);
		hostref = chatObj.data("hostref");
        questid = chatObj.data("questid");
        portid = chatObj.data("portid");
        defaultStyleId = chatObj.data("styleid");
        openimg = "https://" + hostref + "/netagent/client/invites/en-us/style13/chat_open.jpg";
        closeimg = "https://vs.symantec.com/assets/chat/images/spacer.gif";
        var a_url = "https://" + hostref + "/netagent/cimlogin.aspx?questid=" + questid + "&portid=" + portid + "&defaultStyleId=" + defaultStyleId + "&nareferer=https%3A//" + hostref + "/NetAgent/adminasp/chat_portal_edit.aspx%3FRAND%3D4171%26id%3D112";
        var img_src = "https://" + hostref + "/netagent/client/invites/chatimage.aspx?style=" + style + "&questid=" + questid + "&portid=" + portid + "&imagelanguage=en-us&customopenimage=" + openimg + "&customcloseimage=" + closeimg;
        chatObj.append("<a href=\""+a_url+"\" style='visibility:hidden;'><img class='moxieimg' src='"+img_src+"' onload='chatImgloadfunc(this);'/></a>");
    });


// this is optional: Our chat console opens in a popup so we need to deal with that gracefully. I'm leaving it on so you can see how it's used. Comment it out if you don't need it, or feel free to change it. :o)
    $("a.parentLink").bind("click", function(e) {
        e.preventDefault();
        var url = $(this).attr("href");
        chatOpenInParent(url);
    });
});
//Contact us ends here