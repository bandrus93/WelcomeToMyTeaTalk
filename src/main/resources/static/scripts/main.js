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
		case "support":
			$("#support").addClass("active");
			break;
		case "contact":
			$("#contact").addClass("active");
			break;
		case "donate":
			$("#donate").addClass("active");
			break;
		case "privacy":
			$("#privacy").addClass("active");
			break;
		default:
			$("#home").addClass("active");
	}
    $(".navlinks li, .user-links li, .footer-links li").hover(function() {
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
	$(".comment-box ul li a").click(function(e) {
		e.preventDefault();
		var path = $(this).attr("href");
		var pathArray = path.split("/");
		switch(pathArray[0]) {
			case "reply":
				$("#comment-" + pathArray[1]).append("<div class='new-comment'><form action='/comments/reply/" + pathArray[1] + "' method='post'><textarea rows='4' name='reply' placeholder='Type your reply...'></textarea><input type='hidden' name='article' value='" + pathArray[2] + "'><input type='button' class='cancel' value='Cancel'><input type='submit' value='Reply'></form></div>");
				break;
			case "edit":
				var editable = $("#comment-" + pathArray[1] + " p").text();
				$("#comment-" + pathArray[1]).html("<div class='new-comment'><form action='/comments/edit/" + pathArray[1] + "' method='post'><textarea rows='4' name='comment' value='" + editable + "'></textarea><input type='hidden' name='article' value='" + pathArray[2] + "'><input type='button' class='cancel' value='Cancel'><input type='submit' value='Save'></form></div>");
				break;
			case "delete":
				window.location="/comments/delete/" + pathArray[1] + "/" + pathArray[2];
				break;
		}
	});
	$(".comment-box").on("click", ".cancel", function() {
		$(".new-comment").remove();
	});
});