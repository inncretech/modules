
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:choose>
	<c:when test="${success ne null}">
		<c:set var="displaySuccessMessage" value="block"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="displaySuccessMessage" value="none"></c:set>

	</c:otherwise>
</c:choose>
<div class="alert alert-success" style="display: ${displaySuccessMessage}" id="successMessage">
	<button type="button" class="close" data-dismiss="alert">&times;</button>
	<c:forEach var="success" items="${success}">
		<spring:message code="${success}" text="${success}" />
	</c:forEach>

	<br />
</div>

<c:choose>
	<c:when test="${errors ne null}">
		<div class="alert alert-error" id="errorMessage">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<c:forEach var="error" items="${errors}">
				<spring:message code="${error}" text="${error}" />
			</c:forEach>
			<br />
			<c:set var="validationErrors">
				<form:errors path="*" />
			</c:set>

			<c:forEach var="error1" items="${validationErrors}">
				<spring:message code="${error1}" text="${error1}" />
			</c:forEach>
			<br />
		</div>
	</c:when>
	<c:otherwise>
		<div class="alert alert-error" style="display: none" id="errorMessage">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
		</div>

	</c:otherwise>
</c:choose>
<div class="errorMessages alert alert-error" style="display: none;"></div>
