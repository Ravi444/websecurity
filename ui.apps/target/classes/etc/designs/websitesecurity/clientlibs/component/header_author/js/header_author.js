
$(document).ready(function(){

  $(".backButtonthirdlevel").hide();


  $('.tabPanels').hide();
  $('#tab').hide(); 
  $('#navLinks > li').removeClass('active');

  $(".c_h_topRightContainer .c_search-top input").click(function(event){
    event.stopPropagation();
    $(".c_h_topRightContainer .c_search-top").animate({width: '220px'});
    $(".c_h_topRightContainer .c_search-top").css({backgroundColor: "#333333"});
    $('.c_sel_countryShowContainer').removeClass('sel_showCountry');
    $(".c_sel-country").removeClass("sel_addBGColor");
  });
   $("body").click(function() {
    $(".c_h_topRightContainer .c_search-top").animate({width: '100px'});
    $(".c_h_topRightContainer .c_search-top").css({backgroundColor: "#464646"});
  }); 


  // Javascript For Langauge
    $(".c_sel-country").click(function(event){
        event.stopPropagation();
        $(".c_sel_countryShowContainer").toggleClass("sel_showCountry");
        $(".c_sel-country").toggleClass("sel_addBGColor");
        $(".c_h_topRightContainer .c_search-top").animate({width: '100px'});
        $(".c_h_topRightContainer .c_search-top").css({backgroundColor: "#464646"});
        $('.tabPanels').hide();
        $('.tpl-signIn').removeClass('signinActive');
        $('.c-signin-popup-container').hide();
    });

    // stopPropagation click on body
    $("body").click(function() {
        if($(this).attr('class') !== 'sel_showCountry') {
            $('div.c_sel_countryShowContainer').click(function(event) {
                event.stopPropagation();
            });
            $('.c_sel_countryShowContainer').removeClass('sel_showCountry');
            $(".c_sel-country").removeClass("sel_addBGColor");
        }
    });


// Javascript For Sign In
    $(".tpl-signIn").click(function(event){
        event.stopPropagation();
        if ($(this).next().is(':visible')) {
            $('.c-signin-popup-container').removeClass("show_hide");
        } else {
             $('.c-signin-popup-container').toggleClass("show_hide");
        }
		$('.tabPanels').hide();
        $('#navLinks > li > a').removeClass('navActive');
        //$('.c-signin-popup-container').slideToggle();
        $('.tpl-signIn').toggleClass('signinActive');
    });


  $('.matchHeight').matchHeight();
  $('.matchHeight_head').matchHeight();


    if($(window).width() < 1199){
        var smallDevice1 = $(".small-device1").clone();
        var smallDevice2 = $(".small-device2").clone();
        var tabDevice = $('.tab-device').clone();
    
        $(".small-device1").remove();
        $(".small-device2").remove();
        $('.tab-device').remove();
    
        $(".top-border").after(smallDevice2);
        $(".top-border").after(smallDevice1);
        $('.nav-cws-container').after(tabDevice);

      }

    $("#navLinks > li > a").click(function(event) {
            var tabRef = $(this).attr('data-href');
            $('#navLinks > li').removeClass('active');
            $('.tabPanels').hide();
            $('.c-signin-popup-container').hide();
            $('div'+tabRef).show();
            $('#navLinks > li > a').removeClass('navActive');
			$(this).addClass('navActive');
            $('.c-signin-popup-container').hide();
        	$('.c-signin-popup-container').removeClass("show_hide");
            event.preventDefault();
            event.stopPropagation();
		});

        // stopPropagation click on body
        $('.tabPanels').on('click', function(event) {
            event.preventDefault();
			event.stopPropagation();
            var tabRef = $(this).attr('href');
			$('div'+tabRef).show();
            $('#navLinks > li > a').removeClass('navActive');
            var id = $(this).attr('id');
            $('#navLinks li a[data-href="#'+id+'"]').addClass('navActive');
        });


        $("body").click(function () {
            if ($(this).attr('class') !== 'tabPanels') {
                $('div.tabPanels').hide();
                $('div.tabPanels').removeClass('show_hide');
                $('div.tabPanels').click(function (event) {
                    event.stopPropagation();
                });
                $('#navLinks > li > a').removeClass('navActive');
            }

        });




	if( /Android|webOS|iPhone|iPod|iPad|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) {
        $("#navLinks > li > a").click(function(event) {
            var tabRef = $(this).attr('data-href');
            $('#navLinks > li').removeClass('active');
            $('.tabPanels').hide();
            $('.c-signin-popup-container').hide();
            $('div'+tabRef).show();
            $('#navLinks > li > a').removeClass('navActive');
			$(this).addClass('navActive');
            event.preventDefault();
            event.stopPropagation();
		});

        // stopPropagation click on body
        $('.tabPanels').on('click', function(event) {
			event.stopPropagation();
            var tabRef = $(this).attr('href');
			$('div'+tabRef).show();
            $('#navLinks > li > a').removeClass('navActive');
            var id = $(this).attr('id');
            $('#navLinks li a[data-href="#'+id+'"]').addClass('navActive');
        });


		// Javascript For Sign In
        $(".tpl-signIn").click(function(event) {
                $('.c-signin-popup-container').show();
                $('.tpl-signIn').addClass('signinActive');
            	$('.tabPanels').hide();
           		$('#navLinks > li > a').removeClass('navActive');
                event.stopPropagation();
        });


        $("body").click(function() {
            if($(this).attr('class') !== 'tabPanels') {
                $('div.tabPanels').hide();
                $('div.tabPanels').click(function(event) {
                    event.stopPropagation();
                });
                $('#navLinks > li > a').removeClass('navActive');
            }

            if($(this).attr('class') !== 'c-signin-popup-container') {
                $('div.c-signin-popup-container').hide();
				$('div.c-signin-popup-container').click(function(event) {
                    event.stopPropagation();
                });
                $('.tpl-signIn').removeClass('signinActive');
            }

        });
    }

});





$(window).on('load', function() {
  if($('body').width() <= 767){
    $('.menuButton').click(function() {
      if(!$('#navLinks, .c_mobButtonOuter').is(':visible') && !$('.menuButton').hasClass('active')) {
        $('#navLinks, .c_mobButtonOuter').show();
      } else {
        $('#navLinks, .c_mobButtonOuter').hide();
      }
      $('.c_mobile-globe-container').removeClass("countryShowDiv");
      $('.c_mobile-search-container').removeClass("searchDivShow");
	  $('.c-signin-popup-container').hide();        
      $('.tabPanels').hide();
      $(this).parents().show();
      $('span.menuButton i, span.menuButton').toggleClass("active");
      $('span.searchfor-mobile i, span.searchfor-mobile').removeClass("active");
      $('span.globeImg i, span.globeImg').removeClass("active");
      $(".tpl-signIn").show();
      $(".third-level-child.active").hide();
      $(".third-level-child-box.mobileViewOnly.active").hide();

      $(".tabPanels h4").click(function(){
        if($(this).parent().find('span').hasClass('glyphicon')) {
          $(".third-level-child").removeClass("active");
          $(this).addClass("activeThirdlevel");
          $(this).parents(".third-level-child").addClass("active");
          $(this).parents(".third-level-child-box.mobileViewOnly").addClass("active");
          $(".third-level-child .navlist").hide();
          $(this).parents('.navlist').show();
          $(".third-level-child").hide();
          $(".third-level-child-box").hide();
          $(".third-level-child.active").show();
          $(".navTitle").hide();
          $(this).next().show();
          $(".backButtonSec").hide();
          $(this).parents('.tab-pane').find(".backButtonthirdlevel").show();

        }
      });

      $(".backButtonthirdlevel").click(function(){
        $(this).hide();
        $(".third-level-child").show();
        $(".listSectionArea").hide();
        $(".header .tabPanels ul").hide();
        $(".third-level-child-box").show();
        $(".backButtonSec").show();
        $(".tabPanels h4").removeClass("activeThirdlevel");
        $(".navTitle").show();
        $(".third-level-child").removeClass("active");
        $(".third-level-child .navlist").show();
      });
    });



    // Javascript for Header Globe Language

    $('.globeImg').click(function() {
      $('.c_mobile-globe-container').toggleClass("countryShowDiv");
      $('#navLinks, .c_mobButtonOuter').hide();
      $('.c_mobile-search-container').removeClass("searchDivShow");
      $('.tabPanels').hide();
      $('span.globeImg i, span.globeImg').toggleClass("active");
      $('span.menuButton i, span.menuButton').removeClass("active");
      $('span.searchfor-mobile i, span.searchfor-mobile').removeClass("active");
	  $(".third-level-child").show();
      $(".third-level-child-box").show();
        $(".c-country-option ul").hide();
        $(".c-country-option h4").removeClass();
		$(".c-country-option h4").click(function(){
      		//$(".c-country-option ul").hide();
            $(".c-country-option h4").removeClass();
            $(this).next().slideToggle();
            $(this).addClass("current");
        });

    });

// Javascript for Header Search for mobile
    $('.searchfor-mobile').click(function() {
      $('.c_mobile-search-container').toggleClass("searchDivShow");
      $('#navLinks, .c_mobButtonOuter').hide();
      $('.c_mobile-globe-container').removeClass("countryShowDiv");
      $('span.searchfor-mobile i, span.searchfor-mobile').toggleClass("active");
      $('span.menuButton i, span.menuButton').removeClass("active");
      $('span.globeImg i, span.globeImg').removeClass("active");
      $('.tabPanels').hide();
    });

// Javascript For Sign In
      $(".tpl-signIn").click(function() {
          $('.c-signin-popup-container').show();
          $('.tpl-signIn').removeClass('signinActive');
          $('#navLinks').hide();
		  $('.tpl-signIn').hide();
      });

      $(".signinback").click(function() {
          $('.c-signin-popup-container').hide();
          $('.tpl-signIn').removeClass('signinActive');
          $('#navLinks').show();
		  $('.tpl-signIn').show();
      });




    var linkA = $('#navLinks > li > a');
      $.each(linkA, function() {
		var dataHref = $(this).attr('data-href');
          $(this).attr({"href": dataHref});
          $(this).removeAttr("target");
      });

	//$('#navLinks > li > a').off('click');
    $('#navLinks > li > a').on('click', function(e) {
	$('#navLinks > li > a').removeClass('navActive');
      var tabRef = $(this).attr('data-href');
      $('#navLinks > li').removeClass('active');
      $(this).parent().addClass('active');
      //$('.tabPanels').hide();
      $('#navLinks, .c_mobButtonOuter').hide();
      $('.tpl-signIn').hide();
        if($(tabRef).css('display') == 'none') {
			$('div'+tabRef).show();
      }


      $(tabRef).find('.container').find('.backButtonthirdlevel').hide();
      $(tabRef).find('.container').find('.navTitle').show();
      $(tabRef).find('.container').find('.backButtonSec').show();
      $(tabRef).find('.container').find('.columncontrol.section').contents().show();
      var ulDiv = $(tabRef).find('.container').find('.columncontrol.section').find('.third-level-child');
      
      $.each(ulDiv, function() {
        $(this).show();
        $(this).removeClass('active');
        $(this).find('ul').hide();
        $(this).find('.tabPanels h4').show();
        $(this).find('.tabPanels h4').removeClass('activeThirdlevel');
        $(this).find('.navlist').show();
        $(this).find('.activeThirdlevel').find('span').show();
      });

      $('.backButtonSec').click(function() {
        $('#navLinks, .c_mobButtonOuter').show();
        $('#navLinks > li > a').removeClass('navActive');
        $('div'+tabRef).hide();
        $(".tpl-signIn").show();
        $(this).hide();

      });

      $('.tabPanels').on('click', function(event) {
		//event.preventDefault()
        var tabRef = $(this).attr('data-href');
        $('div'+tabRef).show();
        $('body').on('click', function(event){
            if($(this).parents().attr('id') === 'navLinks'){

                event.stopPropagation();
                $('.tabPanels').hide();
                $('div'+tabRef).hide();
            }
        });
      });

      e.stopPropagation();
    });


    var tabDevice = $('.tab-device').clone();
    var expiredCertificate = $('.expiredcertificate').clone();
    var countryShowContainer = $('.c_sel_countryShowContainer').clone();
    $('.tab-device').hide();
    $('.tab-device').remove();
    $('.expiredcertificate').remove();
	$('.c_sel_countryShowContainer').remove();
    $('.code-sining-certificate').after(tabDevice);
    $('.buyandrenew-nav2').after(expiredCertificate);
    $('.c_mobile-globe-container').append(countryShowContainer);

  } 
});