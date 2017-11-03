$(document).ready(function() {

    $(window).on("load resize scroll",function(e){
        $('.videopopup').on('click', function() {

            if($('body').width() < 667) {
                windowHeight = $(window).height() - 140;
                $('object, iframe#youtubeVideo').attr('height', windowHeight);
            } else {
                windowHeight = $(window).height() - 120;
                $('object, iframe#youtubeVideo').attr('height', windowHeight);
            }
        });
    });


    $(".modal .videoclose").click(function(){
        if($('.modal').attr('data-id') === "youtube" || "ustudio" ) {
            var videSrc = $('.modal iframe#youtubeVideo').attr('src');
            var newSrc =  videSrc.replace('?autoplay=1','');
            $('.modal iframe#youtubeVideo').attr('src', newSrc);
        }

    });


	var trigger = $("body").find("[data-toggle='modal']");
    trigger.click(function() {
        var videoSRC = trigger.attr('data-thevideo');
        if(($('.modal').attr('data-id') == 'youtube' )||($('.modal').attr('data-id') == 'ustudio') ) { 
            var videoSRCauto = videoSRC + "?autoplay=1" ;
            $('.modal #youtubeVideo').attr('src', videoSRCauto);
        }
    });


});

/*$(document).ready(function(){
	var trigger = $("body").find('[data-toggle="modal"]');
  	trigger.click(function() {
        var theModal = $(this).data( "target" ),
        videoSRC = $(this).attr( "data-theVideo" ), 
            if($('.modal').attr('data-id') == 'youtube') {
                var videoSRCauto = videoSRC + "?autoplay=1" ;
            }
       // if($('.modal iframe').is(':visible')){
        	$(theModal+' iframe').attr('src', videoSRCauto);
        //} else {
			//$(theModal+' object').attr('src', videoSRCauto);
       // }
        //$('.modal').on('hidden.bs.modal', function () {
            //$('.modal iframe').removeAttr('src');
            //$('.modal object').removeAttr('src');
        //});   
    
    });

});*/