/* global $:false, args:false */

window.linkData = new Array();
var gsaSearch = {
    count:0,
    filter: [],
    startPage: 1,
    start: 0,
    sort: "date:D:L:d1",
    history: undefined,
    init: function() {
       /* var gsaSearchCss = $(document.createElement('link')).attr({
            'rel': 'stylesheet',
            'href': '/etc/designs/websitesecurity/clientlibs/component/gsasearch/css/gsaSearch.css',
            'type': 'text/css'
        });
        $('head').append(gsaSearchCss);*/

        $("#clearsearch").click(function() {
            $('input[name="q"]').val("");
            $('input[name="q"]').focus();
        });

        $("#btn-submit").click(function() {
        	if($('input[name="q"]').val() != ""){
	        	gsaSearch.sort = "date:D:L:d1";
                if($(window).width() > 992) {
                    if($('input[name="q"]').val() === $('input[name="hiddenq"]').val()){
	            		gsaSearch.performSearch(false, {}, 0);
                    } else {
						gsaSearch.performSearch(true, {}, 0);
                    }
                } else {
					gsaSearch.performSearch(false, {}, 0);
                }
        	}
        });

        $('#q').keypress(function(e) {
            if (e.which === 13) {
                e.preventDefault();
                $("#btn-submit").click();
            }
        });

        $("#s-r-how-many").hide();
        $("#no-results").hide();
        $('.s-r-clear').click(function() {
        	if($('input.filterbox').is(':checked')){
	            $(".filterbox").attr('checked', false);
	            gsaSearch.performSearch(true, {}, 0);
        	}
        });

        $('.s-r-sort-actions a').click(function() {
            $('.s-r-sort-actions a').removeClass("current");
            if ($(this).attr('id') === 'byRelevance') {
                gsaSearch.sort = "date:D:L:d1";
                $('.s-r-sort-actions a#byRelevance').addClass('current');
            } else if ($(this).attr('id') === 'byDate') {
                gsaSearch.sort = "date:D:S:d1";
                $('.s-r-sort-actions a#byDate').addClass('current');
            }
            gsaSearch.performSearch(false, {}, 0);
        });
    },
    enableHistory: function() {

        gsaSearch.history = window.History;
				//alert("inside his enable "+gsaSearch.history.enabled);
        if (!gsaSearch.history.enabled) {
            return false;
        }
        gsaSearch.history.Adapter.bind(window, 'statechange', function() {
            var q = gsaSearch.getParameterFromUrl("q");
            if(q != undefined && q != ""){
            	$('input[name="q"]').val(decodeURIComponent(q));
            	$('input[name="hiddenq"]').val(decodeURIComponent(q));
            }
            var gsaSearchJson = decodeURIComponent(gsaSearch.getParameterFromUrl("gsaSearchJson"));
            var gsaSearchObj = JSON.parse(gsaSearchJson);
            gsaSearch.filter = gsaSearchObj.filter;
            gsaSearch.startPage = gsaSearchObj.startPage;
            gsaSearch.start = gsaSearchObj.start;
            gsaSearch.sort = gsaSearchObj.sort;
            for (var facetIndex = 0; facetIndex < gsaSearch.filter.length; facetIndex++) {
                var facet = gsaSearch.filter[facetIndex];
                if ($.inArray(":", facet) > -1) {
                    var facetArray = facet.split(':');
                    var facetElement = $("ul[data-nm='" + facetArray[0] + "']" + " #" + facetArray[1]);
                    if (facetElement.length) {
                        var encodedFacetDataName = encodeURIComponent(facetElement.attr('data-nm')).replace(/%20/g, '%2520').replace(/-/g, '%252D').replace(/\./g, '%252E').replace(/%7E/g, "%257E");
                        var encodedFacetName = encodeURIComponent(facetElement.attr('name')).replace(/%20/g, '%2520').replace(/-/g, '%252D').replace(/\./g, '%252E').replace(/%7E/g, "%257E");
                        q = q + "+inmeta:" + encodedFacetDataName + "%3D" + encodedFacetName;
                    }
                }

            }
            $('.s-r-sort-actions a').removeClass("current");
            if (gsaSearch.sort === 'date:D:L:d1') {
                $('.s-r-sort-actions a#byRelevance').addClass('current');
            } else if (gsaSearch.sort === 'date:D:S:d1') {
                $('.s-r-sort-actions a#byDate').addClass('current');
            }
            var isPartner = 'N';
                if (typeof symcDataLayer !== 'undefined' && typeof symcDataLayer.om !=='undefined' && typeof symcDataLayer.om.user_type !== 'undefined'){
                    if(symcDataLayer.om.user_type == 'partner'){
                        isPartner = 'Y';
                    }
            }

            var data = {
                "q": q,
                "sort": gsaSearch.sort,
                "ulang": args.gsaSearch.pageLanguage,
                "siteOverride": args.gsaSearch.siteOverride,
                "clientOverride": args.gsaSearch.clientOverride,
                "isPartner": isPartner
            };

            $("#search-results-list .list-item").remove();
            $('.search-fliter #list-drop-menu .sub-items').remove();

            $.ajax({
                url: '/bin/websitesecurity/globalsearch',
                method: "POST",
                data: data,
                async: false,
                success: function(data) {
					//alert("Calling: "+data);
					//console.log(data);
                    var resultJson = $.xml2json(data, false);
                    gsaSearch.displayResult(resultJson, gsaSearch.start);
                    gsaSearch.displayFilters(resultJson);

                    if($('.list-drop-menu.list-for-gsasearch li.sub-items input[type="checkbox"]').is(':checked') ) {
                        var html = "<span class='selectTick'></span>";
                        $('.sub-items input[checked="checked"]').parent().prepend(html);
                        $('.sub-items input[checked="checked"]').parent().css('color', '#15ccff');
                    } else {
                        $('.sub-items span.selectTick').remove();
                        $('.sub-items .cat-label').css('color', '#333');
                    }
                    sideLink = $('.list-for-gsasearch .sub-items').find('.selectTick').parent();
                    linkData = [];
                    $.each(sideLink, function(i, data){
                        var labelVal = $(this).contents().text();
                        linkData.push(labelVal);
                    });
                    gsaSearch.displayResult1(resultJson, gsaSearch.start);
                }
            });
        });

    },
    autoComplete: function() {
		//alert("inside auto complete");
		var autoSuggestPathGSA = args.gsaSearch.autoSuggestPath;
        $.ajax({
            url: '/bin/websitesecurity/prodcutazsearch',
            method: 'POST',
            data: {
                'type': 'tagSuggestion',
                'searchPath': autoSuggestPathGSA
            },
            dataType: 'json',
            success: function(result) {
                if(result.length > 0) {
					 $('#q').autocomplete({
                        lookup: result,
                        minChars: 1,
                        onSelect: function(event, ui){ $("#btn-submit").click();}
                    });
                } else {
                      $.getJSON('/content/dam/websitesecurity/gsaautosuggest/allProducts.json', function(data) {
						 $('#q').autocomplete({
                            lookup: data,
                            minChars: 1,
                            onSelect: function(event, ui){ $("#btn-submit").click();}
                        });
                    });
            	}
				//alert("result is: "+result);
            }
        });
    },
    performSearch: function(clearFilters, filter, start) {
		//alert("inside performSearch ");
        //$("#search-results-list-pagination").hide();
        gsaSearch.start = start;
        if (start === 0) {
            gsaSearch.startPage = 1;
        }
        if (clearFilters) {
            gsaSearch.clearFilters();
            filter = undefined;
            gsaSearch.filter = [];
        } else {
            if (filter !== undefined && filter.filterName !== undefined && filter.categoryName !== undefined) {
                gsaSearch.filter.push(gsaSearch.slugify(filter.categoryName) + ":" + gsaSearch.slugify(filter.filterName));
            }

        }
        gsaSearch.addUrlParameter();

    },
    displayResult: function(resultJson, start) {
		//alert("inside display result");
        $("#s-r-how-many").empty();
        $('.s-r-sort-actions').show();
        $('.s-r-sort').show();
        $('#search-results-list').empty();
        $("#search-results-list-pagination").hide();
        var resultContainer = $("#search-results-list");
        var howmanyContainer = $("#s-r-how-many");
        var promotionsContainer = $('#search-promotions-list');
        promotionsContainer.hide();
        promotionsContainer.empty();
        CQ.I18n.setLocale(args.gsaSearch.pageLocale);
        var resultsFor = CQ.I18n.getMessage(" Search Results for");
        var resultFor = CQ.I18n.getMessage("Search Result for");
        if (resultJson !== undefined && resultJson.RES !== undefined && resultJson.RES.R !== undefined) {
            $("#s-r-how-many").show();
            var results = resultJson.RES;
            if (results.R !== undefined) { //Has result(s).
                if ($.isArray(results.R)) { //Has more than one result.
                    if (results.R.length < start) {
                        start = 0;
                    }
                    for (var resultIndex = start; resultIndex < (parseInt(args.gsaSearch.resultsPerPage) + start); resultIndex++) {
                        var result = results.R[resultIndex];
                        if (resultIndex == 0 && resultJson.GM !== undefined) {
                        	var promotions = resultJson.GM;
                        	promotionsContainer.show();
                        	if(promotions.length == undefined && promotions.GD != undefined) {
                        		promotionsContainer.append(gsaSearch.generateSearchPromotions(promotions));
                        	} else {
	                        	for(var promotionIndex = 0; promotionIndex < promotions.length; promotionIndex ++) {
	                        		var promotion = promotions[promotionIndex];
	                        		promotionsContainer.append(gsaSearch.generateSearchPromotions(promotion));
	                        	}
                        	}
                        }
                        if (result !== undefined) {
                            resultContainer.append(gsaSearch.generateSearchResultItem(result));
                        }
                    }
                    if (gsaSearch.startPage > 1) {
                        var qterm = $('input[name="q"]').val();
                        howmanyContainer.append("Page " + gsaSearch.startPage + " of " + results.R.length + " " + resultsFor + " ", $(document.createElement('span')).text("\"" + qterm + "\""));

                    } else {
                        howmanyContainer.append(results.R.length + " " + resultsFor + " ", $(document.createElement('span')).text("\"" + $('input[name="q"]').val() + "\"" ));

                    }
                    gsaSearch.initPagination(results.R.length);
                } else { //Has one result.
                    if (resultJson.GM !== undefined) {
                    	var promotions = resultJson.GM;
                    	promotionsContainer.show();
                    	if(promotions.length == undefined && promotions.GD != undefined) {
                    		promotionsContainer.append(gsaSearch.generateSearchPromotions(promotions));
                    	} else {
                        	for(var promotionIndex = 0; promotionIndex < promotions.length; promotionIndex ++) {
                        		var promotion = promotions[promotionIndex];
                        		promotionsContainer.append(gsaSearch.generateSearchPromotions(promotion));
                        	}
                    	}
                    }
                    resultContainer.append(gsaSearch.generateSearchResultItem(results.R));
                    howmanyContainer.text(resultFor + "\"" + $('input[name="q"]').val() + "\"");

                }
                $("#search-results-list").show();

            }
        } else {
            $('.s-r-sort').hide();
            resultContainer.append(args.gsaSearch.noResultsSuggestion);
            howmanyContainer.text("No results for \"" + $('input[name="q"]').val() + "\"");
            $('#search-results-list').show();

        }
    },
    displayResult1: function(resultJson, start) {
        $("#s-r-how-many").empty();
        $('.s-r-sort-actions').show();
        $('.s-r-sort').show();
        $('#search-results-list').empty();
        $("#search-results-list-pagination").hide();
        var resultContainer = $("#search-results-list");
        var howmanyContainer = $("#s-r-how-many");
        var promotionsContainer = $('#search-promotions-list');
        promotionsContainer.hide();
        promotionsContainer.empty();
        CQ.I18n.setLocale(args.gsaSearch.pageLocale);
        var resultsFor = CQ.I18n.getMessage(" Search Results for");
        var resultFor = CQ.I18n.getMessage("Search Result for");
        if (resultJson !== undefined && resultJson.RES !== undefined && resultJson.RES.R !== undefined) {
            $("#s-r-how-many").show();
            var results = resultJson.RES;
            if (results.R !== undefined) { //Has result(s).
                var lData = "";
                $.each(linkData, function(i, data) {
					lData += " and " + '"' + data + '"';
                });


                if ($.isArray(results.R)) { //Has more than one result.
                    if (results.R.length < start) {
                        start = 0;
                    }
                    for (var resultIndex = start; resultIndex < (parseInt(args.gsaSearch.resultsPerPage) + start); resultIndex++) {
                        var result = results.R[resultIndex];
                        if (resultIndex == 0 && resultJson.GM !== undefined) {
                        	var promotions = resultJson.GM;
                        	promotionsContainer.show();
                        	if(promotions.length == undefined && promotions.GD != undefined) {
                        		promotionsContainer.append(gsaSearch.generateSearchPromotions(promotions));
                        	} else {
	                        	for(var promotionIndex = 0; promotionIndex < promotions.length; promotionIndex ++) {
	                        		var promotion = promotions[promotionIndex];
	                        		promotionsContainer.append(gsaSearch.generateSearchPromotions(promotion));
	                        	}
                        	}
                        }
                        if (result !== undefined) {
                            resultContainer.append(gsaSearch.generateSearchResultItem(result));
                        }
                    }

                    if (gsaSearch.startPage > 1) {
                        var qterm = $('input[name="q"]').val();
                        howmanyContainer.append("Page " + gsaSearch.startPage + " of " + results.R.length + " " + resultsFor + " ", $(document.createElement('span')).text("\"" + qterm + "\"") );

                    } else {
                        if(lData) {
                        	howmanyContainer.append(results.R.length + " " + resultsFor + " ", $(document.createElement('span')).text("\"" + $('input[name="q"]').val() + "\""), $(document.createElement('span')).text(lData));
                        } else {
							howmanyContainer.append(results.R.length + " " + resultsFor + " ", $(document.createElement('span')).text("\"" + $('input[name="q"]').val() + "\""));
                        }
                    }
                    gsaSearch.initPagination(results.R.length);
                } else { //Has one result.
                    if (resultJson.GM !== undefined) {
                    	var promotions = resultJson.GM;
                    	promotionsContainer.show();
                    	if(promotions.length == undefined && promotions.GD != undefined) {
                    		promotionsContainer.append(gsaSearch.generateSearchPromotions(promotions));
                    	} else {
                        	for(var promotionIndex = 0; promotionIndex < promotions.length; promotionIndex ++) {
                        		var promotion = promotions[promotionIndex];
                        		promotionsContainer.append(gsaSearch.generateSearchPromotions(promotion));
                        	}
                    	}
                    }
                    resultContainer.append(gsaSearch.generateSearchResultItem(results.R));
                    howmanyContainer.text(resultFor + "\"" + $('input[name="q"]').val() + "\"");
                    howmanyContainer.append( $(document.createElement('span')).text(lData));

                }
                $("#search-results-list").show();

            }
        } else {
            $('.s-r-sort').hide();
            resultContainer.append(args.gsaSearch.noResultsSuggestion);
            howmanyContainer.text("No results for \"" + $('input[name="q"]').val() + "\"");
            $('#search-results-list').show();

        }
    },
    displayFilters: function(resultJson) {
        var categoryContainer = $("#list-drop-menu");
        if (resultJson !== undefined && resultJson.RES !== undefined && resultJson.RES.PARM !== undefined) {
            var filters = resultJson.RES.PARM;
            if (filters.PMT !== undefined) {
                //$('.desktop-only, .list-drop-menu').attr('style', 'display: inline-block !important')
                //$('.list-drop-menu').attr('style', 'display: block');
                var category = filters.PMT;
                if (Object.prototype.toString.call(category) === '[object Object]') {
                    var categoryObject = JSON.parse(JSON.stringify(category));
                    category = [];
                    category[0] = categoryObject;
                }
                for (var categoryIndex = 0; categoryIndex < category.length; categoryIndex++) {
                    var slug = gsaSearch.slugify(category[categoryIndex].DN);
                    categoryContainer.append(gsaSearch.generateCategorySection(category[categoryIndex], slug));
                    var filterContainer = $("#cat_" + slug);
                    var categoryName = category[categoryIndex].NM;
                    if (category[categoryIndex].PV !== undefined) {
                        if ($.isArray(category[categoryIndex].PV)) {
                            for (var filterIndex = 0; filterIndex < category[categoryIndex].PV.length; filterIndex++) {
                                var filter = category[categoryIndex].PV[filterIndex];
                                filterContainer.append(gsaSearch.generateFilterSection(filter, categoryName));
                            }
                        } else {
                            filterContainer.append(gsaSearch.generateFilterSection(category[categoryIndex].PV, categoryName));
                        }
                    }
                }
            } else {
                $('.desktop-only, .list-drop-menu').attr('style', 'display: none !important');
            }
        } else {
            $('.desktop-only, .list-drop-menu').attr('style', 'display: none !important');
        }
        if (gsaSearch.filter.length) {
            var categoryArray = [];
            for (var facetIndex = 0; facetIndex < gsaSearch.filter.length; facetIndex++) {
                var facet = gsaSearch.filter[facetIndex];
                if ($.inArray(":", facet) > -1) {
                    var facetArray = facet.split(':');
                    $("ul[data-nm='" + facetArray[0] + "']" + " #" + facetArray[1]).attr({
                        'checked': 'checked'
                    });
                    if ($.inArray(facetArray[0], categoryArray) === -1) {
                        categoryArray.push(facetArray[0]);
                    }
                }
            }
            for (var facetCategoryIndex = 0; facetCategoryIndex < categoryArray.length; facetCategoryIndex++) {
                var facetCategory = categoryArray[facetCategoryIndex];
                var facetUnorderedList = $("ul[data-nm='" + facetCategory + "']");
                facetUnorderedList.parent().toggleClass('active');
                facetUnorderedList.parent().parent().addClass('active');
            }
        }
    },
    generateSearchResultItem: function(result) {
        var searchResultItem = $(document.createElement('div')).addClass('s-r-result list-item');
        var searchResultContent = $(document.createElement('div')).addClass('s-r-content');
        var resultUrl = result.U;
        var resultTitle = result.T;
        var resultDescription = result.S;
        var searchResultHeader = $(document.createElement('h5')).append($(document.createElement('a')).html(resultTitle).attr({
            'href': resultUrl.toLowerCase()
        }));
		var displayResultURLSwitch = args.gsaSearch.displayResultURL;
		var searchResultDisplayUrl ="";
		//Control the visibilty of search URL based upon dialog value
		if (displayResultURLSwitch =="yes"){
			searchResultDisplayUrl = $(document.createElement('p')).addClass('s-r-url').css({
				'word-wrap': 'break-word'
			}).html(resultUrl);
		}
		
        var searchResultDescription = $(document.createElement('p')).addClass('description').html(resultDescription);
        searchResultContent.append(searchResultHeader, searchResultDisplayUrl, searchResultDescription);
        searchResultItem.append(searchResultContent);
        searchResultItem.click(function() {
            window.open(resultUrl.toLowerCase(), '_self');
        });
        return searchResultItem;
    },
    generateSearchPromotions: function(promotion) {
    	var GD = promotion.GD || "";
		var title = GD.split("~~~")[0];
        var description = GD.split("~~~")[1];
        var url = promotion.GL || "";
        var promotionItem = $(document.createElement('div')).addClass('search-ad');
        var promotionTitle = $(document.createElement('h4')).append($(document.createElement('a')).html(title).attr({
            'href': url.toLowerCase()
        }));
        var promotionDescription = $(document.createElement('p')).html(description);
        promotionItem.append(promotionTitle, promotionDescription);
        promotionItem.click(function() {
            window.open(url.toLowerCase(), '_self');
        });
        return promotionItem;
    },
    generateCategorySection: function(category, slug) {
        var subItems = $(document.createElement('li')).addClass('sub-items');
        var categoryTitle = category.DN;
        CQ.I18n.setLocale(args.gsaSearch.pageLocale);
        var translatedCategoryTitle = CQ.I18n.getMessage("meta" + categoryTitle.replace(/\s/g, ''));
        if (translatedCategoryTitle.indexOf("meta") == 0) {
            translatedCategoryTitle = categoryTitle;
        }
        var categoryName = category.NM;
        var subItemAnchorTag = $(document.createElement('a')).attr({
            'href': 'javascript:void(0);'
        }).html(translatedCategoryTitle);
        var subItemUnorderedList = $(document.createElement('ul')).attr({
            'id': 'cat_' + slug,
            'data-nm': gsaSearch.slugify(categoryName)
        });
        subItems.append(subItemAnchorTag, subItemUnorderedList);
        return subItems;
    },
    generateFilterSection: function(filter, categoryName) {
        var filterValue = filter.V;
        var filterItem = $(document.createElement('li'));
        var filterLabel = $(document.createElement('label')).addClass('cat-label').append(filterValue);
        var filterCheckBox = $(document.createElement('input')).addClass('filterbox').attr({
            'type': 'checkbox',
            'name': filterValue,
            'id': gsaSearch.slugify(filterValue),
            'data-nm': categoryName
        }).bind('click', function() {
            var filterName = $(this).attr('name');
            var categoryName = $(this).attr('data-nm');
            var filter = {
                filterName: filterName,
                categoryName: categoryName
            };
            if ($(this).is(':checked')) {
                gsaSearch.performSearch(false, filter, 0);
            } else {
                var arrayPosition = $.inArray(gsaSearch.slugify(categoryName) + ":" + gsaSearch.slugify(filterName), gsaSearch.filter);
                if (arrayPosition > -1) {
                    gsaSearch.filter.splice(arrayPosition, 1);
                }
                var name = $(this).attr('name');
                gsaSearch.performSearch(false, {}, 0);
                var parentFilter = $('input[name="'+ name +'"]').parents('li.sub-items:eq(0)');
                if(!parentFilter.hasClass('active')) {
                	parentFilter.find('a:eq(0)').click();
                }
            }

        });
        filterLabel.append(filterCheckBox, $(document.createElement('span')));
        filterItem.append(filterLabel);
        return filterItem;
    },
    clearFilters: function() {
        $("#list-drop-menu .sub-items").remove();
    },
    initPagination: function(totalResults) {
        var pageInit = true;
		var previousLinkText = args.gsaSearch.previousLinkText;
		var nextLinkText = args.gsaSearch.nextLinkText;
		//check previous and next fallback
		if(previousLinkText ==""){
			previousLinkText = '<i class="fa fa-caret-left"></i>';
		}
		if(nextLinkText ==""){
			nextLinkText = '<i class="fa fa-caret-right"></i>';
		}
        $("#search-results-list-pagination").empty().show();
        $("#search-results-list-pagination").removeData("twbs-pagination");
        $("#search-results-list-pagination").unbind("page");
        $('#search-results-list-pagination').twbsPagination({
            totalPages: Math.ceil(parseFloat(totalResults) / parseFloat(args.gsaSearch.resultsPerPage)),
            visiblePages: 10,
            startPage: gsaSearch.startPage,
            prev: previousLinkText,
            next: nextLinkText,
            onPageClick: function(event, page) {
                var start = ((page - 1) * 10);
                if (pageInit) {
                    pageInit = false;
                    return;
                } else {
                    gsaSearch.startPage = page;
                    gsaSearch.performSearch(false, {}, start);
                }
                window.scrollTo(0, 0);
            }
        });
    },
    slugify: function(text) {
        return text.toString().toLowerCase().replace(/\s+/g, '-').replace(/,/g, '-').replace(/[^\w\-]+/g, '').replace(/\-\-+/g, '-').replace(/^-+/, '').replace(/-+$/, '');
    },
    addUrlParameter: function() {
        var stateUrl = location.pathname.substring(location.pathname.lastIndexOf("/") + 1);
        if (document.location.search !== undefined) {
            stateUrl += document.location.search;
            stateUrl = gsaSearch.removeParameterFromUrl(stateUrl, "gsaSearchJson");
            stateUrl = gsaSearch.removeParameterFromUrl(stateUrl, "q");
            stateUrl = stateUrl.replace(/[\?]+$/g, "");
        }
		//alert("stateUrl "+stateUrl);
        var parameterPrefix = (stateUrl.indexOf('?') === -1) ? '?' : '&';
        var gsaSearchObj = {
            filter: gsaSearch.filter,
            startPage: gsaSearch.startPage,
            start: gsaSearch.start,
            sort: gsaSearch.sort,
        };
        var gsaSearchJson = JSON.stringify(gsaSearchObj);
        if (gsaSearch.history.enabled && gsaSearch.count == 0) {       
            if($('input[name="q"]').val() == "") {
            	$('input[name="q"]').val($('input[name="hiddenq"]').val());
            }
            var qterm = escape($('input[name="q"]').val());
            gsaSearch.count += 1;
            if(sessionStorage.getItem('term')){
                var aTerm = sessionStorage.getItem('term');
				gsaSearch.history.replaceState(null, document.title, stateUrl + parameterPrefix + "q=" + aTerm + "&gsaSearchJson=" + gsaSearchJson);
            } else {
            	gsaSearch.history.replaceState(null, document.title, stateUrl + parameterPrefix + "q=" + qterm + "&gsaSearchJson=" + gsaSearchJson);
            }
        }else if (gsaSearch.history.enabled && gsaSearch.count != 0) {
            if($('input[name="q"]').val() == "") {
            	$('input[name="q"]').val($('input[name="hiddenq"]').val());
            }
            var qterm = escape($('input[name="q"]').val());
            gsaSearch.count += 1;
			if(sessionStorage.getItem('term')){
                var aTerm = sessionStorage.getItem('term');
				gsaSearch.history.pushState(null, document.title, stateUrl + parameterPrefix + "q=" + aTerm + "&gsaSearchJson=" + gsaSearchJson);
            } else {
            	gsaSearch.history.pushState(null, document.title, stateUrl + parameterPrefix + "q=" + qterm + "&gsaSearchJson=" + gsaSearchJson);
            }
        }
        sessionStorage.clear();
    },
    removeParameterFromUrl: function(url, parameter) {
        //prefer to use l.search if you have a location/link object
        var urlparts = url.split('?');
        if (urlparts.length >= 2) {

            var prefix = encodeURIComponent(parameter) + '=';
            var pars = urlparts[1].split(/[&;]/g);

            //reverse iteration as may be destructive
            for (var i = pars.length; i-- > 0;) {
                //idiom for string.startsWith
                if (pars[i].lastIndexOf(prefix, 0) !== -1) {
                    pars.splice(i, 1);
                }
            }

            url = urlparts[0] + '?' + pars.join('&');
            return url;
        } else {
            return url;
        }
    },
    getParameterFromUrl: function(param) {
        var vars = {};
        window.location.href.replace(/[?&]+([^=&]+)=?([^&]*)?/gi, // regexp
            function(m, key, value) { // callback
                vars[key] = value !== undefined ? value : '';
            });

        if (param) {
            return vars[param] ? vars[param] : null;
        }
        return vars;
    }
};
$(function() {
    gsaSearch.init();
    gsaSearch.enableHistory();
    gsaSearch.autoComplete();
    if (document.location.search.indexOf("gsaSearchJson") !== -1) {
        var gsaSearchJson = decodeURIComponent(gsaSearch.getParameterFromUrl("gsaSearchJson"));
        var gsaSearchObj = JSON.parse(gsaSearchJson);
        var clearFilters = true;
        if (gsaSearchObj.filter.length > 0) {
            clearFilters = false;
        }


        gsaSearch.startPage = gsaSearchObj.startPage;
        gsaSearch.start = gsaSearchObj.start;
        gsaSearch.sort = gsaSearchObj.sort;
        if (!clearFilters) {
            gsaSearch.performSearch(true, {}, gsaSearch.start);
            gsaSearch.filter = gsaSearchObj.filter;
            gsaSearch.performSearch(false, {}, gsaSearch.start);
        } else {
            gsaSearch.performSearch(clearFilters, {}, gsaSearch.start);
        }
    } else {
        gsaSearch.performSearch(true, {}, 0);
    }
    $('html').on('click', '.list-drop-menu.list-for-gsasearch li a', function(e){
    	if($(this).attr("href").indexOf("javascript:void(0)") >= 0){
     	    $(this).parent().addClass('current');
     	    $(this).parent().siblings().removeClass('current');
     	    $(this).parent().parent().toggleClass('active');
        }
       });
    $('html').on('click', '.list-drop-menu.list-for-gsasearch li.sub-items>a', function(e){
    	if($(this).attr("href").indexOf("javascript:void(0)") >= 0){
    	    $(this).parent().parent().addClass('active');
    	    $(this).parent().toggleClass('active');
    	    e.preventDefault();
    	}
    });

    $('.search-fliter h4').on('click', function(e){
		$('.list-for-gsasearch').animate({width: 'show'},500);

    });
    
    $('.search-fliter .close-btn').on('click', function(e){
		$('.list-for-gsasearch').animate({width: 'hide'},500);
    });

    if($(window).width() > 991) {
		$('.list-for-gsasearch').css('display', 'block!important');
    }

    $( "#list-drop-menu" ).on('mouseover', function() {
    	$('.list-drop-menu.list-for-gsasearch').css('overflow', 'auto');
    });
    $( "#list-drop-menu" ).on('mouseout', function() {
    	$('.list-drop-menu.list-for-gsasearch').css('overflow', 'hidden');
    });

	if($(window).width() <= 991) {
    $('.pagination').on('click', function() {
        if($('.list-drop-menu').is(':visible')) {
			$('.search-fliter .close-btn').click();
        }
    });
	}
});