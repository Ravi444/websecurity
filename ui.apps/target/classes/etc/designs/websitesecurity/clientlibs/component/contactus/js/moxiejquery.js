//moxiejquery starts here

function myFunction() {
    var popup = document.getElementById('myPopup');
    popup.classList.toggle('show');
}



//Place this code in the footer below the jquery library include and the matchHeight library include
$(document).ready(function() {
	$(".cg_float-contact .popup-button a").off("click");
    $(".cg_float-contact .popup-button a").on("click", function(e) {
      e.preventDefault();
      var bubbleid = $(this).attr("href");
      $(bubbleid).fadeToggle();

      $('.menuButton.active').click();      
      $('span.globeImg i, span.globeImg').removeClass("active");
      $('.c_mobile-globe-container').removeClass("countryShowDiv");
      $('.searchfor-mobile.active').click();

    })

    $(".cg_float-contact a.bubble-close").off('click');
    $(".cg_float-contact a.bubble-close").on("click", function(e) {
      e.preventDefault();
      $(this).parents(".popup-bubble").fadeToggle();
    });
});

function popupWindow() {
	var url = document.getElementById("availableLink").href;
    var myWindow = window.open(url,"popup", "width=500,height=600");
}
//moxiejquery ends here