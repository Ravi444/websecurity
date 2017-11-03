$(window).on('load', function() {
    getInfo();
});



function getInfo() {

    var urlName = $('.urlName').val();
    var countryName = $('.countryName').val();
    var brandName = $('.brandName').val();
    var interName = $('.interName').val();
    var alertText = $('.alertName').val();
    var buttonUrl = $('.btnName').val();
    var productType = $('.productName').val();
    var localeName = $('.localName').val();
    var failureText = $('.failureName').val();
    var amount = $('.amountName').val();
    var timeoutTime = $('.timeName').val();
    var timeout = timeoutTime*1000;
    var productName = $('.productName').val();

    var liYear  = $('.hiddenYear').val();
    var liServer = $('.hiddenServer').val();
    var liSans = $('.hiddenSans').val();
    var url = urlName+"?country="+countryName+"&brand="+brandName+"&productType=&validityYears="+ liYear +"&sanCount="+ liSans +"&numServers="+ liServer +"&isInternational="+interName;
    var btnurl = buttonUrl+"?productType="+productType+"&application_locale="+localeName+"&validityYears="+ liYear +"&sanCount="+ liSans +"&numServers="+ liServer;

    var xhr = new XMLHttpRequest();
    xhr.open("GET",url, true);
    xhr.timeout = timeout;
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
            // JSON.parse does not evaluate the attacker's scripts.
            var resp = JSON.parse(xhr.responseText);
            //alert("Result "+JSON.stringify(resp.input.country));
            if(resp.responseStatus.status == "SUCCESS") {
                $('.price_comparison_container').show();
                $.each(resp.data.productPricing, function(res) {
                    if(res == productName) {
                        if(resp.data.productPricing[res][liYear].price){
                            $('.price_size').html("$"+resp.data.productPricing[res][liYear].price);
                            if(resp.data.productPricing[res][liYear].price == "NA"){
                                $('.price_size').html(failureText);
                            }
                        } else {
                            $('.price_size').html(amount);
                        }
                    } else {
                        var resClass = '.'+ res;
                        if(resp.data.productPricing[res][liYear].price){
                            $(resClass).html("$"+resp.data.productPricing[res][liYear].price);
                            if(resp.data.productPricing[res][liYear].price == "NA"){
                                $(resClass).html(failureText);
                            }
                        } else {
                            $(resClass).html(failureText);
                        }
                    }
                });  
            } else {
                var msg = resp.responseStatus.messages;
                swal({ title: msg});

                 blankData();
                //$('.price_comparison_container').hide();
            }
            $('.buy_button').attr('href', btnurl);
        }
    },
    xhr.ontimeout = function (e) {

        swal({ title: alertText});
        blankData();
    },
    xhr.send();
}

function blankData() {
    var failureText = $('.failureName').val();
    var amount = $('.amountName').val();
    $('.price_size').html(amount);
    $('.price-comp-3').html(failureText);
    $('.price-comp-4').html(failureText);
    $('.price-comp-1').html(failureText);
    $('.price-comp-2').html(failureText);
}

/************************************************************
*** Label control through dropdown values of Stats STARTS ***
*************************************************************/
$(document).ready(function(){
    $(".c-year").html("1 ");
    $(".c-server").html("1 ");
    $(".c-sans").html("0 ");

    $('.c-statscomponent_server_data').bind("keyup focusout" ,function() {
        if($('.c-statscomponent_server_data')[0].checkValidity()) {
            var serverData = $(this).val();
            $('.hiddenServer').val(serverData);
            $('.c-server').html(serverData + " ");

        } else {
            swal({ title: "Please enter only numbers and upto 3 digits, starting from 1"});
            $('.hiddenServer').val('1');
            $('.c-server').html("1 ");
			$('.c-statscomponent_server_data').val('1');
        }
        getInfo();
    });
});

function printLabelTextYear(ele){ 
    var labelYear = $(ele).val();
    var spanYear = $(ele).html();
    $(".c-year").html(labelYear + " ");
    $('.hiddenYear').val(labelYear);
    $('#yearSelect span[data-bind="label"]').html(spanYear);
};

function printLabelTextSans(san){ 
    var labelSans = $(san).val();
    var spanSans = $(san).html();
    $(".c-sans").html(labelSans + " ");
    $('.hiddenSans').val(labelSans);
    $('#sanSelect span[data-bind="label"]').html(spanSans);
};

/************************************************************
*** Label control through dropdown values of Stats ENDS ***
*************************************************************/

$( document.body ).on( 'click', '.dropdown-menu li', function( event ) {
    var $dynamicValue = $( event.currentTarget );
    $dynamicValue.closest( '.btn-group' ).find( '[data-bind="label"]' ).text( $dynamicValue.text() ).end().children( '.dropdown-toggle' ).dropdown( 'toggle' );
    return false;
});
