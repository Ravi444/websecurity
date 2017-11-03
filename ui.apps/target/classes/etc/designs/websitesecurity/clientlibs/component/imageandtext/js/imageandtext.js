$(document).ready(function() {

    $(window).on("load resize scroll",function(e){
        $('.videopopup').on('click', function() {

            if($('body').width() < 767) {
                $('object, iframe#youtubeVideo').attr('height', 'auto');
                 $('object, iframe#ustudioVideo').attr('height', 'auto');
            } else {
                windowHeight = $(window).height() - 160;
                $('object, iframe#youtubeVideo').attr('height', windowHeight);
                $('object, iframe#ustudioVideo').attr('height', windowHeight);
            }
        });
    });


    $(".imageantextmodal .videoclose").click(function(){
        if($('.imageantextmodal').attr('data-id') === "youtube" ) {
            var videSrc = $('.imageantextmodal iframe#youtubeVideo').attr('src');
            var newSrc =  videSrc.replace('?autoplay=1','');
            $('.imageantextmodal iframe#youtubeVideo').attr('src', newSrc);
        }
        if($('.imageantextmodal').attr('data-id') === "ustudio" ) {
            var videSrc = $('.imageantextmodal iframe#ustudioVideo').attr('src');
            var newSrc =  videSrc.replace('?autoplay=1','');
            $('.imageantextmodal iframe#ustudioVideo').attr('src', newSrc);
        }

    });


	var trigger = $("body").find("[data-toggle='modal']");
    trigger.click(function() {
        var videoSRC = $(this).attr('data-thevideo');
        var studioSRC = $(this).attr('data-ustudioVideo');
        if($('#videoModal.imageantextmodal').attr('data-id') == 'youtube' ) { 
            var videoSRCauto = videoSRC + "?autoplay=1" ;
            $('#videoModal.imageantextmodal #youtubeVideo').attr('src', videoSRCauto);
        } 

        if($('#videoModal.imageantextmodal').attr('data-id') == 'ustudio') {
            var studioSRC = studioSRC + "?autoplay=1" ;
            $('#videoModal.imageantextmodal #ustudioVideo').attr('src', studioSRC);
        }

        if($('#videoModal.imageantextmodal').attr('data-id') == 'brightcove') { 
            var playerid = $('.playerId').val();
            var playerkey = $('.playerKey').val()
            var videoid = $('.videoPlayer').val();


            var htmlContent = "";
            htmlContent += '<script language="JavaScript" type="text/javascript" src="http://admin.brightcove.com/js/BrightcoveExperiences.js"></script>';
            htmlContent += '<object type="application/x-shockwave-flash" id="myExperience1155633140001" width="100%" height="500" class="BrightcoveExperience BrightcoveExperienceID_9161" seamlesstabbing="undefined" data="http://c.brightcove.com/services/viewer/federated_f9?&amp;width=100%25&amp;height=100%25&amp;flashID=myExperience1155633140001&amp;identifierClassName=BrightcoveExperienceID_9161&amp;bgcolor=%23FFFFFF&amp;playerID='+playerid+'&amp;playerKey='+playerkey+'&amp;isVid=true&amp;isUI=true&amp;dynamicStreaming=true&amp;autoStart=true&amp;%40videoPlayer='+videoid+'&amp;debuggerID=&amp;startTime=1486468559092"><param name="allowScriptAccess" value="always"><param name="allowFullScreen" value="true"><param name="seamlessTabbing" value="false"><param name="swliveconnect" value="true"><param name="wmode" value="window"><param name="quality" value="high"><param name="bgcolor" value="#FFFFFF"></object>';
			$('#videoModal.imageantextmodal .video_data_container').html(htmlContent);	
        }

    });

});
