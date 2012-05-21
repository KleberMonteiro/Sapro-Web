$ = jQuery;
$(document).ready(format());

function format() {	
	
	$(function() {
		
	});
	
}

function showAjaxStatus(id) {
	$(id + " .ajax_loader").css("display", "block");
}

function hideAjaxStatus(id) {
	$(id + " .ajax_loader").css("display", "none");
}