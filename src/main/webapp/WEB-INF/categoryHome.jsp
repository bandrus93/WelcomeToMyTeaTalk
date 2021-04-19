<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags/" %>
<%@ taglib prefix = "spring" uri="http://www.springframework.org/tags" %>
<t:layoutWrapper>
<input type="hidden" name="currentTab" value="${tab}">
<main class="article">
	<c:forEach items="${posts}" var="article">
	<div class="article-container">
        <h2><c:out value="${article.title}"></c:out></h2>
        <ul>
            <li><p><c:out value="${article.author.name}"></c:out></p></li>
            <li><p><c:out value="${article.createdOn}"></c:out></p></li>
        </ul>
        <div class="preview-container">
            <img src="${article.thumbnail}" alt="article thumbnail">
            <div class="article-preview">
                <p>Preview not available</p>
                <a class="more" href="${article.category}/articles/${article.id}">Keep Reading...</a>
            </div>
        </div>
        <ul>
            <li><p><img alt="comments" src="images/icons/comment_icon.png"><c:out value="${article.comments.size()}"></c:out></p></li>
            <li><p><img alt="views" src="images/icons/view_icon.png"><c:out value="${article.views}"></c:out></p></li>
        </ul>
	</div>
	</c:forEach>
</main>
</t:layoutWrapper>