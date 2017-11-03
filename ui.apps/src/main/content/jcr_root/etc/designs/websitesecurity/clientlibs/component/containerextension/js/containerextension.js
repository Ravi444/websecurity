$(document).ready(function(){


function viewshowhide(){
		$(".view-less-feature").hide();
		$(".view-all-feature").show();
}
viewshowhide();

/*if( $(".c_expand-content").is(":visible") == true){
        alert();
        $(".viewallfeatures, .view-all-feature, .view-less-feature").remove();

    }else{
         $(".viewallfeatures, .view-all-feature, .view-less-feature").show();

    }*/
    
var count = false;
$(".viewallfeatures, .view-all-feature, .view-less-feature").on('click', function(){	
    $(".viewallfeatures span").toggleClass("glyphicon-menu-down glyphicon-menu-up active");
    $(".view-all a, .view-all span").toggleClass("active");

    /***************toggling the label of view all & viw less feature STARS*****************/
    if(count == false){
		$(".view-less-feature").show();
	    $(".view-all-feature").hide();
        count = true;
    }else if(count == true){
		$(".view-less-feature").hide();
	    $(".view-all-feature").show();
        count = false;
    }
    else{
		$(".view-less-feature").hide();
	    $(".view-all-feature").show();
    }
    /***************toggling the label of view all & viw less feature ENDS*****************/
     $('.c_expand-content').slideToggle();

});
});