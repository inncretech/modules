<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="f" uri="http://www.inncretech.com/functions"%>
<html lang="en">
<jsp:include page="htmlHeader.jsp" />
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<div class="container clearfix">
			<div class="content clearfix">
				<div class="login_screen">

					<form:form commandName="loginBean"
						action="/j_spring_security_check" method="post">
						<p class="loginhdr">Log In</p>
						<c:if test="${param.errors ne null}">
							<div class="alert alert-error">
								<spring:message text="username or password is wrong" />
								<button type="button" class="close" data-dismiss="alert">&times;</button>
							</div>
						</c:if>
						<div style="padding: 25px; color: #555;">
							<div class="control-group">
								<label class="control-label" for="username">Username </label>
								<div class="controls">
									<form:input path="username" placeholder="Enter email"
										cssClass="input-xlarge" required="true" maxlength="45" />
								</div>
							</div>

							<div class="control-group" style="margin-top: 10px;">
								<label class="control-label" for="password">Password </label>
								<div class="controls">
									<form:password path="password" cssClass="input-xlarge"
										required="true" autocomplete="off" maxlength="30"
										placeholder="Enter password" />
								</div>
							</div>
						</div>
						<div>
							<div class="controls">
								<button style="margin-right: 20px; float: right;" type="submit"
									class="btn btn-success	">Login</button>
							</div>

						</div>
					</form:form>
				</div>

			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

	$('#password').popover({
		'trigger' : 'focus',
		'content' : 'Password is compulsory',
		'placement' : 'right'
	});

	$('#username').popover({
		'trigger' : 'focus',
		'content' : 'Email is compulsory',
		'placement' : 'right'
	});
	
	function checkPageType(){
		
		window.location.href = "/";
	}
</script>
</html>