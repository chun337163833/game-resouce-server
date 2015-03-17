function loadTmBox(self) {
	if ($(self).is('a')) {
		var url = $(self).attr('href');
		
		if (url.split('?').length == 1) {
			url += "?ajax=1";
		} else if (url.indexOf("ajax=1") == 0) {
			url += "&ajax=1";
		}
		
		$(self).attr('href', url);
	}
	$(self).addClass('fancybox.ajax');

	var opts = {
		beforeShow : function(data){
		}, 
		tpl : { error:'<p class="fancybox-error"></p>'},
		padding: 20
	};
	
	if ($(self).attr('data-minWidth') > 0) {
		opts.minWidth = $(self).attr('data-minWidth');
	}
	
	$(self).fancybox(opts);
}

$(document).ready(function() {  
	// Rozbaleni/Zabaleni formularu/boxu
	$('.form-expand').widget_FormExpand();
		
	// Datepicker
	$('.inpDate-fix input[type=text]').datepicker();
	
	/* AJAX */
	$('.tmImgBox').fancybox({
		type:'image', 
		padding: 20,
		prevEffect: 'fade',
		nextEffect: 'fade'
	});
	
	// Zobrazeni obsahu do samostatneho vieweru Fancybox - obrazky, formulare	
	$('.tmBox').each(function(){
		loadTmBox(this);
	});
});

//Zpracovani AJAX formularu zobrazenych ve vieweru Fancybox
$(document).delegate('.tmAjaxBox', 'click', function(){
		function showLoader() {
			$.fancybox.showLoading(); 
			$('.fancybox-inner form :submit').addClass('disabled');
		}                                   
		
		function hideLoader() { 
			$.fancybox.hideLoading();
			$('.fancybox-inner form :submit').removeClass('disabled');
		}
		
		function htmlSuccess (data) {
			$.fancybox.hideLoading(); 

			$('.fancybox-inner').html(data);
		}
		
		
  		var form = $(this).closest('form').get(0);
		var sendAjax = ($(this).is(':submit') && $(this).hasClass('disabled')) ? false : true;

		if (sendAjax) {						
	  		var url = form.action;
	  		var type = form.method.toUpperCase();
	  		var data = $(form).serialize();
	  		
	  		if ($(this).is(':submit') && $(this).attr('name') != '') { 
		        data += (data != '') ? '&' : '';
		        data += $(this).attr('name') + '=' + $(this).val(); 
	  		}
	  	
	  		$.ajax({
	  			type: type,
	  			url: url,
	  			dataType: 'html',
	  			data: data + "&ajax=1",
	  			beforeSend: showLoader,
				complete: hideLoader,	  			
	  			success: htmlSuccess
	  		});
	  	}

 		return false;
});