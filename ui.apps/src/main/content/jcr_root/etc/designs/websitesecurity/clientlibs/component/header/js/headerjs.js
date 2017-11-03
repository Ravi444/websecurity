
var document_width;

$(document).ready(function()
{
	document_width=$(document).width();

});

$(window).on('resize', function () {

     if(document_width!=$(document).width()) 
    {
        document_width=$(document).width(); 
        pageResize();
    }
});

$(window).on('load', function () {


        pageResize();

});


 
function pageResize() {
    showTabs();
    $(".backButtonthirdlevel").hide();
    $('.tabPanels').hide();
    $('#tab').hide();
    $('#navLinks > li').removeClass('active');
 
    $(".c_h_topRightContainer .c_search-top input").click(function (event) {
        event.stopPropagation();
        $(".c_h_topRightContainer .c_search-top").animate({ width: '220px' });
        $(".c_h_topRightContainer .c_search-top").css({ backgroundColor: "#333333" });
        $('.c_sel_countryShowContainer').removeClass('sel_showCountry');
        $(".c_sel-country").removeClass("sel_addBGColor");
        $('.tabPanels').removeClass('show_hide');
        $('.tabPanels').hide();
    });
 
    // Javascript For Langauge
    $(".c_sel-country").off('click');
    $(".c_sel-country").on('click', function (event) {
        event.stopPropagation();
        $(".headerstrip .c_sel_countryShowContainer").toggleClass("sel_showCountry");
        $(".c_sel-country").toggleClass("sel_addBGColor");
        $(".c_h_topRightContainer .c_search-top").animate({ width: '100px' });
        $(".c_h_topRightContainer .c_search-top").css({ backgroundColor: "#464646" });
        $('.tabPanels').hide();
        $('.tabPanels').removeClass('show_hide');
        $('#navLinks > li > a').removeClass('navActive');
        $('.tpl-signIn').removeClass('signinActive');
        $('.c-signin-popup-container').hide();
        $('.ui-autocomplete').hide();
    });
 
    // stopPropagation click on body
    $("body").off('click');
    $("body").on('click', function () {
        if ($(this).attr('class') !== 'sel_showCountry') {
            $('div.c_sel_countryShowContainer').click(function (event) {
                event.stopPropagation();
            });
            $('.c_sel_countryShowContainer').removeClass('sel_showCountry');
            $(".c_sel-country").removeClass("sel_addBGColor");
        }
        $(".c_h_topRightContainer .c_search-top").animate({ width: '100px' });
        $(".c_h_topRightContainer .c_search-top").css({ backgroundColor: "#464646" });
    });
 
    // Javascript For Sign In
    $(".tpl-signIn").mouseenter(function (e) {
        e.preventDefault();
        if ($(window).width() > 974) {
            $('.c-signin-popup-container').show();
            $('.tpl-signIn').addClass('signinActive');
        }
    }).mouseleave(function () {
        if ($(window).width() > 974) {
            $('div.c-signin-popup-container').hide();
            $('.tpl-signIn').removeClass('signinActive');
        }
    });
 
    $("div.c-signin-popup-container").mouseenter(function (e) {
        e.preventDefault();
        if ($(window).width() > 974) {
            $('.tpl-signIn').addClass('signinActive');
            $('div.c-signin-popup-container').show();
        }
    }).mouseleave(function () {
        if ($(window).width() > 974) {
            $('.tpl-signIn').removeClass('signinActive');
            $('div.c-signin-popup-container').hide();
        }
    });
 
    $("#navLinks > li > a").off('click');
    $("#navLinks > li > a").on('click', function() {
        $('.c_mobButtonOuter').show();
        $('.c_mobButtonOuter').find('#navLinks').css('display', 'inline-block');
    });
 
    $('.tab-pane.tabPanels').removeClass('show_hide');
    var smallDevice1 = $(".small-device1").clone();
    var smallDevice2 = $(".small-device2").clone();
    var tabDevice = $('.tab-device').clone();
    var expiredCertificate = $('.expiredcertificate').clone();
    var countryShowContainer = $('.c_sel_countryShowContainer').clone();
 
 
    $(".small-device1").remove();
    $(".small-device2").remove();
    $('.tab-device').remove();
    $('.expiredcertificate').remove();
    $('.c_sel_countryShowContainer').remove();
 
    $('.nav-cws-container').after(smallDevice2);
    $('.nav-cws-container').after(smallDevice1);
    $(".top-border").append(tabDevice);
    $('.buyandrenew-nav-box .helpmechoose').append(expiredCertificate);
    $('.c_sel-country').append(countryShowContainer);
 
    $('.c-country-option ul').show();
    $('.ulist').show();
    $('.listSectionArea').show();
    $('ul.clear').show();
 
    $('.matchHeight').matchHeight();
    $('.matchHeight_head').matchHeight();
 
    if ($(window).width() < 1200) {
 
        $(".small-device1").remove();
        $(".small-device2").remove();
        $('.tab-device').remove();
 
        $(".top-border").after(smallDevice2);
        $(".top-border").after(smallDevice1);
        $('.nav-cws-container').after(tabDevice);
    }
 
    $("#navLinks > li > a").mouseenter(function (event) {
        if ($(window).width() > 974) {
            var tabRef = $(this).attr('data-href');
            $('#navLinks > li').removeClass('active');
            $('.tabPanels').hide();
            $('div' + tabRef).show();
            $('#navLinks > li > a').removeClass('navActive');
            $(this).addClass('navActive');
        }
    }).mouseleave(function () {
        if ($(window).width() > 974) {
            $('div.tabPanels').hide();
            $('#navLinks > li > a').removeClass('navActive');
        }
    });
 
    $("div.tabPanels").mouseenter(function (event) {
        if ($(window).width() > 974) {
            $(this).show();
            $('#navLinks > li > a').removeClass('navActive');
            var id = $(this).attr('id');
            $('#navLinks li a[data-href="#' + id + '"]').addClass('navActive');
        }
    }).mouseleave(function () {
        if ($(window).width() > 974) {
            $(this).hide();
            $('#navLinks > li > a').removeClass('navActive');
        }
    });
 
    // if (/Android|webOS|iPhone|iPod|iPad|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
    if ($(window).width() > 750  && $(window).width() < 974) {
        
        showTabs();
        $("#navLinks > li > a").click(function (event) {
            event.preventDefault();
            var tabRef = $(this).attr('data-href');
            $('#navLinks > li').removeClass('active');
            $('.c-signin-popup-container').hide();
            if ($('div' + tabRef).hasClass('show_hide')) {
                $(this).toggleClass('navActive');
                $('div' + tabRef).toggleClass('show_hide');
 
            } else {
                $('.tabPanels').removeClass('show_hide');
                $('div' + tabRef).addClass('show_hide');
            }
 
            $('#navLinks > li > a').removeClass('navActive');
            $('.c_sel_countryShowContainer').removeClass('sel_showCountry');
            $(".c_sel-country").removeClass("sel_addBGColor");
            $('.tpl-signIn').removeClass('signinActive');
            $(".c_h_topRightContainer .c_search-top").animate({ width: '100px' });
            $(".c_h_topRightContainer .c_search-top").css({ backgroundColor: "#464646" });
            event.stopPropagation();
        });
 
        // stopPropagation click on body
        $('.tabPanels').on('click', function (event) {
            event.stopPropagation();
            var tabRef = $(this).attr('href');
            $('div' + tabRef).show();
            $('#navLinks > li > a').removeClass('navActive');
            var id = $(this).attr('id');
        });
 
 
        // Javascript For Sign In
        $(".tpl-signIn").click(function (event) {
            showTabs();
            $('.c-signin-popup-container').show();
            $('.tpl-signIn').addClass('signinActive');
            $('.tabPanels').hide();
            $('#navLinks > li > a').removeClass('navActive');
            $('.tabPanels').removeClass('show_hide');
            event.stopPropagation();
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
 
            if ($(this).attr('class') !== 'c-signin-popup-container') {
                $('div.c-signin-popup-container').hide();
                $('div.c-signin-popup-container').click(function (event) {
                    event.stopPropagation();
                });
                $('.tpl-signIn').removeClass('signinActive');
            }
        });
    }
 
    if ($(window).width() <= 767) {
        if (/Android|webOS|iPhone|iPod|iPad|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
            mobileView();
        }
 
 
    }
 
    if ($(window).width() <= 767) {
        mobileView();
    }
}
 
function showTabs() {
    $('.c_mobButtonOuter').show();
    $('.c_mobButtonOuter').contents().show();
    $('.c_mobButtonOuter').find('#navLinks').css('display', 'inline-block');
    $('.c-signin-popup-container').hide();
}
 
function mobileView() {
 
    //$(".menuButton").off("click");
    var tabDevice = $('.tab-device').clone();
    var expiredCertificate = $('.expiredcertificate').clone();
    var countryShowContainer = $('.c_sel_countryShowContainer').clone();
 
    $(".menuButton").off("click");
    $("#navLinks").removeClass("currentNav");
    $(".menuButton").on("click", function (event) {
      
        if (!$('#navLinks, .c_mobButtonOuter').is(':visible') && !$('.menuButton').hasClass('active')) {
            $('#navLinks, .c_mobButtonOuter').show();
        } else {
            $('#navLinks, .c_mobButtonOuter').hide();
            $("#navLinks").removeClass("currentNav");
        }
        $(".popup-bubble").hide();
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
 
        $(".tabPanels h4").click(function () {
            if ($(this).parent().find('span').hasClass('glyphicon')) {
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
 
        $(".backButtonthirdlevel").click(function () {
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
        var navHeight = $('#navLinks').innerHeight() + 8;
        $('.tpl-signIn').css('top', navHeight);
        if ($(window).width() < 315) {
            var navHeight = $('#navLinks').innerHeight() + 60;
            $('.tpl-signIn').css('top', navHeight);
        }
       // e.stopPropagation();
 
    });
 
 
 
    if($('.globeImg').hasClass('active')) {
        $('.globeImg').click();
    }
 
    // Javascript for Header Globe Language
    $(".globeImg").off("click");
    $('.globeImg').on('click', function () {
        $('.c_mobile-globe-container').toggleClass("countryShowDiv");
        $('#navLinks, .c_mobButtonOuter').hide();
        $('.c_mobile-search-container').removeClass("searchDivShow");
        $('.tabPanels').hide();
        $('span.globeImg i, span.globeImg').toggleClass("active");
        $('span.menuButton i, span.menuButton').removeClass("active");
        $('span.searchfor-mobile i, span.searchfor-mobile').removeClass("active");
        $(".third-level-child").show();
        $(".popup-bubble").hide();
        $(".third-level-child-box").show();
        $(".c-country-option ul").hide();
        if($(".c-country-option ul").hasClass("show_hide")) {
            $(".c-country-option ul").removeClass("show_hide");
        }
        $(".c-country-option h4").off('click');
        $(".c-country-option h4").on('click', function () {
            if ($(this).next().is(":visible")) {
                $(this).next().removeClass("show_hide");
            } else {
                $(this).next().toggleClass("show_hide");
            }
        });
    });
 
    // Javascript for Header Search for mobile
    $(".searchfor-mobile").off("click");
    $('.searchfor-mobile').click(function () {
        $('.c_mobile-search-container').toggleClass("searchDivShow");
        $('#navLinks, .c_mobButtonOuter').hide();
        $('.c_mobile-globe-container').removeClass("countryShowDiv");
        $('span.searchfor-mobile i, span.searchfor-mobile').toggleClass("active");
        $('span.menuButton i, span.menuButton').removeClass("active");
        $('span.globeImg i, span.globeImg').removeClass("active");
        $('.tabPanels').hide();
        $(".popup-bubble").hide();
    });
 
    // Javascript For Sign In
    $(".tpl-signIn").click(function () {
        $('.c-signin-popup-container').show();
        $('.tpl-signIn').removeClass('signinActive');
        $('#navLinks').hide();
        $('.tpl-signIn').hide();
    });
 
    $(".signinback").click(function () {
        $('.c-signin-popup-container').hide();
        $('.tpl-signIn').removeClass('signinActive');
        $('#navLinks').show();
        $('.tpl-signIn').show();
    });
 
    //$('#navLinks > li > a').off('click');
    $('#navLinks > li > a').on('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        $('#navLinks > li > a').removeClass('navActive');
        var tabRef = $(this).attr('data-href');
        $('#navLinks > li').removeClass('active');
        $(this).parent().addClass('active');
        $('.tabPanels').removeClass('show_hide');
        //$('.tabPanels').hide();
        $('#navLinks, .c_mobButtonOuter').hide();
        $('.tpl-signIn').hide();
        if ($(tabRef).css('display') == 'none') {
            $('div' + tabRef).show();
        }
 
 
        $(tabRef).find('.container').find('.backButtonthirdlevel').hide();
        $(tabRef).find('.container').find('.navTitle').show();
        $(tabRef).find('.container').find('.backButtonSec').show();
        $(tabRef).find('.container').find('.columncontrol.section').contents().show();
        var ulDiv = $(tabRef).find('.container').find('.columncontrol.section').find('.third-level-child');
 
        $.each(ulDiv, function () {
            $(this).show();
            $(this).removeClass('active');
            $(this).find('ul').hide();
            $(this).find('.tabPanels h4').show();
            $(this).find('.tabPanels h4').removeClass('activeThirdlevel');
            $(this).find('.navlist').show();
            $(this).find('.activeThirdlevel').find('span').show();
        });
 
        $('.backButtonSec').click(function () {
            $('#navLinks, .c_mobButtonOuter').show();
            $('#navLinks > li > a').removeClass('navActive');
            $('div' + tabRef).hide();
            $(".tpl-signIn").show();
            $(this).hide();
 
        });
 
        $('.tabPanels').on('click', function (event) {
            //event.preventDefault()
            var tabRef = $(this).attr('data-href');
            $('div' + tabRef).show();
            $('body').on('click', function (event) {
                if ($(this).parents().attr('id') === 'navLinks') {
                    event.stopPropagation();
                    $('.tabPanels').hide();
                    $('div' + tabRef).hide();
                }
            });
        });
 
        e.stopPropagation();
    });
 
    // Js for single box of helpmeChoose
    var singleHelpMeChooseBox = $(".buyandrenew-nav-box .helpmechoose").length;
    if (singleHelpMeChooseBox == 1) {
        $(".helpmechoose").css("width", "100%");
    } else {
        $(".helpmechoose").css("width", "");
    }
 
    $('.tab-device').remove();
    $('.expiredcertificate').remove();
    $('.c_sel_countryShowContainer').remove();
    $('.code-sining-certificate').after(tabDevice);
    $('.buyandrenew-nav2').append(expiredCertificate[0]);
    $('.c_mobile-globe-container').append(countryShowContainer);
 
    if($('.c_mobButtonOuter').is(':visible')) {
        $('.c_mobButtonOuter').hide();
        $('.c_mobButtonOuter').contents().hide();
 
        if($('.menuButton.active').is(':visible')) {
            $('.menuButton.active').click();
        }
    }
}