<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags/" %>
<%@ taglib prefix = "spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<t:layoutWrapper>
<main class="article">
	<form action="/login" method="post">
		<label>Email:
		<input type="text" name="email">
		</label>
		<label>Password:
		<input type="password" name="password">
		</label>
		<p class="error"><c:out value="${error}"></c:out></p>
		<input type="submit" value="Login">
		<p>Need an account? <a href="/register">Register here</a></p>
	</form>
</main>
</t:layoutWrapper>