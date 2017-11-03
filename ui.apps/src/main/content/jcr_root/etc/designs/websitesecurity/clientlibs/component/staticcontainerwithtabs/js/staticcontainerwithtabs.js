//Place this code in the footer below the jquery library include and the matchHeight library include
$(document).ready(function() {

    $.fn.responsiveTabs = function() {

        this.addClass('responsive-tabs');
        this.append($('<span class="glyphicon glyphicon-chevron-down"></span>'));
        this.append($('<span class="glyphicon glyphicon-chevron-up"></span>'));

        var liClick = this.find('li')
        liClick.on('click', function(e)  {
            e.preventDefault();
            $('.staticcontainerwithtabs .tab-pane').removeClass('active');
            liClick.removeClass('active');
			$(this).addClass('active');
            var linkHref = $(this).find('a').attr('href');
            $('div.tab-pane'+linkHref).addClass('active');

        });

        this.on('click', 'li.active > a, span.glyphicon', function() {
          this.toggleClass('open');
        }.bind(this));

        this.on('click', 'li:not(.active) > a', function() {
          this.removeClass('open');
        }.bind(this));
    }

    $('.nav.nav-tabs').responsiveTabs();

    $('.matchHeight').matchHeight();
    $('.matchHeight_head').matchHeight();

});