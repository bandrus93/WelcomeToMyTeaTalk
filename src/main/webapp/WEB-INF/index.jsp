<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags/" %>
<%@ taglib prefix = "spring" uri="http://www.springframework.org/tags" %>
<t:layoutWrapper>
<main class="index">
    <section class="main-content">
        <div class="article-container">
        	<c:forEach items="${posts}" var="article">
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
                <p>Preview not available</p>
            </div>
            </c:forEach>
        </div>
        <div id="recent">
            <h2>Recent Posts</h2>
            <c:forEach items="${recentPosts}" var="article">
            <div class="article-container">
                <img src="${article.thumbnail}" alt="article thumbnail">
                <a href="${article.category}/articles/${article.id}"><c:out value="${article.title}"></c:out></a>
                <p>Preview not available</p>
            </div>
            </c:forEach>
        </div>
    </section>
</main>
</t:layoutWrapper>