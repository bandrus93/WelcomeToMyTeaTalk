<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags/" %>
<%@ taglib prefix = "spring" uri="http://www.springframework.org/tags" %>
<t:layoutWrapper>
<main class="article">
	<h2><c:out value="${article.title}"></c:out></h2>
	<ul>
		<li><p>By: <c:out value="${article.author.name}"></c:out></p></li>
		<li><p>Posted on: <c:out value="${article.createdOn}"></c:out></p></li>
		<li><img alt="views" src="/images/icons/view_icon.png"> <c:out value="${article.views}"></c:out></li>
		<c:choose>
		<c:when test="${user.getPermissions() == 3 || user == article.getAuthor()}">
			<li><a href="/articles/edit/${article.id}">Edit</a></li>
			<li><a href="/articles/delete/${article.id}">Delete</a></li>
		</c:when>
		</c:choose>
	</ul>
	<div class="article-container">
		<img alt="article thumbnail" src="${article.thumbnail}">
		<div class="article-content"><c:out value="${article.postBody}" escapeXml="false"></c:out></div>
	</div>
	<hr>
	<h2>Comments</h2>
	<c:choose>
	<c:when test="${user != null}">
		<div class="comment"><form action="/${article.category}/articles/${article.id}" method="post">
			<textarea rows="4" name="comment" placeholder="Leave a public comment..."></textarea>
			<input type="submit" value="Post">
		</form></div>
	</c:when>
	</c:choose>
	<c:forEach items="${article.comments}" var="comment">
	<div class="comment-box">
		<h3><c:out value="${comment.user.name}"></c:out></h3>
		<p><c:out value="${comment.content}"></c:out></p>
		<ul>
			<c:choose>
			<c:when test="${user != null}">
				<li><a href="#">&#11178;</a></li>
			</c:when>
			<c:when test="${user == comment.user}">
				<li><a href="#"></a>&#x270E;</li>
				<li><a href="/comments/delete/${comment.id}">&#10006;</a></li>
			</c:when>
			<c:when test="${user.getPermissions() == 3}">
				<li><a href="/comments/delete/${comment.id}">&#10006;</a></li>
			</c:when>
			</c:choose>
		</ul>
		<c:choose>
		<c:when test="${comment.replies.size() > 0}">
			<a href="#">See <c:out value="${comment.replies.size()}"></c:out> replies</a>
		</c:when>
		</c:choose>
	</div>
	</c:forEach>
</main>
</t:layoutWrapper>