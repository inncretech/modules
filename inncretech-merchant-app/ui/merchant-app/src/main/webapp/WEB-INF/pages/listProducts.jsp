<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="header.jsp" />

<body>
	<div class="container">
		<jsp:include page="topNav.jsp" />
		<c:choose>
			<c:when test="${productBeans ne null  and !empty productBeans  }">
				<table id="allActiveProducts" cellpadding="0" cellspacing="0" border="0"
					class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>Product Id</th>
							<th>Product Name</th>
							<th>Mrp</th>
							<th>Selling Price</th>
							<th>Product Start Date</th>
							<th>Product End Date</th>
						</tr>

					</thead>
					<tbody>
						<c:forEach items="${productBeans}" var="productBean">
							<tr>
								<td><a href='<c:url value="/editProduct/${productBean.productId}" />'>${productBean.productId}</a></td>
								<td>${productBean.title}</td>
								<td></td>
								<td></td>
								<td><fmt:formatDate value="${productBean.startDate}" pattern="dd-MMM-yyyy hh:mm:ss aa" /></td>
								<td><fmt:formatDate value="${productBean.endDate}" pattern="dd-MMM-yyyy hh:mm:ss aa" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<h1 style="margin-top: 100px; text-align: center">No Product Found.</h1>
			</c:otherwise>
		</c:choose>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$('#allActiveProducts').dataTable();
	});
</script>
</html>
