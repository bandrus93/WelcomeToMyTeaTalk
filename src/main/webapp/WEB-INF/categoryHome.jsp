<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags/" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "spring" uri="http://www.springframework.org/tags" %>
<t:layoutWrapper>
<input type="hidden" name="currentTab" value="${tab}">
<main class="article">
	<c:choose>
	<c:when test="${tab == 'search' && posts.size() == 0}">
		<p class="no-result">I'm sorry; we can't seem to find what you're looking for...</p>
	</c:when>
	<c:when test="${posts.size() == 0}">
		<img class="placeholder" src="images/thumbnails/teacup.png" alt="teacup">
		<p>The tea is still brewing...come back soon and we should have a fresh cup!</p>
	</c:when>
	<c:otherwise>
		<c:forEach items="${posts}" var="article">
		<div class="article-container">
        	<h2><c:out value="${article.title}"></c:out></h2>
        	<ul>
            	<li><p><c:out value="${article.author.name}"></c:out></p></li>
            	<li><p><fmt:formatDate pattern="MMMM dd', 'yyyy" dateStyle="long" value="${article.createdOn}"></fmt:formatDate></p></li>
        	</ul>
        	<div class="preview-container">
            	<img src="${article.thumbnail}" alt="article thumbnail">
            	<div>
                	<div class="article-preview"><c:out value="${article.postBody}" escapeXml="false"></c:out></div>
                	<a class="more" href="${article.category}/articles/${article.id}">Keep Reading...</a>
            	</div>
        	</div>
        	<ul>
        		<c:choose>
        		<c:when test="${tab == 'search'}">
        			<li><p><c:out value="${article.category}"></c:out></p></li>
        		</c:when>
        		</c:choose>
            	<li><p><img alt="comments" src="images/icons/comment_icon.png"><c:out value="${article.comments.size()}"></c:out></p></li>
            	<li><p><img alt="views" src="images/icons/view_icon.png"><c:out value="${article.views}"></c:out></p></li>
        	</ul>
		</div>
		</c:forEach>
	</c:otherwise>
	</c:choose>
</main>
</t:layoutWrapper>