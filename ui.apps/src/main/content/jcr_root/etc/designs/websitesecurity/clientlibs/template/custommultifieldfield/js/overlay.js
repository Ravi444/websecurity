$( document ).ready( function() {
$('.videopopup.overlay_popup').on('click', function(e) {
    debugger
	e.preventDefault();
    alert('sadghjhi');
	var id = $(this).attr('data-id');
    if(id == "brightcove") {
        var playerid = $(this).attr('data-playerid');
        var playerkey = $(this).attr('data-playerkey');
        var videoid = $(this).attr('data-videoid');
        var closeicon = $(this).attr('data-closeicon');
        var hoverclose = $(this).attr('data-hoverclose');
        var videotitle = $(this).attr('data-videotitle');

		var html = "";
        html += '<div class=" col-md-12 col-sm-12 col-xs-12">';
		html +=   '<div class=" col-md-10 col-sm-10 col-xs-10">';
		html +=     '<h2>'+videotitle+'</h2>';
		html += 	'</div>';
		html += 	  '<div class=" col-md-2 col-sm-2 col-xs-2">';
		html += 		'<button type="button" class="close videoclose" data-dismiss="modal" aria-hidden="true">';
		html += 		  '<img class="close-button" src="'+closeicon+'">';
		html += 	      '<img class="hoverClose-button" src="'+hoverclose+'">';
		html += 		'</button>';
		html += 	  '</div>';
		html += 	'</div>';
		html += 	'<div>';
		html += 	  '<object id="myExperience1155633140001" class="BrightcoveExperience">';
		html += 		'<param name="bgcolor" value="#FFFFFF" />';
		html += 		'<param name="playerID" value="'+playerid+'" />';
		html += 		'<param name="playerKey" value="'+playerkey+'" />';
		html += 		'<param name="isVid" value="true" />';
		html += 		'<param name="isUI" value="true" />';
		html += 		'<param name="dynamicStreaming" value="true" />';
		html += 		'<param name="autoStart" value="true" />';
		html += 		'<param name="@videoPlayer" value="'+videoid+'" />';
		html += 	  '</object>';
		html += 	'</div>';

        $('#videoModal1').modal('show');
        $('#videoModal1 .modal-body').html(html);

    } else if(id == "youtube || ustudio") {
        alert('hisdsd');
debugger
        var src = $(this).attr('data-theVideo');
        var closeicon = $(this).attr('data-closeicon');
        var hoverclose = $(this).attr('data-hoverclose');

		var html = "";
        html += '<div class=" col-md-12 col-sm-12 col-xs-12">';
		html +=   '<div class=" col-md-10 col-sm-10 col-xs-10">';
		html +=     '<h2>'+videotitle+'</h2>';
		html += 	'</div>';
		html += 	  '<div class=" col-md-2 col-sm-2 col-xs-2">';
		html += 		'<button type="button" class="close videoclose" data-dismiss="modal" aria-hidden="true">';
		html += 		  '<img class="close-button" src="'+closeicon+'">';
		html += 	      '<img class="hoverClose-button" src="'+hoverclose+'">';
		html += 		'</button>';
		html += 	  '</div>';
		html += 	'</div>';
		html += 	'<div>';
		html += 	  '<iframe id="youtubeVideo" width="100%" height="100%" src="'+src+'" allowfullscreen></iframe>';
		html += 	'</div>';

         $('#videoModal1').modal('show');
        $('#videoModal1 .modal-body').html(html);
    }
});

$('#videoModal1 .videoclose').on('click', function() {
	$(this).parents().find('.modal-body').html("");
});
}