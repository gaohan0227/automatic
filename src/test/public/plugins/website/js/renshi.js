$(function() {
	$(".nav").children('ul').children("li[id!='no_bottom']").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
		var index = $(this).index();
		$(".producttabcon").hide().eq(index).show();
	})
	$('.a').mouseover(function() {
	   $('.subnav').addClass('s')
	})
	$('.a').mouseout(function() {
	    $('.subnav').removeClass('s')
	})
	$('.subnav').mouseover(function() {
	    $('.subnav').addClass('s')
	})
	$('.subnav').mouseout(function() {
	    $('.subnav').removeClass('s')
	})
})