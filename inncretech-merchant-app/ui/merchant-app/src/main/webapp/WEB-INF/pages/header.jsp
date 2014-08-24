<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<div class="toprow xtra-mar_btm clearfix">
	<p class="merchant_logo float_left">
		<a href="<c:url value="/homePage"/>"> <img src="" alt="logo"
			width="130" height="70" />
		</a>
	</p>
	<security:authorize access="isAuthenticated()">
		<p class="right_links float_right">
			<a href="<c:url value="/homePage"/>">Home</a> ${user['firstname']}
			${user['lastname']} <span>| </span> <a
				href='<c:url value="/logout"/>'>Log Out</a>
		</p>
	</security:authorize>
	<div id='toTop'>Back to Top!</div>
</div>