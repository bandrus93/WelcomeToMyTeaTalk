<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to My Tea Talk</title>
    <meta name="description" content="Gather round. Grab your favorite warm beverage. Free your mind and join Alaina as she spills the tea on life lessons, politics, rants, and what it's like raising a child with special needs.">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Dosis|Syncopate">
    <link rel="stylesheet" href="/styles/style.css">
    <link rel="icon" href="data:,">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdn.tiny.cloud/1/3du05hiwuo98zwpx328lf4v8rt19tn7y13rmj0ullqk2y2tb/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
    <script src="/scripts/main.js"></script>
</head>
<body>
    <div class="banner">
        <h1>Welcome to My Tea Talk</h1>
    </div>
    <div class="navbar">
        <ul class="navlinks">
            <li><a href="/" id="home">Home</a></li>
            <li><a href="/recipes" id="recipes">Recipes</a></li>
            <li><a href="/rants" id="rants">Rants</a></li>
            <li><a href="/politics" id="politics">Politics</a></li>
            <li><a href="/childcare" id="childcare">Childcare</a></li>
        </ul>
        <ul class="user-links">
            <li><a href="#" id="search-tab">&#128269;&#xfe0e;</a></li>
            <li><c:choose>
            	<c:when test="${user == null}">
            		<a href="/login">&#129082;]</a>
            	</c:when>
            	<c:otherwise>
            		<p>Welcome <c:out value="${user.name}"></c:out> <a href="/logout">[&#129082;</a></p>
            	</c:otherwise>
            </c:choose></li>
            <c:choose>
            	<c:when test="${user.permissions >= 2}">
            		<li><a href="/articles/new">&#x270E;</a></li>
            	</c:when>
            </c:choose>
        </ul>
    </div>
    <form class="search" action="/search" method="post">
    	<a href="#">X</a>
        <p><input type="text" name="query"><button type="submit">&#128269;&#xfe0e;</button></p>
    </form>
    <jsp:doBody></jsp:doBody>
    <ul class="footer-links">
        <li><a href="/info/support" id="support">Support</a></li>
        <li><a href="/info/contact" id="contact">Contact</a></li>
        <li><a href="/info/donate" id="donate">Donate</a></li>
        <li><a href="/info/privacy" id="privacy">Privacy Policy</a></li>
    </ul>
</body>
</html>
