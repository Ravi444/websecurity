//Include the js library - jquery.matchHeight-min.js (make sure it's included after jquery core library)


//Place this code in the footer below the jquery library include and the matchHeight library include
$(document).ready(function() {
    $('.matchHeight').matchHeight();
    $('.matchHeight_head').matchHeight();

    var id = $('.pricing__customize-select .dropdown-menu').attr('aria-labelledby');
    $('.pricing__customize-select button').attr({'data-toggle': 'dropdown', 'id': id});

    $('.pricing__customize-select .dropdown-menu li a').on('click', function(e) {
        e.preventDefault();
        var liValue = $('.hiddenVal').val();
        var disInputValue = $('.hiddenDiscountVal').val();
		var liText = $(this).text();
        var liTextVal = liText[0];
        var priceVal = liValue * liTextVal;
        var disValue = disInputValue * liTextVal;
        $('.pricing__customize-select .dropdown button:first-child').html(liText);
		$('span.pricingYear').html(liText+ " ");
		$('span.pricingValue').html(priceVal);
		$('span.discountVal').html(disValue);
    });

});