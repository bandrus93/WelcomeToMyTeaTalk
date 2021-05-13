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
			<option value="childcare">Childcare</option>
			<option value="politics">Politics</option>
			<option value="rants">Rants</option>
			<option value="recipes">Recipes</option>
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