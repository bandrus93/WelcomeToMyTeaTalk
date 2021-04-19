<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags/" %>
<%@ taglib prefix = "spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<t:layoutWrapper>
<main class="article">
	<form action="/articles/new" method="post" enctype="multipart/form-data" id="create">
		<p class="error"><c:out value="${error}"></c:out></p>
		<label>Post Title:
		<input type="text" name="title">
		</label>
		<label>Post Thumbnail:
		<input type="file" name="thumbnail">
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
		<input type="text" name="tags">
		</label>
		<label>Post Body:
		<textarea id="content" name="postBody"></textarea>
		</label>
		<input type="submit" value="Post">
	</form>
</main>
</t:layoutWrapper>