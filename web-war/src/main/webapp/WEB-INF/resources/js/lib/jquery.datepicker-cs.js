/* Czech initialisation for the jQuery UI date picker plugin. */
/* Written by Tomas Muller (tomas@tomas-muller.net). */
jQuery(function($){
	$.datepicker.regional['cs'] = {
		closeText: 'Zavřít',
		prevText: '&laquo; Předchozí měsíc',
		nextText: 'Následující měsíc &raquo;',
		currentText: 'Nyní',
		monthNames: ['Leden','Únor','Březen','Duben','Květen','Červen',
        'Červenec','Srpen','Září','Říjen','Listopad','Prosinec'],
		monthNamesShort: ['led','úno','bře','dub','kvě','čer',
		'čvc','srp','zář','říj','lis','pro'],
		dayNames: ['Neděle', 'Pondělí', 'Úterý', 'Středa', 'Čtvrtek', 'Pátek', 'Sobota'],
		dayNamesShort: ['Ne', 'Po', 'Út', 'St', 'Čt', 'Pá', 'So'],
		dayNamesMin: ['Ne', 'Po', 'Út', 'St', 'Čt', 'Pá', 'So'],
		dateFormat: 'dd.mm.yy', firstDay: 1,
		isRTL: false};
	$.datepicker.setDefaults($.datepicker.regional['cs']);
});
