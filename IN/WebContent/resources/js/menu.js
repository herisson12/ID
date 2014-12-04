$(function() {
	$('#menu_principal li').click(function(event) {
		console.log('lick');
	});
	if (location.href.substr(-1) == '/') {
		$('#menu_principal li:first').addClass('active');
	} else {
		$('#menu_principal li a').each(function() {
			if (location.href == this.href) {
				$(this.parentElement).addClass('active');
			}
		});
	}
});
$(document)
		.ready(
				function() {
					$('#multipleForm')
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											gender : {
												validators : {
													notEmpty : {
														message : 'The gender is required'
													}
												}
											},
											'browsers[]' : {
												validators : {
													notEmpty : {
														message : 'Please specify at least one browser you use daily for development'
													}
												}
											},
											'editors[]' : {
												validators : {
													notEmpty : {
														message : 'The editor names are required'
													}
												}
											}
										}
									});
				});