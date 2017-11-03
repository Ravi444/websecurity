var headerSearch  = {
    autoComplete: function() {
		var autoSuggestPath = $('#hdnAutoSuggestPath').val();
        $.ajax({
            url: '/bin/websitesecurity/prodcutazsearch',
            method: 'POST',
            data: {
                'type': 'tagSuggestion',
                'searchPath': autoSuggestPath
            },
            dataType: 'json',
            success: function(result) {
                if(result.length > 0) {
					productData(result);
                }else {

                      $.getJSON('/content/dam/websitesecurity/gsaautosuggest/allProducts.json', function(data) {
						productData(data);
                    });
            	}
            }
        });
    },
}

function productData(data) {
    $('.c_search-top input.suggest').autocomplete({
        lookup: data,
        containerClass: "ui-autocomplete",
        minChars: 1,
        onSelect: function(event, ui){ 
            changeUrl(event);
        }
    });
    
    $('input.mobileSuggest').autocomplete({
        lookup: data,
        containerClass: "mobileUi-autocomplete",
        minChars: 1,
        onSelect: function(event, ui){ 
            changeUrl(event);
        }
    });
    $('input.suggest').keypress(function(e) {
        if (e.which === 13) {
            e.preventDefault();
            var inputVal = $('input.suggest').val();
            changeUrl(inputVal);
        }
    });
    $('button#btn-header-submit').click(function(e) {
        e.preventDefault();
        var inputVal = $('input.mobileSuggest').val();
        changeUrl(inputVal);
    });
}

function changeUrl(event) {
    if(event.value) {
    	var aTerm = event.value;
    } else {
		var aTerm = event;
    }
    var url = window.location.toString();
    var locationVal = window.location.pathname;
    var hiddenValSearch = $('#hdnSearchPath').val();
    var locationHref = location.href; 
	var addedString = locationHref.substring(locationHref.indexOf("?")+1);

    if(window.location.href.indexOf(hiddenValSearch) > -1) {
        window.location = url.replace(window.location.pathname + window.location.search, hiddenValSearch+".html?q="+ aTerm );
    } else {
        if(locationHref.indexOf('?') > -1) {
            var newUrl = hiddenValSearch+".html?"+addedString +"&q="+ aTerm;
			sessionStorage.clear();
            sessionStorage.setItem('term', aTerm);
            window.location.href = url.replace(window.location.pathname + window.location.search, newUrl);
        } else {
			var newUrl = hiddenValSearch+".html?q="+ aTerm;
            window.history.pushState('html', null, newUrl);
            window.location.reload();
        }
    }
}

$(document).ready(function() {
    $('#suggest, #mobileSuggest').on('click', function() {
        headerSearch.autoComplete();
    });
});