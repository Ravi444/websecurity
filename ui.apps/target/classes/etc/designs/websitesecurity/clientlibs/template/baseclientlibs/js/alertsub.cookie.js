//OPS-102486 - Check for cookie and hide form in exists
$(document).ready(function() {
	var chkCookie = getCookie('Symc_ThreatAlert_Subscription');	
	//show form if NOT Subscription form (minimum)
	if (fType != 'minimum') {
    	$('.form-wrapper').show();
	}
	//show form if Subscription and cookie not set
	if (fType == 'minimum') {		
		//based on cookie - show form or subscribed message
		if (chkCookie != 'Symantec Threat Alert: Subscribed') {
    		$('.form-wrapper').show();
		} else {
    		$('.alertSubscribed').show();		
		}
	}	
})
					
function showCookie(name) {
	cook = getCookie(name);
	alert (cook);
}
	
function createCookie(name, value, days)
{
  if (days) {
    var date = new Date();
    date.setTime(date.getTime()+(days*24*60*60*1000));
    var expires = "; expires="+date.toGMTString();
    }
  else var expires = "";
  document.cookie = name+"="+value+expires+"; path=/; domain=.symantec.com";
}

function getCookie(cname) {
     var name = cname + "=";
     var ca = document.cookie.split(';');
     for(var i=0; i<ca.length; i++) {
         var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
         if (c.indexOf(name) == 0) return c.substring(name.length,c.length);
     }
     return "";
 } 
   
function eraseCookie(name) {
	createCookie(name,"",-1);
}