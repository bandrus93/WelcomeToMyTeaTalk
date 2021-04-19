<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags/" %>
<%@ taglib prefix = "spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<t:layoutWrapper>
<main class="article">
	<form:form action="/register" method="post" modelAttribute="user">
		<p>
			<form:label path="name">Name:</form:label>
			<form:errors path="name"></form:errors>
			<form:input path="name"></form:input>
		</p>
		<p>
			<form:label path="email">Email:</form:label>
			<form:errors path="email"></form:errors>
			<form:input path="email"></form:input>
		</p>
		<p>
			<form:label path="password">Password:</form:label>
			<form:errors path="password"></form:errors>
			<form:input path="password" type="password"></form:input>
		</p>
		<p>
			<form:label path="passConfirm">Confirm Password:</form:label>
			<form:errors path="passConfirm"></form:errors>
			<form:input path="passConfirm" type="password"></form:input>
		</p>
		<input type="submit" value="Register">
	</form:form>
</main>
</t:layoutWrapper>