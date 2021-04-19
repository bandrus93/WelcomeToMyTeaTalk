$(document).ready(function() {
	$(".active").removeClass("active");
	switch($("input[name=currentTab]").val()) {
		case "recipes":
			$("#recipes").addClass("active");
			break;
		case "rants":
			$("#rants").addClass("active");
			break;
		case "politics":
			$("#politics").addClass("active");
			break;
		case "childcare":
			$("#childcare").addClass("active");
			break;
		default:
			$("#home").addClass("active");
	}
    $(".navlinks li, .user-links li").hover(function() {
        $(this).css("background-color", "#0c96e4");
    }, function() {
        $(this).css("background-color", "#0074b5");
    });
    $("#search-tab").click(function() {
		$(".search").slideDown();
	});
	$(".search a").click(function() {
		$(".search").slideUp();
	});
	tinymce.init({
		selector: '#content',
		plugins: 'link lists media',
		toolbar: 'alignleft aligncenter alignright alignjustify | formatselect | bullist numlist | outdent indent | link',
		toolbar_mode: 'floating'
	});
});