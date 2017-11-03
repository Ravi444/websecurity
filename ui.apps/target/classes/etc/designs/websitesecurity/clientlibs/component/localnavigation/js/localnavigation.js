$(document).ready(function(){
  // Create a clone of the menu, right next to original.
$('.nav_bgBox').addClass('original').clone().insertAfter('.nav_bgBox').addClass('cloned').removeClass('original').hide();

scrollIntervalID = setInterval(stickIt, 10);


function stickIt() {

  var orgElementPos = $('.original').offset();
  orgElementTop = orgElementPos.top;               

  if ($(window).scrollTop() >= (orgElementTop)) {
    // scrolled past the original position; now only show the cloned, sticky element.

    // Cloned element should always have same left position and width as original element.     
    orgElement = $('.original');
    coordsOrgElement = orgElement.offset();
    leftOrgElement = coordsOrgElement.left;  
    widthOrgElement = orgElement.css('width');
    $('.cloned').css('left',leftOrgElement+'px').css('top',0).css('width',widthOrgElement).show();
    $('.original').css('visibility','hidden');
    $('.renew_button span a').show();
      if($('.renew_button span a').is(':visible')){

      $('.outer_area').addClass("sticky_nav_list");
      }else{
           $('.outer_area').removeClass("sticky_nav_list");

      }
  } else {
    // not scrolled past the menu; only show the original menu.
    $('.cloned').hide();
    $('.original').css('visibility','visible');
    $('.renew_button span a').hide();
       if($('.renew_button span a').is(':visible')){

      $('.outer_area').addClass("sticky_nav_list");
      }else{
           $('.outer_area').removeClass("sticky_nav_list");

      }

  }
}

  // Add scrollspy to <body>
  $('body').scrollspy({target: ".navbar", offset: 50}); 
  // Add smooth scrolling on all links inside the navbar


    $("#nav_secure li a").on('click', function(event) {
        

if (this.hash !== "") {
      // Prevent default anchor click behavior
      event.preventDefault();


      // Store hash
      var hash = this.hash;

      // Using jQuery's animate() method to add smooth page scroll

      $('html, body').animate({
        scrollTop: $(hash).offset().top
      }, 800, function(){

        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
    }  // End if



           if ($(window).width() <= 767) 
			{
                $("#nav_secure ").hide();


                $('.c_mobile-view').removeClass("borderBottom");
            }

    });


$(window).on("load resize scroll",function(){
	if ($(window).width() <= 767) {
		mobileView();
        $("#nav_secure li a").on('click', function(event) {
		$("#nav_secure ").hide();
		$('.c_mobile-view').removeClass("borderBottom");
	});
	} else {
		$(".navbar-nav ").show();
	}




   
});



function mobileView() {

	$(".navbar-nav").hide();
	$(".c_mobile-view").off('click');
    $(".c_mobile-view").removeClass("active");


	$(".c_mobile-view").click(function(e) {


		$(".navbar-nav").slideToggle();
		 $(this).toggleClass("active");

		e.stopPropagation();


	});

	
	$("body").click( function(e) {
		$(".navbar-nav").hide();
		$('.c_mobile-view').removeClass("active");
	});
}




  });

