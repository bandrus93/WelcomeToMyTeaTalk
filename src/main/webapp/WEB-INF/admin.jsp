<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags/" %>
<%@ taglib prefix = "spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<t:layoutWrapper>
<main class="article">
	<form action="/admin" method="post">
		<label>User:
		<select name="user">
			<c:forEach items="${users}" var="user">
			<option value="${user.id}"><c:out value="${user.name}"></c:out></option>
			</c:forEach>
		</select>
		</label>
		<label>Permissions:
		<select name="permission">
			<option value="1">Commenter</option>
			<option value="2">Author</option>
			<option value="3">Editor</option>
		</select>
		</label>
		<input type="submit" value="Submit">
	</form>
	<p><c:out value="${success}"></c:out></p>
</main>
</t:layoutWrapper>