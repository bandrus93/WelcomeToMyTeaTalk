<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags/" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "spring" uri = "http://www.springframework.org/tags" %>
<t:layoutWrapper>
<input type="hidden" name="currentTab" value="${tab}">
<main class="article">
	<h2><c:out value="${article.title}"></c:out></h2>
	<ul>
		<li><p>By: <c:out value="${article.author.name}"></c:out></p></li>
		<li><p>Posted on: <fmt:formatDate pattern="MMMM dd', 'yyyy" dateStyle="long" value="${article.createdOn}"></fmt:formatDate></p></li>
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
	<div class="comment-box" id="comment-${comment.id}">
		<h3><c:out value="${comment.user.name}"></c:out></h3>
		<p><c:out value="${comment.content}"></c:out></p>
		<ul>
			<c:choose>
			<c:when test="${user != null && user.getPermissions() < 3 && user.id != comment.user.id}">
				<li><a href="reply/${comment.id}/${article.id}">&#11178;</a></li>
			</c:when>
			<c:when test="${user.id == comment.user.id}">
				<li><a href="reply/${comment.id}/${article.id}">&#11178;</a></li>
				<li><a href="edit/${comment.id}/${article.id}">&#x270E;</a></li>
				<li><a href="delete/${comment.id}/${article.id}">&#10006;</a></li>
			</c:when>
			<c:when test="${user.getPermissions() == 3}">
				<li><a href="reply/${comment.id}/${article.id}">&#11178;</a></li>
				<li><a href="delete/${comment.id}/${article.id}">&#10006;</a></li>
			</c:when>
			</c:choose>
		</ul>
		<c:choose>
		<c:when test="${comment.replies.size() > 0}">
			<c:forEach items="${comment.replies}" var="reply">
			<div class="reply-box" id="reply-${reply.id}">
				<h3><c:out value="${reply.user.name}"></c:out></h3>
				<p><c:out value="${reply.content}"></c:out></p>
				<ul>
				<c:choose>
				<c:when test="${user == reply.user}">
					<li><a href="edit/${reply.id}">&#x270E;</a></li>
					<li><a href="delete/${reply.id}">&#10006;</a></li>
				</c:when>
				<c:when test="${user.getPermissions() == 3}">
					<li><a href="delete/${reply.id}">&#10006;</a></li>
				</c:when>
				</c:choose>
				</ul>
			</div>
			</c:forEach>
		</c:when>
		</c:choose>
	</div>
	</c:forEach>
</main>
</t:layoutWrapper>