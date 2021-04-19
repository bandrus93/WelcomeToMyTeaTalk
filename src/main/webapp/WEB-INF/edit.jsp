<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags/" %>
<%@ taglib prefix = "spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<t:layoutWrapper>
<main class="article">
	<form action="/articles/edit/${article.id}" method="post" enctype="multipart/form-data" id="edit">
	<p class="error"><c:out value="${error}"></c:out></p>
	<label>Post Title:
	<input type="text" name="title" value="${article.title}">
	</label>
	<label>Post Thumbnail:
	<input type="file" name="thumbnail" value="${article.thumbnail}">
	</label>
	<label>Post Category:
	<select name="category">
		<option value="Childcare">Childcare</option>
		<option value="Politics">Politics</option>
		<option value="Rants">Rants</option>
		<option value="Recipes">Recipes</option>
	</select>
	</label>
	<label>Tags:
	<input type="text" name="tags" value="${article.tags}">
	</label>
	<label>Post Body:
	<textarea id="content" name="postBody"><c:out value="${article.postBody}"></c:out></textarea>
	</label>
	<input type="submit" value="Save">
</form>
</main>
</t:layoutWrapper>