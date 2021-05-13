<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags/" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "spring" uri="http://www.springframework.org/tags" %>
<t:layoutWrapper>
<main class="index">
	<c:choose>
	<c:when test="${posts.size() == 0}">
		<img class="placeholder" src="images/thumbnails/teacup.png" alt="teacup">
		<p>The tea is still brewing...check back soon and we should have a fresh cup!</p>
	</c:when>
	<c:otherwise>
    <section class="main-content">
        <div class="article-container">
        	<c:forEach items="${posts}" var="article">
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
                <li><p><c:out value="${article.category}"></c:out></p></li>
                <li><p><img alt="comments" src="images/icons/comment_icon.png"><c:out value="${article.comments.size()}"></c:out></p></li>
                <li><p><img alt="views" src="images/icons/view_icon.png"><c:out value="${article.views}"></c:out></p></li>
            </ul>
            </c:forEach>
        </div>
    </section>
    <section class="featured-content">
        <div id="popular">
            <h2>Popular Posts</h2>
            <c:forEach items="${popularPosts}" var="article">
            <div class="article-container">
                <img src="${article.thumbnail}" alt="article thumbnail">
                <a href="${article.category}/articles/${article.id}"><c:out value="${article.title}"></c:out></a>
            </div>
            </c:forEach>
        </div>
        <div id="recent">
            <h2>Recent Posts</h2>
            <c:forEach items="${recentPosts}" var="article">
            <div class="article-container">
                <img src="${article.thumbnail}" alt="article thumbnail">
                <a href="${article.category}/articles/${article.id}"><c:out value="${article.title}"></c:out></a>
            </div>
            </c:forEach>
        </div>
    </section>
    </c:otherwise>
    </c:choose>
</main>
</t:layoutWrapper>